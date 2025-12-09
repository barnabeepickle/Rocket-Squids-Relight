package com.fredtargaryen.rocketsquids.entity.projectile;

import com.fredtargaryen.rocketsquids.RocketSquidsBase;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.network.protocol.Packet;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import com.mojang.math.Vector3d;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraftforge.fml.network.FMLPlayMessages;
import net.minecraftforge.fml.network.NetworkHooks;

public class ThrownTubeEntity extends ThrowableItemProjectile {
    public ThrownTubeEntity(EntityType<? extends ThrownTubeEntity> type, Level w) {
        super(RocketSquidsBase.TUBE_TYPE, w);
    }
    public ThrownTubeEntity(LivingEntity elb, Level w)
    {
        super(RocketSquidsBase.TUBE_TYPE, elb, w);
    }
    public ThrownTubeEntity(FMLPlayMessages.SpawnEntity spawn, Level world) {
        this(RocketSquidsBase.TUBE_TYPE, world);
    }

    @Override
    protected void onHit(HitResult result) {
        if (!this.level.isClientSide) {
            Vec3 pos = this.position();
            this.level.explode(this.getOwner(), pos.x, pos.y, pos.z, 1.0F, Explosion.BlockInteraction.NONE);
            this.remove();
        }
    }

    @Override
    protected Item getDefaultItem() {
        return RocketSquidsBase.TURBO_TUBE;
    }

    /**
     * THIS IS REQUIRED FOR ALL NON-LIVING MOD ENTITIES FROM NOW ON
     * Without this, they will not spawn on the client.
     */
    @Override
    public Packet<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
