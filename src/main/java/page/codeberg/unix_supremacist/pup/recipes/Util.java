package page.codeberg.unix_supremacist.pup.recipes;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;

import page.codeberg.unix_supremacist.pup.PUP;
import cpw.mods.fml.common.registry.GameRegistry;

public class Util {

    public static String hammerDict = "craftingToolHardHammer";
    public static List<IRecipe> recipesToRemove = new ArrayList<>();

    static void addBasicCircuitRecipe(ItemStack output, String nugget, String item, String plate) {
        GameRegistry.addRecipe(new ShapedOreRecipe(output, "CCC", "RIR", "CCC", 'C', nugget, 'R', item, 'I', plate));
    }

    static void addAdvancedCircuitRecipe(ItemStack output, String nugget, String item, String plate) {
        GameRegistry.addRecipe(new ShapedOreRecipe(output, "CRC", "CIC", "CRC", 'C', nugget, 'R', item, 'I', plate));
    }

    public static void removeFirstRecipeFor(Item item, int meta) {
        for (Object recipe : CraftingManager.getInstance().getRecipeList()) if (recipe != null) {
            ItemStack stack = ((IRecipe) recipe).getRecipeOutput();
            if (stack != null && stack.getItem() == item
                    && (meta == OreDictionary.WILDCARD_VALUE || meta == stack.getItemDamage())) {
                recipesToRemove.add((IRecipe) recipe);
                return;
            }
        }
    }

    public static void removeALLRecipesFor(Item item, int meta) {
        for (Object recipe : CraftingManager.getInstance().getRecipeList()) if (recipe != null) {
            ItemStack stack = ((IRecipe) recipe).getRecipeOutput();
            if (stack != null && stack.getItem() == item
                    && (meta == OreDictionary.WILDCARD_VALUE || meta == stack.getItemDamage()))
                recipesToRemove.add((IRecipe) recipe);
        }
    }

    public static void bulkRemoveByRecipe() {
        ArrayList<IRecipe> tList = (ArrayList<IRecipe>) CraftingManager.getInstance().getRecipeList();
        PUP.LOG.info("BulkRemoveByRecipe: tList: " + tList.size() + " toRemove: " + recipesToRemove.size());
        tList.removeIf(recipesToRemove::contains);
    }
}
