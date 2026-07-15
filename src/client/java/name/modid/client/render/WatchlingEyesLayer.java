package name.modid.client.render;

import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.rendertype.RenderTypes;

import name.modid.EchoingVoid;

public class WatchlingEyesLayer extends EyesLayer<WatchlingRenderState, WatchlingModel> {
	private static final RenderType EYES = RenderTypes.eyes(EchoingVoid.id("textures/mob/watchling_eyes.png"));

	public WatchlingEyesLayer(RenderLayerParent<WatchlingRenderState, WatchlingModel> parent) {
		super(parent);
	}

	@Override
	public void submit(PoseStack poseStack, SubmitNodeCollector collector, int packedLight, WatchlingRenderState state, float yRot, float xRot) {
		// Only skip the eyes during the teleport-hide window (fully unseen). A real Invisibility
		// potion should still leave the eyes showing, classic Enderman-style.
		if (state.teleportHidden) {
			return;
		}
		super.submit(poseStack, collector, packedLight, state, yRot, xRot);
	}

	@Override
	public RenderType renderType() {
		return EYES;
	}
}
