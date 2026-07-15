package name.modid.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.ModelLayerRegistry;

import net.minecraft.client.renderer.entity.EntityRenderers;

import name.modid.client.render.WatchlingModel;
import name.modid.client.render.WatchlingRenderer;
import name.modid.entity.ModEntities;

public class EchoingVoidClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		// This entrypoint is suitable for setting up client-specific logic, such as rendering.
		ModelLayerRegistry.registerModelLayer(WatchlingRenderer.LAYER, WatchlingModel::createBodyLayer);
		EntityRenderers.register(ModEntities.WATCHLING, WatchlingRenderer::new);
	}
}