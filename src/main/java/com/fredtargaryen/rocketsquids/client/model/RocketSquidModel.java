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
// further manually edited by barnabeepickle on 12-4-2025
public class RocketSquidModel extends EntityModel<RocketSquidEntity> {
    public ModelPart Head;
    public ModelPart[] tent = new ModelPart[8];
    public ModelPart Saddle;
    public ModelPart Straps;

    public RocketSquidModel() {
        this.texWidth = 128;
        this.texHeight = 64;

        // we make the head
        this.Head = new ModelPart(this, 0, 30);
        this.Head.mirror = true;
        this.Head.setPos(0.0F, 0.0F, 0.0F);
        this.Head.addBox(-7.0F, -10.0F, -7.0F, 14, 20, 14, 0.0F);

        // then we make the tenticles
        for (int t = 0; t < this.tent.length; t++) {
            this.tent[t].mirror = true;

            this.tent[t].addBox(-1.0F, -1.0F, -1.0F, 2, 20, 2, 0.0F);

            double doubletentrot = t * Math.PI * 2.0 / this.tent.length;
            float floatx = (float)Math.cos(doubletentrot) * 5.0F;
            float floatz = (float)Math.sin(doubletentrot) * 5.0F;
            this.tent[t].setPos(floatx, 9.0F, floatz);

            doubletentrot = t * Math.PI * -2.0 / this.tent.length + (Math.PI / 2);
            this.tent[t].yRot = (float) doubletentrot;
        }

        // finally we make the saddle and straps
        this.Saddle = new ModelPart(this, 106, 53);
        this.Saddle.mirror = true;
        this.Saddle.setPos(0.0F, 0.0F, 0.0F);
        this.Saddle.addBox(-5.0F, 0.0F, 7.0F, 10, 10, 1, 0.0F);

        this.Straps = new ModelPart(this, 68, 0);
        this.Straps.mirror = true;
        this.Straps.setPos(0.0F, 0.0F, 0.0F);
        this.Straps.addBox(-7.5F, 1.0F, -7.5F, 15, 8, 15, 0.0F);
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
        this.Saddle.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, r, g, b, a);
        this.Straps.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, r, g, b, a);
    }

    @Override
    public void setupAnim(RocketSquidEntity entity, float time, float maxSpeed, float whatever, float rotationYaw, float rotationPitch) {
        this.tent[0].x = whatever;
        this.tent[1].x = whatever;
        this.tent[2].x = whatever;
        this.tent[3].x = whatever;
        this.tent[4].x = whatever;
        this.tent[5].x = whatever;
        this.tent[6].x = whatever;
        this.tent[7].x = whatever;
        this.Saddle.visible = entity.getSaddled();
        this.Straps.visible = entity.getSaddled();
    }
}
