package techreborn.init;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.GameRegistry;
import page.codeberg.unix_supremacist.pup.item.*;
import page.codeberg.unix_supremacist.pup.item.ItemLapotronPack;
import page.codeberg.unix_supremacist.pup.item.ItemLithiumBatpack;
import reborncore.common.util.BucketHandler;
import techreborn.Core;
import techreborn.items.*;
import techreborn.items.tools.*;
import techreborn.powerSystem.PoweredItem;

public class ModItems {

    // This are deprected to stop people using them in the recipes.
    @Deprecated
    public static Item dusts;
    @Deprecated
    public static Item cells;
    public static Item rockCutter;
    public static Item lithiumBatpack;
    public static Item lapotronpack;
    public static Item lithiumBattery;
    public static Item omniTool;
    public static Item advancedDrill;
    public static Item lapotronicOrb;
    public static Item manuel;
    public static Item uuMatter;
    public static Item plate;
    public static Item cloakingDevice;

    public static Item bucketBerylium;
    public static Item bucketcalcium;
    public static Item bucketcalciumcarbonate;
    public static Item bucketChlorite;
    public static Item bucketDeuterium;
    public static Item bucketGlyceryl;
    public static Item bucketHelium;
    public static Item bucketHelium3;
    public static Item bucketHeliumplasma;
    public static Item bucketHydrogen;
    public static Item bucketLithium;
    public static Item bucketMercury;
    public static Item bucketMethane;
    public static Item bucketNitrocoalfuel;
    public static Item bucketNitrofuel;
    public static Item bucketNitrogen;
    public static Item bucketNitrogendioxide;
    public static Item bucketPotassium;
    public static Item bucketSilicon;
    public static Item bucketSodium;
    public static Item bucketSodiumpersulfate;
    public static Item bucketTritium;
    public static Item bucketWolframium;

    public static Item upgrades;

