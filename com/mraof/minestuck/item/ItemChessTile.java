package com.mraof.minestuck.item;

import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemChessTile extends ItemBlock
{
	private final static String[] subNames = {"black", "white", "lightgrey", "darkgrey"};
	
	public ItemChessTile(int par1) 
	{
		super(par1);
		setHasSubtypes(true);
		setUnlocalizedName("chessTile");
	}
	@Override
	public String getUnlocalizedName(ItemStack itemstack) 
	{
		return getUnlocalizedName() + "." + subNames[itemstack.getItemDamage()];
	}
	@Override
	public int getMetadata (int damageValue) 
	{
		return damageValue;
	}
	
}
