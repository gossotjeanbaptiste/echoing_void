package name.modid.blocks;

import java.util.function.Function;

import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;

import name.modid.EchoingVoid;
import name.modid.block.EndBrewingStandBlock;

public class ModBlocks {
	public static final Block VOID_STONE = register("void_stone",
		properties -> new VoidStoneBlock(properties.strength(50.0f, 1200.0f).requiresCorrectToolForDrops()));
	public static final Block END_BREWING_STAND = register("end_brewing_stand",
		properties -> new EndBrewingStandBlock(properties.mapColor(MapColor.METAL).strength(0.5F).lightLevel(state -> 1).noOcclusion()),
		Rarity.RARE);

	private static Block register(String name, Function<BlockBehaviour.Properties, Block> factory) {
		return register(name, factory, Rarity.COMMON);
	}

	private static Block register(String name, Function<BlockBehaviour.Properties, Block> factory, Rarity rarity) {
		ResourceKey<Block> blockKey = ResourceKey.create(Registries.BLOCK, EchoingVoid.id(name));
		Block block = factory.apply(BlockBehaviour.Properties.of().setId(blockKey));
		Block registered = Registry.register(BuiltInRegistries.BLOCK, blockKey, block);

		ResourceKey<Item> itemKey = ResourceKey.create(Registries.ITEM, EchoingVoid.id(name));
		Registry.register(BuiltInRegistries.ITEM, itemKey,
			new BlockItem(registered, new Item.Properties().setId(itemKey).useBlockDescriptionPrefix().rarity(rarity)));
		return registered;
	}

	public static void init() {
		CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.BUILDING_BLOCKS)
			.register(output -> output.accept(VOID_STONE));
		CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.FUNCTIONAL_BLOCKS)
			.register(output -> output.accept(END_BREWING_STAND));
	}
}
