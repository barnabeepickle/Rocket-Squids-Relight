package com.fredtargaryen.rocketsquids.worldgen;

import com.mojang.serialization.Codec;
import net.minecraft.world.level.levelgen.feature.configurations.DecoratorConfiguration;

public class ConchPlacementConfig implements DecoratorConfiguration {
    public static final Codec<ConchPlacementConfig> FACTORY = null;

    public int genChance;

    public ConchPlacementConfig() { }
}
