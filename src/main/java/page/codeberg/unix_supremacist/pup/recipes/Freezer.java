package page.codeberg.unix_supremacist.pup.recipes;

import page.codeberg.unix_supremacist.pup.api.ItemApi;
import techreborn.api.recipe.RecipeHandler;
import techreborn.api.recipe.machines.VacuumFreezerRecipe;

public class Freezer {

    public static void load() {
        RecipeHandler.addRecipe(
                new VacuumFreezerRecipe(
                        ItemApi.getItem("ingotHotTungstensteel"),
                        ItemApi.getItem("ingotTungstensteel"),
                        440,
                        128));
    }
}
