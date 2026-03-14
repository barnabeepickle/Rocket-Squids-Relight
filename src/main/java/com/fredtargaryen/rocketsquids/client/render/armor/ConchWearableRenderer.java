// Copyright 2016-2022, 2025-2026 FredTargaryen and contributors
// See mod_authors in gradle.properties for full contributor list
// See README.md for full copyright notice
package com.fredtargaryen.rocketsquids.client.render.armor;

import com.fredtargaryen.rocketsquids.client.model.armor.ConchWearableModel;
import com.fredtargaryen.rocketsquids.content.item.ItemConch;
import software.bernie.geckolib.renderer.GeoArmorRenderer;


public final class ConchWearableRenderer extends GeoArmorRenderer<ItemConch> {
    public ConchWearableRenderer() {
        super(new ConchWearableModel());
    }
}
