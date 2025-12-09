package com.fredtargaryen.rocketsquids.worldgen;

import com.fredtargaryen.rocketsquids.DataReference;
import com.fredtargaryen.rocketsquids.RocketSquidsBase;
import com.fredtargaryen.rocketsquids.config.GeneralConfig;
import com.mojang.serialization.Codec;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.Feature;

import java.util.List;
import java.util.Random;

public class ConchGen extends Feature<ConchGenConfig> {

    public ConchGen(Codec<ConchGenConfig> codec) {
        super(codec);
    }

    /**
     * Generate the feature at the given BlockPos (which was validated by an IPatchPlacement instance).
     * @param world
     * @param chunkGen
     * @param random
     * @param pos
     * @param config
     * @return
     */
    @Override
    public boolean place(WorldGenLevel world, ChunkGenerator chunkGen, Random random, BlockPos pos, ConchGenConfig config) {
        // First check the config to see if this dimension is allowed
        if(GeneralConfig.CONCH_USE_WHITELIST.get())
        {
            List<? extends String> allowedDimensions = GeneralConfig.CONCH_WHITELIST.get();
            if(!allowedDimensions.contains(world.getLevel().dimension().location().toString())) return false;
        }
        else
        {
            List<? extends String> blockedDimensions = GeneralConfig.CONCH_BLACKLIST.get();
            if(blockedDimensions.contains(world.getLevel().dimension().location().toString())) return false;
        }
        world.setBlock(pos, RocketSquidsBase.BLOCK_CONCH.defaultBlockState()
                .setValue(BlockStateProperties.HORIZONTAL_FACING, DataReference.randomHorizontalFacing(random))
                .setValue(BlockStateProperties.WATERLOGGED, world.getBlockState(pos).getBlock() == Blocks.WATER), 3);
        return true;
    }
}
