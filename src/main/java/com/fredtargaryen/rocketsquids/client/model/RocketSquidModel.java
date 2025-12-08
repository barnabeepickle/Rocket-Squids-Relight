package com.fredtargaryen.rocketsquids.client.model;

import com.fredtargaryen.rocketsquids.entity.RocketSquidEntity;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableList.Builder;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.ListModel;
import net.minecraft.client.model.geom.ModelPart;

import java.util.Arrays;

/**
 * RocketSquidModel - FredTargaryen
 * Created using Tabula 7.0.0
 */
// further manually edited by barnabeepickle on 12-4-2025
public class RocketSquidModel<T extends RocketSquidEntity> extends ListModel<T> {
    public final ModelPart head;
    public final ModelPart[] tent = new ModelPart[8];
    public final ModelPart saddle;
    public final ModelPart straps;
    public final ImmutableList<ModelPart> parts;

    public RocketSquidModel() {
        // initial setup
        this.texWidth = 128;
        this.texHeight = 64;
        ImmutableList.Builder<ModelPart> builder = ImmutableList.builder();

        // making the head
        this.head = new ModelPart(this, 0, 30);
        this.head.mirror = true;
        this.head.setPos(0.0F, 0.0F, 0.0F);
        this.head.addBox(-7.0F, -10.0F, -7.0F, 14, 20, 14, 0.0F, true);
        builder.add(this.head);

        // making the tenticles
        for (int t = 0; t < this.tent.length; t++) {
            this.tent[t] = new ModelPart(this, 0, 0);
            this.tent[t].mirror = true;

            double doubletentrot = t * Math.PI * 2.0 / this.tent.length;
            float floatx = (float)Math.cos(doubletentrot) * 5.0F;
            float floatz = (float)Math.sin(doubletentrot) * 5.0F;
            this.tent[t].setPos(floatx, 9.0F, floatz);
            this.tent[t].addBox(-1.0F, -1.0F, -1.0F, 2, 20, 2, 0.0F, true);

            doubletentrot = t * Math.PI * -2.0 / this.tent.length + (Math.PI / 2);
            this.setRotationAngle(tent[t], 0.0F, (float) doubletentrot, 0.0F);

            head.addChild(tent[t]);
        }

        // finally making the saddle and straps
        this.saddle = new ModelPart(this, 106, 53);
        this.saddle.mirror = true;
        this.saddle.setPos(0.0F, 0.0F, 0.0F);
        this.saddle.addBox(-5.0F, 0.0F, 7.0F, 10, 10, 1, 0.0F, true);
        builder.add(this.saddle);
        head.addChild(saddle);

        this.straps = new ModelPart(this, 68, 0);
        this.straps.mirror = true;
        this.straps.setPos(0.0F, 0.0F, 0.0F);
        this.straps.addBox(-7.5F, 1.0F, -7.5F, 15, 8, 15, 0.0F, true);
        builder.add(this.straps);
        head.addChild(straps);

        builder.addAll(Arrays.asList(this.tent));

        this.parts = builder.build();
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        for (ModelPart modelPart : this.tent) {
            modelPart.xRot = ageInTicks;
        }
        this.saddle.visible = entity.getSaddled();
        this.straps.visible = entity.getSaddled();
    }

    @Override
    public void renderToBuffer(PoseStack matrixStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
        this.head.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    public void setRotationAngle(ModelPart modelRenderer, float x, float y, float z) {
        modelRenderer.xRot = x;
        modelRenderer.yRot = y;
        modelRenderer.zRot = z;
    }

    @Override
    public Iterable<ModelPart> parts() {
        return this.parts;
    }
}
