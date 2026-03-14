// Copyright 2016-2022, 2025-2026 FredTargaryen and contributors
// See mod_authors in gradle.properties for full contributor list
// See README.md for full copyright notice
package com.fredtargaryen.rocketsquids.content;

import com.fredtargaryen.rocketsquids.content.block.ConchBlock;
import com.fredtargaryen.rocketsquids.content.block.StatueBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.fredtargaryen.rocketsquids.DataReference.MODID;

public class ModBlocks {
    private static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);

    // Register all blocks here
    public static final RegistryObject<ConchBlock> BLOCK_CONCH = BLOCKS.register("conch",
            () -> new ConchBlock(Block.Properties.of().mapColor(MapColor.SAND).noCollission())
    );
    public static final RegistryObject<StatueBlock> BLOCK_STATUE = BLOCKS.register("statue",
            () -> new StatueBlock(Block.Properties.of().mapColor(MapColor.STONE).noOcclusion())
    );

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
