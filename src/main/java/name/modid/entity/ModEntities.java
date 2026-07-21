package name.modid.entity;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityType;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.levelgen.Heightmap;

import name.modid.EchoingVoid;

public class ModEntities {
	public static final EntityType<WatchlingEntity> WATCHLING = register("watchling",
		FabricEntityType.Builder.createMob(WatchlingEntity::new, MobCategory.MONSTER,
				mob -> mob.spawnPlacement(SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules))
			.sized(0.95F, 1.95F)
			.eyeHeight(1.7F)
			.clientTrackingRange(8));

	public static final EntityType<BlastlingEntity> BLASTLING = register("blastling",
		FabricEntityType.Builder.createMob(BlastlingEntity::new, MobCategory.MONSTER,
				mob -> mob.spawnPlacement(SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules))
			.sized(0.8F, 1.95F)
			.eyeHeight(1.7F)
			.clientTrackingRange(8));

	public static final EntityType<BlastlingGooEntity> BLASTLING_GOO = register("blastling_goo",
		EntityType.Builder.<BlastlingGooEntity>of(BlastlingGooEntity::new, MobCategory.MISC)
			.noLootTable()
			// Same as vanilla's fishing_bobber: an owner-tied projectile, not something players
			// should be able to /summon directly.
			.noSummon()
			.sized(0.25F, 0.25F)
			.clientTrackingRange(4)
			.updateInterval(10));

	private static <T extends Entity> EntityType<T> register(String name, EntityType.Builder<T> builder) {
		ResourceKey<EntityType<?>> key = ResourceKey.create(Registries.ENTITY_TYPE, EchoingVoid.id(name));
		return Registry.register(BuiltInRegistries.ENTITY_TYPE, key, builder.build(key));
	}

	public static void init() {
		FabricDefaultAttributeRegistry.register(WATCHLING, WatchlingEntity.createAttributes());
		FabricDefaultAttributeRegistry.register(BLASTLING, BlastlingEntity.createAttributes());
		// Natural spawns only in the End, same footing as Endermen (bumped well above vanilla rates).
		BiomeModifications.addSpawn(BiomeSelectors.foundInTheEnd(), MobCategory.MONSTER, WATCHLING, 160, 1, 5);
		BiomeModifications.addSpawn(BiomeSelectors.foundInTheEnd(), MobCategory.MONSTER, BLASTLING, 120, 1, 3);
	}
}
