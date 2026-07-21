package name.modid.sound;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvent;

import name.modid.EchoingVoid;

public class ModSounds {
	public static final SoundEvent WATCHLING_IDLE = register("watchling.idle");
	public static final SoundEvent WATCHLING_HURT = register("watchling.hurt");
	public static final SoundEvent WATCHLING_DEATH = register("watchling.death");
	public static final SoundEvent WATCHLING_ATTACK = register("watchling.attack");
	public static final SoundEvent WATCHLING_HEAVY_ATTACK = register("watchling.heavy_attack");
	public static final SoundEvent WATCHLING_STUN = register("watchling.stun");
	public static final SoundEvent WATCHLING_TELEPORT_START = register("watchling.teleport_start");
	public static final SoundEvent WATCHLING_TELEPORT_END = register("watchling.teleport_end");
	public static final SoundEvent VOID_POISONED_ACTIVATE = register("void_poisoned.activate");
	public static final SoundEvent VOID_POISONED_DEACTIVATE = register("void_poisoned.deactivate");

	public static final SoundEvent BLASTLING_IDLE = register("blastling.idle");
	public static final SoundEvent BLASTLING_HURT = register("blastling.hurt");
	public static final SoundEvent BLASTLING_DEATH = register("blastling.death");
	public static final SoundEvent BLASTLING_STUN = register("blastling.stun");
	public static final SoundEvent BLASTLING_RANGED_ATTACK_ANIMATION = register("blastling.ranged_attack_animation");
	public static final SoundEvent BLASTLING_RANGED_ATTACK_IMPACT = register("blastling.ranged_attack_impact");
	public static final SoundEvent BLASTLING_RANGE_ATTACK_PROJ = register("blastling.range_attack_proj");

	private static SoundEvent register(String name) {
		ResourceKey<SoundEvent> key = ResourceKey.create(Registries.SOUND_EVENT, EchoingVoid.id(name));
		return Registry.register(BuiltInRegistries.SOUND_EVENT, key, SoundEvent.createVariableRangeEvent(EchoingVoid.id(name)));
	}

	public static void init() {
	}
}
