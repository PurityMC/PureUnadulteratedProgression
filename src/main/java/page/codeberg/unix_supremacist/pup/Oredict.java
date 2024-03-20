package page.codeberg.unix_supremacist.pup;

import ganymedes01.etfuturum.ModItems;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import cpw.mods.fml.common.Loader;
import page.codeberg.unix_supremacist.pup.api.ItemApi;
import page.codeberg.unix_supremacist.pup.recipes.Util;

public class Oredict {

    public static void load() {
        register("circuitSimple", "circuitBasic");
        register("circuit", "circuitGood");
        register("circuit3D", "circuitUltimate");
        register("capacitor", "capacitorPrimitive");
        if (!Loader.isModLoaded("EnderIO")) {
            register("capacitor", "capacitorBasic");
            register("capacitor", "capacitorAdvanced");
            register("capacitor", "capacitorEnder");
            register("capacitor", "capacitorCrystalline");
            register("capacitor", "capacitorMelodic");
            register("capacitor", "capacitorStellar");
            register("capacitor", "capacitorTotemic");
        }
        if (!Loader.isModLoaded("Mekanism")) {
            register("circuitPrimitive", "alloyAdvanced");
            register("circuitPrimitive", "alloyElite");
            register("circuitPrimitive", "alloyUltimate");
        }
        register("hullSteel", "itemMachineChassi");
        register("dustNikolite", "dustTeslatite");
        OreDictionary.registerOre(Util.hammerDict, new ItemStack(PUP.testHammer, 1, OreDictionary.WILDCARD_VALUE));
        register("craftingSuperconductor", "itemSuperconductor");
        register("dustBlaze", net.minecraft.init.Items.blaze_powder, OreDictionary.WILDCARD_VALUE);
        register("dustRedstone", net.minecraft.init.Items.redstone, OreDictionary.WILDCARD_VALUE);
        register("dustGlowstone", net.minecraft.init.Items.glowstone_dust, OreDictionary.WILDCARD_VALUE);
        register("gemAmethyst", ModItems.AMETHYST_SHARD.get(), OreDictionary.WILDCARD_VALUE);
        register("ingotIron", net.minecraft.init.Items.iron_ingot, OreDictionary.WILDCARD_VALUE);
        register("ingotGold", net.minecraft.init.Items.gold_ingot, OreDictionary.WILDCARD_VALUE);
        register("ingotCopper", ModItems.COPPER_INGOT.get(), OreDictionary.WILDCARD_VALUE);
        register("nuggetGold", net.minecraft.init.Items.gold_nugget, OreDictionary.WILDCARD_VALUE);
        register("nuggetIron", ModItems.NUGGET_IRON.get(), OreDictionary.WILDCARD_VALUE);

        register("blockObsidian", Blocks.obsidian, OreDictionary.WILDCARD_VALUE);
        register("blockGold", Blocks.gold_block, OreDictionary.WILDCARD_VALUE);
        register("blockIron", Blocks.iron_block, OreDictionary.WILDCARD_VALUE);
        register("blockDiamond", Blocks.diamond_block, OreDictionary.WILDCARD_VALUE);
        register("blockRedstone", Blocks.redstone_block, OreDictionary.WILDCARD_VALUE);
        register("blockGlowstone", Blocks.glowstone, OreDictionary.WILDCARD_VALUE);
        register("blockEmerald", Blocks.emerald_block, OreDictionary.WILDCARD_VALUE);
    }

    public static void register(String od) {
        OreDictionary.registerOre(od, ItemApi.getItem(od));
    }

    public static void register(String name, String od) {
        OreDictionary.registerOre(od, ItemApi.getItem(name));
    }

    public static void register(String name, Item item, int meta) {
        ItemApi.setItem(name, new ItemStack(item, 1, meta));
        OreDictionary.registerOre(name, ItemApi.getItem(name));
    }

    public static void register(String name, ItemStack item) {
        ItemApi.setItem(name, item);
        OreDictionary.registerOre(name, ItemApi.getItem(name));
    }

    public static void register(String name, Block item, int meta) {
        ItemApi.setItem(name, new ItemStack(item, 1, meta));
        OreDictionary.registerOre(name, ItemApi.getItem(name));
    }
}
