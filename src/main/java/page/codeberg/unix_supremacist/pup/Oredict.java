package page.codeberg.unix_supremacist.pup;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import page.codeberg.unix_supremacist.pup.material.MaterialEnum;
import page.codeberg.unix_supremacist.pup.material.Part;
import page.codeberg.unix_supremacist.pup.material.PartEnum;
import cpw.mods.fml.common.Loader;

public class Oredict {

    public static void load() {
        itemRegister("circuitPrimitive", Items.CIRCUIT_PRIMITIVE.ordinal());
        itemRegister("circuitBasic", Items.CIRCUIT_SIMPLE.ordinal());
        itemRegister("circuitGood", Items.CIRCUIT.ordinal());
        itemRegister("circuitAdvanced", Items.CIRCUIT_ADVANCED.ordinal());
        itemRegister("circuitComplex", Items.CIRCUIT_COMPLEX.ordinal());
        itemRegister("circuitData", Items.CIRCUIT_DATA.ordinal());
        itemRegister("circuitElite", Items.CIRCUIT_ELITE.ordinal());
        itemRegister("circuitMaster", Items.CIRCUIT_MASTER.ordinal());
        itemRegister("circuitUltimate", Items.CIRCUIT_3D.ordinal());
        itemRegister("capacitorPrimitive", Items.CAPACITOR.ordinal());
        if (!Loader.isModLoaded("EnderIO")) {
            itemRegister("capacitorBasic", Items.CAPACITOR.ordinal());
            itemRegister("capacitorAdvanced", Items.CAPACITOR.ordinal());
            itemRegister("capacitorEnder", Items.CAPACITOR.ordinal());
            itemRegister("capacitorCrystalline", Items.CAPACITOR.ordinal());
            itemRegister("capacitorMelodic", Items.CAPACITOR.ordinal());
            itemRegister("capacitorStellar", Items.CAPACITOR.ordinal());
            itemRegister("capacitorTotemic", Items.CAPACITOR.ordinal());
        }
        if (!Loader.isModLoaded("Mekanism")) {
            itemRegister("alloyAdvanced", Items.CIRCUIT_PRIMITIVE.ordinal());
            itemRegister("alloyElite", Items.CIRCUIT_PRIMITIVE.ordinal());
            itemRegister("alloyUltimate", Items.CIRCUIT_PRIMITIVE.ordinal());
        }
        itemRegister("boardBasic", Items.BOARD_BASIC.ordinal());
        itemRegister("boardAdvanced", Items.BOARD_ADVANCED.ordinal());
        itemRegister("boardMaster", Items.BOARD_MASTER.ordinal());
        itemRegister("boardProcessor", Items.BOARD_PROCESSOR.ordinal());
        itemRegister("tubeGlass", Items.GLASS_TUBE.ordinal());
        itemRegister("tubeVacuum", Items.VACUUM_TUBE.ordinal());
        matRegister("itemMachineChassi", PartEnum.hull.getPart(), MaterialEnum.steel.ordinal());
        OreDictionary.registerOre(Recipes.hammerDict, new ItemStack(PUP.testHammer, 1, OreDictionary.WILDCARD_VALUE));
        itemRegister("craftingSuperconductor", Items.SUPERCONDUCTOR.ordinal());
        itemRegister("itemSuperconductor", Items.SUPERCONDUCTOR.ordinal());
    }

    public static void itemRegister(String od, int meta) {
        if (new ItemStack(ItemIterator.metaItem, 1, meta).getUnlocalizedName() != "item.null")
            OreDictionary.registerOre(od, new ItemStack(ItemIterator.metaItem, 1, meta));
    }

    public static void matRegister(String od, Part part, int meta) {
        if (new ItemStack(part, 1, meta).getUnlocalizedName() != "item.null")
            OreDictionary.registerOre(od, new ItemStack(part, 1, meta));
    }
}
