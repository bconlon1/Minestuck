package com.mraof.minestuck.block;

import com.mraof.minestuck.util.CustomVoxelShape;
import net.minecraft.block.AnvilBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;

import javax.annotation.Nullable;

public class DecorBlock extends Block
{
	
	
	public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
	
	public final VoxelShape[] shape;
	
	
	public DecorBlock(Properties properties, CustomVoxelShape shape)
	{
		super(properties);
		this.shape = new VoxelShape[] {shape.create(Direction.SOUTH), shape.create(Direction.WEST), shape.create(Direction.NORTH), shape.create(Direction.EAST)};
	}
	
	@Override
	public BlockRenderLayer getRenderLayer()
	{
		return BlockRenderLayer.CUTOUT;
	}
	
	/*TODO
	@Override
	public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos)
	{
        return worldIn.getBlockState(pos.down()).isFullCube();
    }*/
	
	@Nullable
	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context)
	{
        return this.getDefaultState().with(FACING, context.getPlacementHorizontalFacing().getOpposite());
    }
	
	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder)
	{
		builder.add(FACING);
	}
	
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context)
	{
		//return shape.create(state.get(FACING)); <- it lags the game when you stand on a decor block
		return shape[state.get(FACING).getHorizontalIndex()];
	}
}