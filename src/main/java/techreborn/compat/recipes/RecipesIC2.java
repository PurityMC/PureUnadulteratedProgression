package techreborn.compat.recipes;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.oredict.OreDictionary;

import page.codeberg.unix_supremacist.pup.api.ItemApi;
import reborncore.common.util.CraftingHelper;
import reborncore.common.util.OreUtil;
import reborncore.common.util.RecipeRemover;
import techreborn.Core;
import techreborn.api.reactor.FusionReactorRecipe;
import techreborn.api.reactor.FusionReactorRecipeHelper;
import techreborn.api.recipe.RecipeHandler;
import techreborn.api.recipe.machines.*;
import techreborn.compat.CompatManager;
import techreborn.compat.ICompatModule;
import techreborn.config.ConfigTechReborn;
import techreborn.init.ModBlocks;
import techreborn.init.ModFluids;
import techreborn.init.ModItems;
import techreborn.items.*;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import ic2.api.info.IC2Classic;
import ic2.api.item.IC2Items;
import ic2.api.recipe.RecipeInputOreDict;
import ic2.api.recipe.RecipeOutput;
import ic2.api.recipe.Recipes;

public class RecipesIC2 implements ICompatModule {

    @Override
    public void preInit(FMLPreInitializationEvent event) {

    }

