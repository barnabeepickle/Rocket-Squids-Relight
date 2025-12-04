package com.fredtargaryen.rocketsquids.client.model;

import com.fredtargaryen.rocketsquids.entity.BabyRocketSquidEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;

/**
 * BabyRocketSquidModel - FredTargaryen
 * Created using Tabula 7.0.0
 */
// further manually edited by barnabeepickle on 12-4-2025
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


    public void renderToBuffer(PoseStack matrixStackIn, VertexConsumer bufferIn, int packedLightIn, int packedOverlayIn, float r, float g, float b, float a) {
        this.Head.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, r, g, b, a);
        this.tent[0].render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, r, g, b, a);
        this.tent[1].render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, r, g, b, a);
        this.tent[2].render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, r, g, b, a);
        this.tent[3].render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, r, g, b, a);
        this.tent[4].render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, r, g, b, a);
        this.tent[5].render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, r, g, b, a);
        this.tent[6].render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, r, g, b, a);
        this.tent[7].render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, r, g, b, a);
    }


    @Override
    public void setupAnim(BabyRocketSquidEntity entity, float time, float maxSpeed, float whatever, float rotationYaw, float rotationPitch) {
        this.tent[0].x = whatever;
        this.tent[1].x = whatever;
        this.tent[2].x = whatever;
        this.tent[3].x = whatever;
        this.tent[4].x = whatever;
        this.tent[5].x = whatever;
        this.tent[6].x = whatever;
        this.tent[7].x = whatever;
    }
}
