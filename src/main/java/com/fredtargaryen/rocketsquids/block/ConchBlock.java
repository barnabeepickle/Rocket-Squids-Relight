package com.fredtargaryen.rocketsquids.block;

import com.fredtargaryen.rocketsquids.RocketSquidsBase;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
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

public class ConchBlock extends Block {
    private static final VoxelShape CONCH_EAST = Block.box(3.0, 0.0, 2.0, 6.0, 2.0, 8.0);
    private static final VoxelShape CONCH_SOUTH = Block.box(8.0, 0.0, 3.0, 14.0, 2.0, 6.0);
    private static final VoxelShape CONCH_WEST = Block.box(10.0, 0.0, 8.0, 13.0, 2.0, 14.0);
    private static final VoxelShape CONCH_NORTH = Block.box(2.0, 0.0, 10.0, 8.0, 2.0, 13.0);

    public ConchBlock() {
        super(Block.Properties.of(Material.SAND));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(BlockStateProperties.HORIZONTAL_FACING, BlockStateProperties.WATERLOGGED);
    }

    /**
     * Get the Item that this Block should drop when harvested.
     */
    @Override
    public Item asItem() { return RocketSquidsBase.ITEM_CONCH; }

    @Override
    @Deprecated
    public VoxelShape getCollisionShape(BlockState state, BlockGetter reader, BlockPos pos, CollisionContext context) {
        return this.getShape(state, reader, pos, context);
    }

    @Override
    @Deprecated
    public VoxelShape getShape(BlockState state, BlockGetter reader, BlockPos pos, CollisionContext context) {
        switch(state.getValue(BlockStateProperties.HORIZONTAL_FACING)) {
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
        Direction facing = placer.getDirection();
        switch(facing) {
            case NORTH:
                worldLevel.setBlockAndUpdate(pos, state.setValue(BlockStateProperties.HORIZONTAL_FACING, Direction.EAST));
                break;
            case SOUTH:
                worldLevel.setBlockAndUpdate(pos, state.setValue(BlockStateProperties.HORIZONTAL_FACING, Direction.WEST));
                break;
            case WEST:
                worldLevel.setBlockAndUpdate(pos, state.setValue(BlockStateProperties.HORIZONTAL_FACING, Direction.NORTH));
                break;
            default:
                worldLevel.setBlockAndUpdate(pos, state.setValue(BlockStateProperties.HORIZONTAL_FACING, Direction.SOUTH));
                break;
        }
    }
}