    @Override
    public void init(FMLInitializationEvent event) {
        removeIc2Recipes();
        addShappedIc2Recipes();
        addTRMaceratorRecipes();
        addTROreWashingRecipes();
        addTRThermalCentrifugeRecipes();
        addMetalFormerRecipes();
        addTRRecipes();

        FusionReactorRecipeHelper.registerRecipe(
                new FusionReactorRecipe(
                        ItemCells.getCellByName("wolframium"),
                        ItemCells.getCellByName("lithium"),
                        IC2Items.getItem("iridiumOre"),
                        90000000,
                        -2048,
                        1024));
    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {
        // Has to be done later, not sure why
        RecipeRemover.removeAnyRecipe(IC2Items.getItem("iridiumPlate"));
    }

    @Override
    public void serverStarting(FMLServerStartingEvent event) {

    }

    public void addTRRecipes() {
        ItemStack lapotron = new ItemStack(
                IC2Items.getItem("lapotronCrystal").getItem(),
                1,
                OreDictionary.WILDCARD_VALUE);

        // General
        CraftingHelper.addShapelessOreRecipe(new ItemStack(ModItems.manuel), IC2Items.getItem("plateiron"), Items.book);

        CraftingHelper.addShapedOreRecipe(
                ItemApi.getItem("machineParts", 16),
                "CSC",
                "SCS",
                "CSC",
                'S',
                "ingotSteel",
                'C',
                "circuitBasic");

        CraftingHelper.addShapedOreRecipe(
                ItemApi.getItem("circuitMaster", 4),
                "ATA",
                "LIL",
                "ATA",
                'T',
                "plateTungsten",
                'I',
                "plateIridium",
                'A',
                "circuitAdvanced",
                'L',
                lapotron);

        CraftingHelper.addShapedOreRecipe(
                ItemApi.getItem("craftingSuperconductor", 4),
                "CCC",
                "TIT",
                "EEE",
                'E',
                ItemApi.getItem("circuitMaster"),
                'C',
                ItemApi.getItem("heliumCoolantSimple"),
                'T',
                "ingotTungsten",
                'I',
                IC2Items.getItem("iridiumPlate"));

        CraftingHelper.addShapedOreRecipe(
                new ItemStack(ModItems.lapotronicOrb),
                "LLL",
                "LPL",
                "LLL",
                'L',
                new ItemStack(IC2Items.getItem("lapotronCrystal").getItem(), 1, OreDictionary.WILDCARD_VALUE),
                'P',
                IC2Items.getItem("iridiumPlate"));

        CraftingHelper.addShapedOreRecipe(
                new ItemStack(ModBlocks.industrialSawmill),
                "PAP",
                "SSS",
                "ACA",
                'P',
                IC2Items.getItem("pump"),
                'A',
                IC2Items.getItem("advancedCircuit"),
                'S',
                ItemApi.getItem("sawBladeDiamond"),
                'C',
                IC2Items.getItem("advancedMachine"));

        CraftingHelper.addShapedOreRecipe(
                new ItemStack(ModBlocks.ComputerCube),
                "DME",
                "MAM",
                "EMD",
                'E',
                ItemApi.getItem("circuitMaster"),
                'D',
                ItemApi.getItem("circuitQuantum"),
                'M',
                ItemApi.getItem("computerMonitor"),
                'A',
                IC2Items.getItem("advancedMachine"));

        CraftingHelper.addShapedOreRecipe(
                new ItemStack(ModBlocks.MatterFabricator),
                "ETE",
                "AOA",
                "ETE",
                'E',
                ItemApi.getItem("circuitMaster"),
                'T',
                IC2Items.getItem("teleporter"),
                'A',
                ModBlocks.HighAdvancedMachineBlock,
                'O',
                ModItems.lapotronicOrb);

        CraftingHelper.addShapedOreRecipe(
                new ItemStack(ModBlocks.thermalGenerator),
                "III",
                "IHI",
                "CGC",
                'I',
                "plateInvar",
                'H',
                IC2Items.getItem("reinforcedGlass"),
                'C',
                "circuitBasic",
                'G',
                new ItemStack(ModBlocks.heatGenerator));

        CraftingHelper.addShapedOreRecipe(
                new ItemStack(ModBlocks.thermalGenerator),
                "AAA",
                "AHA",
                "CGC",
                'A',
                "plateAluminium",
                'H',
                IC2Items.getItem("reinforcedGlass"),
                'C',
                "circuitBasic",
                'G',
                new ItemStack(ModBlocks.heatGenerator));

        CraftingHelper.addShapedOreRecipe(
                new ItemStack(ModBlocks.heatGenerator),
                "III",
                "IHI",
                "CGC",
                'I',
                "plateIron",
                'H',
                new ItemStack(Blocks.iron_bars),
                'C',
                "circuitBasic",
                'G',
                IC2Items.getItem("geothermalGenerator"));

        CraftingHelper.addShapedOreRecipe(
                new ItemStack(ModBlocks.Gasturbine),
                "IAI",
                "WGW",
                "IAI",
                'I',
                "plateInvar",
                'A',
                IC2Items.getItem("advancedCircuit"),
                'W',
                IC2Items.getItem("windMill"),
                'G',
                IC2Items.getItem("reinforcedGlass"));

        CraftingHelper.addShapedOreRecipe(
                new ItemStack(ModBlocks.Gasturbine),
                "IAI",
                "WGW",
                "IAI",
                'I',
                "plateAluminium",
                'A',
                IC2Items.getItem("advancedCircuit"),
                'W',
                IC2Items.getItem("windMill"),
                'G',
                IC2Items.getItem("reinforcedGlass"));

        CraftingHelper.addShapedOreRecipe(
                new ItemStack(ModBlocks.Semifluidgenerator),
                "III",
                "IHI",
                "CGC",
                'I',
                "plateIron",
                'H',
                IC2Items.getItem("reinforcedGlass"),
                'C',
                "circuitBasic",
                'G',
                IC2Items.getItem("generator"));

        CraftingHelper.addShapedOreRecipe(
                new ItemStack(ModBlocks.Semifluidgenerator),
                "AAA",
                "AHA",
                "CGC",
                'A',
                "plateAluminium",
                'H',
                IC2Items.getItem("reinforcedGlass"),
                'C',
                "circuitBasic",
                'G',
                IC2Items.getItem("generator"));

        CraftingHelper.addShapedOreRecipe(
                new ItemStack(ModBlocks.DieselGenerator),
                "III",
                "I I",
                "CGC",
                'I',
                "plateIron",
                'C',
                "circuitBasic",
                'G',
                IC2Items.getItem("generator"));

        CraftingHelper.addShapedOreRecipe(
                new ItemStack(ModBlocks.DieselGenerator),
                "AAA",
                "A A",
                "CGC",
                'A',
                "plateAluminium",
                'C',
                "circuitBasic",
                'G',
                IC2Items.getItem("generator"));

        CraftingHelper.addShapedOreRecipe(
                new ItemStack(ModBlocks.MagicalAbsorber),
                "CSC",
                "IBI",
                "CAC",
                'C',
                "circuitMaster",
                'S',
                "craftingSuperconductor",
                'B',
                Blocks.beacon,
                'A',
                ModBlocks.Magicenergeyconverter,
                'I',
                IC2Items.getItem("iridiumPlate"));

        CraftingHelper.addShapedOreRecipe(
                new ItemStack(ModBlocks.Magicenergeyconverter),
                "CTC",
                "PBP",
                "CLC",
                'C',
                "circuitAdvanced",
                'P',
                "platePlatinum",
                'B',
                Blocks.beacon,
                'L',
                IC2Items.getItem("lapotronCrystal"),
                'T',
                IC2Items.getItem("teleporter"));

        CraftingHelper.addShapedOreRecipe(
                new ItemStack(ModBlocks.Dragoneggenergysiphoner),
                "CTC",
                "ISI",
                "CBC",
                'I',
                IC2Items.getItem("iridiumPlate"),
                'C',
                "circuitMaster",
                'B',
                "batteryUltimate",
                'S',
                ModBlocks.Supercondensator,
                'T',
                IC2Items.getItem("teleporter"));

        CraftingHelper.addShapedOreRecipe(
                new ItemStack(ModBlocks.centrifuge),
                "SCS",
                "BEB",
                "SCS",
                'S',
                "plateSteel",
                'C',
                "circuitAdvanced",
                'B',
                IC2Items.getItem("advancedMachine"),
                'E',
                IC2Items.getItem("extractor"));

        CraftingHelper.addShapedOreRecipe(
                new ItemStack(ModBlocks.IndustrialElectrolyzer),
                "SXS",
                "CEC",
                "SMS",
                'S',
                "plateSteel",
                'C',
                "circuitAdvanced",
                'X',
                IC2Items.getItem("extractor"),
                'E',
                IC2Items.getItem("electrolyzer"),
                'M',
                IC2Items.getItem("magnetizer"));

        CraftingHelper.addShapedOreRecipe(
                new ItemStack(ModBlocks.BlastFurnace),
                "CHC",
                "HBH",
                "FHF",
                'H',
                ItemApi.getItem("coilCupronickel"),
                'C',
                "circuitAdvanced",
                'B',
                IC2Items.getItem("advancedMachine"),
                'F',
                IC2Items.getItem("inductionFurnace"));

        CraftingHelper.addShapedOreRecipe(
                new ItemStack(ModBlocks.Grinder),
                "ECP",
                "GGG",
                "CBC",
                'E',
                ModBlocks.IndustrialElectrolyzer,
                'P',
                IC2Items.getItem("pump"),
                'C',
                "circuitAdvanced",
                'B',
                IC2Items.getItem("advancedMachine"),
                'G',
                "craftingGrinder");

        CraftingHelper.addShapedOreRecipe(
                new ItemStack(ModBlocks.ImplosionCompressor),
                "ABA",
                "CPC",
                "ABA",
                'A',
                IC2Items.getItem("advancedAlloy"),
                'C',
                "circuitAdvanced",
                'B',
                IC2Items.getItem("advancedMachine"),
                'P',
                IC2Items.getItem("compressor"));

        CraftingHelper.addShapedOreRecipe(
                new ItemStack(ModBlocks.VacuumFreezer),
                "SPS",
                "CGC",
                "SPS",
                'S',
                "plateSteel",
                'C',
                "circuitAdvanced",
                'G',
                IC2Items.getItem("reinforcedGlass"),
                'P',
                IC2Items.getItem("pump"));

        CraftingHelper.addShapedOreRecipe(
                new ItemStack(ModBlocks.Distillationtower),
                "CMC",
                "PBP",
                "EME",
                'E',
                ModBlocks.IndustrialElectrolyzer,
                'M',
                "circuitMaster",
                'B',
                IC2Items.getItem("advancedMachine"),
                'C',
                ModBlocks.centrifuge,
                'P',
                IC2Items.getItem("pump"));

        CraftingHelper.addShapedOreRecipe(
                new ItemStack(ModBlocks.AlloyFurnace),
                "III",
                "F F",
                "III",
                'I',
                "plateIron",
                'F',
                IC2Items.getItem("ironFurnace"));

        CraftingHelper.addShapedOreRecipe(
                new ItemStack(ModBlocks.ChemicalReactor),
                "IMI",
                "CPC",
                "IEI",
                'I',
                "plateInvar",
                'C',
                "circuitAdvanced",
                'M',
                IC2Items.getItem("magnetizer"),
                'P',
                IC2Items.getItem("compressor"),
                'E',
                IC2Items.getItem("extractor"));

        CraftingHelper.addShapedOreRecipe(
                new ItemStack(ModBlocks.ChemicalReactor),
                "AMA",
                "CPC",
                "AEA",
                'A',
                "plateAluminium",
                'C',
                "circuitAdvanced",
                'M',
                IC2Items.getItem("magnetizer"),
                'P',
                IC2Items.getItem("compressor"),
                'E',
                IC2Items.getItem("extractor"));

        CraftingHelper.addShapedOreRecipe(
                new ItemStack(ModBlocks.RollingMachine),
                "PCP",
                "MBM",
                "PCP",
                'P',
                "craftingPiston",
                'C',
                "circuitAdvanced",
                'M',
                IC2Items.getItem("compressor"),
                'B',
                IC2Items.getItem("machine"));

        CraftingHelper.addShapedOreRecipe(
                new ItemStack(ModBlocks.ElectricCraftingTable),
                "ITI",
                "IBI",
                "ICI",
                'I',
                "plateIron",
                'C',
                "circuitAdvanced",
                'T',
                "crafterWood",
                'B',
                IC2Items.getItem("machine"));

        CraftingHelper.addShapedOreRecipe(
                new ItemStack(ModBlocks.ElectricCraftingTable),
                "ATA",
                "ABA",
                "ACA",
                'A',
                "plateAluminium",
                'C',
                "circuitAdvanced",
                'T',
                "crafterWood",
                'B',
                IC2Items.getItem("machine"));

        CraftingHelper.addShapedOreRecipe(
                new ItemStack(ModBlocks.ChunkLoader),
                "SCS",
                "CMC",
                "SCS",
                'S',
                "plateSteel",
                'C',
                "circuitMaster",
                'M',
                ItemApi.getItem("partAdvanced"));

        CraftingHelper.addShapedOreRecipe(
                new ItemStack(ModBlocks.Lesu),
                " L ",
                "CBC",
                " M ",
                'L',
                IC2Items.getItem("lvTransformer"),
                'C',
                "circuitAdvanced",
                'M',
                IC2Items.getItem("mvTransformer"),
                'B',
                ModBlocks.LesuStorage);

        CraftingHelper.addShapedOreRecipe(
                new ItemStack(ModBlocks.HighAdvancedMachineBlock),
                "CTC",
                "TBT",
                "CTC",
                'C',
                "plateChrome",
                'T',
                "plateTitanium",
                'B',
                IC2Items.getItem("advancedMachine"));

        CraftingHelper.addShapedOreRecipe(
                new ItemStack(ModBlocks.MachineCasing, 4, 0),
                "III",
                "CBC",
                "III",
                'I',
                "plateIron",
                'C',
                "circuitBasic",
                'B',
                IC2Items.getItem("machine"));

        CraftingHelper.addShapedOreRecipe(
                new ItemStack(ModBlocks.MachineCasing, 4, 1),
                "SSS",
                "CBC",
                "SSS",
                'S',
                "plateSteel",
                'C',
                "circuitAdvanced",
                'B',
                IC2Items.getItem("advancedMachine"));

        CraftingHelper.addShapedOreRecipe(
                new ItemStack(ModBlocks.MachineCasing, 4, 2),
                "HHH",
                "CBC",
                "HHH",
                'H',
                "plateChrome",
                'C',
                "circuitElite",
                'B',
                ModBlocks.HighAdvancedMachineBlock);

        CraftingHelper.addShapedOreRecipe(
                new ItemStack(ModBlocks.quantumChest),
                "DCD",
                "ATA",
                "DQD",
                'D',
                ItemApi.getItem("circuitQuantum"),
                'C',
                ItemApi.getItem("computerMonitor"),
                'A',
                ModBlocks.HighAdvancedMachineBlock,
                'Q',
                ModBlocks.digitalChest,
                'T',
                IC2Items.getItem("teleporter"));

        CraftingHelper.addShapedOreRecipe(
                new ItemStack(ModBlocks.PlasmaGenerator),
                "PPP",
                "PTP",
                "CGC",
                'P',
                ItemApi.getItem("plateTungstensteel"),
                'T',
                IC2Items.getItem("hvTransformer"),
                'G',
                IC2Items.getItem("generator"),
                'C',
                ItemApi.getItem("circuitMaster"));

        CraftingHelper.addShapedOreRecipe(
                ItemApi.getItem("boardBasic", 2),
                "PPP",
                "PCP",
                "PPP",
                'P',
                "plateIron",
                'C',
                IC2Items.getItem("electronicCircuit"));

        CraftingHelper.addShapedOreRecipe(
                ItemApi.getItem("circuitBio", 4),
                "ASA",
                "SIS",
                "ASA",
                'A',
                IC2Items.getItem("advancedCircuit"),
                'S',
                ItemApi.getItem("circuitData"),
                'I',
                "plateIridium");

        // Smetling
        GameRegistry.addSmelting(ItemApi.getItem("dustCopper", 1), IC2Items.getItem("copperIngot"), 1F);
        GameRegistry.addSmelting(ItemApi.getItem("dustTin", 1), IC2Items.getItem("tinIngot"), 1F);
        GameRegistry.addSmelting(ItemApi.getItem("dustBronze", 1), IC2Items.getItem("bronzeIngot"), 1F);
        GameRegistry.addSmelting(ItemApi.getItem("dustLead", 1), IC2Items.getItem("leadIngot"), 1F);
        GameRegistry.addSmelting(ItemApi.getItem("dustSilver", 1), IC2Items.getItem("silverIngot"), 1F);

        // Saw mill
        ItemStack pulpStack = OreDictionary.getOres("pulpWood").get(0);
        RecipeHandler.addRecipe(
                new IndustrialSawmillRecipe(
                        new ItemStack(Blocks.log, 1, 0),
                        IC2Items.getItem("waterCell"),
                        null,
                        new ItemStack(Blocks.planks, 6, 0),
                        pulpStack,
                        IC2Items.getItem("cell"),
                        200,
                        30,
                        false));
        RecipeHandler.addRecipe(
                new IndustrialSawmillRecipe(
                        new ItemStack(Blocks.log, 1, 0),
                        IC2Items.getItem("waterCell"),
                        null,
                        new ItemStack(Blocks.planks, 6, 0),
                        pulpStack,
                        IC2Items.getItem("cell"),
                        200,
                        30,
                        false));
        RecipeHandler.addRecipe(
                new IndustrialSawmillRecipe(
                        new ItemStack(Blocks.log, 1, 2),
                        IC2Items.getItem("waterCell"),
                        null,
                        new ItemStack(Blocks.planks, 6, 2),
                        pulpStack,
                        IC2Items.getItem("cell"),
                        200,
                        30,
                        false));
        RecipeHandler.addRecipe(
                new IndustrialSawmillRecipe(
                        new ItemStack(Blocks.log, 1, 3),
                        IC2Items.getItem("waterCell"),
                        null,
                        new ItemStack(Blocks.planks, 6, 3),
                        pulpStack,
                        IC2Items.getItem("cell"),
                        200,
                        30,
                        false));
        RecipeHandler.addRecipe(
                new IndustrialSawmillRecipe(
                        new ItemStack(Blocks.log2, 1, 0),
                        IC2Items.getItem("waterCell"),
                        null,
                        new ItemStack(Blocks.planks, 6, 4),
                        pulpStack,
                        IC2Items.getItem("cell"),
                        200,
                        30,
                        false));
        RecipeHandler.addRecipe(
                new IndustrialSawmillRecipe(
                        new ItemStack(Blocks.log2, 1, 1),
                        IC2Items.getItem("waterCell"),
                        null,
                        new ItemStack(Blocks.planks, 6, 5),
                        pulpStack,
                        IC2Items.getItem("cell"),
                        200,
                        30,
                        false));

        // UU
        if (ConfigTechReborn.UUrecipesIridiamOre) CraftingHelper
                .addShapedOreRecipe((IC2Items.getItem("iridiumOre")), "UUU", " U ", "UUU", 'U', ModItems.uuMatter);

        // Blast Furnace
        RecipeHandler.addRecipe(
                new BlastFurnaceRecipe(
                        ItemCells.getCellByName("silicon", 2),
                        null,
                        ItemApi.getItem("plateSilicon"),
                        new ItemStack(IC2Items.getItem("cell").getItem(), 2),
                        1000,
                        120,
                        1500));

        // CentrifugeRecipes

        // Plantball/Bio Chaff
        if (!IC2Classic.isIc2ClassicLoaded()) {
            RecipeHandler.addRecipe(
                    new CentrifugeRecipe(
                            new ItemStack(Blocks.grass, 16),
                            null,
                            new ItemStack(IC2Items.getItem("biochaff").getItem(), 8),
                            new ItemStack(IC2Items.getItem("plantBall").getItem(), 8),
                            new ItemStack(Items.clay_ball),
                            new ItemStack(Blocks.sand, 8),
                            2500,
                            5));
            RecipeHandler.addRecipe(
                    new CentrifugeRecipe(
                            new ItemStack(Blocks.dirt, 16),
                            null,
                            new ItemStack(IC2Items.getItem("biochaff").getItem(), 4),
                            new ItemStack(IC2Items.getItem("plantBall").getItem(), 4),
                            new ItemStack(Items.clay_ball),
                            new ItemStack(Blocks.sand, 8),
                            2500,
                            5));
        }

        // Methane
        RecipeHandler.addRecipe(
                new CentrifugeRecipe(
                        new ItemStack(Items.mushroom_stew, 16),
                        IC2Items.getItem("cell"),
                        ItemCells.getCellByName("methane", 1),
                        null,
                        null,
                        null,
                        5000,
                        5));
        RecipeHandler.addRecipe(
                new CentrifugeRecipe(
                        new ItemStack(Items.apple, 32),
                        IC2Items.getItem("cell"),
                        ItemCells.getCellByName("methane", 1),
                        null,
                        null,
                        null,
                        5000,
                        5));
        RecipeHandler.addRecipe(
                new CentrifugeRecipe(
                        new ItemStack(Items.porkchop, 12),
                        IC2Items.getItem("cell"),
                        ItemCells.getCellByName("methane", 1),
                        null,
                        null,
                        null,
                        5000,
                        5));
        RecipeHandler.addRecipe(
                new CentrifugeRecipe(
                        new ItemStack(Items.cooked_porkchop, 16),
                        IC2Items.getItem("cell"),
                        ItemCells.getCellByName("methane", 1),
                        null,
                        null,
                        null,
                        5000,
                        5));
        RecipeHandler.addRecipe(
                new CentrifugeRecipe(
                        new ItemStack(Items.bread, 64),
                        IC2Items.getItem("cell"),
                        ItemCells.getCellByName("methane", 1),
                        null,
                        null,
                        null,
                        5000,
                        5));
        RecipeHandler.addRecipe(
                new CentrifugeRecipe(
                        new ItemStack(Items.fish, 12),
                        IC2Items.getItem("cell"),
                        ItemCells.getCellByName("methane", 1),
                        null,
                        null,
                        null,
                        5000,
                        5));
        RecipeHandler.addRecipe(
                new CentrifugeRecipe(
                        new ItemStack(Items.cooked_fished, 16),
                        IC2Items.getItem("cell"),
                        ItemCells.getCellByName("methane", 1),
                        null,
                        null,
                        null,
                        5000,
                        5));
        RecipeHandler.addRecipe(
                new CentrifugeRecipe(
                        new ItemStack(Items.beef, 12),
                        IC2Items.getItem("cell"),
                        ItemCells.getCellByName("methane", 1),
                        null,
                        null,
                        null,
                        5000,
                        5));
        RecipeHandler.addRecipe(
                new CentrifugeRecipe(
                        new ItemStack(Items.cooked_beef, 16),
                        IC2Items.getItem("cell"),
                        ItemCells.getCellByName("methane", 1),
                        null,
                        null,
                        null,
                        5000,
                        5));
        RecipeHandler.addRecipe(
                new CentrifugeRecipe(
                        new ItemStack(Blocks.pumpkin, 16),
                        IC2Items.getItem("cell"),
                        ItemCells.getCellByName("methane", 1),
                        null,
                        null,
                        null,
                        5000,
                        5));
        RecipeHandler.addRecipe(
                new CentrifugeRecipe(
                        new ItemStack(Items.speckled_melon, 1),
                        IC2Items.getItem("cell"),
                        ItemCells.getCellByName("methane", 1),
                        new ItemStack(Items.gold_nugget, 6),
                        null,
                        null,
                        5000,
                        5));
        RecipeHandler.addRecipe(
                new CentrifugeRecipe(
                        new ItemStack(Items.spider_eye, 32),
                        IC2Items.getItem("cell"),
                        ItemCells.getCellByName("methane", 1),
                        null,
                        null,
                        null,
                        5000,
                        5));
        RecipeHandler.addRecipe(
                new CentrifugeRecipe(
                        new ItemStack(Items.chicken, 12),
                        IC2Items.getItem("cell"),
                        ItemCells.getCellByName("methane", 1),
                        null,
                        null,
                        null,
                        5000,
                        5));
        RecipeHandler.addRecipe(
                new CentrifugeRecipe(
                        new ItemStack(Items.cooked_chicken, 16),
                        IC2Items.getItem("cell"),
                        ItemCells.getCellByName("methane", 1),
                        null,
                        null,
                        null,
                        5000,
                        5));
        RecipeHandler.addRecipe(
                new CentrifugeRecipe(
                        new ItemStack(Items.rotten_flesh, 16),
                        IC2Items.getItem("cell"),
                        ItemCells.getCellByName("methane", 1),
                        null,
                        null,
                        null,
                        5000,
                        5));
        RecipeHandler.addRecipe(
                new CentrifugeRecipe(
                        new ItemStack(Items.melon, 64),
                        IC2Items.getItem("cell"),
                        ItemCells.getCellByName("methane", 1),
                        null,
                        null,
                        null,
                        5000,
                        5));
        RecipeHandler.addRecipe(
                new CentrifugeRecipe(
                        new ItemStack(Items.cookie, 64),
                        IC2Items.getItem("cell"),
                        ItemCells.getCellByName("methane", 1),
                        null,
                        null,
                        null,
                        5000,
                        5));
        RecipeHandler.addRecipe(
                new CentrifugeRecipe(
                        new ItemStack(Items.cake, 8),
                        IC2Items.getItem("cell"),
                        ItemCells.getCellByName("methane", 1),
                        null,
                        null,
                        null,
                        5000,
                        5));
        RecipeHandler.addRecipe(
                new CentrifugeRecipe(
                        new ItemStack(Items.golden_carrot, 1),
                        IC2Items.getItem("cell"),
                        ItemCells.getCellByName("methane", 1),
                        new ItemStack(Items.gold_nugget, 6),
                        null,
                        null,
                        5000,
                        5));
        RecipeHandler.addRecipe(
                new CentrifugeRecipe(
                        new ItemStack(Items.carrot, 16),
                        IC2Items.getItem("cell"),
                        ItemCells.getCellByName("methane", 1),
                        null,
                        null,
                        null,
                        5000,
                        5));
        RecipeHandler.addRecipe(
                new CentrifugeRecipe(
                        new ItemStack(Items.baked_potato, 24),
                        IC2Items.getItem("cell"),
                        ItemCells.getCellByName("methane", 1),
                        null,
                        null,
                        null,
                        5000,
                        5));
        RecipeHandler.addRecipe(
                new CentrifugeRecipe(
                        new ItemStack(Items.potato, 16),
                        IC2Items.getItem("cell"),
                        ItemCells.getCellByName("methane", 1),
                        null,
                        null,
                        null,
                        5000,
                        5));
        RecipeHandler.addRecipe(
                new CentrifugeRecipe(
                        new ItemStack(Items.poisonous_potato, 12),
                        IC2Items.getItem("cell"),
                        ItemCells.getCellByName("methane", 1),
                        null,
                        null,
                        null,
                        5000,
                        5));
        RecipeHandler.addRecipe(
                new CentrifugeRecipe(
                        new ItemStack(Items.nether_wart, 1),
                        IC2Items.getItem("cell"),
                        ItemCells.getCellByName("methane", 1),
                        null,
                        null,
                        null,
                        5000,
                        5));
        RecipeHandler.addRecipe(
                new CentrifugeRecipe(
                        new ItemStack(IC2Items.getItem("terraWart").getItem(), 16),
                        IC2Items.getItem("cell"),
                        ItemCells.getCellByName("methane", 1),
                        null,
                        null,
                        null,
                        5000,
                        5));
        RecipeHandler.addRecipe(
                new CentrifugeRecipe(
                        new ItemStack(Blocks.brown_mushroom, 1),
                        IC2Items.getItem("cell"),
                        ItemCells.getCellByName("methane", 1),
                        null,
                        null,
                        null,
                        5000,
                        5));
        RecipeHandler.addRecipe(
                new CentrifugeRecipe(
                        new ItemStack(Blocks.red_mushroom, 1),
                        IC2Items.getItem("cell"),
                        ItemCells.getCellByName("methane", 1),
                        null,
                        null,
                        null,
                        5000,
                        5));

        // Rubber Wood Yields
        RecipeHandler.addRecipe(
                new CentrifugeRecipe(
                        new ItemStack(IC2Items.getItem("rubberWood").getItem(), 15),
                        new ItemStack(IC2Items.getItem("cell").getItem(), 5),
                        new ItemStack(IC2Items.getItem("resin").getItem(), 8),
                        new ItemStack(IC2Items.getItem("plantBall").getItem(), 6),
                        ItemCells.getCellByName("methane", 1),
                        ItemCells.getCellByName("carbon", 4),
                        5000,
                        5));

        // Soul Sand Byproducts
        RecipeHandler.addRecipe(
                new CentrifugeRecipe(
                        new ItemStack(Blocks.soul_sand, 16),
                        IC2Items.getItem("cell"),
                        ItemCells.getCellByName("oil", 1),
                        ItemApi.getItem("dustSaltpeter", 4),
                        ItemApi.getItem("dustCoal", 1),
                        new ItemStack(Blocks.sand, 10),
                        2500,
                        5));

        // Ice
        RecipeHandler.addRecipe(
                new CentrifugeRecipe(
                        ItemCells.getCellByName("ice", 1),
                        null,
                        new ItemStack(Blocks.ice, 1),
                        IC2Items.getItem("cell"),
                        null,
                        null,
                        40,
                        5));

        // Dust Byproducts

        RecipeHandler.addRecipe(
                new CentrifugeRecipe(
                        new ItemStack(Items.glowstone_dust, 16),
                        IC2Items.getItem("cell"),
                        new ItemStack(Items.redstone, 8),
                        ItemApi.getItem("dustGold", 8),
                        ItemCells.getCellByName("helium", 1),
                        null,
                        25000,
                        5));
        RecipeHandler.addRecipe(
                new CentrifugeRecipe(
                        ItemApi.getItem("dustPhosphorous", 5),
                        new ItemStack(IC2Items.getItem("cell").getItem(), 3),
                        ItemCells.getCellByName("calcium", 3),
                        null,
                        null,
                        null,
                        1280,
                        5));
        RecipeHandler.addRecipe(
                new CentrifugeRecipe(
                        ItemApi.getItem("dustAsh", 1),
                        IC2Items.getItem("cell"),
                        ItemCells.getCellByName("carbon"),
                        null,
                        null,
                        null,
                        80,
                        5));
        RecipeHandler.addRecipe(
                new CentrifugeRecipe(
                        new ItemStack(Items.redstone, 10),
                        new ItemStack(IC2Items.getItem("cell").getItem(), 4),
                        ItemCells.getCellByName("silicon", 1),
                        ItemApi.getItem("dustPyrite", 3),
                        ItemApi.getItem("dustRuby", 1),
                        ItemCells.getCellByName("mercury", 3),
                        6800,
                        5));
        RecipeHandler.addRecipe(
                new CentrifugeRecipe(
                        ItemApi.getItem("dustEndstone", 16),
                        new ItemStack(IC2Items.getItem("cell").getItem(), 2),
                        ItemCells.getCellByName("helium3", 1),
                        ItemCells.getCellByName("helium"),
                        ItemApi.getItem("dustSmallTungsten", 1),
                        new ItemStack(Blocks.sand, 12),
                        4800,
                        5));
        RecipeHandler.addRecipe(
                new CentrifugeRecipe(
                        ItemApi.getItem("dustCinnabar", 2),
                        IC2Items.getItem("cell"),
                        ItemCells.getCellByName("mercury", 1),
                        ItemApi.getItem("dustSulfur", 1),
                        null,
                        null,
                        80,
                        5));
        RecipeHandler.addRecipe(
                new CentrifugeRecipe(
                        ItemApi.getItem("dustFlint", 1),
                        null,
                        IC2Items.getItem("silicondioxideDust"),
                        null,
                        null,
                        null,
                        160,
                        5));

        // Deuterium/Tritium
        RecipeHandler.addRecipe(
                new CentrifugeRecipe(
                        ItemCells.getCellByName("helium", 16),
                        null,
                        ItemCells.getCellByName("deuterium", 1),
                        new ItemStack(IC2Items.getItem("cell").getItem(), 15),
                        null,
                        null,
                        10000,
                        5));
        RecipeHandler.addRecipe(
                new CentrifugeRecipe(
                        ItemCells.getCellByName("deuterium", 4),
                        null,
                        ItemCells.getCellByName("tritium", 1),
                        new ItemStack(IC2Items.getItem("cell").getItem(), 3),
                        null,
                        null,
                        3000,
                        5));
        RecipeHandler.addRecipe(
                new CentrifugeRecipe(
                        ItemCells.getCellByName("hydrogen", 4),
                        null,
                        ItemCells.getCellByName("deuterium", 1),
                        new ItemStack(IC2Items.getItem("cell").getItem(), 3),
                        null,
                        null,
                        3000,
                        5));

        // Lava Cell Byproducts
        ItemStack lavaCells = IC2Items.getItem("lavaCell").copy();
        lavaCells.stackSize = 8;
        RecipeHandler.addRecipe(
                new CentrifugeRecipe(
                        lavaCells,
                        null,
                        ItemApi.getItem("nuggetElectrum", 4),
                        ItemApi.getItem("ingotCopper", 2),
                        ItemApi.getItem("dustSmallTungsten", 1),
                        ItemApi.getItem("ingotTin", 2),
                        6000,
                        5));

        // IndustrialGrinderRecipes
        RecipeHandler.addRecipe(
                new GrinderRecipe(
                        new ItemStack(Blocks.coal_ore, 1),
                        IC2Items.getItem("waterCell"),
                        null,
                        new ItemStack(Items.coal, 1),
                        ItemApi.getItem("dustSmallCoal", 6),
                        ItemApi.getItem("dustSmallCoal", 2),
                        IC2Items.getItem("cell"),
                        100,
                        120));
        RecipeHandler.addRecipe(
                new GrinderRecipe(
                        new ItemStack(Blocks.iron_ore, 1),
                        IC2Items.getItem("waterCell"),
                        null,
                        ItemApi.getItem("dustIron", 2),
                        ItemApi.getItem("dustSmallNickel", 1),
                        ItemApi.getItem("dustSmallTin", 1),
                        IC2Items.getItem("cell"),
                        100,
                        120));
        RecipeHandler.addRecipe(
                new GrinderRecipe(
                        new ItemStack(Blocks.gold_ore, 1),
                        IC2Items.getItem("waterCell"),
                        null,
                        ItemApi.getItem("dustGold", 2),
                        ItemApi.getItem("dustSmallCopper", 1),
                        ItemApi.getItem("dustSmallNickel", 1),
                        IC2Items.getItem("cell"),
                        100,
                        120));
        RecipeHandler.addRecipe(
                new GrinderRecipe(
                        new ItemStack(Blocks.iron_ore, 1),
                        ItemCells.getCellByName("sodiumPersulfate", 1),
                        null,
                        ItemApi.getItem("dustIron", 2),
                        ItemApi.getItem("dustNickel", 1),
                        ItemApi.getItem("dustSmallTin", 1),
                        IC2Items.getItem("cell"),
                        100,
                        120));
        RecipeHandler.addRecipe(
                new GrinderRecipe(
                        new ItemStack(Blocks.gold_ore, 1),
                        ItemCells.getCellByName("sodiumPersulfate", 1),
                        null,
                        ItemApi.getItem("dustGold", 2),
                        ItemApi.getItem("dustCopper", 1),
                        ItemApi.getItem("dustSmallNickel", 1),
                        IC2Items.getItem("cell"),
                        100,
                        120));
        RecipeHandler.addRecipe(
                new GrinderRecipe(
                        new ItemStack(Blocks.gold_ore, 1),
                        ItemCells.getCellByName("mercury", 1),
                        null,
                        ItemApi.getItem("dustGold", 3),
                        ItemApi.getItem("dustSmallCopper", 1),
                        ItemApi.getItem("dustSmallNickel", 1),
                        IC2Items.getItem("cell"),
                        100,
                        120));
        RecipeHandler.addRecipe(
                new GrinderRecipe(
                        new ItemStack(Blocks.diamond_ore, 1),
                        IC2Items.getItem("waterCell"),
                        null,
                        new ItemStack(Items.diamond, 1),
                        ItemApi.getItem("dustSmallDiamond", 6),
                        ItemApi.getItem("dustSmallCoal", 2),
                        IC2Items.getItem("cell"),
                        100,
                        120));
        RecipeHandler.addRecipe(
                new GrinderRecipe(
                        new ItemStack(Blocks.emerald_ore, 1),
                        IC2Items.getItem("waterCell"),
                        null,
                        new ItemStack(Items.emerald, 1),
                        ItemApi.getItem("dustSmallEmerald", 6),
                        ItemApi.getItem("dustSmallAluminium", 2),
                        IC2Items.getItem("cell"),
                        100,
                        120));
        RecipeHandler.addRecipe(
                new GrinderRecipe(
                        new ItemStack(Blocks.redstone_ore, 1),
                        IC2Items.getItem("waterCell"),
                        null,
                        new ItemStack(Items.redstone, 10),
                        ItemApi.getItem("dustSmallCinnabar", 1),
                        ItemApi.getItem("dustSmallGlowstone", 1),
                        IC2Items.getItem("cell"),
                        100,
                        120));
        RecipeHandler.addRecipe(
                new GrinderRecipe(
                        new ItemStack(Blocks.lapis_ore, 1),
                        IC2Items.getItem("waterCell"),
                        null,
                        new ItemStack(Items.dye, 6, 4),
                        ItemApi.getItem("dustSmallLapis", 36),
                        ItemApi.getItem("dustSmallLazurite", 8),
                        IC2Items.getItem("cell"),
                        100,
                        120));

        // Copper Ore
        if (OreUtil.doesOreExistAndValid("oreCopper")) {
            try {
                ItemStack oreStack = OreDictionary.getOres("oreCopper").get(0);
                RecipeHandler.addRecipe(
                        new GrinderRecipe(
                                oreStack,
                                IC2Items.getItem("waterCell"),
                                null,
                                ItemApi.getItem("dustCopper", 2),
                                ItemApi.getItem("dustSmallGold", 1),
                                ItemApi.getItem("dustSmallNickel", 1),
                                IC2Items.getItem("cell"),
                                100,
                                120));

                RecipeHandler.addRecipe(
                        new GrinderRecipe(
                                oreStack,
                                ItemCells.getCellByName("sodiumPersulfate", 1),
                                null,
                                ItemApi.getItem("dustCopper", 2),
                                ItemApi.getItem("dustGold", 1),
                                ItemApi.getItem("dustSmallNickel", 1),
                                IC2Items.getItem("cell"),
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
                                IC2Items.getItem("cell"),
                                100,
                                120));
            } catch (Exception e) {
                Core.logHelper.info("Failed to Load Grinder Recipe for Copper Ore");
            }
        }

        // Tin Ore
        if (OreUtil.doesOreExistAndValid("oreTin")) {
            try {
                ItemStack oreStack = OreDictionary.getOres("oreTin").get(0);
                RecipeHandler.addRecipe(
                        new GrinderRecipe(
                                oreStack,
                                IC2Items.getItem("waterCell"),
                                null,
                                ItemApi.getItem("dustTin", 2),
                                ItemApi.getItem("dustSmallIron", 1),
                                ItemApi.getItem("dustSmallZinc", 1),
                                IC2Items.getItem("cell"),
                                100,
                                120));
                RecipeHandler.addRecipe(
                        new GrinderRecipe(
                                oreStack,
                                ItemCells.getCellByName("sodiumPersulfate", 1),
                                null,
                                ItemApi.getItem("dustTin", 2),
                                ItemApi.getItem("dustSmallIron", 1),
                                ItemApi.getItem("dustZinc", 1),
                                IC2Items.getItem("cell"),
                                100,
                                120));
            } catch (Exception e) {
                Core.logHelper.info("Failed to Load Grinder Recipe for Tin Ore");
            }
        }

        // Nickel Ore
        if (OreUtil.doesOreExistAndValid("oreNickel")) {
            try {
                ItemStack oreStack = OreDictionary.getOres("oreNickel").get(0);
                RecipeHandler.addRecipe(
                        new GrinderRecipe(
                                oreStack,
                                IC2Items.getItem("waterCell"),
                                null,
                                ItemApi.getItem("dustNickel", 2),
                                ItemApi.getItem("dustSmallIron", 1),
                                ItemApi.getItem("dustSmallPlatinum", 1),
                                IC2Items.getItem("cell"),
                                100,
                                120));
                RecipeHandler.addRecipe(
                        new GrinderRecipe(
                                oreStack,
                                ItemCells.getCellByName("sodiumPersulfate", 1),
                                null,
                                ItemApi.getItem("dustNickel", 3),
                                ItemApi.getItem("dustSmallIron", 1),
                                ItemApi.getItem("dustSmallPlatinum", 1),
                                IC2Items.getItem("cell"),
                                100,
                                120));
                RecipeHandler.addRecipe(
                        new GrinderRecipe(
                                oreStack,
                                ItemCells.getCellByName("mercury", 1),
                                null,
                                ItemApi.getItem("dustNickel", 2),
                                ItemApi.getItem("dustSmallIron", 1),
                                ItemApi.getItem("dustPlatinum", 1),
                                IC2Items.getItem("cell"),
                                100,
                                120));
            } catch (Exception e) {
                Core.logHelper.info("Failed to Load Grinder Recipe for Nickel Ore");
            }
        }

        // Zinc Ore
        if (OreUtil.doesOreExistAndValid("oreZinc")) {
            try {
                ItemStack oreStack = OreDictionary.getOres("oreZinc").get(0);
                RecipeHandler.addRecipe(
                        new GrinderRecipe(
                                oreStack,
                                IC2Items.getItem("waterCell"),
                                null,
                                ItemApi.getItem("dustZinc", 2),
                                ItemApi.getItem("dustSmallIron", 1),
                                ItemApi.getItem("dustSmallTin", 1),
                                IC2Items.getItem("cell"),
                                100,
                                120));
                RecipeHandler.addRecipe(
                        new GrinderRecipe(
                                oreStack,
                                ItemCells.getCellByName("sodiumPersulfate", 1),
                                null,
                                ItemApi.getItem("dustZinc", 2),
                                ItemApi.getItem("dustSmallIron", 1),
                                ItemApi.getItem("dustIron", 1),
                                IC2Items.getItem("cell"),
                                100,
                                120));
            } catch (Exception e) {
                Core.logHelper.info("Failed to Load Grinder Recipe for Zinc Ore");
            }
        }

        // Silver Ore
        if (OreUtil.doesOreExistAndValid("oreSilver")) {
            try {
                ItemStack oreStack = OreDictionary.getOres("oreSilver").get(0);
                RecipeHandler.addRecipe(
                        new GrinderRecipe(
                                oreStack,
                                IC2Items.getItem("waterCell"),
                                null,
                                ItemApi.getItem("dustSilver", 2),
                                ItemApi.getItem("dustSmallLead", 1),
                                ItemApi.getItem("dustSmallSulfur", 1),
                                IC2Items.getItem("cell"),
                                100,
                                120));
                RecipeHandler.addRecipe(
                        new GrinderRecipe(
                                oreStack,
                                ItemCells.getCellByName("mercury", 1),
                                null,
                                ItemApi.getItem("dustSilver", 3),
                                ItemApi.getItem("dustSmallLead", 1),
                                ItemApi.getItem("dustSmallSulfur", 1),
                                IC2Items.getItem("cell"),
                                100,
                                120));
            } catch (Exception e) {
                Core.logHelper.info("Failed to Load Grinder Recipe for Silver Ore");
            }
        }

        // Lead Ore
        if (OreUtil.doesOreExistAndValid("oreLead")) {
            try {
                ItemStack oreStack = OreDictionary.getOres("oreLead").get(0);
                RecipeHandler.addRecipe(
                        new GrinderRecipe(
                                oreStack,
                                IC2Items.getItem("waterCell"),
                                null,
                                ItemApi.getItem("dustLead", 2),
                                ItemApi.getItem("dustSmallSilver", 1),
                                ItemApi.getItem("dustSmallSulfur", 1),
                                IC2Items.getItem("cell"),
                                100,
                                120));
                RecipeHandler.addRecipe(
                        new GrinderRecipe(
                                oreStack,
                                ItemCells.getCellByName("mercury", 1),
                                null,
                                ItemApi.getItem("dustLead", 2),
                                ItemApi.getItem("dustSilver", 1),
                                ItemApi.getItem("dustSmallSulfur", 1),
                                IC2Items.getItem("cell"),
                                100,
                                120));
            } catch (Exception e) {
                Core.logHelper.info("Failed to Load Grinder Recipe for Lead Ore");
            }
        }

        // Uranium Ore
        if (OreUtil.doesOreExistAndValid("oreUranium")) {
            try {
                ItemStack oreStack = OreDictionary.getOres("oreUranium").get(0);
                ItemStack uranium238Stack = IC2Items.getItem("Uran238");
                uranium238Stack.stackSize = 8;
                ItemStack uranium235Stack = IC2Items.getItem("smallUran235");
                uranium235Stack.stackSize = 2;
                RecipeHandler.addRecipe(
                        new GrinderRecipe(
                                oreStack,
                                null,
                                new FluidStack(FluidRegistry.WATER, 1000),
                                uranium238Stack,
                                uranium235Stack,
                                null,
                                null,
                                100,
                                120));
                RecipeHandler.addRecipe(
                        new GrinderRecipe(
                                oreStack,
                                IC2Items.getItem("waterCell"),
                                null,
                                uranium238Stack,
                                uranium235Stack,
                                null,
                                IC2Items.getItem("cell"),
                                100,
                                120));
                RecipeHandler.addRecipe(
                        new GrinderRecipe(
                                oreStack,
                                new ItemStack(Items.water_bucket),
                                null,
                                uranium238Stack,
                                uranium235Stack,
                                null,
                                new ItemStack(Items.bucket),
                                100,
                                120));
            } catch (Exception e) {
                Core.logHelper.info("Failed to Load Grinder Recipe for Uranium Ore");
            }
        }

        // Pitchblende Ore
        if (OreUtil.doesOreExistAndValid("orePitchblende")) {
            try {
                ItemStack oreStack = OreDictionary.getOres("orePitchblende").get(0);
                ItemStack uranium238Stack = IC2Items.getItem("Uran238");
                uranium238Stack.stackSize = 8;
                ItemStack uranium235Stack = IC2Items.getItem("smallUran235");
                uranium235Stack.stackSize = 2;
                RecipeHandler.addRecipe(
                        new GrinderRecipe(
                                oreStack,
                                null,
                                new FluidStack(FluidRegistry.WATER, 1000),
                                uranium238Stack,
                                uranium235Stack,
                                null,
                                null,
                                100,
                                120));
                RecipeHandler.addRecipe(
                        new GrinderRecipe(
                                oreStack,
                                IC2Items.getItem("waterCell"),
                                null,
                                uranium238Stack,
                                uranium235Stack,
                                null,
                                IC2Items.getItem("cell"),
                                100,
                                120));
                RecipeHandler.addRecipe(
                        new GrinderRecipe(
                                oreStack,
                                new ItemStack(Items.water_bucket),
                                null,
                                uranium238Stack,
                                uranium235Stack,
                                null,
                                new ItemStack(Items.bucket),
                                100,
                                120));
            } catch (Exception e) {
                Core.logHelper.info("Failed to Load Grinder Recipe for Uranium Ore");
            }
        }

        // Aluminum Ore
        if (OreUtil.doesOreExistAndValid("oreAluminum")) {
            try {
                ItemStack oreStack = OreDictionary.getOres("oreAluminum").get(0);
                RecipeHandler.addRecipe(
                        new GrinderRecipe(
                                oreStack,
                                IC2Items.getItem("waterCell"),
                                null,
                                ItemApi.getItem("dustAluminium", 2),
                                ItemApi.getItem("dustSmallBauxite", 1),
                                ItemApi.getItem("dustSmallBauxite", 1),
                                IC2Items.getItem("cell"),
                                100,
                                120));
            } catch (Exception e) {
                Core.logHelper.info("Failed to Load Grinder Recipe for Lead Ore");
            }
        }

        // Ardite Ore
        if (OreUtil.doesOreExistAndValid("oreArdite")) {
            try {
                ItemStack oreStack = OreDictionary.getOres("oreArdite").get(0);
                RecipeHandler.addRecipe(
                        new GrinderRecipe(
                                oreStack,
                                IC2Items.getItem("waterCell"),
                                null,
                                ItemApi.getItem("dustArdite", 2),
                                ItemApi.getItem("dustSmallArdite", 1),
                                ItemApi.getItem("dustSmallArdite", 1),
                                IC2Items.getItem("cell"),
                                100,
                                120));
            } catch (Exception e) {
                Core.logHelper.info("Failed to Load Grinder Recipe for Ardite Ore");
            }
        }

        // Cobalt Ore
        if (OreUtil.doesOreExistAndValid("oreCobalt")) {
            try {
                ItemStack oreStack = OreDictionary.getOres("oreCobalt").get(0);
                RecipeHandler.addRecipe(
                        new GrinderRecipe(
                                oreStack,
                                IC2Items.getItem("waterCell"),
                                null,
                                ItemApi.getItem("dustCobalt", 2),
                                ItemApi.getItem("dustSmallCobalt", 1),
                                ItemApi.getItem("dustSmallCobalt", 1),
                                IC2Items.getItem("cell"),
                                100,
                                120));
            } catch (Exception e) {
                Core.logHelper.info("Failed to Load Grinder Recipe for Cobalt Ore");
            }
        }

        // Dark Iron Ore
        if (OreUtil.doesOreExistAndValid("oreDarkIron")) {
            try {
                ItemStack oreStack = OreDictionary.getOres("oreDarkIron").get(0);
                RecipeHandler.addRecipe(
                        new GrinderRecipe(
                                oreStack,
                                IC2Items.getItem("waterCell"),
                                null,
                                ItemApi.getItem("dustDarkIron", 2),
                                ItemApi.getItem("dustSmallDarkIron", 1),
                                ItemApi.getItem("dustSmallIron", 1),
                                IC2Items.getItem("cell"),
                                100,
                                120));
            } catch (Exception e) {
                Core.logHelper.info("Failed to Load Grinder Recipe for Dark Iron Ore");
            }
        }

        // Cadmium Ore
        if (OreUtil.doesOreExistAndValid("oreCadmium")) {
            try {
                ItemStack oreStack = OreDictionary.getOres("oreCadmium").get(0);
                RecipeHandler.addRecipe(
                        new GrinderRecipe(
                                oreStack,
                                IC2Items.getItem("waterCell"),
                                null,
                                ItemApi.getItem("dustCadmium", 2),
                                ItemApi.getItem("dustSmallCadmium", 1),
                                ItemApi.getItem("dustSmallCadmium", 1),
                                IC2Items.getItem("cell"),
                                100,
                                120));
            } catch (Exception e) {
                Core.logHelper.info("Failed to Load Grinder Recipe for Cadmium Ore");
            }
        }

        // Indium Ore
        if (OreUtil.doesOreExistAndValid("oreIndium")) {
            try {
                ItemStack oreStack = OreDictionary.getOres("oreIndium").get(0);
                RecipeHandler.addRecipe(
                        new GrinderRecipe(
                                oreStack,
                                IC2Items.getItem("waterCell"),
                                null,
                                ItemApi.getItem("dustIndium", 2),
                                ItemApi.getItem("dustSmallIndium", 1),
                                ItemApi.getItem("dustSmallIndium", 1),
                                IC2Items.getItem("cell"),
                                100,
                                120));
            } catch (Exception e) {
                Core.logHelper.info("Failed to Load Grinder Recipe for Indium Ore");
            }
        }

        // Calcite Ore
        if (OreUtil.doesOreExistAndValid("oreCalcite") && OreUtil.doesOreExistAndValid("gemCalcite")) {
            try {
                ItemStack oreStack = OreDictionary.getOres("oreCalcite").get(0);
                ItemStack gemStack = OreDictionary.getOres("gemCalcite").get(0);
                RecipeHandler.addRecipe(
                        new GrinderRecipe(
                                oreStack,
                                IC2Items.getItem("waterCell"),
                                null,
                                gemStack,
                                ItemApi.getItem("dustSmallCalcite", 6),
                                null,
                                IC2Items.getItem("cell"),
                                100,
                                120));
            } catch (Exception e) {
                Core.logHelper.info("Failed to Load Grinder Recipe for Calcite Ore");
            }
        }

        // Magnetite Ore
        if (OreUtil.doesOreExistAndValid("oreMagnetite") && OreUtil.doesOreExistAndValid("chunkMagnetite")) {
            try {
                ItemStack oreStack = OreDictionary.getOres("oreMagnetite").get(0);
                ItemStack chunkStack = OreDictionary.getOres("chunkMagnetite").get(0);
                RecipeHandler.addRecipe(
                        new GrinderRecipe(
                                oreStack,
                                IC2Items.getItem("waterCell"),
                                null,
                                chunkStack,
                                ItemApi.getItem("dustSmallMagnetite", 6),
                                null,
                                IC2Items.getItem("cell"),
                                100,
                                120));
            } catch (Exception e) {
                Core.logHelper.info("Failed to Load Grinder Recipe for Magnetite Ore");
            }
        }

        // Graphite Ore
        if (OreUtil.doesOreExistAndValid("oreGraphite") && OreUtil.doesOreExistAndValid("chunkGraphite")) {
            try {
                ItemStack oreStack = OreDictionary.getOres("oreGraphite").get(0);
                ItemStack chunkStack = OreDictionary.getOres("chunkGraphite").get(0);
                RecipeHandler.addRecipe(
                        new GrinderRecipe(
                                oreStack,
                                IC2Items.getItem("waterCell"),
                                null,
                                chunkStack,
                                ItemApi.getItem("dustSmallGraphite", 6),
                                null,
                                IC2Items.getItem("cell"),
                                100,
                                120));
            } catch (Exception e) {
                Core.logHelper.info("Failed to Load Grinder Recipe for Graphite Ore");
            }
        }

        // Osmium Ore
        if (OreUtil.doesOreExistAndValid("oreOsmium")) {
            try {
                ItemStack oreStack = OreDictionary.getOres("oreOsmium").get(0);
                RecipeHandler.addRecipe(
                        new GrinderRecipe(
                                oreStack,
                                IC2Items.getItem("waterCell"),
                                null,
                                ItemApi.getItem("dustOsmium", 2),
                                ItemApi.getItem("dustSmallOsmium", 1),
                                ItemApi.getItem("dustSmallOsmium", 1),
                                IC2Items.getItem("cell"),
                                100,
                                120));
            } catch (Exception e) {
                Core.logHelper.info("Failed to Load Grinder Recipe for Osmium Ore");
            }
        }

        // Teslatite Ore
        if (OreUtil.doesOreExistAndValid("oreTeslatite") && OreUtil.doesOreExistAndValid("dustTeslatite")) {
            try {
                ItemStack oreStack = OreDictionary.getOres("oreTeslatite").get(0);
                ItemStack dustStack = OreDictionary.getOres("dustTeslatite").get(0);
                dustStack.stackSize = 10;
                RecipeHandler.addRecipe(
                        new GrinderRecipe(
                                oreStack,
                                IC2Items.getItem("waterCell"),
                                null,
                                dustStack,
                                ItemApi.getItem("dustSmallSodalite", 1),
                                ItemApi.getItem("dustSmallGlowstone", 1),
                                IC2Items.getItem("cell"),
                                100,
                                120));
            } catch (Exception e) {
                Core.logHelper.info("Failed to Load Grinder Recipe for Teslatite Ore");
            }
        }

        // Sulfur Ore
        if (OreUtil.doesOreExistAndValid("oreSulfur")) {
            try {
                ItemStack oreStack = OreDictionary.getOres("oreSulfur").get(0);
                RecipeHandler.addRecipe(
                        new GrinderRecipe(
                                oreStack,
                                IC2Items.getItem("waterCell"),
                                null,
                                ItemApi.getItem("dustSulfur", 2),
                                ItemApi.getItem("dustSmallSulfur", 1),
                                ItemApi.getItem("dustSmallSulfur", 1),
                                IC2Items.getItem("cell"),
                                100,
                                120));
            } catch (Exception e) {
                Core.logHelper.info("Failed to Load Grinder Recipe for Sulfur Ore");
            }
        }

        // Saltpeter Ore
        if (OreUtil.doesOreExistAndValid("oreSaltpeter")) {
            try {
                ItemStack oreStack = OreDictionary.getOres("oreSaltpeter").get(0);
                RecipeHandler.addRecipe(
                        new GrinderRecipe(
                                oreStack,
                                IC2Items.getItem("waterCell"),
                                null,
                                ItemApi.getItem("dustSaltpeter", 2),
                                ItemApi.getItem("dustSmallSaltpeter", 1),
                                ItemApi.getItem("dustSmallSaltpeter", 1),
                                IC2Items.getItem("cell"),
                                100,
                                120));
            } catch (Exception e) {
                Core.logHelper.info("Failed to Load Grinder Recipe for Saltpeter Ore");
            }
        }

        // Apatite Ore
        if (OreUtil.doesOreExistAndValid("oreApatite") & OreUtil.doesOreExistAndValid("gemApatite")) {
            try {
                ItemStack oreStack = OreDictionary.getOres("oreApatite").get(0);
                ItemStack gemStack = OreDictionary.getOres("gemApatite").get(0);
                gemStack.stackSize = 6;
                RecipeHandler.addRecipe(
                        new GrinderRecipe(
                                oreStack,
                                IC2Items.getItem("waterCell"),
                                null,
                                gemStack,
                                gemStack,
                                ItemApi.getItem("dustSmallPhosphorous", 4),
                                IC2Items.getItem("cell"),
                                100,
                                120));
            } catch (Exception e) {
                Core.logHelper.info("Failed to Load Grinder Recipe for Apatite Ore");
            }
        }

        // Nether Quartz Ore
        if (OreUtil.doesOreExistAndValid("dustNetherQuartz")) {
            try {
                ItemStack dustStack = OreDictionary.getOres("dustNetherQuartz").get(0);
                dustStack.stackSize = 4;
                RecipeHandler.addRecipe(
                        new GrinderRecipe(
                                new ItemStack(Blocks.quartz_ore, 1),
                                IC2Items.getItem("waterCell"),
                                null,
                                new ItemStack(Items.quartz, 2),
                                dustStack,
                                ItemApi.getItem("dustSmallNetherrack", 2),
                                IC2Items.getItem("cell"),
                                100,
                                120));
            } catch (Exception e) {
                Core.logHelper.info("Failed to Load Grinder Recipe for Nether Quartz Ore");
            }
        }

        // Certus Quartz Ore
        if (OreUtil.doesOreExistAndValid("oreCertusQuartz")) {
            try {
                ItemStack oreStack = OreDictionary.getOres("oreCertusQuartz").get(0);
                ItemStack gemStack = OreDictionary.getOres("crystalCertusQuartz").get(0);
                ItemStack dustStack = OreDictionary.getOres("dustCertusQuartz").get(0);
                dustStack.stackSize = 2;
                RecipeHandler.addRecipe(
                        new GrinderRecipe(
                                oreStack,
                                IC2Items.getItem("waterCell"),
                                null,
                                gemStack,
                                dustStack,
                                null,
                                IC2Items.getItem("cell"),
                                100,
                                120));
            } catch (Exception e) {
                Core.logHelper.info("Failed to Load Grinder Recipe for Certus Quartz Ore");
            }
        }

        // Charged Certus Quartz Ore
        if (OreUtil.doesOreExistAndValid("oreChargedCertusQuartz")) {
            try {
                ItemStack oreStack = OreDictionary.getOres("oreChargedCertusQuartz").get(0);
                ItemStack gemStack = OreDictionary.getOres("crystalChargedCertusQuartz").get(0);
                ItemStack dustStack = OreDictionary.getOres("dustCertusQuartz").get(0);
                dustStack.stackSize = 2;
                RecipeHandler.addRecipe(
                        new GrinderRecipe(
                                oreStack,
                                IC2Items.getItem("waterCell"),
                                null,
                                gemStack,
                                dustStack,
                                null,
                                IC2Items.getItem("cell"),
                                100,
                                120));
            } catch (Exception e) {
                Core.logHelper.info("Failed to Load Grinder Recipe for Charged Certus Quartz Ore");
            }
        }

        // Amethyst Ore
        if (OreUtil.doesOreExistAndValid("oreAmethyst") && OreUtil.doesOreExistAndValid("gemAmethyst")) {
            try {
                ItemStack oreStack = OreDictionary.getOres("oreAmethyst").get(0);
                ItemStack gemStack = OreDictionary.getOres("gemAmethyst").get(0);
                gemStack.stackSize = 2;
                ItemStack dustStack = OreDictionary.getOres("gemAmethyst").get(0);
                dustStack.stackSize = 1;
                RecipeHandler.addRecipe(
                        new GrinderRecipe(
                                oreStack,
                                IC2Items.getItem("waterCell"),
                                null,
                                gemStack,
                                dustStack,
                                null,
                                IC2Items.getItem("cell"),
                                100,
                                120));
            } catch (Exception e) {
                Core.logHelper.info("Failed to Load Grinder Recipe for Certus Quartz Ore");
            }
        }

        // Topaz Ore
        if (OreUtil.doesOreExistAndValid("oreTopaz") && OreUtil.doesOreExistAndValid("gemTopaz")) {
            try {
                ItemStack oreStack = OreDictionary.getOres("oreTopaz").get(0);
                ItemStack gemStack = OreDictionary.getOres("gemTopaz").get(0);
                gemStack.stackSize = 2;
                ItemStack dustStack = OreDictionary.getOres("gemTopaz").get(0);
                dustStack.stackSize = 1;
                RecipeHandler.addRecipe(
                        new GrinderRecipe(
                                oreStack,
                                IC2Items.getItem("waterCell"),
                                null,
                                gemStack,
                                dustStack,
                                null,
                                IC2Items.getItem("cell"),
                                100,
                                120));
            } catch (Exception e) {
                Core.logHelper.info("Failed to Load Grinder Recipe for Topaz Ore");
            }
        }

        // Tanzanite Ore
        if (OreUtil.doesOreExistAndValid("oreTanzanite") && OreUtil.doesOreExistAndValid("gemTanzanite")) {
            try {
                ItemStack oreStack = OreDictionary.getOres("oreTanzanite").get(0);
                ItemStack gemStack = OreDictionary.getOres("gemTanzanite").get(0);
                gemStack.stackSize = 2;
                ItemStack dustStack = OreDictionary.getOres("gemTanzanite").get(0);
                dustStack.stackSize = 1;
                RecipeHandler.addRecipe(
                        new GrinderRecipe(
                                oreStack,
                                IC2Items.getItem("waterCell"),
                                null,
                                gemStack,
                                dustStack,
                                null,
                                IC2Items.getItem("cell"),
                                100,
                                120));
            } catch (Exception e) {
                Core.logHelper.info("Failed to Load Grinder Recipe for Tanzanite Ore");
            }
        }

        // Malachite Ore
        if (OreUtil.doesOreExistAndValid("oreMalachite") && OreUtil.doesOreExistAndValid("gemMalachite")) {
            try {
                ItemStack oreStack = OreDictionary.getOres("oreMalachite").get(0);
                ItemStack gemStack = OreDictionary.getOres("gemMalachite").get(0);
                gemStack.stackSize = 2;
                ItemStack dustStack = OreDictionary.getOres("gemMalachite").get(0);
                dustStack.stackSize = 1;
                RecipeHandler.addRecipe(
                        new GrinderRecipe(
                                oreStack,
                                IC2Items.getItem("waterCell"),
                                null,
                                gemStack,
                                dustStack,
                                null,
                                IC2Items.getItem("cell"),
                                100,
                                120));
            } catch (Exception e) {
                Core.logHelper.info("Failed to Load Grinder Recipe for Malachite Ore");
            }
        }

        // Implosion Compressor

        RecipeHandler.addRecipe(
                new ImplosionCompressorRecipe(
                        ItemApi.getItem("ingotIridiumAlloy"),
                        new ItemStack(IC2Items.getItem("industrialTnt").getItem(), 8),
                        IC2Items.getItem("iridiumPlate"),
                        ItemApi.getItem("dustDarkAsh", 4),
                        20,
                        30));
        RecipeHandler.addRecipe(
                new ImplosionCompressorRecipe(
                        ItemApi.getItem("dustDiamond", 4),
                        new ItemStack(IC2Items.getItem("industrialTnt").getItem(), 32),
                        new ItemStack(IC2Items.getItem("industrialDiamond").getItem(), 3),
                        ItemApi.getItem("dustDarkAsh", 16),
                        20,
                        30));
        RecipeHandler.addRecipe(
                new ImplosionCompressorRecipe(
                        IC2Items.getItem("coalChunk"),
                        new ItemStack(IC2Items.getItem("industrialTnt").getItem(), 8),
                        IC2Items.getItem("industrialDiamond"),
                        ItemApi.getItem("dustDarkAsh", 4),
                        20,
                        30));
        RecipeHandler.addRecipe(
                new ImplosionCompressorRecipe(
                        ItemApi.getItem("dustEmerald", 4),
                        new ItemStack(IC2Items.getItem("industrialTnt").getItem(), 24),
                        new ItemStack(Items.emerald, 3),
                        ItemApi.getItem("dustDarkAsh", 12),
                        20,
                        30));
        RecipeHandler.addRecipe(
                new ImplosionCompressorRecipe(
                        ItemApi.getItem("dustSapphire", 4),
                        new ItemStack(IC2Items.getItem("industrialTnt").getItem(), 24),
                        ItemApi.getItem("gemSapphire", 3),
                        ItemApi.getItem("dustDarkAsh", 12),
                        20,
                        30));
        RecipeHandler.addRecipe(
                new ImplosionCompressorRecipe(
                        ItemApi.getItem("dustRuby", 4),
                        new ItemStack(IC2Items.getItem("industrialTnt").getItem(), 24),
                        ItemApi.getItem("gemRuby", 3),
                        ItemApi.getItem("dustDarkAsh", 12),
                        20,
                        30));
        RecipeHandler.addRecipe(
                new ImplosionCompressorRecipe(
                        ItemApi.getItem("dustYellowGarnet", 4),
                        new ItemStack(IC2Items.getItem("industrialTnt").getItem(), 24),
                        ItemApi.getItem("gemYellowGarnet", 3),
                        ItemApi.getItem("dustDarkAsh", 12),
                        20,
                        30));
        RecipeHandler.addRecipe(
                new ImplosionCompressorRecipe(
                        ItemApi.getItem("dustRedGarnet", 4),
                        new ItemStack(IC2Items.getItem("industrialTnt").getItem(), 24),
                        ItemApi.getItem("gemRedGarnet", 3),
                        ItemApi.getItem("dustDarkAsh", 12),
                        20,
                        30));
        RecipeHandler.addRecipe(
                new ImplosionCompressorRecipe(
                        ItemApi.getItem("dustPeridot", 4),
                        new ItemStack(IC2Items.getItem("industrialTnt").getItem(), 24),
                        ItemApi.getItem("gemPeridot", 3),
                        ItemApi.getItem("dustDarkAsh", 12),
                        20,
                        30));

        // Grinder

        RecipeHandler.addRecipe(
                new GrinderRecipe(
                        new ItemStack(ModBlocks.ore, 1, 0),
                        IC2Items.getItem("waterCell"),
                        null,
                        ItemApi.getItem("dustGalena", 2),
                        ItemApi.getItem("dustSmallSulfur", 1),
                        ItemApi.getItem("dustSmallSilver", 1),
                        IC2Items.getItem("cell"),
                        100,
                        120));
        RecipeHandler.addRecipe(
                new GrinderRecipe(
                        new ItemStack(ModBlocks.ore, 1, 0),
                        ItemCells.getCellByName("mercury", 1),
                        null,
                        ItemApi.getItem("dustGalena", 2),
                        ItemApi.getItem("dustSmallSulfur", 1),
                        ItemApi.getItem("dustSilver", 1),
                        IC2Items.getItem("cell"),
                        100,
                        120));

        // Iridium Ore
        RecipeHandler.addRecipe(
                new GrinderRecipe(
                        new ItemStack(ModBlocks.ore, 1, 1),
                        null,
                        new FluidStack(FluidRegistry.WATER, 1000),
                        IC2Items.getItem("iridiumOre"),
                        ItemApi.getItem("dustSmallIridium", 6),
                        ItemApi.getItem("dustSmallPlatinum", 2),
                        null,
                        100,
                        120));
        RecipeHandler.addRecipe(
                new GrinderRecipe(
                        new ItemStack(ModBlocks.ore, 1, 1),
                        IC2Items.getItem("waterCell"),
                        null,
                        IC2Items.getItem("iridiumOre"),
                        ItemApi.getItem("dustSmallIridium", 6),
                        ItemApi.getItem("dustSmallPlatinum", 2),
                        IC2Items.getItem("cell"),
                        100,
                        120));
        RecipeHandler.addRecipe(
                new GrinderRecipe(
                        new ItemStack(ModBlocks.ore, 1, 1),
                        new ItemStack(Items.water_bucket),
                        null,
                        IC2Items.getItem("iridiumOre"),
                        ItemApi.getItem("dustSmallIridium", 6),
                        ItemApi.getItem("dustSmallPlatinum", 2),
                        new ItemStack(Items.bucket),
                        100,
                        120));

        RecipeHandler.addRecipe(
                new GrinderRecipe(
                        new ItemStack(ModBlocks.ore, 1, 1),
                        null,
                        new FluidStack(ModFluids.fluidMercury, 1000),
                        IC2Items.getItem("iridiumOre"),
                        ItemApi.getItem("dustSmallIridium", 6),
                        ItemApi.getItem("dustPlatinum", 2),
                        null,
                        100,
                        120));
        RecipeHandler.addRecipe(
                new GrinderRecipe(
                        new ItemStack(ModBlocks.ore, 1, 1),
                        ItemCells.getCellByName("mercury", 1),
                        null,
                        IC2Items.getItem("iridiumOre"),
                        ItemApi.getItem("dustSmallIridium", 6),
                        ItemApi.getItem("dustPlatinum", 2),
                        IC2Items.getItem("cell"),
                        100,
                        120));
        RecipeHandler.addRecipe(
                new GrinderRecipe(
                        new ItemStack(ModBlocks.ore, 1, 1),
                        new ItemStack(ModItems.bucketMercury),
                        null,
                        IC2Items.getItem("iridiumOre"),
                        ItemApi.getItem("dustSmallIridium", 6),
                        ItemApi.getItem("dustPlatinum", 2),
                        new ItemStack(Items.bucket),
                        100,
                        120));

        RecipeHandler.addRecipe(
                new GrinderRecipe(
                        new ItemStack(ModBlocks.ore, 1, 2),
                        IC2Items.getItem("waterCell"),
                        null,
                        ItemApi.getItem("gemRuby", 1),
                        ItemApi.getItem("dustSmallRuby", 6),
                        ItemApi.getItem("dustSmallChrome", 2),
                        IC2Items.getItem("cell"),
                        100,
                        120));

        RecipeHandler.addRecipe(
                new GrinderRecipe(
                        new ItemStack(ModBlocks.ore, 1, 3),
                        IC2Items.getItem("waterCell"),
                        null,
                        ItemApi.getItem("gemSapphire", 1),
                        ItemApi.getItem("dustSmallSapphire", 6),
                        ItemApi.getItem("dustSmallAluminium", 2),
                        IC2Items.getItem("cell"),
                        100,
                        120));

        RecipeHandler.addRecipe(
                new GrinderRecipe(
                        new ItemStack(ModBlocks.ore, 1, 4),
                        IC2Items.getItem("waterCell"),
                        null,
                        ItemApi.getItem("dustBauxite", 2),
                        ItemApi.getItem("dustSmallGrossular", 4),
                        ItemApi.getItem("dustSmallTitanium", 4),
                        IC2Items.getItem("cell"),
                        100,
                        120));

        RecipeHandler.addRecipe(
                new GrinderRecipe(
                        new ItemStack(ModBlocks.ore, 1, 5),
                        IC2Items.getItem("waterCell"),
                        null,
                        ItemApi.getItem("dustPyrite", 2),
                        ItemApi.getItem("dustSmallSulfur", 1),
                        ItemApi.getItem("dustSmallPhosphorous", 1),
                        IC2Items.getItem("cell"),
                        100,
                        120));

        RecipeHandler.addRecipe(
                new GrinderRecipe(
                        new ItemStack(ModBlocks.ore, 1, 6),
                        IC2Items.getItem("waterCell"),
                        null,
                        ItemApi.getItem("dustCinnabar", 2),
                        ItemApi.getItem("dustSmallRedstone", 1),
                        ItemApi.getItem("dustSmallGlowstone", 1),
                        IC2Items.getItem("cell"),
                        100,
                        120));

        RecipeHandler.addRecipe(
                new GrinderRecipe(
                        new ItemStack(ModBlocks.ore, 1, 7),
                        IC2Items.getItem("waterCell"),
                        null,
                        ItemApi.getItem("dustSphalerite", 2),
                        ItemApi.getItem("dustSmallZinc", 1),
                        ItemApi.getItem("dustSmallYellowGarnet", 1),
                        IC2Items.getItem("cell"),
                        100,
                        120));
        RecipeHandler.addRecipe(
                new GrinderRecipe(
                        new ItemStack(ModBlocks.ore, 1, 7),
                        ItemCells.getCellByName("sodiumPersulfate", 1),
                        null,
                        ItemApi.getItem("dustSphalerite", 2),
                        ItemApi.getItem("dustZinc", 1),
                        ItemApi.getItem("dustSmallYellowGarnet", 1),
                        IC2Items.getItem("cell"),
                        100,
                        120));

        RecipeHandler.addRecipe(
                new GrinderRecipe(
                        new ItemStack(ModBlocks.ore, 1, 8),
                        IC2Items.getItem("waterCell"),
                        null,
                        ItemApi.getItem("dustTungsten", 2),
                        ItemApi.getItem("dustSmallManganese", 1),
                        ItemApi.getItem("dustSmallSilver", 1),
                        IC2Items.getItem("cell"),
                        100,
                        120));
        RecipeHandler.addRecipe(
                new GrinderRecipe(
                        new ItemStack(ModBlocks.ore, 1, 8),
                        ItemCells.getCellByName("mercury", 1),
                        null,
                        ItemApi.getItem("dustTungsten", 2),
                        ItemApi.getItem("dustSmallManganese", 1),
                        ItemApi.getItem("dustSilver", 2),
                        IC2Items.getItem("cell"),
                        100,
                        120));

        RecipeHandler.addRecipe(
                new GrinderRecipe(
                        new ItemStack(ModBlocks.ore, 1, 9),
                        IC2Items.getItem("waterCell"),
                        null,
                        ItemApi.getItem("dustPlatinum", 2),
                        ItemApi.getItem("dustSmallIridium", 1),
                        ItemApi.getItem("dustSmallIridium", 1),
                        IC2Items.getItem("cell"),
                        100,
                        120));
        RecipeHandler.addRecipe(
                new GrinderRecipe(
                        new ItemStack(ModBlocks.ore, 1, 9),
                        ItemCells.getCellByName("mercury", 1),
                        null,
                        ItemApi.getItem("dustPlatinum", 3),
                        ItemApi.getItem("dustSmallIridium", 1),
                        ItemApi.getItem("dustSmallIridium", 1),
                        IC2Items.getItem("cell"),
                        100,
                        120));

        RecipeHandler.addRecipe(
                new GrinderRecipe(
                        new ItemStack(ModBlocks.ore, 1, 10),
                        IC2Items.getItem("waterCell"),
                        null,
                        ItemApi.getItem("gemPeridot", 1),
                        ItemApi.getItem("dustSmallPeridot", 6),
                        ItemApi.getItem("dustSmallPyrope", 2),
                        IC2Items.getItem("cell"),
                        100,
                        120));

        RecipeHandler.addRecipe(
                new GrinderRecipe(
                        new ItemStack(ModBlocks.ore, 1, 11),
                        IC2Items.getItem("waterCell"),
                        null,
                        ItemApi.getItem("dustSodalite", 12),
                        ItemApi.getItem("dustSmallLazurite", 4),
                        ItemApi.getItem("dustSmallLapis", 4),
                        IC2Items.getItem("cell"),
                        100,
                        120));

        RecipeHandler.addRecipe(
                new GrinderRecipe(
                        new ItemStack(ModBlocks.ore, 1, 12),
                        IC2Items.getItem("waterCell"),
                        null,
                        ItemApi.getItem("dustTetrahedrite", 2),
                        ItemApi.getItem("dustSmallAntimony", 1),
                        ItemApi.getItem("dustSmallZinc", 1),
                        IC2Items.getItem("cell"),
                        100,
                        120));
        RecipeHandler.addRecipe(
                new GrinderRecipe(
                        new ItemStack(ModBlocks.ore, 1, 12),
                        ItemCells.getCellByName("sodiumPersulfate", 1),
                        null,
                        ItemApi.getItem("dustTetrahedrite", 3),
                        ItemApi.getItem("dustSmallAntimony", 1),
                        ItemApi.getItem("dustSmallZinc", 1),
                        IC2Items.getItem("cell"),
                        100,
                        120));

        // Chemical Reactor
        RecipeHandler.addRecipe(
                new ChemicalReactorRecipe(
                        ItemApi.getItem("dustCalcite", 1),
                        null,
                        new ItemStack(IC2Items.getItem("fertilizer").getItem(), 1),
                        100,
                        30));
        RecipeHandler.addRecipe(
                new ChemicalReactorRecipe(
                        ItemApi.getItem("dustCalcite", 1),
                        ItemApi.getItem("dustPhosphorous", 1),
                        new ItemStack(IC2Items.getItem("fertilizer").getItem(), 3),
                        100,
                        30));
        RecipeHandler.addRecipe(
                new ChemicalReactorRecipe(
                        ItemCells.getCellByName("sodiumSulfide", 1),
                        IC2Items.getItem("airCell"),
                        ItemCells.getCellByName("sodiumPersulfate", 2),
                        2000,
                        30));
        RecipeHandler.addRecipe(
                new ChemicalReactorRecipe(
                        ItemCells.getCellByName("nitrocarbon", 1),
                        IC2Items.getItem("waterCell"),
                        ItemCells.getCellByName("glyceryl", 2),
                        580,
                        30));
        RecipeHandler.addRecipe(
                new ChemicalReactorRecipe(
                        ItemApi.getItem("dustCalcite", 1),
                        ItemApi.getItem("dustSulfur", 1),
                        new ItemStack(IC2Items.getItem("fertilizer").getItem(), 2),
                        100,
                        30));
        ItemStack waterCells = IC2Items.getItem("waterCell").copy();
        waterCells.stackSize = 2;
        RecipeHandler.addRecipe(
                new ChemicalReactorRecipe(
                        ItemCells.getCellByName("sulfur", 1),
                        waterCells,
                        ItemCells.getCellByName("sulfuricAcid", 3),
                        1140,
                        30));
        ItemStack waterCells2 = IC2Items.getItem("waterCell").copy();
        waterCells2.stackSize = 5;
        RecipeHandler.addRecipe(
                new ChemicalReactorRecipe(
                        ItemCells.getCellByName("hydrogen", 4),
                        IC2Items.getItem("airCell"),
                        waterCells2,
                        10,
                        30));
        RecipeHandler.addRecipe(
                new ChemicalReactorRecipe(
                        ItemCells.getCellByName("nitrogen", 1),
                        IC2Items.getItem("airCell"),
                        ItemCells.getCellByName("nitrogenDioxide", 2),
                        1240,
                        30));

        // IndustrialElectrolyzer

        RecipeHandler.addRecipe(
                new IndustrialElectrolyzerRecipe(
                        ItemCells.getCellByName("sulfuricAcid", 7),
                        null,
                        ItemCells.getCellByName("hydrogen", 2),
                        ItemApi.getItem("dustSulfur"),
                        new ItemStack(IC2Items.getItem("cell").getItem(), 2, 5),
                        new ItemStack(IC2Items.getItem("cell").getItem(), 3, 0),
                        400,
                        90));

        RecipeHandler.addRecipe(
                new IndustrialElectrolyzerRecipe(
                        ItemApi.getItem("dustRuby", 6),
                        IC2Items.getItem("cell"),
                        ItemApi.getItem("dustAluminium", 2),
                        new ItemStack(IC2Items.getItem("cell").getItem(), 1, 5),
                        ItemApi.getItem("dustChrome", 1),
                        null,
                        140,
                        90));

        RecipeHandler.addRecipe(
                new IndustrialElectrolyzerRecipe(
                        ItemApi.getItem("dustSapphire", 5),
                        IC2Items.getItem("cell"),
                        ItemApi.getItem("dustAluminium", 2),
                        new ItemStack(IC2Items.getItem("cell").getItem(), 1, 5),
                        null,
                        null,
                        100,
                        60));

        RecipeHandler.addRecipe(
                new IndustrialElectrolyzerRecipe(
                        ItemCells.getCellByName("nitrogenDioxide", 3),
                        null,
                        ItemCells.getCellByName("nitrogen", 1),
                        new ItemStack(IC2Items.getItem("cell").getItem(), 1, 5),
                        null,
                        IC2Items.getItem("cell"),
                        160,
                        60));

        RecipeHandler.addRecipe(
                new IndustrialElectrolyzerRecipe(
                        ItemCells.getCellByName("sodiumSulfide", 2),
                        null,
                        ItemCells.getCellByName("sodium", 1),
                        ItemApi.getItem("dustSulfur", 1),
                        null,
                        IC2Items.getItem("cell"),
                        200,
                        60));

        RecipeHandler.addRecipe(
                new IndustrialElectrolyzerRecipe(
                        ItemApi.getItem("dustGreenSapphire", 5),
                        IC2Items.getItem("cell"),
                        ItemApi.getItem("dustAluminium", 2),
                        new ItemStack(IC2Items.getItem("cell").getItem(), 1, 5),
                        null,
                        null,
                        100,
                        60));

        RecipeHandler.addRecipe(
                new IndustrialElectrolyzerRecipe(
                        ItemApi.getItem("dustEmerald", 29),
                        new ItemStack(IC2Items.getItem("cell").getItem(), 18, 0),
                        ItemCells.getCellByName("berylium", 3),
                        ItemApi.getItem("dustAluminium", 2),
                        ItemCells.getCellByName("silicon", 6),
                        new ItemStack(IC2Items.getItem("cell").getItem(), 9, 5),
                        520,
                        120));

        RecipeHandler.addRecipe(
                new IndustrialElectrolyzerRecipe(
                        new ItemStack(IC2Items.getItem("silicondioxideDust").getItem(), 3, 0),
                        new ItemStack(IC2Items.getItem("cell").getItem(), 2, 0),
                        ItemCells.getCellByName("silicon", 1),
                        new ItemStack(IC2Items.getItem("cell").getItem(), 1, 5),
                        null,
                        null,
                        60,
                        60));

        RecipeHandler.addRecipe(
                new IndustrialElectrolyzerRecipe(
                        new ItemStack(Items.dye, 3, 15),
                        new ItemStack(IC2Items.getItem("cell").getItem(), 1, 0),
                        null,
                        ItemCells.getCellByName("calcium", 1),
                        null,
                        null,
                        20,
                        106));

        RecipeHandler.addRecipe(
                new IndustrialElectrolyzerRecipe(
                        ItemCells.getCellByName("glyceryl", 20),
                        null,
                        ItemCells.getCellByName("carbon", 3),
                        ItemCells.getCellByName("hydrogen", 5),
                        ItemCells.getCellByName("nitrogen", 3),
                        new ItemStack(IC2Items.getItem("cell").getItem(), 9, 0),
                        800,
                        90));

        RecipeHandler.addRecipe(
                new IndustrialElectrolyzerRecipe(
                        ItemApi.getItem("dustPeridot", 9),
                        new ItemStack(IC2Items.getItem("cell").getItem(), 4, 0),
                        ItemApi.getItem("dustMagnesium", 2),
                        ItemApi.getItem("dustIron"),
                        ItemCells.getCellByName("silicon", 2),
                        new ItemStack(IC2Items.getItem("cell").getItem(), 2, 5),
                        200,
                        120));

        RecipeHandler.addRecipe(
                new IndustrialElectrolyzerRecipe(
                        ItemCells.getCellByName("calciumCarbonate", 5),
                        null,
                        ItemCells.getCellByName("carbon"),
                        ItemCells.getCellByName("calcium"),
                        new ItemStack(IC2Items.getItem("cell").getItem(), 1, 5),
                        new ItemStack(IC2Items.getItem("cell").getItem(), 2, 0),
                        400,
                        90));

        RecipeHandler.addRecipe(
                new IndustrialElectrolyzerRecipe(
                        ItemCells.getCellByName("sodiumPersulfate", 6),
                        null,
                        ItemCells.getCellByName("sodium"),
                        ItemApi.getItem("dustSulfur"),
                        new ItemStack(IC2Items.getItem("cell").getItem(), 2, 5),
                        new ItemStack(IC2Items.getItem("cell").getItem(), 3, 0),
                        420,
                        90));

        RecipeHandler.addRecipe(
                new IndustrialElectrolyzerRecipe(
                        ItemApi.getItem("dustPyrope", 20),
                        new ItemStack(IC2Items.getItem("cell").getItem(), 9, 0),
                        ItemApi.getItem("dustAluminium", 2),
                        ItemApi.getItem("dustMagnesium", 3),
                        ItemCells.getCellByName("silicon", 3),
                        new ItemStack(IC2Items.getItem("cell").getItem(), 6, 5),
                        400,
                        120));

        ItemStack sand = new ItemStack(Blocks.sand);
        sand.stackSize = 16;

        RecipeHandler.addRecipe(
                new IndustrialElectrolyzerRecipe(
                        sand,
                        new ItemStack(IC2Items.getItem("cell").getItem(), 2, 0),
                        ItemCells.getCellByName("silicon", 1),
                        new ItemStack(IC2Items.getItem("cell").getItem(), 1, 5),
                        null,
                        null,
                        1000,
                        25));

        RecipeHandler.addRecipe(
                new IndustrialElectrolyzerRecipe(
                        ItemApi.getItem("dustAlmandine", 20),
                        new ItemStack(IC2Items.getItem("cell").getItem(), 9, 0),
                        ItemApi.getItem("dustAluminium", 2),
                        ItemApi.getItem("dustIron", 3),
                        ItemCells.getCellByName("silicon", 3),
                        new ItemStack(IC2Items.getItem("cell").getItem(), 6, 5),
                        480,
                        120));

        RecipeHandler.addRecipe(
                new IndustrialElectrolyzerRecipe(
                        ItemApi.getItem("dustSpessartine", 20),
                        new ItemStack(IC2Items.getItem("cell").getItem(), 9, 0),
                        ItemApi.getItem("dustAluminium", 2),
                        ItemApi.getItem("dustManganese", 3),
                        ItemCells.getCellByName("silicon", 3),
                        new ItemStack(IC2Items.getItem("cell").getItem(), 6, 5),
                        480,
                        120));

        RecipeHandler.addRecipe(
                new IndustrialElectrolyzerRecipe(
                        ItemApi.getItem("dustAndradite", 20),
                        new ItemStack(IC2Items.getItem("cell").getItem(), 12, 0),
                        ItemCells.getCellByName("calcium", 3),
                        ItemApi.getItem("dustIron", 2),
                        ItemCells.getCellByName("silicon", 3),
                        new ItemStack(IC2Items.getItem("cell").getItem(), 6, 5),
                        480,
                        120));

        RecipeHandler.addRecipe(
                new IndustrialElectrolyzerRecipe(
                        ItemApi.getItem("dustGrossular", 20),
                        new ItemStack(IC2Items.getItem("cell").getItem(), 12, 0),
                        ItemCells.getCellByName("calcium", 3),
                        ItemApi.getItem("dustAluminium", 2),
                        ItemCells.getCellByName("silicon", 3),
                        new ItemStack(IC2Items.getItem("cell").getItem(), 6, 5),
                        440,
                        120));

        RecipeHandler.addRecipe(
                new IndustrialElectrolyzerRecipe(
                        ItemApi.getItem("dustUvarovite", 20),
                        new ItemStack(IC2Items.getItem("cell").getItem(), 12, 0),
                        ItemCells.getCellByName("calcium", 3),
                        ItemApi.getItem("dustChrome", 2),
                        ItemCells.getCellByName("silicon", 3),
                        new ItemStack(IC2Items.getItem("cell").getItem(), 6, 5),
                        480,
                        120));

        RecipeHandler.addRecipe(
                new IndustrialElectrolyzerRecipe(
                        new ItemStack(IC2Items.getItem("cell").getItem(), 6, 10),
                        null,
                        ItemCells.getCellByName("hydrogen", 4),
                        new ItemStack(IC2Items.getItem("cell").getItem(), 1, 5),
                        new ItemStack(IC2Items.getItem("cell").getItem(), 1, 0),
                        null,
                        100,
                        30));

        RecipeHandler.addRecipe(
                new IndustrialElectrolyzerRecipe(
                        ItemApi.getItem("dustDarkAsh"),
                        new ItemStack(IC2Items.getItem("cell").getItem(), 2, 0),
                        ItemCells.getCellByName("carbon", 2),
                        null,
                        null,
                        null,
                        20,
                        30));

        if (OreUtil.doesOreExistAndValid("dustSalt")) {
            ItemStack salt = OreDictionary.getOres("dustSalt").get(0);
            salt.stackSize = 2;
            RecipeHandler.addRecipe(
                    new IndustrialElectrolyzerRecipe(
                            salt,
                            new ItemStack(IC2Items.getItem("cell").getItem(), 2, 0),
                            ItemCells.getCellByName("sodium"),
                            ItemCells.getCellByName("chlorine"),
                            null,
                            null,
                            40,
                            60));
        }

        CraftingHelper.addShapedOreRecipe(
                ItemApi.getItem("nakCoolantSimple"),
                "TST",
                "PCP",
                "TST",
                'T',
                "ingotTin",
                'S',
                ItemCells.getCellByName("sodium"),
                'P',
                ItemCells.getCellByName("potassium"),
                'C',
                IC2Items.getItem("reactorCoolantSimple"));
    }

    static void removeIc2Recipes() {
        if (ConfigTechReborn.ExpensiveMacerator) {
            RecipeRemover.removeAnyRecipe(IC2Items.getItem("macerator"));
        }
        if (ConfigTechReborn.ExpensiveDrill) {
            RecipeRemover.removeAnyRecipe(IC2Items.getItem("miningDrill"));
        }
        if (ConfigTechReborn.ExpensiveDiamondDrill) {
            RecipeRemover.removeAnyRecipe(IC2Items.getItem("diamondDrill"));
        }
        if (ConfigTechReborn.ExpensiveSolar) {
            RecipeRemover.removeAnyRecipe(IC2Items.getItem("solarPanel"));
        }
        if (ConfigTechReborn.ExpensiveWatermill) {
            RecipeRemover.removeAnyRecipe(IC2Items.getItem("waterMill"));
        }
        if (ConfigTechReborn.ExpensiveWindmill) {
            RecipeRemover.removeAnyRecipe(IC2Items.getItem("windMill"));
        }

        Core.logHelper.info("IC2 Recipes Removed");
    }

    static void addShappedIc2Recipes() {
        Item drill = IC2Items.getItem("miningDrill").getItem();
        ItemStack drillStack = new ItemStack(drill, 1, OreDictionary.WILDCARD_VALUE);

        if (ConfigTechReborn.ExpensiveMacerator) CraftingHelper.addShapedOreRecipe(
                IC2Items.getItem("macerator"),
                "FDF",
                "DMD",
                "FCF",
                'F',
                Items.flint,
                'D',
                Items.diamond,
                'M',
                IC2Items.getItem("machine"),
                'C',
                IC2Items.getItem("electronicCircuit"));

        if (ConfigTechReborn.ExpensiveDrill) CraftingHelper.addShapedOreRecipe(
                IC2Items.getItem("miningDrill"),
                " S ",
                "SCS",
                "SBS",
                'S',
                "ingotSteel",
                'B',
                IC2Items.getItem("reBattery"),
                'C',
                IC2Items.getItem("electronicCircuit"));

        if (ConfigTechReborn.ExpensiveDiamondDrill) CraftingHelper.addShapedOreRecipe(
                IC2Items.getItem("diamondDrill"),
                " D ",
                "DBD",
                "TCT",
                'D',
                "gemDiamond",
                'T',
                "ingotTitanium",
                'B',
                drillStack,
                'C',
                IC2Items.getItem("advancedCircuit"));

        if (ConfigTechReborn.ExpensiveSolar) CraftingHelper.addShapedOreRecipe(
                IC2Items.getItem("solarPanel"),
                "PPP",
                "SZS",
                "CGC",
                'P',
                "paneGlass",
                'S',
                ItemApi.getItem("boardBasic"),
                'Z',
                IC2Items.getItem("carbonPlate"),
                'G',
                IC2Items.getItem("generator"),
                'C',
                IC2Items.getItem("electronicCircuit"));

        CraftingHelper.addShapedOreRecipe(
                ItemApi.getItem("ingotIridiumAlloy"),
                "IAI",
                "ADA",
                "IAI",
                'I',
                ItemApi.getItem("ingotIridium"),
                'D',
                ItemApi.getItem("dustDiamond"),
                'A',
                IC2Items.getItem("advancedAlloy"));

        CraftingHelper.addShapedOreRecipe(
                new ItemStack(ModItems.lithiumBatpack, 1, OreDictionary.WILDCARD_VALUE),
                "BCB",
                "BPB",
                "B B",
                'B',
                new ItemStack(ModItems.lithiumBattery),
                'P',
                "plateAluminium",
                'C',
                IC2Items.getItem("advancedCircuit"));

        CraftingHelper.addShapedOreRecipe(
                new ItemStack(ModItems.lithiumBattery, 1, OreDictionary.WILDCARD_VALUE),
                " C ",
                "PFP",
                "PFP",
                'F',
                ItemCells.getCellByName("lithium"),
                'P',
                "plateAluminium",
                'C',
                IC2Items.getItem("insulatedGoldCableItem"));

        CraftingHelper.addShapedOreRecipe(
                new ItemStack(ModItems.lapotronpack, 1, OreDictionary.WILDCARD_VALUE),
                "FOF",
                "SPS",
                "FIF",
                'F',
                ItemApi.getItem("circuitMaster"),
                'O',
                new ItemStack(ModItems.lapotronicOrb),
                'S',
                ItemApi.getItem("craftingSuperconductor"),
                'I',
                "ingotIridium",
                'P',
                new ItemStack(ModItems.lapotronpack));

        Recipes.compressor
                .addRecipe(new RecipeInputOreDict("dustLazurite"), null, ItemApi.getItem("chunkLazurite"));

        Core.logHelper.info("Added Expensive IC2 Recipes");
    }

    static void addTRMaceratorRecipes() {
        // Macerator

        List<String> blackList = new ArrayList<String>();
        blackList.add("lapis");
        blackList.add("redstone");
        blackList.add("teslatite");

        for (String ore : OreUtil.oreNames) {
            if (OreUtil.hasCrushedOre(ore) && OreUtil.hasOre(ore)) {
                try {
                    Recipes.macerator.addRecipe(
                            new RecipeInputOreDict("ore" + OreUtil.capitalizeFirstLetter(ore)),
                            null,
                            ItemApi.getItem("crushed" + OreUtil.capitalizeFirstLetter(ore), 2));
                } catch (Exception e) {
                    Core.logHelper.error("Failed to load recipe for " + ore + " crushed ore");
                }
            }
            if (OreUtil.hasOre(ore) && OreUtil.hasDust(ore) && !blackList.contains(ore)) {
                try {
                    Recipes.macerator.addRecipe(
                            new RecipeInputOreDict("ore" + OreUtil.capitalizeFirstLetter(ore)),
                            null,
                            ItemApi.getItem("dust" + OreUtil.capitalizeFirstLetter(ore), 2));
                } catch (Exception e) {
                    Core.logHelper.error("Failed to load recipe for " + ore);
                }
            }
        }

        if (CompatManager.isGregTechLoaded) {
            if (!IC2Classic.isIc2ClassicLoaded() && OreUtil.doesOreExistAndValid("oreRedstone")) {
                Recipes.macerator
                        .addRecipe(new RecipeInputOreDict("oreRedstone"), null, new ItemStack(Items.redstone, 9));
            }
            if (OreUtil.doesOreExistAndValid("oreLapis")) {
                Recipes.macerator.addRecipe(new RecipeInputOreDict("oreLapis"), null, ItemApi.getItem("dustLapis", 9));
            }
        }

        if (OreUtil.doesOreExistAndValid("oreTeslatite")) {
            ItemStack teslatiteStack = OreDictionary.getOres("dustTeslatite").get(0);
            teslatiteStack.stackSize = 10;
            Recipes.macerator.addRecipe(new RecipeInputOreDict("oreTeslatite"), null, teslatiteStack);
        }
        if (OreUtil.doesOreExistAndValid("gemRuby")) {
            Recipes.macerator.getRecipes().put(
                    new RecipeInputOreDict("gemRuby"),
                    new RecipeOutput(new NBTTagCompound(), ItemApi.getItem("dustRuby")));
        }
    }

    static void addTROreWashingRecipes() {
        // Ore Washing Plant
        NBTTagCompound liquidAmount = new NBTTagCompound();
        liquidAmount.setInteger("amount", 1000);
        Recipes.oreWashing.addRecipe(
                new RecipeInputOreDict("crushedAluminium"),
                liquidAmount,
                ItemApi.getItem("crushedPurifiedAluminium", 1),
                ItemApi.getItem("dustSmallAluminium", 2),
                IC2Items.getItem("stoneDust"));
        Recipes.oreWashing.addRecipe(
                new RecipeInputOreDict("crushedArdite"),
                liquidAmount,
                ItemApi.getItem("crushedPurifiedArdite", 1),
                ItemApi.getItem("dustSmallArdite", 2),
                IC2Items.getItem("stoneDust"));
        Recipes.oreWashing.addRecipe(
                new RecipeInputOreDict("crushedBauxite"),
                liquidAmount,
                ItemApi.getItem("crushedPurifiedBauxite", 1),
                ItemApi.getItem("dustSmallBauxite", 2),
                IC2Items.getItem("stoneDust"));
        Recipes.oreWashing.addRecipe(
                new RecipeInputOreDict("crushedCadmium"),
                liquidAmount,
                ItemApi.getItem("crushedPurifiedCadmium", 1),
                ItemApi.getItem("dustSmallCadmium", 2),
                IC2Items.getItem("stoneDust"));
        Recipes.oreWashing.addRecipe(
                new RecipeInputOreDict("crushedCinnabar"),
                liquidAmount,
                ItemApi.getItem("crushedPurifiedCinnabar", 1),
                ItemApi.getItem("dustSmallCinnabar", 2),
                IC2Items.getItem("stoneDust"));
        Recipes.oreWashing.addRecipe(
                new RecipeInputOreDict("crushedCobalt"),
                liquidAmount,
                ItemApi.getItem("crushedPurifiedCobalt", 1),
                ItemApi.getItem("dustSmallCobalt", 2),
                IC2Items.getItem("stoneDust"));
        Recipes.oreWashing.addRecipe(
                new RecipeInputOreDict("crushedDarkIron"),
                liquidAmount,
                ItemApi.getItem("crushedPurifiedDarkIron", 1),
                ItemApi.getItem("dustSmallDarkIron", 2),
                IC2Items.getItem("stoneDust"));
        Recipes.oreWashing.addRecipe(
                new RecipeInputOreDict("crushedIndium"),
                liquidAmount,
                ItemApi.getItem("crushedPurifiedIndium", 1),
                ItemApi.getItem("dustSmallIndium", 2),
                IC2Items.getItem("stoneDust"));
        Recipes.oreWashing.addRecipe(
                new RecipeInputOreDict("crushedNickel"),
                liquidAmount,
                ItemApi.getItem("crushedPurifiedNickel", 1),
                ItemApi.getItem("dustSmallNickel", 2),
                IC2Items.getItem("stoneDust"));
        Recipes.oreWashing.addRecipe(
                new RecipeInputOreDict("crushedOsmium"),
                liquidAmount,
                ItemApi.getItem("crushedPurifiedOsmium", 1),
                ItemApi.getItem("dustSmallOsmium", 2),
                IC2Items.getItem("stoneDust"));
        Recipes.oreWashing.addRecipe(
                new RecipeInputOreDict("crushedPyrite"),
                liquidAmount,
                ItemApi.getItem("crushedPurifiedPyrite", 1),
                ItemApi.getItem("dustSmallPyrite", 2),
                IC2Items.getItem("stoneDust"));
        Recipes.oreWashing.addRecipe(
                new RecipeInputOreDict("crushedSphalerite"),
                liquidAmount,
                ItemApi.getItem("crushedPurifiedSphalerite", 1),
                ItemApi.getItem("dustSmallSphalerite", 2),
                IC2Items.getItem("stoneDust"));
        Recipes.oreWashing.addRecipe(
                new RecipeInputOreDict("crushedTetrahedrite"),
                liquidAmount,
                ItemApi.getItem("crushedPurifiedTetrahedrite", 1),
                ItemApi.getItem("dustSmallTetrahedrite", 2),
                IC2Items.getItem("stoneDust"));
        Recipes.oreWashing.addRecipe(
                new RecipeInputOreDict("crushedGalena"),
                liquidAmount,
                ItemApi.getItem("crushedPurifiedGalena", 1),
                ItemApi.getItem("dustSmallGalena", 2),
                IC2Items.getItem("stoneDust"));

        if (!Loader.isModLoaded("aobd")) {
            Recipes.oreWashing.addRecipe(
                    new RecipeInputOreDict("crushedPlatinum"),
                    liquidAmount,
                    ItemApi.getItem("crushedPurifiedPlatinum", 1),
                    ItemApi.getItem("dustSmallPlatinum", 2),
                    IC2Items.getItem("stoneDust"));
            Recipes.oreWashing.addRecipe(
                    new RecipeInputOreDict("crushedIridium"),
                    liquidAmount,
                    ItemApi.getItem("crushedPurifiedIridium", 1),
                    ItemApi.getItem("dustSmallIridium", 2),
                    IC2Items.getItem("stoneDust"));
            Recipes.oreWashing.addRecipe(
                    new RecipeInputOreDict("crushedTungsten"),
                    liquidAmount,
                    ItemApi.getItem("crushedPurifiedTungsten", 1),
                    ItemApi.getItem("dustSmallTungsten", 2),
                    IC2Items.getItem("stoneDust"));
        }
    }

    static void addTRThermalCentrifugeRecipes() {
        // Thermal Centrifuge

        // Heat Values
        NBTTagCompound aluminumHeat = new NBTTagCompound();
        aluminumHeat.setInteger("minHeat", 2000);
        NBTTagCompound arditeHeat = new NBTTagCompound();
        arditeHeat.setInteger("minHeat", 3000);
        NBTTagCompound bauxiteHeat = new NBTTagCompound();
        bauxiteHeat.setInteger("minHeat", 2500);
        NBTTagCompound cadmiumHeat = new NBTTagCompound();
        cadmiumHeat.setInteger("minHeat", 1500);
        NBTTagCompound cinnabarHeat = new NBTTagCompound();
        cinnabarHeat.setInteger("minHeat", 1500);
        NBTTagCompound cobaltHeat = new NBTTagCompound();
        cobaltHeat.setInteger("minHeat", 3000);
        NBTTagCompound darkIronHeat = new NBTTagCompound();
        darkIronHeat.setInteger("minHeat", 2500);
        NBTTagCompound indiumHeat = new NBTTagCompound();
        indiumHeat.setInteger("minHeat", 2000);
        NBTTagCompound iridiumHeat = new NBTTagCompound();
        iridiumHeat.setInteger("minHeat", 4000);
        NBTTagCompound nickelHeat = new NBTTagCompound();
        nickelHeat.setInteger("minHeat", 2000);
        NBTTagCompound osmiumHeat = new NBTTagCompound();
        osmiumHeat.setInteger("minHeat", 2000);
        NBTTagCompound platinumHeat = new NBTTagCompound();
        platinumHeat.setInteger("minHeat", 3000);
        NBTTagCompound pyriteHeat = new NBTTagCompound();
        pyriteHeat.setInteger("minHeat", 1500);
        NBTTagCompound sphaleriteHeat = new NBTTagCompound();
        sphaleriteHeat.setInteger("minHeat", 1500);
        NBTTagCompound tetrahedriteHeat = new NBTTagCompound();
        tetrahedriteHeat.setInteger("minHeat", 500);
        NBTTagCompound tungstenHeat = new NBTTagCompound();
        tungstenHeat.setInteger("minHeat", 2000);
        NBTTagCompound galenaHeat = new NBTTagCompound();
        galenaHeat.setInteger("minHeat", 2500);

        Recipes.centrifuge.addRecipe(
                new RecipeInputOreDict("crushedAluminum"),
                aluminumHeat,
                ItemApi.getItem("dustSmallBauxite", 1),
                ItemApi.getItem("dustAluminium", 1),
                IC2Items.getItem("stoneDust"));
        Recipes.centrifuge.addRecipe(
                new RecipeInputOreDict("crushedArdite"),
                arditeHeat,
                ItemApi.getItem("dustSmallArdite", 1),
                ItemApi.getItem("dustArdite", 1),
                IC2Items.getItem("stoneDust"));
        Recipes.centrifuge.addRecipe(
                new RecipeInputOreDict("crushedBauxite"),
                bauxiteHeat,
                ItemApi.getItem("dustSmallAluminium", 1),
                ItemApi.getItem("dustBauxite", 1),
                IC2Items.getItem("stoneDust"));
        Recipes.centrifuge.addRecipe(
                new RecipeInputOreDict("crushedCadmium"),
                cadmiumHeat,
                ItemApi.getItem("dustSmallCadmium", 1),
                ItemApi.getItem("dustCadmium", 1),
                IC2Items.getItem("stoneDust"));
        Recipes.centrifuge.addRecipe(
                new RecipeInputOreDict("crushedCinnabar"),
                cinnabarHeat,
                ItemApi.getItem("dustSmallRedstone", 1),
                ItemApi.getItem("dustCinnabar", 1),
                IC2Items.getItem("stoneDust"));
        Recipes.centrifuge.addRecipe(
                new RecipeInputOreDict("crushedCobalt"),
                cobaltHeat,
                ItemApi.getItem("dustSmallCobalt", 1),
                ItemApi.getItem("dustCobalt", 1),
                IC2Items.getItem("stoneDust"));
        Recipes.centrifuge.addRecipe(
                new RecipeInputOreDict("crushedDarkIron"),
                darkIronHeat,
                ItemApi.getItem("dustSmallIron", 1),
                ItemApi.getItem("dustDarkIron", 1),
                IC2Items.getItem("stoneDust"));
        Recipes.centrifuge.addRecipe(
                new RecipeInputOreDict("crushedIndium"),
                indiumHeat,
                ItemApi.getItem("dustSmallIndium", 1),
                ItemApi.getItem("dustIndium", 1),
                IC2Items.getItem("stoneDust"));
        Recipes.centrifuge.addRecipe(
                new RecipeInputOreDict("crushedNickel"),
                nickelHeat,
                ItemApi.getItem("dustSmallIron", 1),
                ItemApi.getItem("dustNickel", 1),
                IC2Items.getItem("stoneDust"));
        Recipes.centrifuge.addRecipe(
                new RecipeInputOreDict("crushedOsmium"),
                osmiumHeat,
                ItemApi.getItem("dustSmallOsmium", 1),
                ItemApi.getItem("dustOsmium", 1),
                IC2Items.getItem("stoneDust"));
        Recipes.centrifuge.addRecipe(
                new RecipeInputOreDict("crushedPyrite"),
                pyriteHeat,
                ItemApi.getItem("dustSmallSulfur", 1),
                ItemApi.getItem("dustPyrite", 1),
                IC2Items.getItem("stoneDust"));
        Recipes.centrifuge.addRecipe(
                new RecipeInputOreDict("crushedSphalerite"),
                sphaleriteHeat,
                ItemApi.getItem("dustSmallZinc", 1),
                ItemApi.getItem("dustSphalerite", 1),
                IC2Items.getItem("stoneDust"));
        Recipes.centrifuge.addRecipe(
                new RecipeInputOreDict("crushedTetrahedrite"),
                tetrahedriteHeat,
                ItemApi.getItem("dustSmallAntimony", 1),
                ItemApi.getItem("dustTetrahedrite", 1),
                IC2Items.getItem("stoneDust"));
        Recipes.centrifuge.addRecipe(
                new RecipeInputOreDict("crushedGalena"),
                galenaHeat,
                ItemApi.getItem("dustSmallSulfur", 1),
                ItemApi.getItem("dustGalena", 1),
                IC2Items.getItem("stoneDust"));

        Recipes.centrifuge.addRecipe(
                new RecipeInputOreDict("crushedPurifiedAluminum"),
                aluminumHeat,
                ItemApi.getItem("dustSmallBauxite", 1),
                ItemApi.getItem("dustAluminium", 1));
        Recipes.centrifuge.addRecipe(
                new RecipeInputOreDict("crushedPurifiedArdite"),
                arditeHeat,
                ItemApi.getItem("dustSmallArdite", 1),
                ItemApi.getItem("dustArdite", 1));
        Recipes.centrifuge.addRecipe(
                new RecipeInputOreDict("crushedPurifiedBauxite"),
                bauxiteHeat,
                ItemApi.getItem("dustSmallAluminium", 1),
                ItemApi.getItem("dustBauxite", 1));
        Recipes.centrifuge.addRecipe(
                new RecipeInputOreDict("crushedPurifiedCadmium"),
                cadmiumHeat,
                ItemApi.getItem("dustSmallCadmium", 1),
                ItemApi.getItem("dustCadmium", 1));
        Recipes.centrifuge.addRecipe(
                new RecipeInputOreDict("crushedPurifiedCinnabar"),
                cinnabarHeat,
                ItemApi.getItem("dustSmallRedstone", 1),
                ItemApi.getItem("dustCinnabar", 1));
        Recipes.centrifuge.addRecipe(
                new RecipeInputOreDict("crushedPurifiedCobalt"),
                cobaltHeat,
                ItemApi.getItem("dustSmallCobalt", 1),
                ItemApi.getItem("dustCobalt", 1));
        Recipes.centrifuge.addRecipe(
                new RecipeInputOreDict("crushedPurifiedDarkIron"),
                darkIronHeat,
                ItemApi.getItem("dustSmallIron", 1),
                ItemApi.getItem("dustDarkIron", 1));
        Recipes.centrifuge.addRecipe(
                new RecipeInputOreDict("crushedPurifiedIndium"),
                indiumHeat,
                ItemApi.getItem("dustSmallIndium", 1),
                ItemApi.getItem("dustIndium", 1));
        Recipes.centrifuge.addRecipe(
                new RecipeInputOreDict("crushedPurifiedNickel"),
                nickelHeat,
                ItemApi.getItem("dustSmallIron", 1),
                ItemApi.getItem("dustNickel", 1));
        Recipes.centrifuge.addRecipe(
                new RecipeInputOreDict("crushedPurifiedOsmium"),
                osmiumHeat,
                ItemApi.getItem("dustSmallOsmium", 1),
                ItemApi.getItem("dustOsmium", 1));
        Recipes.centrifuge.addRecipe(
                new RecipeInputOreDict("crushedPurifiedPyrite"),
                pyriteHeat,
                ItemApi.getItem("dustSmallSulfur", 1),
                ItemApi.getItem("dustPyrite", 1));
        Recipes.centrifuge.addRecipe(
                new RecipeInputOreDict("crushedPurifiedSphalerite"),
                sphaleriteHeat,
                ItemApi.getItem("dustSmallZinc", 1),
                ItemApi.getItem("dustSphalerite", 1));
        Recipes.centrifuge.addRecipe(
                new RecipeInputOreDict("crushedPurifiedTetrahedrite"),
                tetrahedriteHeat,
                ItemApi.getItem("dustSmallAntimony", 1),
                ItemApi.getItem("dustTetrahedrite", 1));
        Recipes.centrifuge.addRecipe(
                new RecipeInputOreDict("crushedPurifiedGalena"),
                galenaHeat,
                ItemApi.getItem("dustSmallSulfur", 1),
                ItemApi.getItem("dustGalena", 1));

        if (!Loader.isModLoaded("aobd")) {
            Recipes.centrifuge.addRecipe(
                    new RecipeInputOreDict("crushedIridium"),
                    iridiumHeat,
                    ItemApi.getItem("dustSmallPlatinum", 1),
                    ItemApi.getItem("dustIridium", 1),
                    IC2Items.getItem("stoneDust"));
            Recipes.centrifuge.addRecipe(
                    new RecipeInputOreDict("crushedPlatinum"),
                    platinumHeat,
                    ItemApi.getItem("dustSmallIridium", 1),
                    ItemApi.getItem("dustPlatinum", 1),
                    IC2Items.getItem("stoneDust"));
            Recipes.centrifuge.addRecipe(
                    new RecipeInputOreDict("crushedTungsten"),
                    tungstenHeat,
                    ItemApi.getItem("dustSmallManganese", 1),
                    ItemApi.getItem("dustTungsten", 1),
                    IC2Items.getItem("stoneDust"));

            Recipes.centrifuge.addRecipe(
                    new RecipeInputOreDict("crushedPurifiedIridium"),
                    iridiumHeat,
                    ItemApi.getItem("dustSmallPlatinum", 1),
                    ItemApi.getItem("dustIridium", 1));
            Recipes.centrifuge.addRecipe(
                    new RecipeInputOreDict("crushedPurifiedPlatinum"),
                    platinumHeat,
                    ItemApi.getItem("dustSmallIridium", 1),
                    ItemApi.getItem("dustPlatinum", 1));
            Recipes.centrifuge.addRecipe(
                    new RecipeInputOreDict("crushedPurifiedTungsten"),
                    tungstenHeat,
                    ItemApi.getItem("dustSmallManganese", 1),
                    ItemApi.getItem("dustTungsten", 1));
        }
    }

    static void addMetalFormerRecipes() {
        // Metal Former
        NBTTagCompound mode = new NBTTagCompound();
        mode.setInteger("mode", 1);
        Recipes.metalformerRolling
                .addRecipe(new RecipeInputOreDict("ingotAluminium"), mode, ItemApi.getItem("plateAluminium"));
        Recipes.metalformerRolling
                .addRecipe(new RecipeInputOreDict("ingotBatteryAlloy"), mode, ItemApi.getItem("plateBatteryAlloy"));
        Recipes.metalformerRolling.addRecipe(new RecipeInputOreDict("ingotBrass"), mode, ItemApi.getItem("plateBrass"));
        Recipes.metalformerRolling
                .addRecipe(new RecipeInputOreDict("ingotChrome"), mode, ItemApi.getItem("plateChrome"));
        Recipes.metalformerRolling
                .addRecipe(new RecipeInputOreDict("ingotElectrum"), mode, ItemApi.getItem("plateElectrum"));
        Recipes.metalformerRolling.addRecipe(new RecipeInputOreDict("ingotInvar"), mode, ItemApi.getItem("plateInvar"));
        Recipes.metalformerRolling
                .addRecipe(new RecipeInputOreDict("ingotIridium"), mode, ItemApi.getItem("plateIridium"));
        Recipes.metalformerRolling
                .addRecipe(new RecipeInputOreDict("ingotMagnalium"), mode, ItemApi.getItem("plateMagnalium"));
        Recipes.metalformerRolling
                .addRecipe(new RecipeInputOreDict("ingotNickel"), mode, ItemApi.getItem("plateNickel"));
        Recipes.metalformerRolling
                .addRecipe(new RecipeInputOreDict("ingotOsmium"), mode, ItemApi.getItem("plateOsmium"));
        Recipes.metalformerRolling
                .addRecipe(new RecipeInputOreDict("ingotPlatinum"), mode, ItemApi.getItem("platePlatinum"));
        Recipes.metalformerRolling
                .addRecipe(new RecipeInputOreDict("ingotSilver"), mode, ItemApi.getItem("plateSilver"));
        Recipes.metalformerRolling
                .addRecipe(new RecipeInputOreDict("ingotTitanium"), mode, ItemApi.getItem("plateTitanium"));
        Recipes.metalformerRolling
                .addRecipe(new RecipeInputOreDict("ingotTungsten"), mode, ItemApi.getItem("plateTungsten"));
        Recipes.metalformerRolling
                .addRecipe(new RecipeInputOreDict("ingotTungstensteel"), mode, ItemApi.getItem("plateTungstensteel"));
        Recipes.metalformerRolling.addRecipe(new RecipeInputOreDict("ingotZinc"), mode, ItemApi.getItem("plateZinc"));
        Recipes.metalformerRolling
                .addRecipe(new RecipeInputOreDict("ingotRedAlloy"), mode, ItemApi.getItem("plateRedAlloy"));
        Recipes.metalformerRolling
                .addRecipe(new RecipeInputOreDict("ingotBlueAlloy"), mode, ItemApi.getItem("plateBlueAlloy"));
    }
}
