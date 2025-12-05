package com.fredtargaryen.rocketsquids.client.model;

import com.fredtargaryen.rocketsquids.entity.BabyRocketSquidEntity;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableList.Builder;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.ListModel;
import net.minecraft.client.model.geom.ModelPart;

import java.util.Arrays;

/**
 * BabyRocketSquidModel - FredTargaryen
 * Created using Tabula 7.0.0
 */
// further manually edited by barnabeepickle on 12-4-2025
public class BabyRocketSquidModel<T extends BabyRocketSquidEntity> extends ListModel<T> {
    public final ModelPart head;
    public final ModelPart[] tent = new ModelPart[8];
    public final ImmutableList<ModelPart> parts;

    public BabyRocketSquidModel() {
        // initial setup
        this.texWidth = 32;
        this.texHeight = 32;
        ImmutableList.Builder<ModelPart> builder = ImmutableList.builder();

        // making the head
        this.head = new ModelPart(this, 0, 6);
        this.head.mirror = true;
        this.head.setPos(0.0F, 0.0F, 0.0F);
        this.head.addBox(-2.0F, -3.0F, -2.0F, 5, 7, 5, 0.0F);
        builder.add(this.head);

        // making the tenticles
        for (int t = 0; t < this.tent.length; t++) {
            this.tent[t] = new ModelPart(this, 0, 0);
            this.tent[t].mirror = true;

            double doubletentrot = t * Math.PI * 2.0 / this.tent.length;
            float floatx = (float)Math.cos(doubletentrot) * 5.0F;
            float floatz = (float)Math.sin(doubletentrot) * 5.0F;
            this.tent[t].setPos(floatx, 4.0F, floatz);
            this.tent[t].addBox(-0.5F, 0.0F, -0.5F, 1, 5, 1, 0.0F);

            doubletentrot = t * Math.PI * -2.0 / this.tent.length + (Math.PI / 2);
            this.tent[t].yRot = (float) doubletentrot;
        }

        this.parts = builder.build();
    }

    @Override
    public void renderToBuffer(PoseStack matrixStackIn, VertexConsumer bufferIn, int packedLightIn, int packedOverlayIn, float r, float g, float b, float a) {
        this.head.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, r, g, b, a);
        for (ModelPart modelPart : this.tent) {
            modelPart.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, r, g, b, a);
        }
    }

    @Override
    public void setupAnim(BabyRocketSquidEntity entity, float time, float maxSpeed, float whatever, float rotationYaw, float rotationPitch) {
        for (ModelPart modelPart : this.tent) {
            modelPart.x = whatever;
        }
    }

    @Override
    public Iterable<ModelPart> parts() {
        return this.parts;
    }
}
