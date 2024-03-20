package page.codeberg.unix_supremacist.pup.item;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import cpw.mods.fml.common.registry.GameRegistry;
import page.codeberg.unix_supremacist.pup.PUP;
import page.codeberg.unix_supremacist.pup.Tags;
import page.codeberg.unix_supremacist.pup.api.ItemApi;

public class ItemIterator {

    public static MetaItem metaItem = new MetaItem(Tags.MODID);

    public static void load() {
        GameRegistry.registerItem(metaItem, "metaItem");
        for (Items item : Items.values()) {
            metaItem.addItem(item.ordinal(), item.name());
            ItemApi.setItem(item.name(), new ItemStack(metaItem, 1, item.ordinal()));
            OreDictionary.registerOre(item.name(), ItemApi.getItem(item.name()));
            PUP.LOG.info("Registered " + item.name());
        }
        for (PartEnum partVal : PartEnum.values()) {
            HashMap<Integer, Material> mats = new HashMap<>();
            for (MaterialEnum mat : MaterialEnum.values()) if (mat.getMaterial()
                .getParts()
                .contains(partVal.name())) mats.put(mat.ordinal(), mat.material);
            Part part = new Part(Tags.MODID, partVal.name()).setMats(mats);
            GameRegistry.registerItem(part, partVal.name());
            PUP.LOG.info("Registered " + partVal.name() + " Item");
            for (Map.Entry<Integer, Material> mat : mats.entrySet()) {
                OreDictionary.registerOre(
                    partVal.name() + mat.getValue()
                        .getName(),
                    new ItemStack(part, 1, mat.getKey()));
                ItemApi.setItem(
                    partVal.name() + mat.getValue()
                        .getName(),
                    new ItemStack(part, 1, mat.getKey()));
            }
        }
    }
}
