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
public abstract class BabyRocketSquidModel extends EntityModel<BabyRocketSquidEntity> {
    public ModelPart Head;
    public ModelPart Tent1;
    public ModelPart Tent2;
    public ModelPart Tent3;
    public ModelPart Tent4;
    public ModelPart Tent5;
    public ModelPart Tent6;
    public ModelPart Tent7;
    public ModelPart Tent8;

    public BabyRocketSquidModel() {
        this.texWidth = 32;
        this.texHeight = 32;
        this.Head = new ModelPart(this, 0, 6);
        this.Head.mirror = true;
        this.Head.setPos(0.0F, 0.0F, 0.0F);
        this.Head.addBox(-2.0F, -3.0F, -2.0F, 5, 7, 5, 0.0F);

        this.Tent1 = new ModelPart(this, 0, 0);
        this.Tent1.setPos(-0.5F, 4.0F, -0.5F);
        this.Tent1.addBox(-0.5F, 0.0F, -0.5F, 1, 5, 1, 0.0F);
        this.setRotateAngle(Tent1, 0.0F, -2.356194490192345F, 0.0F);

        this.Tent2 = new ModelPart(this, 0, 0);
        this.Tent2.setPos(0.5F, 4.0F, -1.0F);
        this.Tent2.addBox(-0.5F, 0.0F, -0.5F, 1, 5, 1, 0.0F);
        this.setRotateAngle(Tent2, 0.0F, 3.141592653589793F, 0.0F);

        this.Tent3 = new ModelPart(this, 0, 0);
        this.Tent3.setPos(1.5F, 4.0F, -0.5F);
        this.Tent3.addBox(-0.5F, 0.0F, -0.5F, 1, 5, 1, 0.0F);
        this.setRotateAngle(Tent3, 0.0F, 2.356194490192345F, 0.0F);

        this.Tent4 = new ModelPart(this, 0, 0);
        this.Tent4.setPos(2.0F, 4.0F, 0.5F);
        this.Tent4.addBox(-0.5F, 0.0F, -0.5F, 1, 5, 1, 0.0F);
        this.setRotateAngle(Tent4, 0.0F, 1.5707963267948966F, 0.0F);

        this.Tent5 = new ModelPart(this, 0, 0);
        this.Tent5.setPos(1.5F, 4.0F, 1.5F);
        this.Tent5.addBox(-0.5F, 0.0F, -0.5F, 1, 5, 1, 0.0F);
        this.setRotateAngle(Tent5, 0.0F, 0.7853981633974483F, 0.0F);

        this.Tent6 = new ModelPart(this, 0, 0);
        this.Tent6.setPos(0.5F, 4.0F, 2.0F);
        this.Tent6.addBox(-0.5F, 0.0F, -0.5F, 1, 5, 1, 0.0F);

        this.Tent7 = new ModelPart(this, 0, 0);
        this.Tent7.setPos(-0.5F, 4.0F, 1.5F);
        this.Tent7.addBox(-0.5F, 0.0F, -0.5F, 1, 5, 1, 0.0F);
        this.setRotateAngle(Tent7, 0.0F, -0.7853981633974483F, 0.0F);

        this.Tent8 = new ModelPart(this, 0, 0);
        this.Tent8.setPos(-1.0F, 4.0F, 0.5F);
        this.Tent8.addBox(-0.5F, 0.0F, -0.5F, 1, 5, 1, 0.0F);
        this.setRotateAngle(Tent8, 0.0F, -1.5707963267948966F, 0.0F);
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

    public void render(PoseStack matrixStackIn, VertexConsumer bufferIn, int packedLightIn, int packedOverlayIn, float r, float g, float b, float a) {
        this.Head.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, r, g, b, a);
        this.Tent1.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, r, g, b, a);
        this.Tent2.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, r, g, b, a);
        this.Tent3.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, r, g, b, a);
        this.Tent4.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, r, g, b, a);
        this.Tent5.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, r, g, b, a);
        this.Tent6.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, r, g, b, a);
        this.Tent7.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, r, g, b, a);
        this.Tent8.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, r, g, b, a);
    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelPart modelParts, float x, float y, float z) {
        modelParts.x = x;
        modelParts.y = y;
        modelParts.z = z;
    }

    @Override
    public void setupAnim(BabyRocketSquidEntity entity, float time, float maxSpeed, float whatever, float rotationYaw, float rotationPitch) {
        this.Tent1.x = whatever;
        this.Tent2.x = whatever;
        this.Tent3.x = whatever;
        this.Tent4.x = whatever;
        this.Tent5.x = whatever;
        this.Tent6.x = whatever;
        this.Tent7.x = whatever;
        this.Tent8.x = whatever;
    }
}
