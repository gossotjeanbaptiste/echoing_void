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
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.item.component.Consumables;
import net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect;

import name.modid.EchoingVoid;
import name.modid.effect.ModMobEffects;

public class ModItems {
	public static final Item VOID_POISONED_POTION = register("void_poisoned_potion",
		properties -> new Item(properties
			.stacksTo(1)
			.usingConvertsTo(Items.GLASS_BOTTLE)
			.component(DataComponents.CONSUMABLE, voidPoisonedConsumable())));

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
	}
}
