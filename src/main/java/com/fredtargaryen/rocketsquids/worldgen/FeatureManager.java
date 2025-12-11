package com.fredtargaryen.rocketsquids.worldgen;

import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneDecoratorConfiguration;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
import net.minecraftforge.common.world.MobSpawnInfoBuilder;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;
import java.util.Objects;

import static com.fredtargaryen.rocketsquids.RocketSquidsBase.*;

@Mod.EventBusSubscriber
public class FeatureManager {
    public static ConchPlacement CONCH_PLACEMENT;
    public static StatuePlacement STATUE_PLACEMENT;

    public void registerFeatures(RegistryEvent.Register<Feature<?>> event) {
        CONCH_FEATURE = new ConchGen(ConchGenConfig.FACTORY);
        CONCH_FEATURE.setRegistryName("conchgen");
        CONCH_PLACEMENT = new ConchPlacement(NoneDecoratorConfiguration.CODEC);
        STATUE_FEATURE = new StatueGen(StatueGenConfig.FACTORY);
        STATUE_FEATURE.setRegistryName("statuegen");
        STATUE_PLACEMENT = new StatuePlacement(NoneDecoratorConfiguration.CODEC);
        event.getRegistry().registerAll(CONCH_FEATURE, STATUE_FEATURE);
    }

    @SubscribeEvent
    public static void loadBiome(BiomeLoadingEvent ble)
    {
        MobSpawnInfoBuilder builder = ble.getSpawns();
        List<MobSpawnSettings.SpawnerData> spawners = builder.getSpawner(MobCategory.WATER_CREATURE);
        boolean squidFound = false;
        for (MobSpawnSettings.SpawnerData s : spawners) {
            if(Objects.requireNonNull(s.type.getRegistryName()).toString().equals("minecraft:squid")) {
                squidFound = true;
            }
        }
        if(squidFound) builder.addSpawn(MobCategory.WATER_CREATURE, ROCKET_SQUID_SPAWN_INFO);
        BiomeGenerationSettingsBuilder bgsb = ble.getGeneration();
        bgsb.getFeatures(GenerationStep.Decoration.RAW_GENERATION).add(() -> STATUE_FEATURE.configured(new StatueGenConfig()).decorated(STATUE_PLACEMENT.configured(NoneDecoratorConfiguration.INSTANCE)));
        if(ble.getCategory() == Biome.BiomeCategory.BEACH)
        {
            bgsb.getFeatures(GenerationStep.Decoration.TOP_LAYER_MODIFICATION).add(() -> CONCH_FEATURE.configured(new ConchGenConfig()).decorated(CONCH_PLACEMENT.configured(NoneDecoratorConfiguration.INSTANCE)));
        }
    }
}
