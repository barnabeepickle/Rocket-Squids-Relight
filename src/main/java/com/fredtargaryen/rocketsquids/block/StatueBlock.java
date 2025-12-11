package com.fredtargaryen.rocketsquids.block;

import com.fredtargaryen.rocketsquids.RocketSquidsBase;
import com.fredtargaryen.rocketsquids.Sounds;
import com.fredtargaryen.rocketsquids.world.StatueManager;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FallingBlock;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;

public class StatueBlock extends FallingBlock implements SimpleWaterloggedBlock {
    private static final VoxelShape TALLBOX = Block.box(0.0, 0.0, 0.0, 16.0, 32.0, 16.0);
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    public StatueBlock(Block.Properties properties) {
        super(properties);
        registerDefaultState(getStateDefinition().any()
                .setValue(BlockStateProperties.FACING, Direction.UP)
                .setValue(WATERLOGGED, false));
    }

    /**
     * Get the Item that this Block should drop when harvested.
     */
    @Override
    public Item asItem() { return RocketSquidsBase.ITEM_STATUE.get(); }

    @Override
    @Deprecated
    public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
        return TALLBOX;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(BlockStateProperties.FACING, WATERLOGGED);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext ctx) {
        return this.getStateDefinition().any()
                .setValue(BlockStateProperties.FACING, ctx.getNearestLookingDirection().getOpposite())
                .setValue(WATERLOGGED, ctx.getLevel().getFluidState(ctx.getClickedPos()).getType() == Fluids.WATER);
    }

    @Override
    @Deprecated
    public FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    @Override
    public BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor world, BlockPos pos, BlockPos neighborPos) {
        if (state.getValue(WATERLOGGED)) {
            world.getLiquidTicks().scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(world));
        }

        return super.updateShape(state, direction, neighborState, world, pos, neighborPos);
    }

    @Override
    @Deprecated
    public boolean canSurvive(BlockState state, LevelReader worldIn, BlockPos pos) {
        return worldIn.getBlockState(pos.below()).getMaterial().isSolid() && !worldIn.getBlockState(pos.above()).getMaterial().isSolid();
    }

    /**
     * Called by ItemBlocks after a block is set in the world, to allow post-place logic
     */
    public void setPlacedBy(Level worldIn, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
        super.setPlacedBy(worldIn, pos, state, placer, stack);
        if(!worldIn.isClientSide) {
            StatueManager.forWorld(worldIn).addStatue(pos);
        }
    }

    @Override
    @Deprecated
    public void onPlace(BlockState state, Level worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
        super.onPlace(state, worldIn, pos, newState, isMoving);
        StatueManager.forWorld(worldIn).removeStatue(pos);
    }

    public void dispenseGift(Level world, BlockPos pos, Direction facing) {
        int x = pos.getX();
        int y = pos.getY();
        int z = pos.getZ();
        //Play some kind of wonderful "you found treasure" chord
        //Going with B4, D5 and F#5 (the minor chord makes it foreboding)
        world.playSound(null, pos, Sounds.CONCH_NOTES[23],SoundSource.BLOCKS, 1.0F, 1.0F);
        world.playSound(null, pos, Sounds.CONCH_NOTES[26],SoundSource.BLOCKS, 1.0F, 1.0F);
        world.playSound(null, pos, Sounds.CONCH_NOTES[30],SoundSource.BLOCKS, 1.0F, 1.0F);
        switch(facing) {
            case NORTH:
                ItemEntity squav = new ItemEntity(world,x + 0.5D, y + 0.5D, z - 0.5D);
                squav.setItem(RocketSquidsBase.SQUAVIGATOR.get().getDefaultInstance());
                //North is negative Z I think
                squav.setDeltaMovement(0.0, 0.05, -0.1);
                world.addFreshEntity(squav);
                ItemEntity squel = new ItemEntity(world,x + 0.5D, y + 0.5D, z - 0.5D);
                squel.setItem(RocketSquidsBase.SQUELEPORTER_INACTIVE.get().getDefaultInstance());
                squel.setDeltaMovement(0.0, 0.05, -0.1);
                world.addFreshEntity(squel);
                break;
            default:
                break;
        }
    }

    /**
     * Remove its location. It can add a new one when it lands
     */
    @Override
    protected void falling(FallingBlockEntity fallingEntity) {
        BlockPos startPos = fallingEntity.blockPosition();
        BlockState startState = fallingEntity.level.getBlockState(startPos);
        if(startState.getValue(BlockStateProperties.FACING) == Direction.UP) {
            StatueManager.forWorld(fallingEntity.level).removeStatue(startPos);
        }
    }

    /**
     * Add a new location for where it ended up
     */
    @Override
    public void onLand(Level worldIn, BlockPos pos, BlockState fallingState, BlockState hitState, FallingBlockEntity fallingBlock) {
        if(fallingState.getValue(BlockStateProperties.FACING) == Direction.UP) {
            StatueManager.forWorld(worldIn).addStatue(pos);
        }
    }
}
