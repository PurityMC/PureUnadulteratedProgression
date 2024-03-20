package techreborn.init;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.oredict.OreDictionary;

import page.codeberg.unix_supremacist.pup.api.ItemApi;
import reborncore.common.util.CraftingHelper;
import reborncore.common.util.OreUtil;
import techreborn.Core;
import techreborn.api.TechRebornAPI;
import techreborn.api.reactor.FusionReactorRecipe;
import techreborn.api.reactor.FusionReactorRecipeHelper;
import techreborn.api.recipe.RecipeHandler;
import techreborn.api.recipe.machines.*;
import techreborn.blocks.BlockOre;
import techreborn.config.ConfigTechReborn;
import techreborn.items.ItemCells;

public class ModRecipes {

    public static ConfigTechReborn config;

    public static void init() {
        addGeneralShapedRecipes();
        addMachineRecipes();
        addUUrecipes();
        addAlloySmelterRecipes();
        addIndustrialCentrifugeRecipes();
        addChemicalReactorRecipes();
        addIndustrialElectrolyzerRecipes();
        addIndustrialSawmillRecipes();
        addBlastFurnaceRecipes();
        addIndustrialGrinderRecipes();
        addReactorRecipes();
    }

    static void addReactorRecipes() {
        FusionReactorRecipeHelper.registerRecipe(
            new FusionReactorRecipe(
                ItemCells.getCellByName("tritium"),
                ItemCells.getCellByName("deuterium"),
                ItemCells.getCellByName("helium"),
                40000000,
                32768,
                1024));
        FusionReactorRecipeHelper.registerRecipe(
            new FusionReactorRecipe(
                ItemCells.getCellByName("tritium"),
                ItemCells.getCellByName("deuterium"),
                ItemCells.getCellByName("helium3"),
                60000000,
                32768,
                2048));
        FusionReactorRecipeHelper.registerRecipe(
            new FusionReactorRecipe(
                ItemCells.getCellByName("wolframium"),
                ItemCells.getCellByName("Berylium"),
                ItemApi.getItem("dustPlatinum"),
                80000000,
                -2048,
                1024));
    }

    static void addGeneralShapedRecipes() {
        CraftingHelper.addShapedOreRecipe(
            ItemApi.getItem("circuitData"),
            "EEE",
            "ECE",
            "EEE",
            'E',
            new ItemStack(Items.emerald),
            'C',
            ItemApi.getItem("boardBasic"));

        CraftingHelper.addShapedOreRecipe(
            ItemApi.getItem("grindingHeadDiamond", 4),
            "DSD",
            "S S",
            "DSD",
            'D',
            "dustDiamond",
            'S',
            "ingotSteel");

        CraftingHelper.addShapedOreRecipe(
            ItemApi.getItem("nakCoolantTriple"),
            "AAA",
            "AMA",
            "AAA",
            'A',
            "ingotAluminium",
            'M',
            ItemApi.getItem("heliumCoolantSix"));

        CraftingHelper.addShapedOreRecipe(
            new ItemStack(ModBlocks.Supercondensator),
            "EOE",
            "SAS",
            "EOE",
            'E',
            ItemApi.getItem("circuitMaster"),
            'O',
            ModItems.lapotronicOrb,
            'S',
            ItemApi.getItem("craftingSuperconductor"),
            'A',
            ModBlocks.HighAdvancedMachineBlock);

        CraftingHelper.addShapedOreRecipe(
            ItemApi.getItem("sawBladeDiamond"),
            "DSD",
            "S S",
            "DSD",
            'S',
            "plateSteel",
            'D',
            "dustDiamond");

        CraftingHelper.addShapedOreRecipe(
            ItemApi.getItem("grindingHeadTungsten", 2),
            "TST",
            "SBS",
            "TST",
            'T',
            "plateTungsten",
            'S',
            "plateSteel",
            'B',
            "blockSteel");

        CraftingHelper.addShapedOreRecipe(
            new ItemStack(ModItems.cloakingDevice),
            "CIC",
            "IOI",
            "CIC",
            'C',
            "ingotChrome",
            'I',
            "plateIridium",
            'O',
            new ItemStack(ModItems.lapotronicOrb));

        CraftingHelper.addShapedOreRecipe(
            new ItemStack(ModItems.rockCutter),
            "DT ",
            "DT ",
            "DCB",
            'D',
            ItemApi.getItem("rockCutterBlade"),
            'T',
            "ingotTitanium",
            'C',
            ItemApi.getItem("boardBasic"),
            'B',
            new ItemStack(Items.diamond));

        CraftingHelper.addShapedOreRecipe(
            ItemApi.getItem("rockCutterBlade"),
            "SDS",
            "SDS",
            "SDS",
            'D',
            new ItemStack(Items.diamond),
            'S',
            "ingotSteel");

        CraftingHelper.addShapedOreRecipe(
            ItemApi.getItem("coilNichrome"),
            " N ",
            "NCN",
            " N ",
            'N',
            "ingotNickel",
            'C',
            "ingotChrome");

        CraftingHelper.addShapedOreRecipe(
            ItemApi.getItem("coilKanthal"),
            "III",
            "CAA",
            "AAA",
            'I',
            "ingotSteel",
            'C',
            "ingotChrome",
            'A',
            "ingotAluminium");

        CraftingHelper.addShapedOreRecipe(
            ItemApi.getItem("heliumCoolantSimple"),
            " T ",
            "TCT",
            " T ",
            'T',
            "ingotTin",
            'C',
            ItemCells.getCellByName("helium", 1, true));

        CraftingHelper.addShapedOreRecipe(
            ItemApi.getItem("heliumCoolantSimple"),
            " T ",
            "TCT",
            " T ",
            'T',
            "ingotTin",
            'C',
            ItemCells.getCellByName("helium", 1, false));

        CraftingHelper.addShapedOreRecipe(
            ItemApi.getItem("heliumCoolantTriple"),
            "TTT",
            "CCC",
            "TTT",
            'T',
            "ingotTin",
            'C',
            ItemApi.getItem("heliumCoolantSimple"));

        CraftingHelper.addShapedOreRecipe(
            ItemApi.getItem("heliumCoolantSix"),
            "THT",
            "TCT",
            "THT",
            'T',
            "ingotTin",
            'C',
            "ingotCopper",
            'H',
            ItemApi.getItem("heliumCoolantTriple"));

        CraftingHelper.addShapedOreRecipe(
            ItemApi.getItem("nakCoolantTriple"),
            "TTT",
            "CCC",
            "TTT",
            'T',
            "ingotTin",
            'C',
            ItemApi.getItem("nakCoolantSimple"));

        CraftingHelper.addShapedOreRecipe(
            ItemApi.getItem("nakCoolantSix"),
            "THT",
            "TCT",
            "THT",
            'T',
            "ingotTin",
            'C',
            "ingotCopper",
            'H',
            ItemApi.getItem("nakCoolantTriple"));

        CraftingHelper.addShapedOreRecipe(
            new ItemStack(ModBlocks.Aesu),
            "LLL",
            "LCL",
            "LLL",
            'L',
            new ItemStack(ModItems.lapotronicOrb),
            'C',
            new ItemStack(ModBlocks.ComputerCube));

        CraftingHelper.addShapedOreRecipe(
            new ItemStack(ModBlocks.Idsu),
            "PAP",
            "ACA",
            "PAP",
            'P',
            ItemApi.getItem("plateIridium"),
            'C',
            new ItemStack(Blocks.ender_chest),
            'A',
            new ItemStack(ModBlocks.Aesu));

        CraftingHelper.addShapedOreRecipe(
            new ItemStack(ModBlocks.FusionControlComputer),
            "CCC",
            "PTP",
            "CCC",
            'P',
            new ItemStack(ModBlocks.ComputerCube),
            'T',
            new ItemStack(ModBlocks.FusionCoil),
            'C',
            ItemApi.getItem("circuitMaster"));

        CraftingHelper.addShapedOreRecipe(
            new ItemStack(ModBlocks.LightningRod),
            "CAC",
            "ACA",
            "CAC",
            'A',
            new ItemStack(ModBlocks.MachineCasing, 1, 2),
            'S',
            ItemApi.getItem("craftingSuperconductor"),
            'C',
            ItemApi.getItem("circuitMaster"));

        CraftingHelper.addShapedOreRecipe(
            new ItemStack(ModBlocks.FusionCoil),
            "CSC",
            "NAN",
            "CRC",
            'A',
            new ItemStack(ModBlocks.MachineCasing, 1, 2),
            'N',
            ItemApi.getItem("coilNichrome"),
            'C',
            ItemApi.getItem("circuitMaster"),
            'S',
            ItemApi.getItem("craftingSuperconductor"),
            'R',
            ItemApi.getItem("iridiumNeutronReflector"));

        CraftingHelper.addShapedOreRecipe(
            ItemApi.getItem("iridiumNeutronReflector"),
            "PPP",
            "PIP",
            "PPP",
            'P',
            ItemApi.getItem("thickNeutronReflector"),
            'I',
            "ingotIridium");

        CraftingHelper.addShapedOreRecipe(
            ItemApi.getItem("thickNeutronReflector"),
            " P ",
            "PCP",
            " P ",
            'P',
            ItemApi.getItem("neutronReflector"),
            'C',
            ItemCells.getCellByName("Berylium"));

        CraftingHelper.addShapedOreRecipe(
            ItemApi.getItem("neutronReflector"),
            "TCT",
            "CPC",
            "TCT",
            'T',
            "dustTin",
            'C',
            "dustCoal",
            'P',
            "plateCopper");

        CraftingHelper.addShapedOreRecipe(
            ItemApi.getItem("circuitQuantum"),
            "SSS",
            "SCS",
            "SSS",
            'S',
            ItemApi.getItem("circuitData"),
            'C',
            ItemApi.getItem("circuitBio"));

        CraftingHelper.addShapedOreRecipe(
            ItemApi.getItem("computerMonitor"),
            "AGA",
            "RCB",
            "AYA",
            'A',
            "plateAluminium",
            'G',
            new ItemStack(Items.dye, 1, 2),
            'R',
            new ItemStack(Items.dye, 1, 1),
            'B',
            new ItemStack(Items.dye, 1, 5),
            'Y',
            new ItemStack(Items.glowstone_dust));

        Core.logHelper.info("Shapped Recipes Added");
    }

    public static String capitalizeFirstLetter(String original) {
        if (original.length() == 0) return original;
        return original.substring(0, 1)
            .toUpperCase() + original.substring(1);
    }

    static void addMachineRecipes() {
        CraftingHelper.addShapedOreRecipe(
            new ItemStack(ModBlocks.quantumTank),
            "EPE",
            "PCP",
            "EPE",
            'P',
            "platePlatinum",
            'E',
            "circuitMaster",
            'C',
            ModBlocks.quantumChest);

        CraftingHelper.addShapedOreRecipe(
            new ItemStack(ModBlocks.digitalChest),
            "PPP",
            "PDP",
            "PCP",
            'P',
            "plateAluminium",
            'D',
            ItemApi.getItem("circuitQuantum"),
            'C',
            ItemApi.getItem("computerMonitor"));

        CraftingHelper.addShapedOreRecipe(
            new ItemStack(ModBlocks.digitalChest),
            "PPP",
            "PDP",
            "PCP",
            'P',
            "plateSteel",
            'D',
            ItemApi.getItem("circuitQuantum"),
            'C',
            ItemApi.getItem("computerMonitor"));

        CraftingHelper.addShapedOreRecipe(
            new ItemStack(ModBlocks.AlloySmelter),
            "IHI",
            "CFC",
            "IHI",
            'I',
            "plateInvar",
            'C',
            "circuitBasic",
            'H',
            ItemApi.getItem("coilCupronickel"),
            'F',
            ModBlocks.AlloyFurnace);

        CraftingHelper.addShapedOreRecipe(
            new ItemStack(ModBlocks.LesuStorage),
            "LLL",
            "LCL",
            "LLL",
            'L',
            "blockLapis",
            'C',
            "circuitBasic");

        TechRebornAPI.addRollingOreMachinceRecipe(
            ItemApi.getItem("coilCupronickel"),
            "NCN",
            "C C",
            "NCN",
            'N',
            "ingotCupronickel",
            'C',
            "ingotCopper");
    }

