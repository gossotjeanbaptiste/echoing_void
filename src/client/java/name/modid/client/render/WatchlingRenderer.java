package name.modid.client.render;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.resources.Identifier;

import name.modid.EchoingVoid;
import name.modid.entity.WatchlingEntity;

public class WatchlingRenderer extends HumanoidMobRenderer<WatchlingEntity, WatchlingRenderState, WatchlingModel> {
	public static final ModelLayerLocation LAYER = new ModelLayerLocation(EchoingVoid.id("watchling"), "main");
	private static final Identifier TEXTURE = EchoingVoid.id("textures/mob/watchling.png");

	public WatchlingRenderer(EntityRendererProvider.Context context) {
		super(context, new WatchlingModel(context.bakeLayer(LAYER)), 0.5F);
		this.addLayer(new WatchlingEyesLayer(this));
	}

	@Override
	public WatchlingRenderState createRenderState() {
		return new WatchlingRenderState();
	}

	@Override
	protected boolean isBodyVisible(WatchlingRenderState state) {
		return super.isBodyVisible(state);
	}

	@Override
	public Identifier getTextureLocation(WatchlingRenderState state) {
		return TEXTURE;
	}
}