    public static void init() throws InstantiationException, IllegalAccessException {
        cells = new ItemCells();
        GameRegistry.registerItem(cells, "cell");
        for (int i = 0; i < ItemCells.types.length; i++) {
            if (FluidRegistry.getFluid("fluid" + ItemCells.types[i].toLowerCase()) != null) {
                FluidContainerRegistry.registerFluidContainer(
                    FluidRegistry.getFluid("fluid" + ItemCells.types[i].toLowerCase()),
                    ItemCells.getCellByName(ItemCells.types[i]));
            }
        }

        rockCutter = PoweredItem.createItem(ItemRockCutter.class);
        GameRegistry.registerItem(rockCutter, "rockCutter");
        lithiumBatpack = PoweredItem.createItem(ItemLithiumBatpack.class);
        GameRegistry.registerItem(lithiumBatpack, "lithiumBatpack");
        lapotronpack = PoweredItem.createItem(ItemLapotronPack.class);
        GameRegistry.registerItem(lapotronpack, "lapotronPack");
        lithiumBattery = PoweredItem.createItem(ItemLithiumBattery.class);
        GameRegistry.registerItem(lithiumBattery, "lithiumBattery");
        lapotronicOrb = PoweredItem.createItem(ItemLapotronicOrb.class);
        GameRegistry.registerItem(lapotronicOrb, "lapotronicOrb");
        omniTool = PoweredItem.createItem(ItemOmniTool.class);
        GameRegistry.registerItem(omniTool, "omniTool");
        advancedDrill = PoweredItem.createItem(ItemAdvancedDrill.class);
        GameRegistry.registerItem(advancedDrill, "advancedDrill");
        manuel = new ItemTechPda();
        GameRegistry.registerItem(manuel, "techmanuel");
        uuMatter = new ItemUUmatter();
        GameRegistry.registerItem(uuMatter, "uumatter");

        upgrades = new ItemUpgrade();
        GameRegistry.registerItem(upgrades, "upgrades");

        cloakingDevice = PoweredItem.createItem(ItemCloakingDevice.class);
        GameRegistry.registerItem(cloakingDevice, "cloakingdevice");

        // buckets
        bucketBerylium = new ItemFluidbucket(ModFluids.BlockFluidBerylium);
        bucketBerylium.setUnlocalizedName("bucketberylium")
            .setContainerItem(Items.bucket);
        GameRegistry.registerItem(bucketBerylium, "bucketberylium");
        FluidContainerRegistry.registerFluidContainer(
            FluidRegistry.getFluidStack("fluidberylium", FluidContainerRegistry.BUCKET_VOLUME),
            new ItemStack(bucketBerylium),
            new ItemStack(Items.bucket));
        BucketHandler.INSTANCE.buckets.put(ModFluids.BlockFluidBerylium, bucketBerylium);

        bucketcalcium = new ItemFluidbucket(ModFluids.BlockFluidCalcium);
        bucketcalcium.setUnlocalizedName("bucketcalcium")
            .setContainerItem(Items.bucket);
        GameRegistry.registerItem(bucketcalcium, "bucketcalcium");
        FluidContainerRegistry.registerFluidContainer(
            FluidRegistry.getFluidStack("fluidcalcium", FluidContainerRegistry.BUCKET_VOLUME),
            new ItemStack(bucketcalcium),
            new ItemStack(Items.bucket));
        BucketHandler.INSTANCE.buckets.put(ModFluids.BlockFluidCalcium, bucketcalcium);

        bucketcalciumcarbonate = new ItemFluidbucket(ModFluids.BlockFluidCalciumCarbonate);
        bucketcalciumcarbonate.setUnlocalizedName("bucketcalciumcarbonate")
            .setContainerItem(Items.bucket);
        GameRegistry.registerItem(bucketcalciumcarbonate, "bucketcalciumcarbonate");
        FluidContainerRegistry.registerFluidContainer(
            FluidRegistry.getFluidStack("fluidcalciumcarbonate", FluidContainerRegistry.BUCKET_VOLUME),
            new ItemStack(bucketcalciumcarbonate),
            new ItemStack(Items.bucket));
        BucketHandler.INSTANCE.buckets.put(ModFluids.BlockFluidCalciumCarbonate, bucketcalciumcarbonate);

        bucketChlorite = new ItemFluidbucket(ModFluids.BlockFluidChlorite);
        bucketChlorite.setUnlocalizedName("bucketchlorite")
            .setContainerItem(Items.bucket);
        GameRegistry.registerItem(bucketChlorite, "bucketcalchlorite");
        FluidContainerRegistry.registerFluidContainer(
            FluidRegistry.getFluidStack("fluidchlorite", FluidContainerRegistry.BUCKET_VOLUME),
            new ItemStack(bucketcalciumcarbonate),
            new ItemStack(Items.bucket));
        BucketHandler.INSTANCE.buckets.put(ModFluids.BlockFluidChlorite, bucketChlorite);

        bucketDeuterium = new ItemFluidbucket(ModFluids.BlockFluidDeuterium);
        bucketDeuterium.setUnlocalizedName("bucketdeuterium")
            .setContainerItem(Items.bucket);
        GameRegistry.registerItem(bucketDeuterium, "bucketdeuterium");
        FluidContainerRegistry.registerFluidContainer(
            FluidRegistry.getFluidStack("fluiddeuterium", FluidContainerRegistry.BUCKET_VOLUME),
            new ItemStack(bucketDeuterium),
            new ItemStack(Items.bucket));
        BucketHandler.INSTANCE.buckets.put(ModFluids.BlockFluidDeuterium, bucketDeuterium);

        bucketGlyceryl = new ItemFluidbucket(ModFluids.BlockFluidGlyceryl);
        bucketGlyceryl.setUnlocalizedName("bucketglyceryl")
            .setContainerItem(Items.bucket);
        GameRegistry.registerItem(bucketGlyceryl, "bucketglyceryl");
        FluidContainerRegistry.registerFluidContainer(
            FluidRegistry.getFluidStack("fluidglyceryl", FluidContainerRegistry.BUCKET_VOLUME),
            new ItemStack(bucketGlyceryl),
            new ItemStack(Items.bucket));
        BucketHandler.INSTANCE.buckets.put(ModFluids.BlockFluidGlyceryl, bucketGlyceryl);

        bucketHelium = new ItemFluidbucket(ModFluids.BlockFluidHelium);
        bucketHelium.setUnlocalizedName("buckethelium")
            .setContainerItem(Items.bucket);
        GameRegistry.registerItem(bucketHelium, "buckethelium");
        FluidContainerRegistry.registerFluidContainer(
            FluidRegistry.getFluidStack("fluidhelium", FluidContainerRegistry.BUCKET_VOLUME),
            new ItemStack(bucketHelium),
            new ItemStack(Items.bucket));
        BucketHandler.INSTANCE.buckets.put(ModFluids.BlockFluidHelium, bucketHelium);

        bucketHelium3 = new ItemFluidbucket(ModFluids.BlockFluidHelium3);
        bucketHelium3.setUnlocalizedName("buckethelium3")
            .setContainerItem(Items.bucket);
        GameRegistry.registerItem(bucketHelium3, "buckethelium3");
        FluidContainerRegistry.registerFluidContainer(
            FluidRegistry.getFluidStack("fluidhelium3", FluidContainerRegistry.BUCKET_VOLUME),
            new ItemStack(bucketHelium3),
            new ItemStack(Items.bucket));
        BucketHandler.INSTANCE.buckets.put(ModFluids.BlockFluidHelium3, bucketHelium3);

        bucketHeliumplasma = new ItemFluidbucket(ModFluids.BlockFluidHeliumplasma);
        bucketHeliumplasma.setUnlocalizedName("bucketheliumplasma")
            .setContainerItem(Items.bucket);
        GameRegistry.registerItem(bucketHeliumplasma, "bucketheliumplasma");
        FluidContainerRegistry.registerFluidContainer(
            FluidRegistry.getFluidStack("fluidheliumplasma", FluidContainerRegistry.BUCKET_VOLUME),
            new ItemStack(bucketHeliumplasma),
            new ItemStack(Items.bucket));
        BucketHandler.INSTANCE.buckets.put(ModFluids.BlockFluidHeliumplasma, bucketHeliumplasma);

        bucketHydrogen = new ItemFluidbucket(ModFluids.BlockFluidHydrogen);
        bucketHydrogen.setUnlocalizedName("buckethydrogen")
            .setContainerItem(Items.bucket);
        GameRegistry.registerItem(bucketHydrogen, "buckethydrogen");
        FluidContainerRegistry.registerFluidContainer(
            FluidRegistry.getFluidStack("fluidhydrogen", FluidContainerRegistry.BUCKET_VOLUME),
            new ItemStack(bucketHydrogen),
            new ItemStack(Items.bucket));
        BucketHandler.INSTANCE.buckets.put(ModFluids.BlockFluidHydrogen, bucketHydrogen);

        bucketLithium = new ItemFluidbucket(ModFluids.BlockFluidLithium);
        bucketLithium.setUnlocalizedName("bucketlithium")
            .setContainerItem(Items.bucket);
        GameRegistry.registerItem(bucketLithium, "bucketlithium");
        FluidContainerRegistry.registerFluidContainer(
            FluidRegistry.getFluidStack("fluidlithium", FluidContainerRegistry.BUCKET_VOLUME),
            new ItemStack(bucketLithium),
            new ItemStack(Items.bucket));
        BucketHandler.INSTANCE.buckets.put(ModFluids.BlockFluidLithium, bucketLithium);

        bucketMercury = new ItemFluidbucket(ModFluids.BlockFluidMercury);
        bucketMercury.setUnlocalizedName("bucketmercury")
            .setContainerItem(Items.bucket);
        GameRegistry.registerItem(bucketMercury, "bucketmercury");
        FluidContainerRegistry.registerFluidContainer(
            FluidRegistry.getFluidStack("fluidmercury", FluidContainerRegistry.BUCKET_VOLUME),
            new ItemStack(bucketMercury),
            new ItemStack(Items.bucket));
        BucketHandler.INSTANCE.buckets.put(ModFluids.BlockFluidMercury, bucketMercury);

        bucketMethane = new ItemFluidbucket(ModFluids.BlockFluidMethane);
        bucketMethane.setUnlocalizedName("bucketmethane")
            .setContainerItem(Items.bucket);
        GameRegistry.registerItem(bucketMethane, "bucketmethane");
        FluidContainerRegistry.registerFluidContainer(
            FluidRegistry.getFluidStack("fluidmethane", FluidContainerRegistry.BUCKET_VOLUME),
            new ItemStack(bucketMethane),
            new ItemStack(Items.bucket));
        BucketHandler.INSTANCE.buckets.put(ModFluids.BlockFluidMethane, bucketMethane);

        bucketNitrocoalfuel = new ItemFluidbucket(ModFluids.BlockFluidNitrocoalfuel);
        bucketNitrocoalfuel.setUnlocalizedName("bucketnitrocoalfuel")
            .setContainerItem(Items.bucket);
        GameRegistry.registerItem(bucketNitrocoalfuel, "bucketnitrocoalfuel");
        FluidContainerRegistry.registerFluidContainer(
            FluidRegistry.getFluidStack("fluidnitrocoalfuel", FluidContainerRegistry.BUCKET_VOLUME),
            new ItemStack(bucketNitrocoalfuel),
            new ItemStack(Items.bucket));
        BucketHandler.INSTANCE.buckets.put(ModFluids.BlockFluidNitrocoalfuel, bucketNitrocoalfuel);

        bucketNitrofuel = new ItemFluidbucket(ModFluids.BlockFluidNitrofuel);
        bucketNitrofuel.setUnlocalizedName("bucketnitrofuel")
            .setContainerItem(Items.bucket);
        GameRegistry.registerItem(bucketNitrofuel, "bucketnitrofuel");
        FluidContainerRegistry.registerFluidContainer(
            FluidRegistry.getFluidStack("fluidnitrofuel", FluidContainerRegistry.BUCKET_VOLUME),
            new ItemStack(bucketNitrofuel),
            new ItemStack(Items.bucket));
        BucketHandler.INSTANCE.buckets.put(ModFluids.BlockFluidNitrofuel, bucketNitrofuel);

        bucketNitrogen = new ItemFluidbucket(ModFluids.BlockFluidNitrogen);
        bucketNitrogen.setUnlocalizedName("bucketnitrogen")
            .setContainerItem(Items.bucket);
        GameRegistry.registerItem(bucketNitrogen, "bucketnitrogen");
        FluidContainerRegistry.registerFluidContainer(
            FluidRegistry.getFluidStack("fluidnitrogen", FluidContainerRegistry.BUCKET_VOLUME),
            new ItemStack(bucketNitrogen),
            new ItemStack(Items.bucket));
        BucketHandler.INSTANCE.buckets.put(ModFluids.BlockFluidNitrogen, bucketNitrogen);

        bucketNitrogendioxide = new ItemFluidbucket(ModFluids.BlockFluidNitrogendioxide);
        bucketNitrogendioxide.setUnlocalizedName("bucketnitrogendioxide")
            .setContainerItem(Items.bucket);
        GameRegistry.registerItem(bucketNitrogendioxide, "bucketnitrogendioxide");
        FluidContainerRegistry.registerFluidContainer(
            FluidRegistry.getFluidStack("fluidnitrogendioxide", FluidContainerRegistry.BUCKET_VOLUME),
            new ItemStack(bucketNitrogendioxide),
            new ItemStack(Items.bucket));
        BucketHandler.INSTANCE.buckets.put(ModFluids.BlockFluidNitrogendioxide, bucketNitrogendioxide);

        bucketPotassium = new ItemFluidbucket(ModFluids.BlockFluidPotassium);
        bucketPotassium.setUnlocalizedName("bucketpotassium")
            .setContainerItem(Items.bucket);
        GameRegistry.registerItem(bucketPotassium, "bucketpotassium");
        FluidContainerRegistry.registerFluidContainer(
            FluidRegistry.getFluidStack("fluidpotassium", FluidContainerRegistry.BUCKET_VOLUME),
            new ItemStack(bucketPotassium),
            new ItemStack(Items.bucket));
        BucketHandler.INSTANCE.buckets.put(ModFluids.BlockFluidPotassium, bucketPotassium);

        bucketSilicon = new ItemFluidbucket(ModFluids.BlockFluidSilicon);
        bucketSilicon.setUnlocalizedName("bucketsilicon")
            .setContainerItem(Items.bucket);
        GameRegistry.registerItem(bucketSilicon, "bucketsilicon");
        FluidContainerRegistry.registerFluidContainer(
            FluidRegistry.getFluidStack("fluidsilicon", FluidContainerRegistry.BUCKET_VOLUME),
            new ItemStack(bucketSilicon),
            new ItemStack(Items.bucket));
        BucketHandler.INSTANCE.buckets.put(ModFluids.BlockFluidSilicon, bucketSilicon);

        bucketSodium = new ItemFluidbucket(ModFluids.BlockFluidSodium);
        bucketSodium.setUnlocalizedName("bucketsodium")
            .setContainerItem(Items.bucket);
        GameRegistry.registerItem(bucketSodium, "bucketsodium");
        FluidContainerRegistry.registerFluidContainer(
            FluidRegistry.getFluidStack("fluidsodium", FluidContainerRegistry.BUCKET_VOLUME),
            new ItemStack(bucketSodium),
            new ItemStack(Items.bucket));
        BucketHandler.INSTANCE.buckets.put(ModFluids.BlockFluidSodium, bucketSodium);

        bucketSodiumpersulfate = new ItemFluidbucket(ModFluids.BlockFluidSodiumpersulfate);
        bucketSodiumpersulfate.setUnlocalizedName("bucketsodiumpersulfate")
            .setContainerItem(Items.bucket);
        GameRegistry.registerItem(bucketSodiumpersulfate, "bucketsodiumpersulfate");
        FluidContainerRegistry.registerFluidContainer(
            FluidRegistry.getFluidStack("fluidsodiumpersulfate", FluidContainerRegistry.BUCKET_VOLUME),
            new ItemStack(bucketSodiumpersulfate),
            new ItemStack(Items.bucket));
        BucketHandler.INSTANCE.buckets.put(ModFluids.BlockFluidSodiumpersulfate, bucketSodiumpersulfate);

        bucketTritium = new ItemFluidbucket(ModFluids.BlockFluidTritium);
        bucketTritium.setUnlocalizedName("buckettritium")
            .setContainerItem(Items.bucket);
        GameRegistry.registerItem(bucketTritium, "buckettritium");
        FluidContainerRegistry.registerFluidContainer(
            FluidRegistry.getFluidStack("fluidtritium", FluidContainerRegistry.BUCKET_VOLUME),
            new ItemStack(bucketTritium),
            new ItemStack(Items.bucket));
        BucketHandler.INSTANCE.buckets.put(ModFluids.BlockFluidTritium, bucketTritium);

        bucketWolframium = new ItemFluidbucket(ModFluids.BlockFluidWolframium);
        bucketWolframium.setUnlocalizedName("bucketwolframium")
            .setContainerItem(Items.bucket);
        GameRegistry.registerItem(bucketWolframium, "bucketwolframium");
        FluidContainerRegistry.registerFluidContainer(
            FluidRegistry.getFluidStack("fluidwolframium", FluidContainerRegistry.BUCKET_VOLUME),
            new ItemStack(bucketWolframium),
            new ItemStack(Items.bucket));
        BucketHandler.INSTANCE.buckets.put(ModFluids.BlockFluidWolframium, bucketWolframium);

        MinecraftForge.EVENT_BUS.register(BucketHandler.INSTANCE);

        Core.logHelper.info("TechReborns Items Loaded");
    }
}
