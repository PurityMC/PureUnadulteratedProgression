package page.codeberg.unix_supremacist.pup;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import org.apache.commons.lang3.StringUtils;
import page.codeberg.unix_supremacist.pup.material.MaterialEnum;
import page.codeberg.unix_supremacist.pup.material.PartEnum;

import java.util.ArrayList;
import java.util.List;

public class Recipes {

    public static World w;
    public static List<IRecipe> recipesToRemove = new ArrayList<>();
    public static String hammerDict = "craftingToolHardHammer";

    public static void load() {
        // removeFirstRecipeFor(GameRegistry.findItem("minecraft", "wheat"), OreDictionary.WILDCARD_VALUE);
        // removeFirstRecipeFor(GameRegistry.findItem("minecraft", "map"), OreDictionary.WILDCARD_VALUE);
        // GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ItemIterator.metaItem, 1,
        // Items.CIRCUIT_PRIMITIVE.ordinal()), new Object[]{copper, copper, copper}));
        addBasicCircuitRecipe(Items.CIRCUIT_PRIMITIVE.ordinal(), 2, "nuggetCopper", "dustNikolite", "plateIron");
        addBasicCircuitRecipe(Items.BOARD_BASIC.ordinal(), 2, "nuggetGold", "alloyAdvanced", "plateCopper");
        GameRegistry.addRecipe(
                new ShapedOreRecipe(
                        new ItemStack(ItemIterator.metaItem, 8, Items.GLASS_TUBE.ordinal()),
                        "CC",
                        "CC",
                        "CC",
                        'C',
                        "paneGlassColorless"));
        GameRegistry.addRecipe(
                new ShapedOreRecipe(
                        new ItemStack(ItemIterator.metaItem, 1, Items.VACUUM_TUBE.ordinal()),
                        "T",
                        "C",
                        'C',
                        "nuggetGold",
                        'T',
                        "tubeGlass"));
        GameRegistry.addRecipe(
                new ShapedOreRecipe(
                        new ItemStack(ItemIterator.metaItem, 8, Items.CAPACITOR.ordinal()),
                        "AIA",
                        'A',
                        "plateAluminium",
                        'I',
                        "plateIron"));
        addBasicCircuitRecipe(Items.CIRCUIT_SIMPLE.ordinal(), 1, "nuggetLead", "tubeVacuum", "boardBasic");
        addBasicCircuitRecipe(Items.CIRCUIT.ordinal(), 1, "nuggetTin", "capacitorPrimitive", "boardBasic");
        addBasicCircuitRecipe(Items.BOARD_ADVANCED.ordinal(), 2, "nuggetAluminium", "alloyElite", "plateGold");
        addAdvancedCircuitRecipe(
                Items.CIRCUIT_ADVANCED.ordinal(),
                1,
                "nuggetSilver",
                "capacitorBasic",
                "boardAdvanced");
        addAdvancedCircuitRecipe(Items.CIRCUIT_COMPLEX.ordinal(), 1, "nuggetGold", "capacitorBasic", "boardAdvanced");
        addAdvancedCircuitRecipe(Items.CIRCUIT_ELITE.ordinal(), 1, "nuggetRuby", "capacitorAdvanced", "boardAdvanced");
        addAdvancedCircuitRecipe(Items.CIRCUIT_DATA.ordinal(), 1, "dustTinyOlivine", "capacitorBasic", "boardAdvanced");
        addBasicCircuitRecipe(Items.BOARD_MASTER.ordinal(), 2, "nuggetAluminium", "alloyUltimate", "platePlatinum");
        addBasicCircuitRecipe(Items.CIRCUIT_MASTER.ordinal(), 1, "nuggetPlatinum", "capacitorEnder", "boardMaster");
        addBasicCircuitRecipe(Items.CIRCUIT_3D.ordinal(), 1, "nuggetElectrum", "capacitorEnder", "boardMaster");
        for (MaterialEnum mat : MaterialEnum.values()) {
            String orename = StringUtils.capitalize(mat.name().toLowerCase());
            if (PartEnum.ingot.getPart().getMats().containsKey(mat.ordinal()))
                if (PartEnum.plate.getPart().getMats().containsKey(mat.ordinal())) {
                    GameRegistry.addRecipe(
                            new ShapelessOreRecipe(
                                    new ItemStack(PartEnum.plate.getPart(), 1, mat.ordinal()),
                                    new Object[] { hammerDict, "ingot" + orename, "ingot" + orename }));
                    if (PartEnum.casing.getPart().getMats().containsKey(mat.ordinal())) GameRegistry.addRecipe(
                            new ShapelessOreRecipe(
                                    new ItemStack(PartEnum.casing.getPart(), 2, mat.ordinal()),
                                    new Object[] { hammerDict, "plate" + orename }));
                }
        }
    }

    static void addBasicCircuitRecipe(int meta, int count, String nugget, String item, String plate) {
        GameRegistry.addRecipe(
                new ShapedOreRecipe(
                        new ItemStack(ItemIterator.metaItem, count, meta),
                        "CCC",
                        "RIR",
                        "CCC",
                        'C',
                        nugget,
                        'R',
                        item,
                        'I',
                        plate));
    }

    static void addAdvancedCircuitRecipe(int meta, int count, String nugget, String item, String plate) {
        GameRegistry.addRecipe(
                new ShapedOreRecipe(
                        new ItemStack(ItemIterator.metaItem, count, meta),
                        "CRC",
                        "CIC",
                        "CRC",
                        'C',
                        nugget,
                        'R',
                        item,
                        'I',
                        plate));
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