    static void addAlloySmelterRecipes() {
        // Bronze
        RecipeHandler.addRecipe(
            new AlloySmelterRecipe(
                ItemApi.getItem("ingotCopper", 3),
                ItemApi.getItem("ingotTin", 1),
                ItemApi.getItem("ingotBronze", 4),
                200,
                16));
        RecipeHandler.addRecipe(
            new AlloySmelterRecipe(
                ItemApi.getItem("ingotCopper", 3),
                ItemApi.getItem("dustTin", 1),
                ItemApi.getItem("ingotBronze", 4),
                200,
                16));
        RecipeHandler.addRecipe(
            new AlloySmelterRecipe(
                ItemApi.getItem("dustCopper", 3),
                ItemApi.getItem("ingotTin", 1),
                ItemApi.getItem("ingotBronze", 4),
                200,
                16));
        RecipeHandler.addRecipe(
            new AlloySmelterRecipe(
                ItemApi.getItem("dustCopper", 3),
                ItemApi.getItem("dustTin", 1),
                ItemApi.getItem("ingotBronze", 4),
                200,
                16));

        // Electrum
        RecipeHandler.addRecipe(
            new AlloySmelterRecipe(
                ItemApi.getItem("ingotGold", 1),
                ItemApi.getItem("ingotSilver", 1),
                ItemApi.getItem("ingotElectrum", 2),
                200,
                16));
        RecipeHandler.addRecipe(
            new AlloySmelterRecipe(
                ItemApi.getItem("ingotGold", 1),
                ItemApi.getItem("dustSilver", 1),
                ItemApi.getItem("ingotElectrum", 2),
                200,
                16));
        RecipeHandler.addRecipe(
            new AlloySmelterRecipe(
                ItemApi.getItem("dustGold", 1),
                ItemApi.getItem("ingotSilver", 1),
                ItemApi.getItem("ingotElectrum", 2),
                200,
                16));
        RecipeHandler.addRecipe(
            new AlloySmelterRecipe(
                ItemApi.getItem("dustGold", 1),
                ItemApi.getItem("dustSilver", 1),
                ItemApi.getItem("ingotElectrum", 2),
                200,
                16));

        // Invar
        RecipeHandler.addRecipe(
            new AlloySmelterRecipe(
                ItemApi.getItem("ingotIron", 2),
                ItemApi.getItem("ingotNickel", 1),
                ItemApi.getItem("ingotInvar", 3),
                200,
                16));
        RecipeHandler.addRecipe(
            new AlloySmelterRecipe(
                ItemApi.getItem("ingotIron", 2),
                ItemApi.getItem("dustNickel", 1),
                ItemApi.getItem("ingotInvar", 3),
                200,
                16));
        RecipeHandler.addRecipe(
            new AlloySmelterRecipe(
                ItemApi.getItem("dustIron", 2),
                ItemApi.getItem("ingotNickel", 1),
                ItemApi.getItem("ingotInvar", 3),
                200,
                16));
        RecipeHandler.addRecipe(
            new AlloySmelterRecipe(
                ItemApi.getItem("dustIron", 2),
                ItemApi.getItem("dustNickel", 1),
                ItemApi.getItem("ingotInvar", 3),
                200,
                16));

        // Cupronickel
        RecipeHandler.addRecipe(
            new AlloySmelterRecipe(
                ItemApi.getItem("ingotCopper", 1),
                ItemApi.getItem("ingotNickel", 1),
                ItemApi.getItem("ingotCupronickel", 2),
                200,
                16));
        RecipeHandler.addRecipe(
            new AlloySmelterRecipe(
                ItemApi.getItem("ingotCopper", 1),
                ItemApi.getItem("dustNickel", 1),
                ItemApi.getItem("ingotCupronickel", 2),
                200,
                16));
        RecipeHandler.addRecipe(
            new AlloySmelterRecipe(
                ItemApi.getItem("dustCopper", 1),
                ItemApi.getItem("ingotNickel", 1),
                ItemApi.getItem("ingotCupronickel", 2),
                200,
                16));
        RecipeHandler.addRecipe(
            new AlloySmelterRecipe(
                ItemApi.getItem("dustCopper", 1),
                ItemApi.getItem("dustNickel", 1),
                ItemApi.getItem("ingotCupronickel", 2),
                200,
                16));

        // Nichrome
        RecipeHandler.addRecipe(
            new AlloySmelterRecipe(
                ItemApi.getItem("ingotChrome", 1),
                ItemApi.getItem("ingotNickel", 4),
                ItemApi.getItem("ingotNichrome", 5),
                200,
                16));
        RecipeHandler.addRecipe(
            new AlloySmelterRecipe(
                ItemApi.getItem("ingotChrome", 1),
                ItemApi.getItem("dustNickel", 4),
                ItemApi.getItem("ingotNichrome", 5),
                200,
                16));
        RecipeHandler.addRecipe(
            new AlloySmelterRecipe(
                ItemApi.getItem("dustChrome", 1),
                ItemApi.getItem("ingotNickel", 4),
                ItemApi.getItem("ingotNichrome", 5),
                200,
                16));
        RecipeHandler.addRecipe(
            new AlloySmelterRecipe(
                ItemApi.getItem("dustChrome", 1),
                ItemApi.getItem("dustNickel", 4),
                ItemApi.getItem("ingotNichrome", 5),
                200,
                16));

        // Magnalium
        RecipeHandler.addRecipe(
            new AlloySmelterRecipe(
                ItemApi.getItem("dustMagnesium", 1),
                ItemApi.getItem("ingotAluminium", 4),
                ItemApi.getItem("ingotMagnalium", 3),
                200,
                16));
        RecipeHandler.addRecipe(
            new AlloySmelterRecipe(
                ItemApi.getItem("dustMagnesium", 1),
                ItemApi.getItem("dustAluminium", 4),
                ItemApi.getItem("ingotMagnalium", 3),
                200,
                16));

        // Battery Alloy
        RecipeHandler.addRecipe(
            new AlloySmelterRecipe(
                ItemApi.getItem("ingotLead", 4),
                ItemApi.getItem("ingotAntimony", 1),
                ItemApi.getItem("ingotBatteryAlloy", 5),
                200,
                16));
        RecipeHandler.addRecipe(
            new AlloySmelterRecipe(
                ItemApi.getItem("ingotLead", 4),
                ItemApi.getItem("dustAntimony", 1),
                ItemApi.getItem("ingotBatteryAlloy", 5),
                200,
                16));
        RecipeHandler.addRecipe(
            new AlloySmelterRecipe(
                ItemApi.getItem("dustLead", 4),
                ItemApi.getItem("ingotAntimony", 1),
                ItemApi.getItem("ingotBatteryAlloy", 5),
                200,
                16));
        RecipeHandler.addRecipe(
            new AlloySmelterRecipe(
                ItemApi.getItem("dustLead", 4),
                ItemApi.getItem("dustAntimony", 1),
                ItemApi.getItem("ingotBatteryAlloy", 5),
                200,
                16));

        // Brass
        RecipeHandler.addRecipe(
            new AlloySmelterRecipe(
                ItemApi.getItem("ingotCopper", 3),
                ItemApi.getItem("ingotZinc", 1),
                ItemApi.getItem("ingotBrass", 4),
                200,
                16));
        RecipeHandler.addRecipe(
            new AlloySmelterRecipe(
                ItemApi.getItem("ingotCopper", 3),
                ItemApi.getItem("dustZinc", 1),
                ItemApi.getItem("ingotBrass", 4),
                200,
                16));
        RecipeHandler.addRecipe(
            new AlloySmelterRecipe(
                ItemApi.getItem("dustCopper", 3),
                ItemApi.getItem("ingotZinc", 1),
                ItemApi.getItem("ingotBrass", 4),
                200,
                16));
        RecipeHandler.addRecipe(
            new AlloySmelterRecipe(
                ItemApi.getItem("dustCopper", 3),
                ItemApi.getItem("dustZinc", 1),
                ItemApi.getItem("ingotBrass", 4),
                200,
                16));

        // Inductive
        RecipeHandler.addRecipe(
            new AlloySmelterRecipe(
                ItemApi.getItem("dustRedstone", 1),
                ItemApi.getItem("ingotGold", 1),
                ItemApi.getItem("ingotInductive", 1),
                200,
                16));
        RecipeHandler.addRecipe(
            new AlloySmelterRecipe(
                ItemApi.getItem("dustRedstone", 1),
                ItemApi.getItem("dustGold", 1),
                ItemApi.getItem("ingotInductive", 1),
                200,
                16));

        // Red Alloy
        if (OreUtil.doesOreExistAndValid("ingotRedAlloy")) {
            ItemStack redAlloyStack = OreDictionary.getOres("ingotRedAlloy")
                .get(0);
            redAlloyStack.stackSize = 1;
            RecipeHandler.addRecipe(
                new AlloySmelterRecipe(
                    new ItemStack(Items.redstone, 4),
                    ItemApi.getItem("ingotCopper", 1),
                    redAlloyStack,
                    200,
                    16));
            RecipeHandler.addRecipe(
                new AlloySmelterRecipe(
                    new ItemStack(Items.redstone, 4),
                    ItemApi.getItem("ingotIron", 1),
                    redAlloyStack,
                    200,
                    16));
        }

        // Blue Alloy
        RecipeHandler.addRecipe(
            new AlloySmelterRecipe(
                ItemApi.getItem("dustNikolite", 4),
                ItemApi.getItem("ingotSilver", 1),
                ItemApi.getItem("ingotBlueAlloy", 1),
                200,
                16));

        // Blue Alloy
        if (OreUtil.doesOreExistAndValid("ingotPurpleAlloy") && OreUtil.doesOreExistAndValid("dustInfusedTeslatite")) {
            ItemStack purpleAlloyStack = OreDictionary.getOres("ingotPurpleAlloy")
                .get(0);
            purpleAlloyStack.stackSize = 1;
            ItemStack infusedTeslatiteStack = OreDictionary.getOres("ingotPurpleAlloy")
                .get(0);
            infusedTeslatiteStack.stackSize = 8;
            RecipeHandler.addRecipe(
                new AlloySmelterRecipe(
                    ItemApi.getItem("ingotRedAlloy", 1),
                    ItemApi.getItem("ingotBlueAlloy", 1),
                    purpleAlloyStack,
                    200,
                    16));
            RecipeHandler.addRecipe(
                new AlloySmelterRecipe(
                    ItemApi.getItem("ingotGold", 1),
                    infusedTeslatiteStack,
                    purpleAlloyStack,
                    200,
                    16));
        }

        // Aluminum Brass
        if (OreUtil.doesOreExistAndValid("ingotAluminumBrass")) {
            ItemStack aluminumBrassStack = OreDictionary.getOres("ingotAluminumBrass")
                .get(0);
            aluminumBrassStack.stackSize = 4;
            RecipeHandler.addRecipe(
                new AlloySmelterRecipe(
                    ItemApi.getItem("ingotCopper", 3),
                    ItemApi.getItem("ingotAluminium", 1),
                    aluminumBrassStack,
                    200,
                    16));
            RecipeHandler.addRecipe(
                new AlloySmelterRecipe(
                    ItemApi.getItem("ingotCopper", 3),
                    ItemApi.getItem("dustAluminium", 1),
                    aluminumBrassStack,
                    200,
                    16));
            RecipeHandler.addRecipe(
                new AlloySmelterRecipe(
                    ItemApi.getItem("dustCopper", 3),
                    ItemApi.getItem("ingotAluminium", 1),
                    aluminumBrassStack,
                    200,
                    16));
            RecipeHandler.addRecipe(
                new AlloySmelterRecipe(
                    ItemApi.getItem("dustCopper", 3),
                    ItemApi.getItem("dustAluminium", 1),
                    aluminumBrassStack,
                    200,
                    16));
        }

        // Manyullyn
        if (OreUtil.doesOreExistAndValid("ingotManyullyn") && OreUtil.doesOreExistAndValid("ingotArdite")) {
            ItemStack manyullynStack = OreDictionary.getOres("ingotManyullyn")
                .get(0);
            manyullynStack.stackSize = 1;
            ItemStack arditeStack = OreDictionary.getOres("ingotArdite")
                .get(0);
            arditeStack.stackSize = 1;
            RecipeHandler.addRecipe(
                new AlloySmelterRecipe(ItemApi.getItem("ingotCobalt", 1), arditeStack, manyullynStack, 200, 16));
            RecipeHandler.addRecipe(
                new AlloySmelterRecipe(ItemApi.getItem("dustCobalt", 1), arditeStack, manyullynStack, 200, 16));
        }

        if (OreUtil.doesOreExistAndValid("ingotManyullyn") && OreUtil.doesOreExistAndValid("dustArdite")) {
            ItemStack manyullynStack = OreDictionary.getOres("ingotManyullyn")
                .get(0);
            manyullynStack.stackSize = 1;
            ItemStack arditeStack = OreDictionary.getOres("dustArdite")
                .get(0);
            arditeStack.stackSize = 1;
            RecipeHandler.addRecipe(
                new AlloySmelterRecipe(ItemApi.getItem("ingotCobalt", 1), arditeStack, manyullynStack, 200, 16));
            RecipeHandler.addRecipe(
                new AlloySmelterRecipe(ItemApi.getItem("dustCobalt", 1), arditeStack, manyullynStack, 200, 16));
        }

        // Conductive Iron
        if (OreUtil.doesOreExistAndValid("ingotConductiveIron")) {
            ItemStack conductiveIronStack = null;
            for (int i = 0; i < OreDictionary.getOres("ingotConductiveIron")
                .size(); i++) {
                if (OreDictionary.getOres("ingotConductiveIron")
                    .get(i) != null) {
                    conductiveIronStack = OreDictionary.getOres("ingotConductiveIron")
                        .get(i);
                    conductiveIronStack.stackSize = 1;
                    break;
                }
            }
            if (conductiveIronStack != null) RecipeHandler.addRecipe(
                new AlloySmelterRecipe(
                    new ItemStack(Items.redstone, 1),
                    ItemApi.getItem("ingotIron", 1),
                    conductiveIronStack,
                    200,
                    16));
        }

        // Redstone Alloy
        if (OreUtil.doesOreExistAndValid("ingotRedstoneAlloy") && OreUtil.doesOreExistAndValid("itemSilicon")) {
            ItemStack redstoneAlloyStack = OreDictionary.getOres("ingotRedstoneAlloy")
                .get(0);
            redstoneAlloyStack.stackSize = 1;
            ItemStack siliconStack = OreDictionary.getOres("itemSilicon")
                .get(0);
            siliconStack.stackSize = 1;
            RecipeHandler.addRecipe(
                new AlloySmelterRecipe(new ItemStack(Items.redstone, 1), siliconStack, redstoneAlloyStack, 200, 16));
        }

        // Pulsating Iron
        if (OreUtil.doesOreExistAndValid("ingotPhasedIron")) {
            ItemStack pulsatingIronStack = OreDictionary.getOres("ingotPhasedIron")
                .get(0);
            pulsatingIronStack.stackSize = 1;
            RecipeHandler.addRecipe(
                new AlloySmelterRecipe(
                    ItemApi.getItem("ingotIron", 1),
                    new ItemStack(Items.ender_pearl, 1),
                    pulsatingIronStack,
                    200,
                    16));
            RecipeHandler.addRecipe(
                new AlloySmelterRecipe(
                    ItemApi.getItem("ingotIron", 1),
                    ItemApi.getItem("dustEnderPearl", 1),
                    pulsatingIronStack,
                    200,
                    16));
        }

        // Energetic Alloy
        if (OreUtil.doesOreExistAndValid("ingotEnergeticAlloy")) {
            ItemStack energeticAlloy = OreDictionary.getOres("ingotEnergeticAlloy")
                .get(0);
            energeticAlloy.stackSize = 1;
            RecipeHandler.addRecipe(
                new AlloySmelterRecipe(
                    ItemApi.getItem("ingotInductive", 1),
                    ItemApi.getItem("dustGlowstone", 1),
                    energeticAlloy,
                    200,
                    16));
        }

        // Energetic Silver
        if (OreUtil.doesOreExistAndValid("ingotEnergeticSilver")) {
            ItemStack energeticSilverStack = OreDictionary.getOres("ingotEnergeticSilver")
                .get(0);
            energeticSilverStack.stackSize = 1;
            RecipeHandler.addRecipe(
                new AlloySmelterRecipe(
                    ItemApi.getItem("ingotBlueAlloy", 1),
                    ItemApi.getItem("dustGlowstone", 1),
                    energeticSilverStack,
                    200,
                    16));
        }

        // Vibrant Alloy
        if (OreUtil.doesOreExistAndValid("ingotEnergeticAlloy") && OreUtil.doesOreExistAndValid("ingotPhasedGold")) {
            ItemStack energeticAlloyStack = OreDictionary.getOres("ingotEnergeticAlloy")
                .get(0);
            energeticAlloyStack.stackSize = 1;
            ItemStack vibrantAlloyStack = OreDictionary.getOres("ingotPhasedGold")
                .get(0);
            vibrantAlloyStack.stackSize = 1;
            RecipeHandler.addRecipe(
                new AlloySmelterRecipe(
                    energeticAlloyStack,
                    new ItemStack(Items.ender_pearl, 1),
                    vibrantAlloyStack,
                    200,
                    16));
            RecipeHandler.addRecipe(
                new AlloySmelterRecipe(
                    energeticAlloyStack,
                    ItemApi.getItem("dustEnderPearl", 1),
                    vibrantAlloyStack,
                    200,
                    16));
        }

        // Vivid Alloy
        if (OreUtil.doesOreExistAndValid("ingotEnergeticSilver") && OreUtil.doesOreExistAndValid("ingotVividAlloy")) {
            ItemStack energeticSilverStack = OreDictionary.getOres("ingotEnergeticSilver")
                .get(0);
            energeticSilverStack.stackSize = 1;
            ItemStack vividAlloyStack = OreDictionary.getOres("ingotVividAlloy")
                .get(0);
            vividAlloyStack.stackSize = 1;
            RecipeHandler.addRecipe(
                new AlloySmelterRecipe(
                    energeticSilverStack,
                    new ItemStack(Items.ender_pearl, 1),
                    vividAlloyStack,
                    200,
                    16));
            RecipeHandler.addRecipe(
                new AlloySmelterRecipe(
                    energeticSilverStack,
                    ItemApi.getItem("dustEnderPearl", 1),
                    vividAlloyStack,
                    200,
                    16));
        }

        // Soularium
        if (OreUtil.doesOreExistAndValid("ingotSoularium")) {
            ItemStack soulariumStack = OreDictionary.getOres("ingotSoularium")
                .get(0);
            soulariumStack.stackSize = 1;
            RecipeHandler.addRecipe(
                new AlloySmelterRecipe(
                    new ItemStack(Blocks.soul_sand, 1),
                    ItemApi.getItem("ingotGold", 1),
                    soulariumStack,
                    200,
                    16));
        }

        // Dark Steel
        if (OreUtil.doesOreExistAndValid("ingotDarkSteel")) {
            ItemStack darkSteelStack = OreDictionary.getOres("ingotDarkSteel")
                .get(0);
            darkSteelStack.stackSize = 1;
            RecipeHandler.addRecipe(
                new AlloySmelterRecipe(
                    new ItemStack(Blocks.obsidian, 1),
                    ItemApi.getItem("ingotSteel", 1),
                    darkSteelStack,
                    200,
                    16));
        }

        // Electrical Steel
        if (OreUtil.doesOreExistAndValid("ingotElectricalSteel") && OreUtil.doesOreExistAndValid("itemSilicon")) {
            ItemStack ElectricalStack = OreDictionary.getOres("ingotElectricalSteel")
                .get(0);
            ElectricalStack.stackSize = 1;
            ItemStack siliconStack = OreDictionary.getOres("itemSilicon")
                .get(0);
            siliconStack.stackSize = 1;
            RecipeHandler.addRecipe(
                new AlloySmelterRecipe(siliconStack, ItemApi.getItem("ingotSteel", 1), ElectricalStack, 200, 16));
        }

        // End Steel
        if (OreUtil.doesOreExistAndValid("ingotDarkSteel") && OreUtil.doesOreExistAndValid("ingotEndSteel")) {
            ItemStack endSteelStack = OreDictionary.getOres("ingotEndSteel")
                .get(0);
            endSteelStack.stackSize = 1;
            ItemStack darkSteelStack = OreDictionary.getOres("ingotDarkSteel")
                .get(0);
            darkSteelStack.stackSize = 1;
            RecipeHandler.addRecipe(
                new AlloySmelterRecipe(new ItemStack(Blocks.end_stone, 1), darkSteelStack, endSteelStack, 200, 16));
        }

        // Melodic Alloy
        if (OreUtil.doesOreExistAndValid("ingotEndSteel") && OreUtil.doesOreExistAndValid("ingotMelodicAlloy")) {
            ItemStack melodicAlloyStack = OreDictionary.getOres("ingotMelodicAlloy")
                .get(0);
            melodicAlloyStack.stackSize = 1;
            ItemStack endSteelStack = OreDictionary.getOres("ingotEndSteel")
                .get(0);
            endSteelStack.stackSize = 1;
            RecipeHandler.addRecipe(
                new AlloySmelterRecipe(new ItemStack(Items.ender_eye, 1), endSteelStack, melodicAlloyStack, 200, 16));
        }

        // Stellar Alloy
        if (OreUtil.doesOreExistAndValid("ingotMelodicAlloy") && OreUtil.doesOreExistAndValid("ingotStellarAlloy")) {
            ItemStack stellarAlloyStack = OreDictionary.getOres("ingotStellarAlloy")
                .get(0);
            stellarAlloyStack.stackSize = 2;
            ItemStack melodicAlloyStack = OreDictionary.getOres("ingotMelodicAlloy")
                .get(0);
            melodicAlloyStack.stackSize = 1;
            RecipeHandler.addRecipe(
                new AlloySmelterRecipe(
                    new ItemStack(Items.nether_star, 1),
                    melodicAlloyStack,
                    stellarAlloyStack,
                    200,
                    16));
        }
    }

