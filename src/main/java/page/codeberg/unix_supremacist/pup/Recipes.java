package page.codeberg.unix_supremacist.pup;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;

import java.util.ArrayList;
import java.util.List;

public class Recipes {
    public static World w;
    public static List<IRecipe> recipesToRemove = new ArrayList<>();
    public static void load(){
        //removeFirstRecipeFor(GameRegistry.findItem("minecraft", "wheat"), OreDictionary.WILDCARD_VALUE);
        removeFirstRecipeFor(GameRegistry.findItem("minecraft", "map"), OreDictionary.WILDCARD_VALUE);
    }

    public static void removeFirstRecipeFor(Item item, int meta) {
        for (Object recipe : CraftingManager.getInstance().getRecipeList()) if (recipe != null) {
            ItemStack stack = ((IRecipe) recipe).getRecipeOutput();
            if (stack != null && stack.getItem() == item && (meta == OreDictionary.WILDCARD_VALUE || meta == stack.getItemDamage())) {
                recipesToRemove.add((IRecipe) recipe);
                return;
            }
        }
    }

    public static void removeALLRecipesFor(Item item, int meta) {
        for (Object recipe : CraftingManager.getInstance().getRecipeList()) if (recipe != null) {
            ItemStack stack = ((IRecipe) recipe).getRecipeOutput();
            if (stack != null && stack.getItem() == item && (meta == OreDictionary.WILDCARD_VALUE || meta == stack.getItemDamage())) recipesToRemove.add((IRecipe) recipe);
        }
    }

    public static void bulkRemoveByRecipe() {
        ArrayList<IRecipe> tList = (ArrayList<IRecipe>) CraftingManager.getInstance().getRecipeList();
        PUP.LOG.info("BulkRemoveByRecipe: tList: " + tList.size() + " toRemove: " + recipesToRemove.size());
        tList.removeIf(recipesToRemove::contains);
    }
}
