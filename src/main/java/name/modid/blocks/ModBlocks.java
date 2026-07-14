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
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;

import name.modid.EchoingVoid;

public class ModBlocks {
	public static final Block VOID_STONE = register("void_stone",
		properties -> new VoidStoneBlock(properties.strength(50.0f, 1200.0f).requiresCorrectToolForDrops()));

	private static Block register(String name, Function<BlockBehaviour.Properties, Block> factory) {
		ResourceKey<Block> blockKey = ResourceKey.create(Registries.BLOCK, EchoingVoid.id(name));
		Block block = factory.apply(BlockBehaviour.Properties.of().setId(blockKey));
		Block registered = Registry.register(BuiltInRegistries.BLOCK, blockKey, block);

		ResourceKey<Item> itemKey = ResourceKey.create(Registries.ITEM, EchoingVoid.id(name));
		Registry.register(BuiltInRegistries.ITEM, itemKey, new BlockItem(registered, new Item.Properties().setId(itemKey).useBlockDescriptionPrefix()));
		return registered;
	}

	public static void init() {
		CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.BUILDING_BLOCKS)
			.register(output -> output.accept(VOID_STONE));
	}
}
