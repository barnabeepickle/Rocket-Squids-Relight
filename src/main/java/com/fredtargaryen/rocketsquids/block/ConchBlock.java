package com.fredtargaryen.rocketsquids.block;

import com.fredtargaryen.rocketsquids.RocketSquidsBase;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.properties.BooleanProperty;


public class ConchBlock extends Block {
    private static final VoxelShape CONCH_EAST = Block.box(3.0, 0.0, 2.0, 6.0, 2.0, 8.0);
    private static final VoxelShape CONCH_SOUTH = Block.box(8.0, 0.0, 3.0, 14.0, 2.0, 6.0);
    private static final VoxelShape CONCH_WEST = Block.box(10.0, 0.0, 8.0, 13.0, 2.0, 14.0);
    private static final VoxelShape CONCH_NORTH = Block.box(2.0, 0.0, 10.0, 8.0, 2.0, 13.0);
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    public ConchBlock(Block.Properties properties) {
        super(properties);
        registerDefaultState(getStateDefinition().any()
                .setValue(BlockStateProperties.FACING, Direction.UP)
                .setValue(WATERLOGGED, false));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(BlockStateProperties.FACING, BlockStateProperties.WATERLOGGED);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext ctx) {
        return this.getStateDefinition().any()
                .setValue(BlockStateProperties.FACING, ctx.getNearestLookingDirection().getOpposite())
                .setValue(WATERLOGGED, ctx.getLevel().getFluidState(ctx.getClickedPos()).getType() == Fluids.WATER);
    }

    @SuppressWarnings("deprecation")
    @Override
    @Deprecated
    public FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    @SuppressWarnings("deprecation")
    @Override
    @Deprecated
    public BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor world, BlockPos pos, BlockPos neighborPos) {
        if (state.getValue(WATERLOGGED)) {
            world.getLiquidTicks().scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(world));
        }

        return super.updateShape(state, direction, neighborState, world, pos, neighborPos);
    }

    /**
     * Get the Item that this Block should drop when harvested.
     */
    @Override
    public Item asItem() { return RocketSquidsBase.ITEM_CONCH; }

    @SuppressWarnings("deprecation")
    @Override
    @Deprecated
    public VoxelShape getCollisionShape(BlockState state, BlockGetter reader, BlockPos pos, CollisionContext context) {
        return this.getShape(state, reader, pos, context);
    }

    @SuppressWarnings("deprecation")
    @Override
    @Deprecated
    public VoxelShape getShape(BlockState state, BlockGetter reader, BlockPos pos, CollisionContext context) {
        switch(state.getValue(BlockStateProperties.FACING)) {
            case NORTH:
                return CONCH_NORTH;
            case SOUTH:
                return CONCH_SOUTH;
            case WEST:
                return CONCH_WEST;
            default:
                return CONCH_EAST;
        }
    }

    /**
     * Called by ItemBlocks after a block is set in the world, to allow post-place logic
     */
    @Override
    public void setPlacedBy(Level worldLevel, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
        assert placer != null;
        Direction facing = placer.getDirection();
        switch(facing) {
            case NORTH:
                worldLevel.setBlockAndUpdate(pos, state.setValue(BlockStateProperties.FACING, Direction.EAST));
                break;
            case SOUTH:
                worldLevel.setBlockAndUpdate(pos, state.setValue(BlockStateProperties.FACING, Direction.WEST));
                break;
            case WEST:
                worldLevel.setBlockAndUpdate(pos, state.setValue(BlockStateProperties.FACING, Direction.NORTH));
                break;
            default:
                worldLevel.setBlockAndUpdate(pos, state.setValue(BlockStateProperties.FACING, Direction.SOUTH));
                break;
        }
    }
}
