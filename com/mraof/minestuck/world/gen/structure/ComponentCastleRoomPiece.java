package com.mraof.minestuck.world.gen.structure;

import java.util.List;
import java.util.Random;

import com.mraof.minestuck.Minestuck;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;

public class ComponentCastleRoomPiece extends ComponentCastlePiece {

	protected ComponentCastleRoomPiece(int par1, ComponentCastleStartPiece startPiece, StructureBoundingBox structureBoundingBox) 
	{
		super(par1, startPiece);
		this.boundingBox = structureBoundingBox;
		this.componentType = 2;
	}
	@Override
	public void buildComponent(StructureComponent par1StructureComponent, List components, Random random) 
	{
    	if(this.startPiece.bottom)
    	{
			this.componentType = 0;
			this.getNextComponentNormal(startPiece, components, random, 0, -8, 0, true);
    	}
	}
	public static ComponentCastleRoomPiece createRandomRoom(List par0List, ComponentCastleStartPiece startPiece, int x, int y, int z, int par5, int par6, Random random)
	{
		if(random.nextInt(30) == 0)return ComponentCastleStaircasePiece.findValidPlacement(par0List, startPiece, x, y, z, par5, par6);
		return findValidPlacement(par0List, startPiece, x, y, z, par5, par6);
	}
	public static ComponentCastleRoomPiece findValidPlacement(List par0List, ComponentCastleStartPiece startPiece, int par2, int par3, int par4, int par5, int par6)
    {
        StructureBoundingBox structureboundingbox = new StructureBoundingBox(par2 + 0, par3 + 0, par4 + 0, par2 + 8 + 0, 0 + 8 + 0, par4 + 8 + 0);
        return new ComponentCastleRoomPiece(par6, startPiece, structureboundingbox);
    }

	@Override
	public boolean addComponentParts(World world, Random random, StructureBoundingBox structureboundingbox) 
	{
    	int chessTileMetadata = startPiece.isBlack ? 0 : 1;
    	int chessTileMetadata1 = startPiece.isBlack ? 2 : 3;
        if (startPiece.averageGroundLevel < 0)
        {
            startPiece.averageGroundLevel = startPiece.getAverageGroundLevel(world);

            if (startPiece.averageGroundLevel < 0)
            {
                return true;
            }

            System.out.println(startPiece.averageGroundLevel);
        }
        if(this.boundingBox.minY < startPiece.averageGroundLevel - 1)
        {
//        	System.out.println(this.boundingBox.minY);
        	this.boundingBox.offset(0, startPiece.averageGroundLevel - 1, 0);
        }
        if (this.isLiquidInStructureBoundingBox(world, structureboundingbox))
        {
            return false;
        }
        else
        {
            this.fillWithAlternatingBlocks(world, structureboundingbox, 0, 0, 0, 7 ,7, 7, Minestuck.chessTile.blockID, chessTileMetadata,  Minestuck.chessTile.blockID, chessTileMetadata1, false);
            this.fillWithBlocks(world, structureboundingbox, 0, 1, 0, 7, 6, 7, 0, 0, false);
    		this.placeBlockAtCurrentPosition(world, Block.torchWood.blockID, 5, 3, 1, 3, structureboundingbox);
            return true;
        }
	}


}