    static void addIndustrialSawmillRecipes() {
        ItemStack pulpStack = ItemApi.getItem("dustWood");
        RecipeHandler.addRecipe(
            new IndustrialSawmillRecipe(
                new ItemStack(Blocks.log, 1, 0),
                null,
                new FluidStack(FluidRegistry.WATER, 1000),
                new ItemStack(Blocks.planks, 6, 0),
                pulpStack,
                null,
                200,
                30,
                false));
        RecipeHandler.addRecipe(
            new IndustrialSawmillRecipe(
                new ItemStack(Blocks.log, 1, 0),
                new ItemStack(Items.water_bucket),
                null,
                new ItemStack(Blocks.planks, 6, 0),
                pulpStack,
                new ItemStack(Items.bucket),
                200,
                30,
                false));

        RecipeHandler.addRecipe(
            new IndustrialSawmillRecipe(
                new ItemStack(Blocks.log, 1, 1),
                null,
                new FluidStack(FluidRegistry.WATER, 1000),
                new ItemStack(Blocks.planks, 6, 1),
                pulpStack,
                null,
                200,
                30,
                false));
        RecipeHandler.addRecipe(
            new IndustrialSawmillRecipe(
                new ItemStack(Blocks.log, 1, 1),
                new ItemStack(Items.water_bucket),
                null,
                new ItemStack(Blocks.planks, 6, 1),
                pulpStack,
                new ItemStack(Items.bucket),
                200,
                30,
                false));

        RecipeHandler.addRecipe(
            new IndustrialSawmillRecipe(
                new ItemStack(Blocks.log, 1, 2),
                null,
                new FluidStack(FluidRegistry.WATER, 1000),
                new ItemStack(Blocks.planks, 6, 2),
                pulpStack,
                null,
                200,
                30,
                false));
        RecipeHandler.addRecipe(
            new IndustrialSawmillRecipe(
                new ItemStack(Blocks.log, 1, 2),
                new ItemStack(Items.water_bucket),
                null,
                new ItemStack(Blocks.planks, 6, 2),
                pulpStack,
                new ItemStack(Items.bucket),
                200,
                30,
                false));

        RecipeHandler.addRecipe(
            new IndustrialSawmillRecipe(
                new ItemStack(Blocks.log, 1, 3),
                null,
                new FluidStack(FluidRegistry.WATER, 1000),
                new ItemStack(Blocks.planks, 6, 3),
                pulpStack,
                null,
                200,
                30,
                false));
        RecipeHandler.addRecipe(
            new IndustrialSawmillRecipe(
                new ItemStack(Blocks.log, 1, 3),
                new ItemStack(Items.water_bucket),
                null,
                new ItemStack(Blocks.planks, 6, 3),
                pulpStack,
                new ItemStack(Items.bucket),
                200,
                30,
                false));

        RecipeHandler.addRecipe(
            new IndustrialSawmillRecipe(
                new ItemStack(Blocks.log2, 1, 0),
                null,
                new FluidStack(FluidRegistry.WATER, 1000),
                new ItemStack(Blocks.planks, 6, 4),
                pulpStack,
                null,
                200,
                30,
                false));
        RecipeHandler.addRecipe(
            new IndustrialSawmillRecipe(
                new ItemStack(Blocks.log2, 1, 0),
                new ItemStack(Items.water_bucket),
                null,
                new ItemStack(Blocks.planks, 6, 4),
                pulpStack,
                new ItemStack(Items.bucket),
                200,
                30,
                false));

        RecipeHandler.addRecipe(
            new IndustrialSawmillRecipe(
                new ItemStack(Blocks.log2, 1, 1),
                null,
                new FluidStack(FluidRegistry.WATER, 1000),
                new ItemStack(Blocks.planks, 6, 5),
                pulpStack,
                null,
                200,
                30,
                false));
        RecipeHandler.addRecipe(
            new IndustrialSawmillRecipe(
                new ItemStack(Blocks.log2, 1, 1),
                new ItemStack(Items.water_bucket),
                null,
                new ItemStack(Blocks.planks, 6, 5),
                pulpStack,
                new ItemStack(Items.bucket),
                200,
                30,
                false));
    }

