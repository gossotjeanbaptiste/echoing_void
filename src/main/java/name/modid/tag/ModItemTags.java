package name.modid.tag;

import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

import name.modid.EchoingVoid;

public class ModItemTags {
	// Empty until the void-touched ingot exists - repair recipes will populate this tag then.
	public static final TagKey<Item> VOID_TOUCHED_REPAIR =
		TagKey.create(Registries.ITEM, EchoingVoid.id("void_touched_repair"));
}
