package com.fusionflux.somethingmagical.mixin;

import com.fusionflux.somethingmagical.accessor.PlayerEntityExtensions;
import com.fusionflux.somethingmagical.effects.CustomEffects;
import net.minecraft.entity.*;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.decoration.EndCrystalEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerAbilities;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import java.util.List;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends LivingEntity implements PlayerEntityExtensions {

	@Unique
	private EndCrystalEntity connectedCrystal;
	@Override
	public EndCrystalEntity somethingmagical_getConnectedCrystal() {
		return connectedCrystal;
	}

	protected PlayerEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
		super(entityType, world);
	}

	@Inject(method = "tick", at = @At("HEAD"), cancellable = true)
	public void tick(CallbackInfo ci) {
            if (!this.isDead()) {
                if (this.connectedCrystal != null) {
                    this.applyStatusEffect(new StatusEffectInstance(CustomEffects.DRAGONHEALING, 1205, 0));
                    if (this.connectedCrystal.removed) {
                        this.connectedCrystal = null;
                    } else if (this.age % 10 == 0 && this.getHealth() < this.getMaxHealth()) {
                        this.setHealth(this.getHealth() + 1.0F);
                    }
                }else{
                    this.removeStatusEffectInternal(CustomEffects.DRAGONHEALING);
                }

                //if (this.random.nextInt(10) == 0) {
                List<EndCrystalEntity> list = this.world.getNonSpectatingEntities(EndCrystalEntity.class, this.getBoundingBox().expand(32.0D));
                EndCrystalEntity endCrystalEntity = null;
                double d = 1000;
                for (EndCrystalEntity endCrystalEntity2 : list) {
                    double e = endCrystalEntity2.squaredDistanceTo(this);

                    if (e < d) {
                        d = e;
                        endCrystalEntity = endCrystalEntity2;
                    }
                }

                this.connectedCrystal = endCrystalEntity;
            }
	}
}