    static void addBlastFurnaceRecipes() {
        RecipeHandler.addRecipe(
            new BlastFurnaceRecipe(
                ItemApi.getItem("dustTitanium"),
                null,
                ItemApi.getItem("ingotTitanium"),
                null,
                3600,
                120,
                1500));
        RecipeHandler.addRecipe(
            new BlastFurnaceRecipe(
                ItemApi.getItem("dustSmalltitanium", 4),
                null,
                ItemApi.getItem("ingotTitanium"),
                null,
                3600,
                120,
                1500));
        // RecipeHandler.addRecipe(new
        // BlastFurnaceRecipe(ItemDustsTiny.getTinyDustByName("titanium", 9), null,
        // ItemApi.getItem("ingotTitanium"), null, 3600, 120, 1500));
        RecipeHandler.addRecipe(
            new BlastFurnaceRecipe(
                ItemApi.getItem("dustAluminium"),
                null,
                ItemApi.getItem("ingotAluminium"),
                null,
                2200,
                120,
                1700));
        RecipeHandler.addRecipe(
            new BlastFurnaceRecipe(
                ItemApi.getItem("dustSmallAluminium", 4),
                null,
                ItemApi.getItem("ingotAluminium"),
                null,
                2200,
                120,
                1700));
        // RecipeHandler.addRecipe(new
        // BlastFurnaceRecipe(ItemDustsTiny.getTinyDustByName("aluminum", 9), null,
        // ItemApi.getItem("ingotAluminium"), null, 2200, 120, 1700));
        RecipeHandler.addRecipe(
            new BlastFurnaceRecipe(
                ItemApi.getItem("dustKanthal"),
                null,
                ItemApi.getItem("ingotKanthal"),
                null,
                5500,
                120,
                2500));
        RecipeHandler.addRecipe(
            new BlastFurnaceRecipe(
                ItemApi.getItem("dustSmallkanthal", 4),
                null,
                ItemApi.getItem("ingotKanthal"),
                null,
                5500,
                120,
                2500));
        // RecipeHandler.addRecipe(new
        // BlastFurnaceRecipe(ItemDustsTiny.getTinyDustByName("kanthal", 9), null,
        // ItemApi.getItem("ingotKanthal"), null, 5500, 120, 2500));
        RecipeHandler.addRecipe(
            new BlastFurnaceRecipe(
                ItemApi.getItem("dustTungsten"),
                null,
                ItemApi.getItem("ingotTungsten"),
                null,
                18000,
                120,
                2500));
        RecipeHandler.addRecipe(
            new BlastFurnaceRecipe(
                ItemApi.getItem("dustSmalltungsten", 4),
                null,
                ItemApi.getItem("ingotTungsten"),
                null,
                18000,
                120,
                2500));
        // RecipeHandler.addRecipe(new
        // BlastFurnaceRecipe(ItemDustsTiny.getTinyDustByName("tungsten", 9), null,
        // ItemApi.getItem("ingotTungsten"), null, 18000, 120, 2500));
        RecipeHandler.addRecipe(
            new BlastFurnaceRecipe(
                ItemApi.getItem("dustChrome"),
                null,
                ItemApi.getItem("ingotChrome"),
                null,
                4420,
                120,
                1700));
        RecipeHandler.addRecipe(
            new BlastFurnaceRecipe(
                ItemApi.getItem("dustSmallchrome", 4),
                null,
                ItemApi.getItem("ingotChrome"),
                null,
                4420,
                120,
                1700));
        // RecipeHandler.addRecipe(new
        // BlastFurnaceRecipe(ItemDustsTiny.getTinyDustByName("chrome", 9), null,
        // ItemApi.getItem("ingotChrome"), null, 4420, 120, 1700));
        RecipeHandler.addRecipe(
            new BlastFurnaceRecipe(
                ItemApi.getItem("dustSteel"),
                null,
                ItemApi.getItem("ingotSteel"),
                null,
                2800,
                120,
                1000));
        RecipeHandler.addRecipe(
            new BlastFurnaceRecipe(
                ItemApi.getItem("dustSmallsteel", 4),
                null,
                ItemApi.getItem("ingotSteel"),
                null,
                2800,
                120,
                1000));
        // RecipeHandler.addRecipe(new
        // BlastFurnaceRecipe(ItemDustsTiny.getTinyDustByName("steel", 9), null,
        // ItemApi.getItem("ingotSteel"), null, 2800, 120, 1000));
        RecipeHandler.addRecipe(
            new BlastFurnaceRecipe(
                ItemApi.getItem("dustGalena", 2),
                null,
                ItemApi.getItem("ingotSilver"),
                ItemApi.getItem("ingotLead"),
                80,
                120,
                1500));
        RecipeHandler.addRecipe(
            new BlastFurnaceRecipe(
                new ItemStack(Items.iron_ingot),
                ItemApi.getItem("dustCoal", 2),
                ItemApi.getItem("ingotSteel"),
                ItemApi.getItem("dustDarkAsh", 2),
                500,
                120,
                1000));
        RecipeHandler.addRecipe(
            new BlastFurnaceRecipe(
                ItemApi.getItem("ingotTungsten"),
                ItemApi.getItem("ingotSteel"),
                ItemApi.getItem("ingotHotTungstensteel"),
                ItemApi.getItem("dustDarkAsh", 4),
                500,
                500,
                3000));
        RecipeHandler.addRecipe(
            new BlastFurnaceRecipe(
                new ItemStack(Blocks.iron_ore),
                ItemApi.getItem("dustCalcite"),
                ItemApi.getItem("ingotIron", 3),
                ItemApi.getItem("dustDarkAsh"),
                140,
                120,
                1000));
        RecipeHandler.addRecipe(
            new BlastFurnaceRecipe(
                BlockOre.getOreByName("Pyrite"),
                ItemApi.getItem("dustCalcite"),
                ItemApi.getItem("ingotIron", 2),
                ItemApi.getItem("dustDarkAsh"),
                140,
                120,
                1000));
    }

    static void addUUrecipes() {
        if (ConfigTechReborn.UUrecipesWood) CraftingHelper
            .addShapedOreRecipe(new ItemStack(Blocks.log, 8), " U ", "   ", "   ", 'U', ModItems.uuMatter);

        if (ConfigTechReborn.UUrecipesStone) CraftingHelper
            .addShapedOreRecipe(new ItemStack(Blocks.stone, 16), "   ", " U ", "   ", 'U', ModItems.uuMatter);

        if (ConfigTechReborn.UUrecipesSnowBlock) CraftingHelper
            .addShapedOreRecipe(new ItemStack(Blocks.snow, 16), "U U", "   ", "   ", 'U', ModItems.uuMatter);

        if (ConfigTechReborn.UUrecipesGrass) CraftingHelper
            .addShapedOreRecipe(new ItemStack(Blocks.grass, 16), "   ", "U  ", "U  ", 'U', ModItems.uuMatter);

        if (ConfigTechReborn.UUrecipesObsidian) CraftingHelper
            .addShapedOreRecipe(new ItemStack(Blocks.obsidian, 12), "U U", "U U", "   ", 'U', ModItems.uuMatter);

        if (ConfigTechReborn.UUrecipesGlass) CraftingHelper
            .addShapedOreRecipe(new ItemStack(Blocks.glass, 32), " U ", "U U", " U ", 'U', ModItems.uuMatter);

        if (ConfigTechReborn.UUrecipesWater) CraftingHelper
            .addShapedOreRecipe(new ItemStack(Blocks.water, 1), "   ", " U ", " U ", 'U', ModItems.uuMatter);

        if (ConfigTechReborn.UUrecipesLava) CraftingHelper
            .addShapedOreRecipe(new ItemStack(Blocks.lava, 1), " U ", " U ", " U ", 'U', ModItems.uuMatter);

        if (ConfigTechReborn.UUrecipesCocoa) CraftingHelper
            .addShapedOreRecipe(new ItemStack(Items.dye, 32, 3), "UU ", "  U", "UU ", 'U', ModItems.uuMatter);

        if (ConfigTechReborn.UUrecipesGlowstoneBlock) CraftingHelper
            .addShapedOreRecipe(new ItemStack(Blocks.glowstone, 8), " U ", "U U", "UUU", 'U', ModItems.uuMatter);

        if (ConfigTechReborn.UUrecipesCactus) CraftingHelper
            .addShapedOreRecipe(new ItemStack(Blocks.cactus, 48), " U ", "UUU", "U U", 'U', ModItems.uuMatter);

        if (ConfigTechReborn.UUrecipesSugarCane) CraftingHelper
            .addShapedOreRecipe(new ItemStack(Items.reeds, 48), "U U", "U U", "U U", 'U', ModItems.uuMatter);

        if (ConfigTechReborn.UUrecipesVine) CraftingHelper
            .addShapedOreRecipe(new ItemStack(Blocks.vine, 24), "U  ", "U  ", "U  ", 'U', ModItems.uuMatter);

        if (ConfigTechReborn.UUrecipesSnowBall) CraftingHelper
            .addShapedOreRecipe(new ItemStack(Items.snowball, 16), "   ", "   ", "UUU", 'U', ModItems.uuMatter);

        CraftingHelper
            .addShapedOreRecipe(new ItemStack(Items.clay_ball, 48), "UU ", "U  ", "UU ", 'U', ModItems.uuMatter);

        if (ConfigTechReborn.UUrecipeslilypad) CraftingHelper
            .addShapedOreRecipe(new ItemStack(Blocks.waterlily, 64), "U U", " U ", " U ", 'U', ModItems.uuMatter);

        if (ConfigTechReborn.UUrecipesGunpowder) CraftingHelper
            .addShapedOreRecipe(new ItemStack(Items.gunpowder, 15), "UUU", "U  ", "UUU", 'U', ModItems.uuMatter);

        if (ConfigTechReborn.UUrecipesBone) CraftingHelper
            .addShapedOreRecipe(new ItemStack(Items.bone, 32), "U  ", "UU ", "U  ", 'U', ModItems.uuMatter);

        if (ConfigTechReborn.UUrecipesFeather) CraftingHelper
            .addShapedOreRecipe(new ItemStack(Items.feather, 32), " U ", " U ", "U U", 'U', ModItems.uuMatter);

        if (ConfigTechReborn.UUrecipesInk) CraftingHelper
            .addShapedOreRecipe(new ItemStack(Items.dye, 48), " UU", " UU", " U ", 'U', ModItems.uuMatter);

        if (ConfigTechReborn.UUrecipesEnderPearl) CraftingHelper
            .addShapedOreRecipe(new ItemStack(Items.ender_pearl, 1), "UUU", "U U", " U ", 'U', ModItems.uuMatter);

        if (ConfigTechReborn.UUrecipesCoal) CraftingHelper
            .addShapedOreRecipe(new ItemStack(Items.coal, 5), "  U", "U  ", "  U", 'U', ModItems.uuMatter);

        if (ConfigTechReborn.UUrecipesIronOre) CraftingHelper
            .addShapedOreRecipe(new ItemStack(Blocks.iron_ore, 2), "U U", " U ", "U U", 'U', ModItems.uuMatter);

        if (ConfigTechReborn.UUrecipesGoldOre) CraftingHelper
            .addShapedOreRecipe(new ItemStack(Blocks.gold_ore, 2), " U ", "UUU", " U ", 'U', ModItems.uuMatter);

        if (ConfigTechReborn.UUrecipesRedStone) CraftingHelper
            .addShapedOreRecipe(new ItemStack(Items.redstone, 24), "   ", " U ", "UUU", 'U', ModItems.uuMatter);

        if (ConfigTechReborn.UUrecipesLapis) CraftingHelper
            .addShapedOreRecipe(new ItemStack(Items.dye, 9, 4), " U ", " U ", " UU", 'U', ModItems.uuMatter);

        if (ConfigTechReborn.UUrecipesEmeraldOre) CraftingHelper
            .addShapedOreRecipe(new ItemStack(Blocks.emerald_ore, 1), "UU ", "U U", " UU", 'U', ModItems.uuMatter);

        if (ConfigTechReborn.UUrecipesEmerald) CraftingHelper
            .addShapedOreRecipe(new ItemStack(Items.emerald, 2), "UUU", "UUU", " U ", 'U', ModItems.uuMatter);

        if (ConfigTechReborn.UUrecipesDiamond) CraftingHelper
            .addShapedOreRecipe(new ItemStack(Items.diamond, 1), "UUU", "UUU", "UUU", 'U', ModItems.uuMatter);

        if (ConfigTechReborn.UUrecipesTinDust) CraftingHelper
            .addShapedOreRecipe(new ItemStack(ModItems.dusts, 10, 77), "   ", "U U", "  U", 'U', ModItems.uuMatter);

        if (ConfigTechReborn.UUrecipesCopperDust) CraftingHelper
            .addShapedOreRecipe(new ItemStack(ModItems.dusts, 10, 21), "  U", "U U", "   ", 'U', ModItems.uuMatter);

        if (ConfigTechReborn.UUrecipesLeadDust) CraftingHelper
            .addShapedOreRecipe(new ItemStack(ModItems.dusts, 14, 42), "UUU", "UUU", "U  ", 'U', ModItems.uuMatter);

        if (ConfigTechReborn.UUrecipesPlatinumDust) CraftingHelper
            .addShapedOreRecipe(new ItemStack(ModItems.dusts, 1, 58), "  U", "UUU", "UUU", 'U', ModItems.uuMatter);

        if (ConfigTechReborn.UUrecipesTungstenDust) CraftingHelper
            .addShapedOreRecipe(new ItemStack(ModItems.dusts, 1, 79), "U  ", "UUU", "UUU", 'U', ModItems.uuMatter);

        if (ConfigTechReborn.UUrecipesTitaniumDust) CraftingHelper
            .addShapedOreRecipe(new ItemStack(ModItems.dusts, 2, 78), "UUU", " U ", " U ", 'U', ModItems.uuMatter);

        if (ConfigTechReborn.UUrecipesAluminumDust) CraftingHelper
            .addShapedOreRecipe(new ItemStack(ModItems.dusts, 16, 2), " U ", " U ", "UUU", 'U', ModItems.uuMatter);
    }

