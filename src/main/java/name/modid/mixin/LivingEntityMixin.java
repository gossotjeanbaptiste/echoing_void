package name.modid.mixin;

import java.util.Collection;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;

import name.modid.effect.ModMobEffects;
import name.modid.sound.ModSounds;
import name.modid.tag.ModEntityTypeTags;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {
	@Inject(method = "canBeAffected", at = @At("HEAD"), cancellable = true)
	private void echoingVoid$immuneToVoidPoisoned(MobEffectInstance newEffect, CallbackInfoReturnable<Boolean> cir) {
		LivingEntity self = (LivingEntity) (Object) this;
		if (newEffect.is(ModMobEffects.VOID_POISONED) && self.is(ModEntityTypeTags.IMMUNE_TO_VOID_POISONED)) {
			cir.setReturnValue(false);
		}
	}

	@Inject(method = "onEffectsRemoved", at = @At("HEAD"))
	private void echoingVoid$playVoidPoisonedDeactivateSound(Collection<MobEffectInstance> effects, CallbackInfo ci) {
		LivingEntity self = (LivingEntity) (Object) this;
		for (MobEffectInstance instance : effects) {
			if (instance.is(ModMobEffects.VOID_POISONED)) {
				self.level().playSound(null, self.getX(), self.getY(), self.getZ(),
					ModSounds.VOID_POISONED_DEACTIVATE, self.getSoundSource(), 1.0F, 1.0F);
				break;
			}
		}
	}
}
