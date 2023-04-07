package page.codeberg.unix_supremacist.pup.recipes;

import page.codeberg.unix_supremacist.pup.PUP;

public class Recipes {

    public static void load() {
        // removeFirstRecipeFor(GameRegistry.findItem("minecraft", "wheat"), OreDictionary.WILDCARD_VALUE);
        // removeFirstRecipeFor(GameRegistry.findItem("minecraft", "map"), OreDictionary.WILDCARD_VALUE);
        // GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ItemIterator.metaItem, 1,
        // Items.CIRCUIT_PRIMITIVE.ordinal()), new Object[]{copper, copper, copper}));

        PUP.LOG.info("Loading Recipes");
        PUP.LOG.info("Material Recipes Started");
        Material.load();
        PUP.LOG.info("Material Recipes Finished");
        PUP.LOG.info("Crafting Recipes Started");
        Crafting.load();
        PUP.LOG.info("Crafting Recipes Finished");
        PUP.LOG.info("Smelting Recipes Started");
        Smelting.load();
        PUP.LOG.info("Smelting Recipes Finished");
        PUP.LOG.info("Assembler Recipes Started");
        Assembler.load();
        PUP.LOG.info("Assembler Recipes Finished");
        PUP.LOG.info("Freezer Recipes Started");
        Freezer.load();
        PUP.LOG.info("Freezer Recipes Finished");
        PUP.LOG.info("Cutting Recipes Started");
        Cutting.load();
        PUP.LOG.info("Cutting Recipes Finished");
    }
}