    static void addIndustrialCentrifugeRecipes() {
        // Mycelium Byproducts
        RecipeHandler.addRecipe(
            new CentrifugeRecipe(
                new ItemStack(Blocks.mycelium, 8),
                null,
                new ItemStack(Blocks.brown_mushroom, 2),
                new ItemStack(Blocks.red_mushroom, 2),
                new ItemStack(Items.clay_ball, 1),
                new ItemStack(Blocks.sand, 4),
                1640,
                5));

        // Blaze Powder Byproducts
        RecipeHandler.addRecipe(
            new CentrifugeRecipe(
                ItemApi.getItem("dustBlaze", 1),
                null,
                ItemApi.getItem("dustDarkAsh", 1),
                ItemApi.getItem("dustSulfur", 1),
                null,
                null,
                1240,
                5));

        // Magma Cream Products
        RecipeHandler.addRecipe(
            new CentrifugeRecipe(
                new ItemStack(Items.magma_cream, 1),
                null,
                ItemApi.getItem("dustBlaze", 1),
                new ItemStack(Items.slime_ball, 1),
                null,
                null,
                2500,
                5));

        // Dust Byproducts
        // RecipeHandler.addRecipe(new CentrifugeRecipe(ItemApi.getItem("dustPlatinum",
        // 1), null, ItemDustsTiny.getTinyDustByName("Iridium", 1),
        // ItemApi.getItem("dustSmallNickel", 1), null, null, 3000, 5));
        RecipeHandler.addRecipe(
            new CentrifugeRecipe(
                ItemApi.getItem("dustElectrum", 2),
                null,
                ItemApi.getItem("dustSilver", 1),
                ItemApi.getItem("dustGold", 1),
                null,
                null,
                2400,
                5));
        RecipeHandler.addRecipe(
            new CentrifugeRecipe(
                ItemApi.getItem("dustInvar", 3),
                null,
                ItemApi.getItem("dustIron", 2),
                ItemApi.getItem("dustNickel", 1),
                null,
                null,
                1340,
                5));
        RecipeHandler.addRecipe(
            new CentrifugeRecipe(
                ItemApi.getItem("dustMarble", 8),
                null,
                ItemApi.getItem("dustMagnesium", 1),
                ItemApi.getItem("dustCalcite", 7),
                null,
                null,
                1280,
                5));
        RecipeHandler.addRecipe(
            new CentrifugeRecipe(
                ItemApi.getItem("dustRedrock", 4),
                null,
                ItemApi.getItem("dustCalcite", 2),
                ItemApi.getItem("dustFlint", 1),
                ItemApi.getItem("dustClay", 1),
                null,
                640,
                5));
        RecipeHandler.addRecipe(
            new CentrifugeRecipe(
                ItemApi.getItem("dustBasalt", 16),
                null,
                ItemApi.getItem("dustPeridot", 1),
                ItemApi.getItem("dustCalcite", 3),
                ItemApi.getItem("dustMagnesium", 8),
                ItemApi.getItem("dustDarkAsh", 4),
                2680,
                5));
        RecipeHandler.addRecipe(
            new CentrifugeRecipe(
                ItemApi.getItem("dustYellowGarnet", 16),
                null,
                ItemApi.getItem("dustAndradite", 5),
                ItemApi.getItem("dustGrossular", 8),
                ItemApi.getItem("dustUvarovite", 3),
                null,
                2940,
                5));
        RecipeHandler.addRecipe(
            new CentrifugeRecipe(
                ItemApi.getItem("dustRedGarnet", 16),
                null,
                ItemApi.getItem("dustPyrope", 3),
                ItemApi.getItem("dustAlmandine", 5),
                ItemApi.getItem("dustSpessartine", 8),
                null,
                2940,
                5));
        RecipeHandler.addRecipe(
            new CentrifugeRecipe(
                ItemApi.getItem("dustDarkAsh", 2),
                null,
                ItemApi.getItem("dustAsh", 2),
                null,
                null,
                null,
                240,
                5));
        RecipeHandler.addRecipe(
            new CentrifugeRecipe(
                ItemApi.getItem("dustManyullyn", 2),
                null,
                ItemApi.getItem("dustCobalt", 1),
                ItemApi.getItem("dustArdite", 1),
                null,
                null,
                1240,
                5));
        RecipeHandler.addRecipe(
            new CentrifugeRecipe(
                ItemApi.getItem("dustNichrome", 5),
                null,
                ItemApi.getItem("dustNickel", 4),
                ItemApi.getItem("dustChrome", 1),
                null,
                null,
                2240,
                5));
        RecipeHandler.addRecipe(
            new CentrifugeRecipe(
                ItemApi.getItem("dustCupronickel", 2),
                null,
                ItemApi.getItem("dustCopper", 1),
                ItemApi.getItem("dustNickel", 1),
                null,
                null,
                960,
                5));
        RecipeHandler.addRecipe(
            new CentrifugeRecipe(
                ItemApi.getItem("dustKanthal", 3),
                null,
                ItemApi.getItem("dustIron", 1),
                ItemApi.getItem("dustAluminium", 1),
                ItemApi.getItem("dustChrome", 1),
                null,
                1040,
                5));
        RecipeHandler.addRecipe(
            new CentrifugeRecipe(
                ItemApi.getItem("dustBrass", 4),
                null,
                ItemApi.getItem("dustZinc", 1),
                ItemApi.getItem("dustCopper", 3),
                null,
                null,
                2000,
                5));
        RecipeHandler.addRecipe(
            new CentrifugeRecipe(
                ItemApi.getItem("dustAluminumBrass", 4),
                null,
                ItemApi.getItem("dustAluminium", 1),
                ItemApi.getItem("dustCopper", 3),
                null,
                null,
                2000,
                5));
        RecipeHandler.addRecipe(
            new CentrifugeRecipe(
                ItemApi.getItem("dustBronze", 4),
                null,
                ItemApi.getItem("dustTin", 1),
                ItemApi.getItem("dustCopper", 3),
                null,
                null,
                2420,
                5));
        RecipeHandler.addRecipe(
            new CentrifugeRecipe(
                ItemApi.getItem("dustNetherrack", 16),
                null,
                ItemApi.getItem("dustRedstone", 1),
                ItemApi.getItem("dustSulfur", 4),
                ItemApi.getItem("dustBasalt", 1),
                new ItemStack(Items.gold_nugget, 1),
                2400,
                5));
        RecipeHandler.addRecipe(
            new CentrifugeRecipe(
                ItemApi.getItem("dustEnderEye", 1),
                null,
                ItemApi.getItem("dustEnderPearl", 1),
                ItemApi.getItem("dustBlaze", 1),
                null,
                null,
                1280,
                5));
        RecipeHandler.addRecipe(
            new CentrifugeRecipe(
                ItemApi.getItem("dustTetrahedrite", 8),
                null,
                ItemApi.getItem("dustCopper", 3),
                ItemApi.getItem("dustAntimony", 1),
                ItemApi.getItem("dustSulfur", 3),
                ItemApi.getItem("dustIron", 1),
                3640,
                5));
        RecipeHandler.addRecipe(
            new CentrifugeRecipe(
                ItemApi.getItem("dustLapis", 16),
                null,
                ItemApi.getItem("dustLazurite", 12),
                ItemApi.getItem("dustSodalite", 2),
                ItemApi.getItem("dustPyrite", 7),
                ItemApi.getItem("dustCalcite", 1),
                3580,
                5));
        // RecipeHandler.addRecipe(new CentrifugeRecipe(new
        // ItemStack(Items.glowstone_dust, 16), RecipeUtils.getEmptyCell(1),
        // ItemCells.getCellByName("helium", 1, false), ItemApi.getItem("dustGold", 8),
        // new ItemStack(Items.redstone), null, 25000, 20));
        // RecipeHandler.addRecipe(new CentrifugeRecipe(ItemApi.getItem("dustendstone",
        // 16), RecipeUtils.getEmptyCell(2), ItemCells.getCellByName("helium3", 1,
        // false), ItemCells.getCellByName("helium", 1, false),
        // ItemDustsTiny.getTinyDustByName("Tungsten"), new ItemStack(Blocks.sand, 12),
        // 4800, 5));
    }

