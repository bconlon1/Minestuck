package com.mraof.minestuck.item.crafting.alchemy.generator;

import com.mraof.minestuck.Minestuck;
import com.mraof.minestuck.item.crafting.alchemy.GristCostRecipe;
import com.mraof.minestuck.item.crafting.alchemy.GristSet;
import net.minecraft.client.resources.ReloadListener;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.profiler.IProfiler;
import net.minecraft.resources.IResourceManager;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.server.FMLServerAboutToStartEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Mod.EventBusSubscriber(modid = Minestuck.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public final class GristCostGenerator extends ReloadListener<Void>
{
	private static final Logger LOGGER = LogManager.getLogger();
	
	private final MinecraftServer server;
	
	private GristCostGenerator(MinecraftServer server)
	{
		this.server = server;
	}
	
	@SubscribeEvent(priority = EventPriority.LOW)
	public static void serverAboutToStart(FMLServerAboutToStartEvent event)
	{
		event.getServer().getResourceManager().addReloadListener(new GristCostGenerator(event.getServer()));
	}
	
	@Override
	protected Void prepare(IResourceManager resourceManagerIn, IProfiler profilerIn)
	{
		return null;
	}
	
	@Override
	protected void apply(Void splashList, IResourceManager resourceManagerIn, IProfiler profilerIn)
	{
		GeneratorProcess process = new GeneratorProcess();
		
		//Collect providers
		Stream<GristCostRecipe> stream = server.getRecipeManager().getRecipes().stream().filter(recipe -> recipe instanceof GristCostRecipe).map(recipe -> (GristCostRecipe) recipe);
		for(IRecipe<?> recipe : stream.sorted(Comparator.comparingInt(value -> -value.getPriority())).collect(Collectors.toList()))
		{
			((GristCostRecipe) recipe).addCostProvider((item, provider) ->
			{
				process.providersByItem.computeIfAbsent(item, i -> new ArrayList<>()).add(provider);
				process.providers.add(provider);
			});
		}
		
		//Iterate through items
		for(Item item : process.providersByItem.keySet())
		{
			lookupCost(item, process, true);
		}
		
		for(GeneratedCostProvider provider : process.providers)
		{
			try
			{
				provider.build();
			} catch(Exception e)
			{
				LOGGER.error("Got exception while building generated cost provider {}:", provider, e);
			}
		}
	}
	
	private GristSet lookupCost(Item item, GeneratorProcess process, boolean primary)
	{
		GristCostResult cost = null;
		if(!process.itemsInProcess.contains(item))
		{
			process.itemsInProcess.add(item);
			for(GeneratedCostProvider provider : process.providersByItem.getOrDefault(item, Collections.emptyList()))
			{
				try
				{
					cost = provider.generate(item, cost, primary, otherItem -> lookupCost(otherItem, process, false));
				} catch(Exception e)
				{
					LOGGER.error("Got exception from generated cost provider {} while generating for item {}:", provider, item, e);
				}
			}
			process.itemsInProcess.remove(item);
		} else LOGGER.debug("Got recursive call from generating grist cost for {}.", item);
		
		return cost != null ? cost.getCost() : null;
	}
	
	private static class GeneratorProcess
	{
		private final Map<Item, List<GeneratedCostProvider>> providersByItem = new HashMap<>();
		private final Set<GeneratedCostProvider> providers = new HashSet<>();
		private final Set<Item> itemsInProcess = new HashSet<>();
	}
}