package name.modid.worldgen;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import name.modid.EchoingVoid;

public class ModWorldGen {
	private static final ResourceKey<PlacedFeature> ENDERITE_DEBRIS_LARGE =
		ResourceKey.create(Registries.PLACED_FEATURE, EchoingVoid.id("enderite_debris_large"));
	private static final ResourceKey<PlacedFeature> ENDERITE_DEBRIS_SMALL =
		ResourceKey.create(Registries.PLACED_FEATURE, EchoingVoid.id("enderite_debris_small"));

	public static void init() {
		// Same pairing (large vein + small vein, each placed once per chunk, same
		// discard-on-air-exposure/height-range shape) that vanilla uses for ancient debris in the
		// nether - only in the outer End islands, not the central main island.
		BiomeModifications.addFeature(
			BiomeSelectors.includeByKey(Biomes.END_HIGHLANDS, Biomes.END_MIDLANDS, Biomes.END_BARRENS, Biomes.SMALL_END_ISLANDS),
			GenerationStep.Decoration.UNDERGROUND_DECORATION,
			ENDERITE_DEBRIS_LARGE);
		BiomeModifications.addFeature(
			BiomeSelectors.includeByKey(Biomes.END_HIGHLANDS, Biomes.END_MIDLANDS, Biomes.END_BARRENS, Biomes.SMALL_END_ISLANDS),
			GenerationStep.Decoration.UNDERGROUND_DECORATION,
			ENDERITE_DEBRIS_SMALL);
	}
}
