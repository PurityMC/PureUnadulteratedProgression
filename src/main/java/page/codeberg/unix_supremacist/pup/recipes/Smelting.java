package page.codeberg.unix_supremacist.pup.recipes;

import cpw.mods.fml.common.registry.GameRegistry;
import page.codeberg.unix_supremacist.pup.api.ItemApi;

public class Smelting {

    public static void load() {
        // make part of mat system
        GameRegistry.addSmelting(ItemApi.getItem("dustIron", 1), ItemApi.getItem("ingotIron", 1), 1F);
        GameRegistry.addSmelting(ItemApi.getItem("dustGold", 1), ItemApi.getItem("ingotGold", 1), 1F);
    }
}
