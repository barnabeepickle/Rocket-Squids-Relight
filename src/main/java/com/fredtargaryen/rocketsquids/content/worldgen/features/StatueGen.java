// Copyright 2016-2022, 2025-2026 FredTargaryen and contributors
// See README.md for full copyright notice and contributor info
package com.fredtargaryen.rocketsquids.content.worldgen.features;

import com.fredtargaryen.rocketsquids.DataReference;
import com.fredtargaryen.rocketsquids.config.GeneralConfig;
import com.fredtargaryen.rocketsquids.content.ModBlocks;
import com.fredtargaryen.rocketsquids.content.worldgen.StatueData;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class StatueGen extends Feature<NoneFeatureConfiguration> {

    public StatueGen() {
        super(NoneFeatureConfiguration.CODEC);
    }

    /**
     * Generate the feature at the given BlockPos (which was validated by an IPatchPlacement instance).
     *
     * @param context context
     * @return return
     */
    @SuppressWarnings("deprecation")
    @Override
    public boolean place(@NotNull FeaturePlaceContext<NoneFeatureConfiguration> context) {
        // First we create a few variables out of the context in order to adapt from the old way place was written
        WorldGenLevel world = context.level();
        ChunkGenerator chunkGen = context.chunkGenerator();
        RandomSource random = context.random();
        BlockPos pos = context.origin();
        // Then we check the config to see if this dimension is allowed
        if (GeneralConfig.STATUE_USE_WHITELIST.get()) {
            List<? extends String> allowedDimensions = GeneralConfig.STATUE_WHITELIST.get();
            if (!allowedDimensions.contains(world.getLevel().dimension().location().toString())) return false;
        } else {
            List<? extends String> blockedDimensions = GeneralConfig.STATUE_BLACKLIST.get();
            if (blockedDimensions.contains(world.getLevel().dimension().location().toString())) return false;
        }
        StatueData statueManager = StatueData.forWorld(world.getLevel());
        int frequency = GeneralConfig.STATUE_FREQUENCY.get();
        int chunkX = StatueData.posToChunk(pos.getX());
        int chunkZ = StatueData.posToChunk(pos.getZ());
        int chunkAreaX = StatueData.posToChunkArea(pos.getX());
        int chunkAreaZ = StatueData.posToChunkArea(pos.getZ());
        int[] statueLocation = statueManager.getChunkArea(chunkAreaX, chunkAreaZ);
        if (statueLocation == null) {
            //A statue location hasn't been decided for this chunk area. Decide one
            statueLocation = new int[]{chunkAreaX, chunkAreaZ,
                    //Random chunk in the sizexsize area
                    (chunkAreaX * frequency + random.nextInt(frequency))
                            //Random block in the 16x16 chunk
                            * 16 + random.nextInt(16),
                    random.nextInt(chunkGen.getGenDepth() - 3) + chunkGen.getMinY() + 1,
                    (chunkAreaZ * frequency + random.nextInt(frequency))
                            * 16 + random.nextInt(16)};
            statueManager.addStatue(statueLocation);
        }
        if (chunkX == StatueData.posToChunk(statueLocation[2]) && chunkZ == StatueData.posToChunk(statueLocation[4])) {
            //The statue should go in this chunk. Put a statue in here
            BlockPos placePos = new BlockPos(statueLocation[2], statueLocation[3], statueLocation[4]);
            //Simulate the block falling down onto a solid block
            statueManager.removeStatue(statueLocation);
            int chunkMinY = chunkGen.getMinY();
            while (!world.getBlockState(placePos.below()).isSolid() && placePos.getY() > chunkMinY) {
                placePos = placePos.below();
            }

            if (world.getBlockState(placePos.below()).isSolid()) {
                Direction facing = DataReference.randomHorizontalFacing(world.getRandom());
                FluidState fs = world.getFluidState(placePos);
                world.setBlock(placePos, ModBlocks.BLOCK_STATUE.get().defaultBlockState()
                        .setValue(HorizontalDirectionalBlock.FACING, facing)
                        .setValue(BlockStateProperties.WATERLOGGED, fs.is(Fluids.WATER)), 3);
                fs = world.getFluidState(placePos.above());
                world.setBlock(placePos.above(), ModBlocks.BLOCK_STATUE.get().defaultBlockState()
                        .setValue(HorizontalDirectionalBlock.FACING, facing)
                        .setValue(BlockStateProperties.DOUBLE_BLOCK_HALF, DoubleBlockHalf.UPPER)
                        .setValue(BlockStateProperties.WATERLOGGED, fs.is(Fluids.WATER)), 3);
                statueLocation[3] = placePos.getY();
                statueManager.addStatue(statueLocation);
                return true;
            }
        }
        return false;
    }
}
