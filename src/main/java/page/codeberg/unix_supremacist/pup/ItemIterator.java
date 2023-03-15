package page.codeberg.unix_supremacist.pup;

import cpw.mods.fml.common.registry.GameRegistry;
import page.codeberg.unix_supremacist.pup.material.PartEnum;

public class ItemIterator {

    public static MetaItem metaItem = new MetaItem(Tags.MODID);

    public static void load() {
        for (Items item : Items.values()) metaItem.addItem(item.ordinal(), item.name());
        GameRegistry.registerItem(metaItem, "metaItem");
        for (PartEnum part : PartEnum.values()) GameRegistry.registerItem(part.getPart(), part.name());
    }
}
