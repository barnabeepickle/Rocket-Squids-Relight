// Copyright 2016-2022, 2025-2026 FredTargaryen and contributors
// See mod_authors in gradle.properties for full contributor list
// See README.md for full copyright notice
package com.fredtargaryen.rocketsquids.content;

import com.fredtargaryen.rocketsquids.content.worldgen.features.ConchGen;
import com.fredtargaryen.rocketsquids.content.worldgen.features.StatueGen;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.fredtargaryen.rocketsquids.DataReference.MODID;

public class ModFeatures {
    public static final DeferredRegister<Feature<?>> FEATURES =
            DeferredRegister.create(ForgeRegistries.FEATURES, MODID);

    public static final RegistryObject<ConchGen> CONCH_FEATURE = FEATURES.register("conchgen",
            ConchGen::new
    );
    public static final RegistryObject<StatueGen> STATUE_FEATURE = FEATURES.register("statuegen",
            StatueGen::new
    );

    public static void register(IEventBus eventBus) {
        FEATURES.register(eventBus);
    }
}
