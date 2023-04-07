package page.codeberg.unix_supremacist.pup.recipes;

import static page.codeberg.unix_supremacist.pup.recipes.Util.*;

import net.minecraftforge.oredict.ShapedOreRecipe;

import page.codeberg.unix_supremacist.pup.api.ItemApi;
import cpw.mods.fml.common.registry.GameRegistry;

public class Crafting {

    public static void load() {
        addBasicCircuitRecipe(ItemApi.getItem("circuitPrimitive", 2), "nuggetCopper", "dustNikolite", "plateIron");
        addBasicCircuitRecipe(ItemApi.getItem("boardBasic", 2), "nuggetGold", "alloyAdvanced", "plateCopper");
        GameRegistry.addRecipe(
                new ShapedOreRecipe(ItemApi.getItem("tubeGlass", 8), "CC", "CC", "CC", 'C', "paneGlassColorless"));
        GameRegistry.addRecipe(
                new ShapedOreRecipe(ItemApi.getItem("tubeVacuum", 1), "T", "C", 'C', "nuggetGold", 'T', "tubeGlass"));
        GameRegistry.addRecipe(
                new ShapedOreRecipe(ItemApi.getItem("capacitor", 8), "AIA", 'A', "plateAluminium", 'I', "plateIron"));
        addBasicCircuitRecipe(ItemApi.getItem("circuitSimple", 1), "nuggetLead", "tubeVacuum", "boardBasic");
        addBasicCircuitRecipe(ItemApi.getItem("circuit", 1), "nuggetTin", "capacitorPrimitive", "boardBasic");
        addBasicCircuitRecipe(ItemApi.getItem("boardAdvanced", 2), "nuggetAluminium", "alloyElite", "plateGold");
        addAdvancedCircuitRecipe(
                ItemApi.getItem("circuitAdvanced", 1),
                "nuggetSilver",
                "capacitorBasic",
                "boardAdvanced");
        addAdvancedCircuitRecipe(ItemApi.getItem("circuitComplex", 1), "nuggetGold", "capacitorBasic", "boardAdvanced");
        addAdvancedCircuitRecipe(
                ItemApi.getItem("circuitElite", 1),
                "nuggetRuby",
                "capacitorAdvanced",
                "boardAdvanced");
        addAdvancedCircuitRecipe(
                ItemApi.getItem("circuitData", 1),
                "dustTinyOlivine",
                "capacitorBasic",
                "boardAdvanced");
        addBasicCircuitRecipe(ItemApi.getItem("boardMaster", 2), "nuggetAluminium", "alloyUltimate", "platePlatinum");
        addBasicCircuitRecipe(ItemApi.getItem("circuitMaster", 1), "nuggetPlatinum", "capacitorEnder", "boardMaster");
        addBasicCircuitRecipe(ItemApi.getItem("circuit3D", 1), "nuggetElectrum", "capacitorEnder", "boardMaster");
        GameRegistry.addRecipe(
                new ShapedOreRecipe(
                        ItemApi.getItem("partAdvanced", 1),
                        "PLP",
                        "RGB",
                        "PYP",
                        'P',
                        "plateAluminium",
                        'L',
                        "dyeLime",
                        'R',
                        "dyeRed",
                        'G',
                        "paneGlass",
                        'B',
                        "dyeBlue",
                        'Y',
                        "dustGlowstone"));
    }
}
