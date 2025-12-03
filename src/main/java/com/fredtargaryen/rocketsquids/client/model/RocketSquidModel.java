package com.fredtargaryen.rocketsquids.client.model;

import com.fredtargaryen.rocketsquids.entity.RocketSquidEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;

/**
 * RocketSquidModel - FredTargaryen
 * Created using Tabula 7.0.0
 */
public class RocketSquidModel extends EntityModel<RocketSquidEntity> {
    public ModelPart Head;
    public ModelPart Tent0;
    public ModelPart Tent1;
    public ModelPart Tent2;
    public ModelPart Tent3;
    public ModelPart Tent4;
    public ModelPart Tent5;
    public ModelPart Tent6;
    public ModelPart Tent7;
    public ModelPart Saddle;
    public ModelPart Straps;

    public RocketSquidModel() {
        this.texWidth = 128;
        this.texHeight = 64;

        this.Head = new ModelPart(this, 0, 30);
        this.Head.mirror = true;
        this.Head.setPos(0.0F, 0.0F, 0.0F);
        this.Head.addBox(-7.0F, -10.0F, -7.0F, 14, 20, 14, 0.0F);

        this.Tent0 = new ModelPart(this, 0, 0);
        this.Tent0.mirror = true;
        this.Tent0.setPos(0.0F, 9.0F, -5.0F);
        this.Tent0.addBox(-1.0F, -1.0F, -1.0F, 2, 20, 2, 0.0F);
        this.setRotateAngle(Tent0, 0.0F, 3.141592653589793F, 0.0F);

        this.Tent1 = new ModelPart(this, 0, 0);
        this.Tent1.mirror = true;
        this.Tent1.setPos(5.0F, 9.0F, -5.0F);
        this.Tent1.addBox(-1.0F, -1.0F, -1.0F, 2, 20, 2, 0.0F);
        this.setRotateAngle(Tent1, 0.0F, 2.356194490192345F, 0.0F);

        this.Tent2 = new ModelPart(this, 0, 0);
        this.Tent2.mirror = true;
        this.Tent2.setPos(5.0F, 9.0F, 0.0F);
        this.Tent2.addBox(-1.0F, -1.0F, -1.0F, 2, 20, 2, 0.0F);
        this.setRotateAngle(Tent2, 0.0F, 1.5707963267948966F, 0.0F);

        this.Tent3 = new ModelPart(this, 0, 0);
        this.Tent3.mirror = true;
        this.Tent3.setPos(5.0F, 9.0F, 5.0F);
        this.Tent3.addBox(-1.0F, -1.0F, -1.0F, 2, 20, 2, 0.0F);
        this.setRotateAngle(Tent3, 0.0F, 0.7853981633974483F, 0.0F);

        this.Tent4 = new ModelPart(this, 0, 0);
        this.Tent4.mirror = true;
        this.Tent4.setPos(0.0F, 9.0F, 5.0F);
        this.Tent4.addBox(-1.0F, -1.0F, -1.0F, 2, 20, 2, 0.0F);

        this.Tent5 = new ModelPart(this, 0, 0);
        this.Tent5.mirror = true;
        this.Tent5.setPos(-5.0F, 9.0F, 5.0F);
        this.Tent5.addBox(-1.0F, -1.0F, -1.0F, 2, 20, 2, 0.0F);
        this.setRotateAngle(Tent5, 0.0F, -0.7853981633974483F, 0.0F);

        this.Tent6 = new ModelPart(this, 0, 0);
        this.Tent6.mirror = true;
        this.Tent6.setPos(-5.0F, 9.0F, 0.0F);
        this.Tent6.addBox(-1.0F, -1.0F, -1.0F, 2, 20, 2, 0.0F);
        this.setRotateAngle(Tent6, 0.0F, -1.5707963267948966F, 0.0F);

        this.Tent7 = new ModelPart(this, 0, 0);
        this.Tent7.mirror = true;
        this.Tent7.setPos(-5.0F, 9.0F, -5.0F);
        this.Tent7.addBox(-1.0F, -1.0F, -1.0F, 2, 20, 2, 0.0F);
        this.setRotateAngle(Tent7, 0.0F, -2.356194490192345F, 0.0F);

        this.Saddle = new ModelPart(this, 106, 53);
        this.Saddle.mirror = true;
        this.Saddle.setPos(0.0F, 0.0F, 0.0F);
        this.Saddle.addBox(-5.0F, 0.0F, 7.0F, 10, 10, 1, 0.0F);

        this.Straps = new ModelPart(this, 68, 0);
        this.Straps.mirror = true;
        this.Straps.setPos(0.0F, 0.0F, 0.0F);
        this.Straps.addBox(-7.5F, 1.0F, -7.5F, 15, 8, 15, 0.0F);
    }

//    @Override
//    public void render(RocketSquidEntity entity, float f, float f1, float f2, float f3, float f4, float f5) {
//        this.setRotationAngles(entity, f, f1, f2, f3, f4, f5);
//        this.Head.render(f5);
//        this.Tent0.render(f5);
//        this.Tent1.render(f5);
//        this.Tent2.render(f5);
//        this.Tent3.render(f5);
//        this.Tent4.render(f5);
//        this.Tent5.render(f5);
//        this.Tent6.render(f5);
//        this.Tent7.render(f5);
//        if(entity.getSaddled()) {
//            Saddle.render(f5);
//            Straps.render(f5);
//        }
//    }

    public void renderToBuffer(PoseStack matrixStackIn, VertexConsumer bufferIn, int packedLightIn, int packedOverlayIn, float r, float g, float b, float a) {
        //this.setRotationAngles(entity, f, f1, f2, f3, f4, f5);
        this.Head.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, r, g, b, a);
        this.Tent0.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, r, g, b, a);
        this.Tent1.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, r, g, b, a);
        this.Tent2.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, r, g, b, a);
        this.Tent3.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, r, g, b, a);
        this.Tent4.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, r, g, b, a);
        this.Tent5.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, r, g, b, a);
        this.Tent6.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, r, g, b, a);
        this.Tent7.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, r, g, b, a);
        Saddle.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, r, g, b, a);
        Straps.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, r, g, b, a);
    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelPart modelRenderer, float x, float y, float z) {
        modelRenderer.xRot = x;
        modelRenderer.yRot = y;
        modelRenderer.zRot = z;
    }

    @Override
    public void setupAnim(RocketSquidEntity entity, float time, float maxSpeed, float whatever, float rotationYaw, float rotationPitch) {
        this.Tent0.x = whatever;
        this.Tent1.x = whatever;
        this.Tent2.x = whatever;
        this.Tent3.x = whatever;
        this.Tent4.x = whatever;
        this.Tent5.x = whatever;
        this.Tent6.x = whatever;
        this.Tent7.x = whatever;
        this.Saddle.visible = entity.getSaddled();
        this.Straps.visible = entity.getSaddled();
    }
}
