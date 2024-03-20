package reborncore.common.util;

import java.util.ArrayList;

import net.minecraftforge.oredict.OreDictionary;

import reborncore.RebornCore;

public class OreUtil {

    public static ArrayList<String> oreNames = new ArrayList<String>();

    public static boolean doesOreExistAndValid(String name) {
        if (!OreDictionary.doesOreNameExist(name)) {
            return false;
        }
        return OreDictionary.getOres(name)
            .size() >= 1;
    }

    public static void scanForOres() {
        String[] validPrefixes = new String[] { "ingot", "ore", "crushed", "plate", "nugget", "dustSmall", "dust",
            "block" };
        for (String oreDicName : OreDictionary.getOreNames()) {
            for (String prefix : validPrefixes) {
                if (oreDicName.startsWith(prefix)) {
                    if (!oreNames.contains(
                        oreDicName.replace(prefix, "")
                            .toLowerCase())) {
                        oreNames.add(
                            oreDicName.replace(prefix, "")
                                .toLowerCase());
                    }
                }
            }
        }
        RebornCore.logHelper.info("Found " + oreNames.size() + " ores");
    }

    public static boolean hasIngot(String name) {
        return doesOreExistAndValid("ingot" + capitalizeFirstLetter(name));
    }

    public static boolean hasOre(String name) {
        return doesOreExistAndValid("ore" + capitalizeFirstLetter(name));
    }

    public static boolean hasCrushedOre(String name) {
        return doesOreExistAndValid("crushed" + capitalizeFirstLetter(name));
    }

    public static boolean hasPlate(String name) {
        return doesOreExistAndValid("plate" + capitalizeFirstLetter(name));
    }

    public static boolean hasNugget(String name) {
        return doesOreExistAndValid("nugget" + capitalizeFirstLetter(name));
    }

    public static boolean hasDustSmall(String name) {
        return doesOreExistAndValid("dustSmall" + capitalizeFirstLetter(name));
    }

    public static boolean hasDust(String name) {
        return doesOreExistAndValid("dust" + capitalizeFirstLetter(name));
    }

    public static boolean hasBlock(String name) {
        return doesOreExistAndValid("block" + capitalizeFirstLetter(name));
    }

    public static String capitalizeFirstLetter(String original) {
        if (original.length() == 0) return original;
        return original.substring(0, 1)
            .toUpperCase() + original.substring(1);
    }

}
