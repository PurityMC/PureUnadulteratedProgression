package page.codeberg.unix_supremacist.pup.recipes;

import static page.codeberg.unix_supremacist.pup.recipes.Util.hammerDict;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapelessOreRecipe;

import page.codeberg.unix_supremacist.pup.PUP;
import page.codeberg.unix_supremacist.pup.api.ItemApi;
import page.codeberg.unix_supremacist.pup.item.MaterialEnum;
import page.codeberg.unix_supremacist.pup.item.PartEnum;
import techreborn.api.recipe.RecipeHandler;
import techreborn.api.recipe.machines.PlateBendingRecipe;
import cpw.mods.fml.common.registry.GameRegistry;

import java.util.Map;

public class Material {

    public static void load() {
        for (Map.Entry<String, ItemStack> entry : ItemApi.getItems().entrySet()) {
            //PUP.LOG.info("Loading " + mat.name() + " Material Recipes");
            if (entry.getKey().startsWith("ingot")) {
                String plate = "plate" + entry.getKey().replaceFirst("^ingot", "");
                if (ItemApi.itemExists(plate)) {
                    GameRegistry.addRecipe(
                        new ShapelessOreRecipe(
                            ItemApi.getItem(plate),
                            new Object[]{hammerDict, entry.getKey(), entry.getKey()}));
                    RecipeHandler.addRecipe(
                        new PlateBendingRecipe(
                            entry.getValue().copy(),
                            null,
                            ItemApi.getItem(plate),
                            null,
                            null,
                            null,
                            100,
                            4));
                    String casing = "casing" + entry.getKey().replaceFirst("^ingot", "");
                    if (ItemApi.itemExists(casing)) GameRegistry.addRecipe(
                        new ShapelessOreRecipe(
                            ItemApi.getItem(casing),
                            new Object[]{hammerDict, plate}));
                }
            }
        }
    }
}
