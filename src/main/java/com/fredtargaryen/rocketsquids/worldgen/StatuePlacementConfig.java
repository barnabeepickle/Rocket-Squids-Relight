package com.fredtargaryen.rocketsquids.worldgen;

import com.mojang.serialization.Codec;
import net.minecraft.world.level.levelgen.feature.configurations.DecoratorConfiguration;

public class StatuePlacementConfig implements DecoratorConfiguration {
    public static final Codec<StatuePlacementConfig> FACTORY = null;

    public int genChance;

    public StatuePlacementConfig() { }
}
