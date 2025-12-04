package com.fredtargaryen.rocketsquids.client.model;

import com.fredtargaryen.rocketsquids.entity.BabyRocketSquidEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.Model;
import net.minecraft.world.entity.Entity;

/**
 * BabyRocketSquidModel - FredTargaryen
 * Created using Tabula 7.0.0
 */
public class BabyRocketSquidModel extends EntityModel<BabyRocketSquidEntity> {
    public ModelPart Head;
    public ModelPart[] tent = new ModelPart[8];

    public BabyRocketSquidModel() {
        this.texWidth = 32;
        this.texHeight = 32;

        // we make the head
        this.Head = new ModelPart(this, 0, 6);
        this.Head.mirror = true;
        this.Head.setPos(0.0F, 0.0F, 0.0F);
        this.Head.addBox(-2.0F, -3.0F, -2.0F, 5, 7, 5, 0.0F);

        // then we make the tenticles
        for (int t = 0; t < this.tent.length; t++) {
            this.tent[t].mirror = true;

            this.tent[t].addBox(-0.5F, 0.0F, -0.5F, 1, 5, 1, 0.0F);

            double doubletentrot = t * Math.PI * 2.0 / this.tent.length;
            float floatx = (float)Math.cos(doubletentrot) * 5.0F;
            float floatz = (float)Math.sin(doubletentrot) * 5.0F;
            this.tent[t].setPos(floatx, 4.0F, floatz);

            doubletentrot = t * Math.PI * -2.0 / this.tent.length + (Math.PI / 2);
            this.tent[t].yRot = (float) doubletentrot;
        }
    }

//    @Override
//    public void render(BabyRocketSquidEntity entity, float f, float f1, float f2, float f3, float f4, float f5) {
//        super.render(entity, f, f1, f2, f3, f4, f5);
//        setRotationAngles(entity, f, f1, f2, f3, f4, f5);
//        this.Head.render(f5);
//        this.Tent1.render(f5);
//        this.Tent2.render(f5);
//        this.Tent3.render(f5);
//        this.Tent4.render(f5);
//        this.Tent5.render(f5);
//        this.Tent6.render(f5);
//        this.Tent7.render(f5);
//        this.Tent8.render(f5);
//    }

    public void renderToBuffer(PoseStack matrixStackIn, VertexConsumer bufferIn, int packedLightIn, int packedOverlayIn, float r, float g, float b, float a) {
        this.Head.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, r, g, b, a);
        for (int t = 0; t < this.tent.length; t++) {
            this.tent[t].render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, r, g, b, a);
        }
    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelPart modelParts, float x, float y, float z) {
        modelParts.xRot = x;
        modelParts.yRot = y;
        modelParts.zRot = z;
    }

    @Override
    public void setupAnim(BabyRocketSquidEntity entity, float time, float maxSpeed, float whatever, float rotationYaw, float rotationPitch) {
        for (int t = 0; t < this.tent.length; t++) {
            this.tent[t].x = whatever;
        }
    }
}