    static void addIndustrialGrinderRecipes() {
        for (String ore : OreUtil.oreNames) {
            if (OreUtil.hasIngot(ore) && OreUtil.hasDustSmall(ore) && OreUtil.hasBlock(ore)) {
                RecipeHandler.addRecipe(
                    new GrinderRecipe(
                        ItemApi.getItem("block" + capitalizeFirstLetter(ore)),
                        null,
                        new FluidStack(FluidRegistry.WATER, 1000),
                        ItemApi.getItem("ingot" + capitalizeFirstLetter(ore)),
                        ItemApi.getItem("dustSmall" + capitalizeFirstLetter(ore), 6),
                        ItemApi.getItem("dustSmall" + capitalizeFirstLetter(ore), 2),
                        null,
                        100,
                        120));
                RecipeHandler.addRecipe(
                    new GrinderRecipe(
                        ItemApi.getItem("block" + capitalizeFirstLetter(ore)),
                        new ItemStack(Items.water_bucket),
                        null,
                        ItemApi.getItem("ingot" + capitalizeFirstLetter(ore)),
                        ItemApi.getItem("dustSmall" + capitalizeFirstLetter(ore), 6),
                        ItemApi.getItem("dustSmall" + capitalizeFirstLetter(ore), 2),
                        new ItemStack(Items.bucket),
                        100,
                        120));
            }
        }

        // Copper Ore
        if (OreUtil.doesOreExistAndValid("oreCopper")) {
            try {
                ItemStack oreStack = OreDictionary.getOres("oreCopper")
                    .get(0);
                RecipeHandler.addRecipe(
                    new GrinderRecipe(
                        oreStack,
                        null,
                        new FluidStack(FluidRegistry.WATER, 1000),
                        ItemApi.getItem("dustCopper", 2),
                        ItemApi.getItem("dustSmallGold", 1),
                        ItemApi.getItem("dustSmallNickel", 1),
                        null,
                        100,
                        120));
                RecipeHandler.addRecipe(
                    new GrinderRecipe(
                        oreStack,
                        new ItemStack(Items.water_bucket),
                        null,
                        ItemApi.getItem("dustCopper", 2),
                        ItemApi.getItem("dustSmallGold", 1),
                        ItemApi.getItem("dustSmallNickel", 1),
                        new ItemStack(Items.bucket),
                        100,
                        120));

                RecipeHandler.addRecipe(
                    new GrinderRecipe(
                        oreStack,
                        null,
                        new FluidStack(ModFluids.fluidSodiumpersulfate, 1000),
                        ItemApi.getItem("dustCopper", 2),
                        ItemApi.getItem("dustGold", 1),
                        ItemApi.getItem("dustSmallNickel", 1),
                        null,
                        100,
                        120));
                RecipeHandler.addRecipe(
                    new GrinderRecipe(
                        oreStack,
                        new ItemStack(ModItems.bucketSodiumpersulfate),
                        null,
                        ItemApi.getItem("dustCopper", 2),
                        ItemApi.getItem("dustGold", 1),
                        ItemApi.getItem("dustSmallNickel", 1),
                        new ItemStack(Items.bucket),
                        100,
                        120));

                RecipeHandler.addRecipe(
                    new GrinderRecipe(
                        oreStack,
                        null,
                        new FluidStack(ModFluids.fluidMercury, 1000),
                        ItemApi.getItem("dustCopper", 2),
                        ItemApi.getItem("dustSmallGold", 1),
                        ItemApi.getItem("dustNickel", 1),
                        null,
                        100,
                        120));
                RecipeHandler.addRecipe(
                    new GrinderRecipe(
                        oreStack,
                        ItemCells.getCellByName("mercury", 1),
                        null,
                        ItemApi.getItem("dustCopper", 2),
                        ItemApi.getItem("dustSmallGold", 1),
                        ItemApi.getItem("dustNickel", 1),
                        new ItemStack(Items.bucket),
                        100,
                        120));
            } catch (Exception e) {
                Core.logHelper.info("Failed to Load Grinder Recipe for Copper Ore");
            }
        }

        // Tin Ore
        if (OreUtil.doesOreExistAndValid("oreTin")) {
            try {
                ItemStack oreStack = OreDictionary.getOres("oreTin")
                    .get(0);
                RecipeHandler.addRecipe(
                    new GrinderRecipe(
                        oreStack,
                        null,
                        new FluidStack(FluidRegistry.WATER, 1000),
                        ItemApi.getItem("dustTin", 2),
                        ItemApi.getItem("dustSmallIron", 1),
                        ItemApi.getItem("dustSmallZinc", 1),
                        null,
                        100,
                        120));
                RecipeHandler.addRecipe(
                    new GrinderRecipe(
                        oreStack,
                        new ItemStack(Items.water_bucket),
                        null,
                        ItemApi.getItem("dustTin", 2),
                        ItemApi.getItem("dustSmallIron", 1),
                        ItemApi.getItem("dustSmallZinc", 1),
                        new ItemStack(Items.bucket),
                        100,
                        120));

                RecipeHandler.addRecipe(
                    new GrinderRecipe(
                        oreStack,
                        null,
                        new FluidStack(ModFluids.fluidSodiumpersulfate, 1000),
                        ItemApi.getItem("dustTin", 2),
                        ItemApi.getItem("dustSmallIron", 1),
                        ItemApi.getItem("dustZinc", 1),
                        null,
                        100,
                        120));
                RecipeHandler.addRecipe(
                    new GrinderRecipe(
                        oreStack,
                        new ItemStack(ModItems.bucketSodiumpersulfate),
                        null,
                        ItemApi.getItem("dustTin", 2),
                        ItemApi.getItem("dustSmallIron", 1),
                        ItemApi.getItem("dustZinc", 1),
                        new ItemStack(Items.bucket),
                        100,
                        120));
            } catch (Exception e) {
                Core.logHelper.info("Failed to Load Grinder Recipe for Tin Ore");
            }
        }

        // Nickel Ore
        if (OreUtil.doesOreExistAndValid("oreNickel")) {
            try {
                ItemStack oreStack = OreDictionary.getOres("oreNickel")
                    .get(0);
                RecipeHandler.addRecipe(
                    new GrinderRecipe(
                        oreStack,
                        null,
                        new FluidStack(FluidRegistry.WATER, 1000),
                        ItemApi.getItem("dustNickel", 2),
                        ItemApi.getItem("dustSmallIron", 1),
                        ItemApi.getItem("dustSmallPlatinum", 1),
                        null,
                        100,
                        120));
                RecipeHandler.addRecipe(
                    new GrinderRecipe(
                        oreStack,
                        new ItemStack(Items.water_bucket),
                        null,
                        ItemApi.getItem("dustNickel", 2),
                        ItemApi.getItem("dustSmallIron", 1),
                        ItemApi.getItem("dustSmallPlatinum", 1),
                        new ItemStack(Items.bucket),
                        100,
                        120));

                RecipeHandler.addRecipe(
                    new GrinderRecipe(
                        oreStack,
                        null,
                        new FluidStack(ModFluids.fluidSodiumpersulfate, 1000),
                        ItemApi.getItem("dustNickel", 3),
                        ItemApi.getItem("dustSmallIron", 1),
                        ItemApi.getItem("dustSmallPlatinum", 1),
                        null,
                        100,
                        120));
                RecipeHandler.addRecipe(
                    new GrinderRecipe(
                        oreStack,
                        new ItemStack(ModItems.bucketSodiumpersulfate),
                        null,
                        ItemApi.getItem("dustNickel", 3),
                        ItemApi.getItem("dustSmallIron", 1),
                        ItemApi.getItem("dustSmallPlatinum", 1),
                        new ItemStack(Items.bucket),
                        100,
                        120));

                RecipeHandler.addRecipe(
                    new GrinderRecipe(
                        oreStack,
                        null,
                        new FluidStack(ModFluids.fluidMercury, 1000),
                        ItemApi.getItem("dustNickel", 2),
                        ItemApi.getItem("dustSmallIron", 1),
                        ItemApi.getItem("dustPlatinum", 1),
                        null,
                        100,
                        120));
                RecipeHandler.addRecipe(
                    new GrinderRecipe(
                        oreStack,
                        new ItemStack(ModItems.bucketMercury),
                        null,
                        ItemApi.getItem("dustNickel", 2),
                        ItemApi.getItem("dustSmallIron", 1),
                        ItemApi.getItem("dustPlatinum", 1),
                        new ItemStack(Items.bucket),
                        100,
                        120));
            } catch (Exception e) {
                Core.logHelper.info("Failed to Load Grinder Recipe for Nickel Ore");
            }
        }

        // Zinc Ore
        if (OreUtil.doesOreExistAndValid("oreZinc")) {
            try {
                ItemStack oreStack = OreDictionary.getOres("oreZinc")
                    .get(0);
                RecipeHandler.addRecipe(
                    new GrinderRecipe(
                        oreStack,
                        null,
                        new FluidStack(FluidRegistry.WATER, 1000),
                        ItemApi.getItem("dustZinc", 2),
                        ItemApi.getItem("dustSmallIron", 1),
                        ItemApi.getItem("dustSmallTin", 1),
                        null,
                        100,
                        120));
                RecipeHandler.addRecipe(
                    new GrinderRecipe(
                        oreStack,
                        new ItemStack(Items.water_bucket),
                        null,
                        ItemApi.getItem("dustZinc", 2),
                        ItemApi.getItem("dustSmallIron", 1),
                        ItemApi.getItem("dustSmallTin", 1),
                        new ItemStack(Items.bucket),
                        100,
                        120));

                RecipeHandler.addRecipe(
                    new GrinderRecipe(
                        oreStack,
                        null,
                        new FluidStack(ModFluids.fluidSodiumpersulfate, 1000),
                        ItemApi.getItem("dustZinc", 2),
                        ItemApi.getItem("dustSmallIron", 1),
                        ItemApi.getItem("dustIron", 1),
                        null,
                        100,
                        120));
                RecipeHandler.addRecipe(
                    new GrinderRecipe(
                        oreStack,
                        new ItemStack(ModItems.bucketSodiumpersulfate),
                        null,
                        ItemApi.getItem("dustZinc", 2),
                        ItemApi.getItem("dustSmallIron", 1),
                        ItemApi.getItem("dustIron", 1),
                        new ItemStack(Items.bucket),
                        100,
                        120));
            } catch (Exception e) {
                Core.logHelper.info("Failed to Load Grinder Recipe for Zinc Ore");
            }
        }

        // Silver Ore
        if (OreUtil.doesOreExistAndValid("oreSilver")) {
            try {
                ItemStack oreStack = OreDictionary.getOres("oreSilver")
                    .get(0);
                RecipeHandler.addRecipe(
                    new GrinderRecipe(
                        oreStack,
                        null,
                        new FluidStack(FluidRegistry.WATER, 1000),
                        ItemApi.getItem("dustSilver", 2),
                        ItemApi.getItem("dustSmallLead", 1),
                        ItemApi.getItem("dustSmallSulfur", 1),
                        null,
                        100,
                        120));
                RecipeHandler.addRecipe(
                    new GrinderRecipe(
                        oreStack,
                        new ItemStack(Items.water_bucket),
                        null,
                        ItemApi.getItem("dustSilver", 2),
                        ItemApi.getItem("dustSmallLead", 1),
                        ItemApi.getItem("dustSmallSulfur", 1),
                        new ItemStack(Items.bucket),
                        100,
                        120));

                RecipeHandler.addRecipe(
                    new GrinderRecipe(
                        oreStack,
                        null,
                        new FluidStack(ModFluids.fluidMercury, 1000),
                        ItemApi.getItem("dustSilver", 3),
                        ItemApi.getItem("dustSmallLead", 1),
                        ItemApi.getItem("dustSmallSulfur", 1),
                        null,
                        100,
                        120));
                RecipeHandler.addRecipe(
                    new GrinderRecipe(
                        oreStack,
                        new ItemStack(ModItems.bucketMercury),
                        null,
                        ItemApi.getItem("dustSilver", 3),
                        ItemApi.getItem("dustSmallLead", 1),
                        ItemApi.getItem("dustSmallSulfur", 1),
                        new ItemStack(Items.bucket),
                        100,
                        120));
            } catch (Exception e) {
                Core.logHelper.info("Failed to Load Grinder Recipe for Silver Ore");
            }
        }

        // Lead Ore
        if (OreUtil.doesOreExistAndValid("oreLead")) {
            try {
                ItemStack oreStack = OreDictionary.getOres("oreLead")
                    .get(0);
                RecipeHandler.addRecipe(
                    new GrinderRecipe(
                        oreStack,
                        null,
                        new FluidStack(FluidRegistry.WATER, 1000),
                        ItemApi.getItem("dustLead", 2),
                        ItemApi.getItem("dustSmallSilver", 1),
                        ItemApi.getItem("dustSmallSulfur", 1),
                        null,
                        100,
                        120));
                RecipeHandler.addRecipe(
                    new GrinderRecipe(
                        oreStack,
                        new ItemStack(Items.water_bucket),
                        null,
                        ItemApi.getItem("dustLead", 2),
                        ItemApi.getItem("dustSmallSilver", 1),
                        ItemApi.getItem("dustSmallSulfur", 1),
                        new ItemStack(Items.bucket),
                        100,
                        120));

                RecipeHandler.addRecipe(
                    new GrinderRecipe(
                        oreStack,
                        null,
                        new FluidStack(ModFluids.fluidMercury, 1000),
                        ItemApi.getItem("dustLead", 2),
                        ItemApi.getItem("dustSilver", 1),
                        ItemApi.getItem("dustSmallSulfur", 1),
                        null,
                        100,
                        120));
                RecipeHandler.addRecipe(
                    new GrinderRecipe(
                        oreStack,
                        new ItemStack(ModItems.bucketMercury),
                        null,
                        ItemApi.getItem("dustLead", 2),
                        ItemApi.getItem("dustSilver", 1),
                        ItemApi.getItem("dustSmallSulfur", 1),
                        new ItemStack(Items.bucket),
                        100,
                        120));
            } catch (Exception e) {
                Core.logHelper.info("Failed to Load Grinder Recipe for Lead Ore");
            }
        }

        // Apatite Ore
        if (OreUtil.doesOreExistAndValid("oreApatite") & OreUtil.doesOreExistAndValid("gemApatite")) {
            try {
                ItemStack oreStack = OreDictionary.getOres("oreApatite")
                    .get(0);
                ItemStack gemStack = OreDictionary.getOres("gemApatite")
                    .get(0);
                gemStack.stackSize = 6;
                RecipeHandler.addRecipe(
                    new GrinderRecipe(
                        oreStack,
                        null,
                        new FluidStack(FluidRegistry.WATER, 1000),
                        gemStack,
                        gemStack,
                        ItemApi.getItem("dustSmallPhosphorous", 4),
                        null,
                        100,
                        120));
                RecipeHandler.addRecipe(
                    new GrinderRecipe(
                        oreStack,
                        new ItemStack(Items.water_bucket),
                        null,
                        gemStack,
                        gemStack,
                        ItemApi.getItem("dustSmallPhosphorous", 4),
                        new ItemStack(Items.bucket),
                        100,
                        120));
            } catch (Exception e) {
                Core.logHelper.info("Failed to Load Grinder Recipe for Apatite Ore");
            }
        }

        // Nether Quartz Ore
        if (OreUtil.doesOreExistAndValid("dustNetherQuartz")) {
            try {
                ItemStack dustStack = OreDictionary.getOres("dustNetherQuartz")
                    .get(0);
                dustStack.stackSize = 4;
                RecipeHandler.addRecipe(
                    new GrinderRecipe(
                        new ItemStack(Blocks.quartz_ore, 1),
                        null,
                        new FluidStack(FluidRegistry.WATER, 1000),
                        new ItemStack(Items.quartz, 2),
                        dustStack,
                        ItemApi.getItem("dustSmallNetherrack", 2),
                        null,
                        100,
                        120));
                RecipeHandler.addRecipe(
                    new GrinderRecipe(
                        new ItemStack(Blocks.quartz_ore, 1),
                        new ItemStack(Items.water_bucket),
                        null,
                        new ItemStack(Items.quartz, 2),
                        dustStack,
                        ItemApi.getItem("dustSmallNetherrack", 2),
                        new ItemStack(Items.bucket),
                        100,
                        120));
            } catch (Exception e) {
                Core.logHelper.info("Failed to Load Grinder Recipe for Nether Quartz Ore");
            }
        }

        // Certus Quartz Ore
        if (OreUtil.doesOreExistAndValid("oreCertusQuartz")) {
            try {
                ItemStack oreStack = OreDictionary.getOres("oreCertusQuartz")
                    .get(0);
                ItemStack gemStack = OreDictionary.getOres("crystalCertusQuartz")
                    .get(0);
                ItemStack dustStack = OreDictionary.getOres("dustCertusQuartz")
                    .get(0);
                dustStack.stackSize = 2;
                RecipeHandler.addRecipe(
                    new GrinderRecipe(
                        oreStack,
                        null,
                        new FluidStack(FluidRegistry.WATER, 1000),
                        gemStack,
                        dustStack,
                        null,
                        null,
                        100,
                        120));
                RecipeHandler.addRecipe(
                    new GrinderRecipe(
                        oreStack,
                        new ItemStack(Items.water_bucket),
                        null,
                        gemStack,
                        dustStack,
                        null,
                        new ItemStack(Items.bucket),
                        100,
                        120));
            } catch (Exception e) {
                Core.logHelper.info("Failed to Load Grinder Recipe for Certus Quartz Ore");
            }
        }

        // Charged Certus Quartz Ore
        if (OreUtil.doesOreExistAndValid("oreChargedCertusQuartz")) {
            try {
                ItemStack oreStack = OreDictionary.getOres("oreChargedCertusQuartz")
                    .get(0);
                ItemStack gemStack = OreDictionary.getOres("crystalChargedCertusQuartz")
                    .get(0);
                ItemStack dustStack = OreDictionary.getOres("dustCertusQuartz")
                    .get(0);
                dustStack.stackSize = 2;
                RecipeHandler.addRecipe(
                    new GrinderRecipe(
                        oreStack,
                        null,
                        new FluidStack(FluidRegistry.WATER, 1000),
                        gemStack,
                        dustStack,
                        null,
                        null,
                        100,
                        120));
                RecipeHandler.addRecipe(
                    new GrinderRecipe(
                        oreStack,
                        new ItemStack(Items.water_bucket),
                        null,
                        gemStack,
                        dustStack,
                        null,
                        new ItemStack(Items.bucket),
                        100,
                        120));
            } catch (Exception e) {
                Core.logHelper.info("Failed to Load Grinder Recipe for Charged Certus Quartz Ore");
            }
        }

        // Amethyst Ore
        if (OreUtil.doesOreExistAndValid("oreAmethyst") && OreUtil.doesOreExistAndValid("gemAmethyst")) {
            try {
                ItemStack oreStack = OreDictionary.getOres("oreAmethyst")
                    .get(0);
                ItemStack gemStack = OreDictionary.getOres("gemAmethyst")
                    .get(0);
                gemStack.stackSize = 2;
                ItemStack dustStack = OreDictionary.getOres("gemAmethyst")
                    .get(0);
                dustStack.stackSize = 1;
                RecipeHandler.addRecipe(
                    new GrinderRecipe(
                        oreStack,
                        null,
                        new FluidStack(FluidRegistry.WATER, 1000),
                        gemStack,
                        dustStack,
                        null,
                        null,
                        100,
                        120));
                RecipeHandler.addRecipe(
                    new GrinderRecipe(
                        oreStack,
                        new ItemStack(Items.water_bucket),
                        null,
                        gemStack,
                        dustStack,
                        null,
                        new ItemStack(Items.bucket),
                        100,
                        120));
            } catch (Exception e) {
                Core.logHelper.info("Failed to Load Grinder Recipe for Certus Quartz Ore");
            }
        }

        // Topaz Ore
        if (OreUtil.doesOreExistAndValid("oreTopaz") && OreUtil.doesOreExistAndValid("gemTopaz")) {
            try {
                ItemStack oreStack = OreDictionary.getOres("oreTopaz")
                    .get(0);
                ItemStack gemStack = OreDictionary.getOres("gemTopaz")
                    .get(0);
                gemStack.stackSize = 2;
                ItemStack dustStack = OreDictionary.getOres("gemTopaz")
                    .get(0);
                dustStack.stackSize = 1;
                RecipeHandler.addRecipe(
                    new GrinderRecipe(
                        oreStack,
                        null,
                        new FluidStack(FluidRegistry.WATER, 1000),
                        gemStack,
                        dustStack,
                        null,
                        null,
                        100,
                        120));
                RecipeHandler.addRecipe(
                    new GrinderRecipe(
                        oreStack,
                        new ItemStack(Items.water_bucket),
                        null,
                        gemStack,
                        dustStack,
                        null,
                        new ItemStack(Items.bucket),
                        100,
                        120));
            } catch (Exception e) {
                Core.logHelper.info("Failed to Load Grinder Recipe for Topaz Ore");
            }
        }

        // Tanzanite Ore
        if (OreUtil.doesOreExistAndValid("oreTanzanite") && OreUtil.doesOreExistAndValid("gemTanzanite")) {
            try {
                ItemStack oreStack = OreDictionary.getOres("oreTanzanite")
                    .get(0);
                ItemStack gemStack = OreDictionary.getOres("gemTanzanite")
                    .get(0);
                gemStack.stackSize = 2;
                ItemStack dustStack = OreDictionary.getOres("gemTanzanite")
                    .get(0);
                dustStack.stackSize = 1;
                RecipeHandler.addRecipe(
                    new GrinderRecipe(
                        oreStack,
                        null,
                        new FluidStack(FluidRegistry.WATER, 1000),
                        gemStack,
                        dustStack,
                        null,
                        null,
                        100,
                        120));
                RecipeHandler.addRecipe(
                    new GrinderRecipe(
                        oreStack,
                        new ItemStack(Items.water_bucket),
                        null,
                        gemStack,
                        dustStack,
                        null,
                        new ItemStack(Items.bucket),
                        100,
                        120));
            } catch (Exception e) {
                Core.logHelper.info("Failed to Load Grinder Recipe for Tanzanite Ore");
            }
        }

        // Malachite Ore
        if (OreUtil.doesOreExistAndValid("oreMalachite") && OreUtil.doesOreExistAndValid("gemMalachite")) {
            try {
                ItemStack oreStack = OreDictionary.getOres("oreMalachite")
                    .get(0);
                ItemStack gemStack = OreDictionary.getOres("gemMalachite")
                    .get(0);
                gemStack.stackSize = 2;
                ItemStack dustStack = OreDictionary.getOres("gemMalachite")
                    .get(0);
                dustStack.stackSize = 1;
                RecipeHandler.addRecipe(
                    new GrinderRecipe(
                        oreStack,
                        null,
                        new FluidStack(FluidRegistry.WATER, 1000),
                        gemStack,
                        dustStack,
                        null,
                        null,
                        100,
                        120));
                RecipeHandler.addRecipe(
                    new GrinderRecipe(
                        oreStack,
                        new ItemStack(Items.water_bucket),
                        null,
                        gemStack,
                        dustStack,
                        null,
                        new ItemStack(Items.bucket),
                        100,
                        120));
            } catch (Exception e) {
                Core.logHelper.info("Failed to Load Grinder Recipe for Malachite Ore");
            }
        }

        // Galena Ore
        RecipeHandler.addRecipe(
            new GrinderRecipe(
                new ItemStack(ModBlocks.ore, 1, 0),
                null,
                new FluidStack(FluidRegistry.WATER, 1000),
                ItemApi.getItem("dustGalena", 2),
                ItemApi.getItem("dustSmallSulfur", 1),
                ItemApi.getItem("dustSmallSilver", 1),
                null,
                100,
                120));
        RecipeHandler.addRecipe(
            new GrinderRecipe(
                new ItemStack(ModBlocks.ore, 1, 0),
                new ItemStack(Items.water_bucket),
                null,
                ItemApi.getItem("dustGalena", 2),
                ItemApi.getItem("dustSmallSulfur", 1),
                ItemApi.getItem("dustSmallSilver", 1),
                new ItemStack(Items.bucket),
                100,
                120));

        RecipeHandler.addRecipe(
            new GrinderRecipe(
                new ItemStack(ModBlocks.ore, 1, 0),
                null,
                new FluidStack(ModFluids.fluidMercury, 1000),
                ItemApi.getItem("dustGalena", 2),
                ItemApi.getItem("dustSmallSulfur", 1),
                ItemApi.getItem("dustSilver", 1),
                null,
                100,
                120));
        RecipeHandler.addRecipe(
            new GrinderRecipe(
                new ItemStack(ModBlocks.ore, 1, 0),
                new ItemStack(ModItems.bucketMercury),
                null,
                ItemApi.getItem("dustGalena", 2),
                ItemApi.getItem("dustSmallSulfur", 1),
                ItemApi.getItem("dustSilver", 1),
                new ItemStack(Items.bucket),
                100,
                120));

        // Ruby Ore
        RecipeHandler.addRecipe(
            new GrinderRecipe(
                new ItemStack(ModBlocks.ore, 1, 2),
                null,
                new FluidStack(FluidRegistry.WATER, 1000),
                ItemApi.getItem("gemRuby", 1),
                ItemApi.getItem("dustSmallRuby", 6),
                ItemApi.getItem("dustSmallChrome", 2),
                null,
                100,
                120));
        RecipeHandler.addRecipe(
            new GrinderRecipe(
                new ItemStack(ModBlocks.ore, 1, 2),
                new ItemStack(Items.water_bucket),
                null,
                ItemApi.getItem("gemRuby", 1),
                ItemApi.getItem("dustSmallRuby", 6),
                ItemApi.getItem("dustSmallChrome", 2),
                new ItemStack(Items.bucket),
                100,
                120));

        // Sapphire Ore
        RecipeHandler.addRecipe(
            new GrinderRecipe(
                new ItemStack(ModBlocks.ore, 1, 3),
                null,
                new FluidStack(FluidRegistry.WATER, 1000),
                ItemApi.getItem("gemSapphire", 1),
                ItemApi.getItem("dustSmallSapphire", 6),
                ItemApi.getItem("dustSmallAluminium", 2),
                null,
                100,
                120));
        RecipeHandler.addRecipe(
            new GrinderRecipe(
                new ItemStack(ModBlocks.ore, 1, 3),
                new ItemStack(Items.water_bucket),
                null,
                ItemApi.getItem("gemSapphire", 1),
                ItemApi.getItem("dustSmallSapphire", 6),
                ItemApi.getItem("dustSmallAluminium", 2),
                new ItemStack(Items.bucket),
                100,
                120));

        // Bauxite Ore
        RecipeHandler.addRecipe(
            new GrinderRecipe(
                new ItemStack(ModBlocks.ore, 1, 4),
                null,
                new FluidStack(FluidRegistry.WATER, 1000),
                ItemApi.getItem("dustBauxite", 2),
                ItemApi.getItem("dustSmallGrossular", 4),
                ItemApi.getItem("dustSmallTitanium", 4),
                null,
                100,
                120));
        RecipeHandler.addRecipe(
            new GrinderRecipe(
                new ItemStack(ModBlocks.ore, 1, 4),
                new ItemStack(Items.water_bucket),
                null,
                ItemApi.getItem("dustBauxite", 2),
                ItemApi.getItem("dustSmallGrossular", 4),
                ItemApi.getItem("dustSmallTitanium", 4),
                new ItemStack(Items.bucket),
                100,
                120));

        // Pyrite Ore
        RecipeHandler.addRecipe(
            new GrinderRecipe(
                new ItemStack(ModBlocks.ore, 1, 5),
                null,
                new FluidStack(FluidRegistry.WATER, 1000),
                ItemApi.getItem("dustPyrite", 2),
                ItemApi.getItem("dustSmallSulfur", 1),
                ItemApi.getItem("dustSmallPhosphorous", 1),
                null,
                100,
                120));
        RecipeHandler.addRecipe(
            new GrinderRecipe(
                new ItemStack(ModBlocks.ore, 1, 5),
                new ItemStack(Items.water_bucket),
                null,
                ItemApi.getItem("dustPyrite", 2),
                ItemApi.getItem("dustSmallSulfur", 1),
                ItemApi.getItem("dustSmallPhosphorous", 1),
                new ItemStack(Items.bucket),
                100,
                120));

        // Cinnabar Ore
        RecipeHandler.addRecipe(
            new GrinderRecipe(
                new ItemStack(ModBlocks.ore, 1, 6),
                null,
                new FluidStack(FluidRegistry.WATER, 1000),
                ItemApi.getItem("dustCinnabar", 2),
                ItemApi.getItem("dustSmallRedstone", 1),
                ItemApi.getItem("dustSmallGlowstone", 1),
                null,
                100,
                120));
        RecipeHandler.addRecipe(
            new GrinderRecipe(
                new ItemStack(ModBlocks.ore, 1, 6),
                new ItemStack(Items.water_bucket),
                null,
                ItemApi.getItem("dustCinnabar", 2),
                ItemApi.getItem("dustSmallRedstone", 1),
                ItemApi.getItem("dustSmallGlowstone", 1),
                new ItemStack(Items.bucket),
                100,
                120));

        // Sphalerite Ore
        RecipeHandler.addRecipe(
            new GrinderRecipe(
                new ItemStack(ModBlocks.ore, 1, 7),
                null,
                new FluidStack(FluidRegistry.WATER, 1000),
                ItemApi.getItem("dustSphalerite", 2),
                ItemApi.getItem("dustSmallZinc", 1),
                ItemApi.getItem("dustSmallYellowGarnet", 1),
                null,
                100,
                120));
        RecipeHandler.addRecipe(
            new GrinderRecipe(
                new ItemStack(ModBlocks.ore, 1, 7),
                new ItemStack(Items.water_bucket),
                null,
                ItemApi.getItem("dustSphalerite", 2),
                ItemApi.getItem("dustSmallZinc", 1),
                ItemApi.getItem("dustSmallYellowGarnet", 1),
                new ItemStack(Items.bucket),
                100,
                120));

        RecipeHandler.addRecipe(
            new GrinderRecipe(
                new ItemStack(ModBlocks.ore, 1, 7),
                null,
                new FluidStack(ModFluids.fluidSodiumpersulfate, 1000),
                ItemApi.getItem("dustSphalerite", 2),
                ItemApi.getItem("dustZinc", 1),
                ItemApi.getItem("dustSmallYellowGarnet", 1),
                null,
                100,
                120));
        RecipeHandler.addRecipe(
            new GrinderRecipe(
                new ItemStack(ModBlocks.ore, 1, 7),
                new ItemStack(ModItems.bucketSodiumpersulfate),
                null,
                ItemApi.getItem("dustSphalerite", 2),
                ItemApi.getItem("dustZinc", 1),
                ItemApi.getItem("dustSmallYellowGarnet", 1),
                new ItemStack(Items.bucket),
                100,
                120));

        // Tungsten Ore
        RecipeHandler.addRecipe(
            new GrinderRecipe(
                new ItemStack(ModBlocks.ore, 1, 8),
                null,
                new FluidStack(FluidRegistry.WATER, 1000),
                ItemApi.getItem("dustTungsten", 2),
                ItemApi.getItem("dustSmallManganese", 1),
                ItemApi.getItem("dustSmallSilver", 1),
                null,
                100,
                120));
        RecipeHandler.addRecipe(
            new GrinderRecipe(
                new ItemStack(ModBlocks.ore, 1, 8),
                new ItemStack(Items.water_bucket),
                null,
                ItemApi.getItem("dustTungsten", 2),
                ItemApi.getItem("dustSmallManganese", 1),
                ItemApi.getItem("dustSmallSilver", 1),
                new ItemStack(Items.bucket),
                100,
                120));

        RecipeHandler.addRecipe(
            new GrinderRecipe(
                new ItemStack(ModBlocks.ore, 1, 8),
                null,
                new FluidStack(ModFluids.fluidMercury, 1000),
                ItemApi.getItem("dustTungsten", 2),
                ItemApi.getItem("dustSmallManganese", 1),
                ItemApi.getItem("dustSilver", 2),
                null,
                100,
                120));
        RecipeHandler.addRecipe(
            new GrinderRecipe(
                new ItemStack(ModBlocks.ore, 1, 8),
                new ItemStack(ModItems.bucketMercury),
                null,
                ItemApi.getItem("dustTungsten", 2),
                ItemApi.getItem("dustSmallManganese", 1),
                ItemApi.getItem("dustSilver", 2),
                new ItemStack(Items.bucket),
                100,
                120));

        // Sheldonite Ore
        RecipeHandler.addRecipe(
            new GrinderRecipe(
                new ItemStack(ModBlocks.ore, 1, 9),
                null,
                new FluidStack(FluidRegistry.WATER, 1000),
                ItemApi.getItem("dustPlatinum", 2),
                ItemApi.getItem("dustSmallIridium", 1),
                ItemApi.getItem("dustSmallIridium", 1),
                null,
                100,
                120));
        RecipeHandler.addRecipe(
            new GrinderRecipe(
                new ItemStack(ModBlocks.ore, 1, 9),
                new ItemStack(Items.water_bucket),
                null,
                ItemApi.getItem("dustPlatinum", 2),
                ItemApi.getItem("dustSmallIridium", 1),
                ItemApi.getItem("dustSmallIridium", 1),
                new ItemStack(Items.bucket),
                100,
                120));

        RecipeHandler.addRecipe(
            new GrinderRecipe(
                new ItemStack(ModBlocks.ore, 1, 9),
                null,
                new FluidStack(ModFluids.fluidMercury, 1000),
                ItemApi.getItem("dustPlatinum", 3),
                ItemApi.getItem("dustSmallIridium", 1),
                ItemApi.getItem("dustSmallIridium", 1),
                null,
                100,
                120));
        RecipeHandler.addRecipe(
            new GrinderRecipe(
                new ItemStack(ModBlocks.ore, 1, 9),
                new ItemStack(ModItems.bucketMercury),
                null,
                ItemApi.getItem("dustPlatinum", 3),
                ItemApi.getItem("dustSmallIridium", 1),
                ItemApi.getItem("dustSmallIridium", 1),
                new ItemStack(Items.bucket),
                100,
                120));

        // Peridot Ore
        RecipeHandler.addRecipe(
            new GrinderRecipe(
                new ItemStack(ModBlocks.ore, 1, 10),
                null,
                new FluidStack(FluidRegistry.WATER, 1000),
                ItemApi.getItem("gemPeridot", 1),
                ItemApi.getItem("dustSmallPeridot", 6),
                ItemApi.getItem("dustSmallPyrope", 2),
                null,
                100,
                120));
        RecipeHandler.addRecipe(
            new GrinderRecipe(
                new ItemStack(ModBlocks.ore, 1, 10),
                new ItemStack(Items.water_bucket),
                null,
                ItemApi.getItem("gemPeridot", 1),
                ItemApi.getItem("dustSmallPeridot", 6),
                ItemApi.getItem("dustSmallPyrope", 2),
                new ItemStack(Items.bucket),
                100,
                120));

        // Sodalite Ore
        RecipeHandler.addRecipe(
            new GrinderRecipe(
                new ItemStack(ModBlocks.ore, 1, 11),
                null,
                new FluidStack(FluidRegistry.WATER, 1000),
                ItemApi.getItem("dustSodalite", 12),
                ItemApi.getItem("dustSmallLazurite", 4),
                ItemApi.getItem("dustSmallLapis", 4),
                null,
                100,
                120));
        RecipeHandler.addRecipe(
            new GrinderRecipe(
                new ItemStack(ModBlocks.ore, 1, 11),
                new ItemStack(Items.water_bucket),
                null,
                ItemApi.getItem("dustSodalite", 12),
                ItemApi.getItem("dustSmallLazurite", 4),
                ItemApi.getItem("dustSmallLapis", 4),
                new ItemStack(Items.bucket),
                100,
                120));

        // Tetrahedrite Ore
        RecipeHandler.addRecipe(
            new GrinderRecipe(
                new ItemStack(ModBlocks.ore, 1, 12),
                null,
                new FluidStack(FluidRegistry.WATER, 1000),
                ItemApi.getItem("dustTetrahedrite", 2),
                ItemApi.getItem("dustSmallAntimony", 1),
                ItemApi.getItem("dustSmallZinc", 1),
                null,
                100,
                120));
        RecipeHandler.addRecipe(
            new GrinderRecipe(
                new ItemStack(ModBlocks.ore, 1, 12),
                new ItemStack(Items.water_bucket),
                null,
                ItemApi.getItem("dustTetrahedrite", 2),
                ItemApi.getItem("dustSmallAntimony", 1),
                ItemApi.getItem("dustSmallZinc", 1),
                new ItemStack(Items.bucket),
                100,
                120));

        RecipeHandler.addRecipe(
            new GrinderRecipe(
                new ItemStack(ModBlocks.ore, 1, 12),
                null,
                new FluidStack(ModFluids.fluidSodiumpersulfate, 1000),
                ItemApi.getItem("dustTetrahedrite", 3),
                ItemApi.getItem("dustSmallAntimony", 1),
                ItemApi.getItem("dustSmallZinc", 1),
                null,
                100,
                120));
        RecipeHandler.addRecipe(
            new GrinderRecipe(
                new ItemStack(ModBlocks.ore, 1, 12),
                new ItemStack(ModItems.bucketSodiumpersulfate),
                null,
                ItemApi.getItem("dustTetrahedrite", 3),
                ItemApi.getItem("dustSmallAntimony", 1),
                ItemApi.getItem("dustSmallZinc", 1),
                new ItemStack(Items.bucket),
                100,
                120));
    }

