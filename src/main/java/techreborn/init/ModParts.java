package techreborn.init;

import java.util.HashMap;

import net.minecraft.item.ItemStack;

import cpw.mods.fml.common.Loader;
import techreborn.partSystem.IPartProvider;
import techreborn.partSystem.ModPartRegistry;
import techreborn.partSystem.parts.CablePart;

public class ModParts {

    public static HashMap<Integer, ItemStack> stackCable = new HashMap<Integer, ItemStack>();

    public static void init() {
        if (Loader.isModLoaded("IC2")) {
            for (int i = 0; i < 11; i++) {
                CablePart part = new CablePart();
                part.setType(i);
                ModPartRegistry.registerPart(part);
            }
        }
        ModPartRegistry.addProvider("techreborn.partSystem.fmp.FMPFactory", "ForgeMultipart");
        ModPartRegistry.addProvider("techreborn.partSystem.QLib.QModPartFactory", "qmunitylib");
        ModPartRegistry.addAllPartsToSystems();
        for (IPartProvider provider : ModPartRegistry.providers) {
            if (provider.modID()
                .equals("ForgeMultipart")) {
                ModPartRegistry.masterProvider = provider;
            }
        }
    }
}
