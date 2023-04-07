package page.codeberg.unix_supremacist.pup.recipes;

import page.codeberg.unix_supremacist.pup.api.ItemApi;
import techreborn.api.recipe.RecipeHandler;
import techreborn.api.recipe.machines.AssemblingMachineRecipe;

public class Assembler {

    public static void load() {
        RecipeHandler.addRecipe(
                new AssemblingMachineRecipe(
                        ItemApi.getItem("ingotInductive", 1),
                        ItemApi.getItem("dustGlowstone", 1),
                        ItemApi.getItem("craftingSuperconductor", 1),
                        200,
                        16));
    }
}