    static void addChemicalReactorRecipes() {
        RecipeHandler.addRecipe(
            new ChemicalReactorRecipe(
                ItemCells.getCellByName("calcium", 1),
                ItemCells.getCellByName("carbon", 1),
                ItemCells.getCellByName("calciumCarbonate", 2),
                240,
                30));
        RecipeHandler.addRecipe(
            new ChemicalReactorRecipe(
                new ItemStack(Items.gold_nugget, 8),
                new ItemStack(Items.melon, 1),
                new ItemStack(Items.speckled_melon, 1),
                40,
                30));
        RecipeHandler.addRecipe(
            new ChemicalReactorRecipe(
                ItemCells.getCellByName("nitrogen", 1),
                ItemCells.getCellByName("carbon", 1),
                ItemCells.getCellByName("nitrocarbon", 2),
                1500,
                30));
        RecipeHandler.addRecipe(
            new ChemicalReactorRecipe(
                ItemCells.getCellByName("carbon", 1),
                ItemCells.getCellByName("hydrogen", 4),
                ItemCells.getCellByName("methane", 5),
                3500,
                30));
        RecipeHandler.addRecipe(
            new ChemicalReactorRecipe(
                ItemCells.getCellByName("sulfur", 1),
                ItemCells.getCellByName("sodium", 1),
                ItemCells.getCellByName("sodiumSulfide", 2),
                100,
                30));
        RecipeHandler.addRecipe(
            new ChemicalReactorRecipe(
                ItemApi.getItem("dustBlaze", 1),
                new ItemStack(Items.ender_pearl, 1),
                new ItemStack(Items.ender_eye, 1),
                40,
                30));
        RecipeHandler.addRecipe(
            new ChemicalReactorRecipe(
                new ItemStack(Items.gold_nugget, 8),
                new ItemStack(Items.carrot, 1),
                new ItemStack(Items.golden_carrot, 1),
                40,
                30));
        RecipeHandler.addRecipe(
            new ChemicalReactorRecipe(
                ItemCells.getCellByName("glyceryl", 1),
                ItemCells.getCellByName("diesel", 4),
                ItemCells.getCellByName("nitroDiesel", 5),
                1000,
                30));
        RecipeHandler.addRecipe(
            new ChemicalReactorRecipe(
                ItemApi.getItem("ingotGold", 8),
                new ItemStack(Items.apple, 1),
                new ItemStack(Items.golden_apple, 1),
                40,
                30));
        RecipeHandler.addRecipe(
            new ChemicalReactorRecipe(
                new ItemStack(Blocks.gold_block, 8),
                new ItemStack(Items.apple, 1),
                new ItemStack(Items.golden_apple, 1, 1),
                40,
                30));
        RecipeHandler.addRecipe(
            new ChemicalReactorRecipe(
                ItemApi.getItem("dustBlaze", 1),
                new ItemStack(Items.slime_ball, 1),
                new ItemStack(Items.magma_cream, 1),
                40,
                30));
    }

    static void addIndustrialElectrolyzerRecipes() {
        RecipeHandler.addRecipe(
            new IndustrialElectrolyzerRecipe(
                ItemCells.getCellByName("nitrocarbon", 2),
                null,
                ItemCells.getCellByName("nitrogen"),
                ItemCells.getCellByName("carbon"),
                null,
                null,
                80,
                60));

        RecipeHandler.addRecipe(
            new IndustrialElectrolyzerRecipe(
                ItemApi.getItem("dustPyrite", 3),
                null,
                ItemApi.getItem("dustIron"),
                ItemApi.getItem("dustSulfur"),
                null,
                null,
                120,
                128));

        RecipeHandler.addRecipe(
            new IndustrialElectrolyzerRecipe(
                ItemApi.getItem("dustSphalerite", 2),
                null,
                ItemApi.getItem("dustZinc"),
                ItemApi.getItem("dustSulfur"),
                null,
                null,
                150,
                100));
    }
}
