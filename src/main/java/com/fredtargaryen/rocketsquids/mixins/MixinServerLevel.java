package com.fredtargaryen.rocketsquids.mixins;

import com.fredtargaryen.rocketsquids.content.entity.AgeableWaterAnimal;
import dev.architectury.patchedmixin.staticmixin.spongepowered.asm.mixin.Mixin;
import dev.architectury.patchedmixin.staticmixin.spongepowered.asm.mixin.injection.At;
import dev.architectury.patchedmixin.staticmixin.spongepowered.asm.mixin.injection.Inject;
import dev.architectury.patchedmixin.staticmixin.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;

@Mixin(ServerLevel.class)
public class MixinServerLevel {
    @SuppressWarnings("unused")
    @Inject(method = "Lnet/minecraft/server/level/ServerLevel;shouldDiscardEntity(Lnet/minecraft/world/entity/Entity;)Z", at = @At("RETURN"), cancellable = true)
    private void shouldDiscardEntity(Entity entity, CallbackInfoReturnable<Boolean> cir) {
        if (!cir.getReturnValue()) {
            cir.setReturnValue(!(entity instanceof AgeableWaterAnimal));
        }
    }
}
