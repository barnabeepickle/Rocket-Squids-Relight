// Copyright 2016-2022, 2025-2026 FredTargaryen and contributors
// See mod_authors in gradle.properties for full contributor list
// See README.md for full copyright notice
package com.fredtargaryen.rocketsquids.client.model.armor;

import com.fredtargaryen.rocketsquids.content.item.ItemConch;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

import static com.fredtargaryen.rocketsquids.DataReference.MODID;

@SuppressWarnings("removal")
public class ConchWearableModel extends GeoModel<ItemConch> {
    @Override
    public ResourceLocation getModelResource(ItemConch object) {
        return new ResourceLocation(MODID, "geo/armor/conch_armor_model.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(ItemConch object) {
        return new ResourceLocation(MODID, "textures/models/armor/conch_item_1_layer_1.png");
    }

    @Override
    public ResourceLocation getAnimationResource(ItemConch animatable) {
        return new ResourceLocation(MODID, "animations/armor/conch_armor.animation.json");
    }
}
