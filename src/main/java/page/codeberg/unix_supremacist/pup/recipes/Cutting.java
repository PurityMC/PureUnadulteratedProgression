package page.codeberg.unix_supremacist.pup.recipes;

import page.codeberg.unix_supremacist.pup.api.ItemApi;
import techreborn.api.recipe.RecipeHandler;
import techreborn.api.recipe.machines.PlateCuttingMachineRecipe;

public class Cutting {

    public static void load() {
        RecipeHandler.addRecipe(
            new PlateCuttingMachineRecipe(
                ItemApi.getItem("blockObsidian"),
                ItemApi.getItem("plateObsidian", 9),
                100,
                4));
    }
}
