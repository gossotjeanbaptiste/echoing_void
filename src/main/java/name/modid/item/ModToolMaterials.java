package name.modid.item;

import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.ToolMaterial;

import name.modid.tag.ModItemTags;

public class ModToolMaterials {
	// Shared by the Echoing Void unique weapons - on par with netherite until the void-touched
	// ingot exists to back its own tier and repair recipe.
	public static final ToolMaterial VOID_TOUCHED = new ToolMaterial(
		BlockTags.INCORRECT_FOR_NETHERITE_TOOL,
		2031,
		9.0F,
		5.0F,
		20,
		ModItemTags.VOID_TOUCHED_REPAIR);
}
