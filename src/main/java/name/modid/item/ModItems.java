package name.modid.item;

import java.util.function.Function;

import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.fabricmc.fabric.api.registry.FabricPotionBrewingBuilder;

import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.LingeringPotionItem;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.item.SplashPotionItem;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.item.component.Consumables;

import name.modid.EchoingVoid;
import name.modid.entity.ModEntities;

public class ModItems {
	private static final float LINGERING_DURATION_SCALE = 0.25F;

	public static final Item VOID_POISONED_POTION = register("void_poisoned_potion",
		properties -> new VoidPoisonedPotionItem(properties
			.stacksTo(1)
			.usingConvertsTo(Items.GLASS_BOTTLE)
			.component(DataComponents.CONSUMABLE, voidPoisonedConsumable())
			.component(DataComponents.POTION_CONTENTS, voidPoisonedPotionContents())));

	public static final Item VOID_POISONED_SPLASH_POTION = register("void_poisoned_splash_potion",
		properties -> new SplashPotionItem(properties
			.stacksTo(1)
			.component(DataComponents.POTION_CONTENTS, voidPoisonedPotionContents())));

	public static final Item VOID_POISONED_LINGERING_POTION = register("void_poisoned_lingering_potion",
		properties -> new LingeringPotionItem(properties
			.stacksTo(1)
			.component(DataComponents.POTION_CONTENTS, voidPoisonedPotionContents())
			.component(DataComponents.POTION_DURATION_SCALE, LINGERING_DURATION_SCALE)));

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
		// No explicit onConsume effect here: PotionContents (attached below) is itself a
		// ConsumableListener and applies its effects automatically when the item is drunk.
		return Consumables.defaultDrink()
			.hasConsumeParticles(false)
			.build();
	}

	private static PotionContents voidPoisonedPotionContents() {
		return new PotionContents(ModPotions.VOID_POISONED);
	}

	private static boolean isVanillaVoidPoisonedContainer(ItemStack stack) {
		if (!stack.is(Items.POTION) && !stack.is(Items.SPLASH_POTION) && !stack.is(Items.LINGERING_POTION)) {
			return false;
		}
		PotionContents contents = stack.getOrDefault(DataComponents.POTION_CONTENTS, PotionContents.EMPTY);
		return contents.is(ModPotions.VOID_POISONED);
	}

	private static Item register(String name, Function<Item.Properties, Item> factory) {
		ResourceKey<Item> key = ResourceKey.create(Registries.ITEM, EchoingVoid.id(name));
		return Registry.register(BuiltInRegistries.ITEM, key, factory.apply(new Item.Properties().setId(key)));
	}

	public static void init() {
		CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.FOOD_AND_DRINKS)
			.register(output -> {
				output.accept(VOID_POISONED_POTION);
				output.accept(VOID_POISONED_SPLASH_POTION);
				output.accept(VOID_POISONED_LINGERING_POTION);
				// Registering ModPotions.VOID_POISONED globally makes vanilla's own
				// generatePotionEffectTypes list it again under the generic minecraft:potion/
				// splash_potion/lingering_potion items - remove those duplicates.
				output.getDisplayStacks().removeIf(ModItems::isVanillaVoidPoisonedContainer);
				output.getSearchTabStacks().removeIf(ModItems::isVanillaVoidPoisonedContainer);
			});
		CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.SPAWN_EGGS)
			.register(output -> {
				output.accept(WATCHLING_SPAWN_EGG);
				output.accept(BLASTLING_SPAWN_EGG);
			});

		// Same container-conversion mechanism vanilla uses for Potion -> Splash -> Lingering.
		FabricPotionBrewingBuilder.BUILD.register(builder -> {
			builder.addContainer(VOID_POISONED_POTION);
			builder.addContainer(VOID_POISONED_SPLASH_POTION);
			builder.addContainer(VOID_POISONED_LINGERING_POTION);
			builder.addContainerRecipe(VOID_POISONED_POTION, Items.GUNPOWDER, VOID_POISONED_SPLASH_POTION);
			builder.addContainerRecipe(VOID_POISONED_SPLASH_POTION, Items.DRAGON_BREATH, VOID_POISONED_LINGERING_POTION);
		});
	}
}
