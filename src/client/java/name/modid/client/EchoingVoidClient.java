package name.modid.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.ModelLayerRegistry;

import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;

import name.modid.client.render.BlastlingFlameModel;
import name.modid.client.render.BlastlingModel;
import name.modid.client.render.BlastlingRenderer;
import name.modid.client.render.WatchlingModel;
import name.modid.client.render.WatchlingRenderer;
import name.modid.entity.ModEntities;

public class EchoingVoidClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		// This entrypoint is suitable for setting up client-specific logic, such as rendering.
		ModelLayerRegistry.registerModelLayer(WatchlingRenderer.LAYER, WatchlingModel::createBodyLayer);
		EntityRenderers.register(ModEntities.WATCHLING, WatchlingRenderer::new);

		ModelLayerRegistry.registerModelLayer(BlastlingRenderer.LAYER, BlastlingModel::createBodyLayer);
		ModelLayerRegistry.registerModelLayer(BlastlingRenderer.FLAME_LAYER, BlastlingFlameModel::createBodyLayer);
		EntityRenderers.register(ModEntities.BLASTLING, BlastlingRenderer::new);
		// Full-bright flat billboard, like a snowball/pearl - it's magic goo, not a physical object.
		EntityRenderers.register(ModEntities.BLASTLING_GOO, context -> new ThrownItemRenderer<>(context, 0.9F, true));
	}
}