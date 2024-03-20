package techreborn.compat.recipes;

import java.util.Iterator;
import java.util.Map;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.oredict.OreDictionary;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import forestry.api.arboriculture.EnumWoodType;
import forestry.api.arboriculture.TreeManager;
import forestry.api.fuels.FuelManager;
import forestry.api.fuels.GeneratorFuel;
import reborncore.api.fuel.FluidPowerManager;
import techreborn.api.recipe.RecipeHandler;
import techreborn.api.recipe.machines.IndustrialSawmillRecipe;
import techreborn.compat.ICompatModule;
import techreborn.config.ConfigTechReborn;

public class RecipesForestry implements ICompatModule {

    @Override
    public void preInit(FMLPreInitializationEvent event) {}

    @Override
    public void init(FMLInitializationEvent event) {

    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {
        if (ConfigTechReborn.AllowForestryRecipes) {
            ItemStack pulpStack = OreDictionary.getOres("pulpWood")
                .get(0);
            for (EnumWoodType woodType : EnumWoodType.VALUES) {
                ItemStack log = TreeManager.woodItemAccess.getLog(woodType, true);
                log.stackSize = 1;
                ItemStack plank = TreeManager.woodItemAccess.getPlanks(woodType, true);
                plank.stackSize = 6;
                RecipeHandler.addRecipe(
                    new IndustrialSawmillRecipe(
                        log,
                        null,
                        new FluidStack(FluidRegistry.WATER, 1000),
                        plank,
                        pulpStack,
                        null,
                        200,
                        30,
                        false));
                // RecipeHandler.addRecipe(
                //     new IndustrialSawmillRecipe(
                //         log,
                //         IC2Items.getItem("waterCell"),
                //         null,
                //         plank,
                //         pulpStack,
                //         IC2Items.getItem("cell"),
                //         200,
                //         30,
                //         false));
                RecipeHandler.addRecipe(
                    new IndustrialSawmillRecipe(
                        log,
                        new ItemStack(Items.water_bucket),
                        null,
                        plank,
                        pulpStack,
                        new ItemStack(Items.bucket),
                        200,
                        30,
                        false));

                log = TreeManager.woodItemAccess.getLog(woodType, false);
                log.stackSize = 1;
                plank = TreeManager.woodItemAccess.getPlanks(woodType, false);
                plank.stackSize = 6;
                RecipeHandler.addRecipe(
                    new IndustrialSawmillRecipe(
                        log,
                        null,
                        new FluidStack(FluidRegistry.WATER, 1000),
                        plank,
                        pulpStack,
                        null,
                        200,
                        30,
                        false));
                // RecipeHandler.addRecipe(
                //     new IndustrialSawmillRecipe(
                //         log,
                //         IC2Items.getItem("waterCell"),
                //         null,
                //         plank,
                //         pulpStack,
                //         IC2Items.getItem("cell"),
                //         200,
                //         30,
                //         false));
                RecipeHandler.addRecipe(
                    new IndustrialSawmillRecipe(
                        log,
                        new ItemStack(Items.water_bucket),
                        null,
                        plank,
                        pulpStack,
                        new ItemStack(Items.bucket),
                        200,
                        30,
                        false));
            }
        }

        Iterator entries = FuelManager.generatorFuel.entrySet()
            .iterator();
        while (entries.hasNext()) {
            Map.Entry thisEntry = (Map.Entry) entries.next();
            Fluid fluid = (Fluid) thisEntry.getKey();
            GeneratorFuel generatorFuel = (GeneratorFuel) thisEntry.getValue();
            FluidPowerManager.fluidPowerValues.put(fluid, (double) (generatorFuel.eu / generatorFuel.rate));
        }
    }

    @Override
    public void serverStarting(FMLServerStartingEvent event) {

    }
}
