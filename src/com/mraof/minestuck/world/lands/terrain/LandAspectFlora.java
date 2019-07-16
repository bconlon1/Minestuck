package com.mraof.minestuck.world.lands.terrain;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.mraof.minestuck.block.MinestuckBlocks;
import com.mraof.minestuck.entity.consort.EnumConsort;
import com.mraof.minestuck.world.biome.BiomeMinestuck;
import com.mraof.minestuck.world.lands.decorator.CanopyTreeDecorator;
import com.mraof.minestuck.world.lands.decorator.FlowerDecorator;
import com.mraof.minestuck.world.lands.decorator.ILandDecorator;
import com.mraof.minestuck.world.lands.decorator.SingleBlockDecorator;
import com.mraof.minestuck.world.lands.decorator.SurfaceDecoratorVein;
import com.mraof.minestuck.world.lands.decorator.TallGrassDecorator;
import com.mraof.minestuck.world.lands.decorator.UndergroundDecoratorVein;
import com.mraof.minestuck.world.lands.decorator.structure.SwordDecorator;
import com.mraof.minestuck.world.lands.structure.blocks.StructureBlockRegistry;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.DirectionalBlock;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class LandAspectFlora extends TerrainLandAspect
{
	static Vec3d fogColor = new Vec3d(0.5D, 0.6D, 0.9D);
	static Vec3d skyColor = new Vec3d(0.6D, 0.8D, 0.6D);
	
	@Override
	public void registerBlocks(StructureBlockRegistry registry)
	{
		registry.setBlockState("surface", Blocks.GRASS.getDefaultState());
		registry.setBlockState("upper", Blocks.DIRT.getDefaultState());
		//registry.setBlockState("ocean", MinestuckBlocks.blockBlood.getDefaultState());
		registry.setBlockState("structure_primary", Blocks.MOSSY_STONE_BRICKS.getDefaultState());
		registry.setBlockState("structure_primary_decorative", MinestuckBlocks.FLOWERY_MOSS_BRICKS.getDefaultState());
		registry.setBlockState("structure_secondary_stairs", Blocks.STONE_BRICK_STAIRS.getDefaultState());
		registry.setBlockState("structure_secondary", Blocks.MOSSY_COBBLESTONE.getDefaultState());
		registry.setBlockState("structure_secondary_decorative", MinestuckBlocks.FLOWERY_MOSS_STONE.getDefaultState());
		registry.setBlockState("structure_secondary_stairs", Blocks.DARK_OAK_STAIRS.getDefaultState());
		registry.setBlockState("village_path", Blocks.GRASS_PATH.getDefaultState());
		registry.setBlockState("bush", Blocks.FERN.getDefaultState());
		registry.setBlockState("structure_wool_1", Blocks.YELLOW_WOOL.getDefaultState());
		registry.setBlockState("structure_wool_3", Blocks.CYAN_WOOL.getDefaultState());
	}
	
	private static class StrawberryDecorator extends SingleBlockDecorator
	{
		@Override
		public BlockState pickBlock(Random random)
		{
			return MinestuckBlocks.STRAWBERRY.getDefaultState().with(DirectionalBlock.FACING, Direction.random(random));
		}
		@Override
		public int getCount(Random random)
		{
			return random.nextFloat() < 0.01 ? random.nextInt(13) + 1 : 0;
		}
		@Override
		public boolean canPlace(BlockPos pos, World world)
		{
			return !world.getBlockState(pos.down()).getMaterial().isLiquid();
		}
	}
	
	@Override
	public String getPrimaryName()
	{
		return "flora";
	}
	
	@Override
	public String[] getNames()
	{
		return new String[] {"flora", "flowers", "thorns"};
	}
	
	@Override
	public List<ILandDecorator> getDecorators()
	{
		ArrayList<ILandDecorator> list = new ArrayList<ILandDecorator>();
		list.add(new SwordDecorator());
		list.add(new StrawberryDecorator());
		
		list.add(new CanopyTreeDecorator(25, BiomeMinestuck.mediumNormal));
		list.add(new CanopyTreeDecorator(3, BiomeMinestuck.mediumRough));
		list.add(new TallGrassDecorator(0.3F, BiomeMinestuck.mediumNormal));
		list.add(new TallGrassDecorator(0.5F, 0.2F, BiomeMinestuck.mediumRough));
		list.add(new FlowerDecorator(0.5F, 0.2F, BiomeMinestuck.mediumNormal, BiomeMinestuck.mediumRough));
		
		list.add(new UndergroundDecoratorVein(Blocks.DIRT.getDefaultState(), 3, 33, 64));
		list.add(new UndergroundDecoratorVein(Blocks.GRAVEL.getDefaultState(), 2, 28, 64));
		list.add(new UndergroundDecoratorVein(Blocks.COAL_ORE.getDefaultState(), 13, 17, 64));
		list.add(new UndergroundDecoratorVein(Blocks.EMERALD_ORE.getDefaultState(), 8, 3, 32));
		list.add(new UndergroundDecoratorVein(Blocks.DIAMOND_ORE.getDefaultState(), 8, 3, 32));
		list.add(new UndergroundDecoratorVein(Blocks.LAPIS_ORE.getDefaultState(), 8, 3, 32));
		list.add(new UndergroundDecoratorVein(MinestuckBlocks.QUARTZ_ORE_STONE.getDefaultState(), 8, 5, 32));
		list.add(new SurfaceDecoratorVein(Blocks.CLAY.getDefaultState(), 15, 10, BiomeMinestuck.mediumOcean));
		return list;
	}
	
	@Override
	public Vec3d getFogColor()
	{
		return fogColor;
	}
	
	@Override
	public Vec3d getSkyColor()
	{
		return skyColor;
	}
	
	@Override
	public float getRainfall()
	{
		return 0.4F;
	}
	
	@Override
	public EnumConsort getConsortType()
	{
		return EnumConsort.IGUANA;
	}
}