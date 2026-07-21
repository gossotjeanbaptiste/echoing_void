package name.modid.item;

import java.util.function.Function;

import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;

import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.item.component.Consumables;
import net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect;

import name.modid.EchoingVoid;
import name.modid.effect.ModMobEffects;
import name.modid.entity.ModEntities;

public class ModItems {
	public static final Item VOID_POISONED_POTION = register("void_poisoned_potion",
		properties -> new Item(properties
			.stacksTo(1)
			.usingConvertsTo(Items.GLASS_BOTTLE)
			.component(DataComponents.CONSUMABLE, voidPoisonedConsumable())));

	// Not obtainable/creative-tab listed - only exists so BlastlingGooEntity has an ItemStack to
	// hand to ThrownItemRenderer (the projectile renders as a flat thrown-item billboard).
	public static final Item BLASTLING_GOO = register("blastling_goo", Item::new);

	// Real functional spawn eggs (spawn the mob on use, like vanilla) - creative/command only,
	// same as every vanilla spawn egg, not craftable.
	public static final Item WATCHLING_SPAWN_EGG = register("watchling_spawn_egg",
		properties -> new SpawnEggItem(properties.spawnEgg(ModEntities.WATCHLING)));
	public static final Item BLASTLING_SPAWN_EGG = register("blastling_spawn_egg",
		properties -> new SpawnEggItem(properties.spawnEgg(ModEntities.BLASTLING)));

	private static Consumable voidPoisonedConsumable() {
		return Consumables.defaultDrink()
			.hasConsumeParticles(false)
			.onConsume(new ApplyStatusEffectsConsumeEffect(
				new MobEffectInstance(ModMobEffects.VOID_POISONED, ModMobEffects.VOID_POISONED_DEFAULT_DURATION_TICKS)))
			.build();
	}

	private static Item register(String name, Function<Item.Properties, Item> factory) {
		ResourceKey<Item> key = ResourceKey.create(Registries.ITEM, EchoingVoid.id(name));
		return Registry.register(BuiltInRegistries.ITEM, key, factory.apply(new Item.Properties().setId(key)));
	}

	public static void init() {
		CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.FOOD_AND_DRINKS)
			.register(output -> output.accept(VOID_POISONED_POTION));
		CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.SPAWN_EGGS)
			.register(output -> {
				output.accept(WATCHLING_SPAWN_EGG);
				output.accept(BLASTLING_SPAWN_EGG);
			});
	}
}
