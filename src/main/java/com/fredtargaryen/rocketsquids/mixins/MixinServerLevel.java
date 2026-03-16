package com.fredtargaryen.rocketsquids.mixins;

import com.fredtargaryen.rocketsquids.content.entity.AgeableWaterAnimal;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ServerLevel.class)
public class MixinServerLevel {
    @SuppressWarnings("unused")
    @Inject(method = "shouldDiscardEntity(Lnet/minecraft/world/entity/Entity;)Z", at = @At("RETURN"), cancellable = true)
    private void shouldDiscardEntity(Entity entity, CallbackInfoReturnable<Boolean> cir) {
        if (!cir.getReturnValue()) {
            cir.setReturnValue(!(entity instanceof AgeableWaterAnimal));
        }
    }
}
