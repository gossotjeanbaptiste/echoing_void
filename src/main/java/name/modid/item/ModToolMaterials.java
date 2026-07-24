package name.modid.item;

import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.ToolMaterial;

import name.modid.tag.ModItemTags;

public class ModToolMaterials {
	// Shared by the Echoing Void unique weapons. The void-touched ingot will be a smithing
	// upgrade on top of netherite (diamond -> netherite +30%), so durability follows the same
	// jump from netherite's 2031 until the ingot/recipe exist.
	public static final ToolMaterial VOID_TOUCHED = new ToolMaterial(
		BlockTags.INCORRECT_FOR_NETHERITE_TOOL,
		3000,
		9.0F,
		5.0F,
		20,
		ModItemTags.VOID_TOUCHED_REPAIR);
}
