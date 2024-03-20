package techreborn.compat;

import java.util.ArrayList;

import cpw.mods.fml.common.Loader;
import techreborn.Core;
import techreborn.compat.fmp.ForgeMultipartCompat;
import techreborn.compat.minetweaker.MinetweakerCompat;
import techreborn.compat.recipes.*;
import techreborn.compat.waila.CompatModuleWaila;
import techreborn.config.ConfigTechReborn;

public class CompatManager {

    public ArrayList<ICompatModule> compatModules = new ArrayList<ICompatModule>();

    public static CompatManager INSTANCE = new CompatManager();

    public static boolean isIC2Loaded = false;
    public static boolean isIC2ClassicLoaded = false;
    public static boolean isClassicEnet = false;

    public CompatManager() {
        registerCompact(CompatModuleWaila.class, "Waila");
        registerCompact(RecipesStandalone.class, "!IC2");
        registerCompact(RecipesBiomesOPlenty.class, "BiomesOPlenty");
        registerCompact(RecipesThaumcraft.class, "Thaumcraft");
        registerCompact(RecipesForestry.class, "Forestry", isForestry4());
        registerCompact(MinetweakerCompat.class, "MineTweaker3");
        registerCompact(ForgeMultipartCompat.class, "ForgeMultipart");
    }

    public void registerCompact(Class<?> moduleClass, Object... objs) {
        Core.logHelper.info("Attempting to loading compat module " + moduleClass.getSimpleName());
        boolean shouldLoad = ConfigTechReborn.config
            .get(
                ConfigTechReborn.CATEGORY_INTEGRATION,
                "Compat:" + moduleClass.getSimpleName(),
                true,
                "Should the " + moduleClass.getSimpleName() + " be loaded?")
            .getBoolean(true);
        if (ConfigTechReborn.config.hasChanged()) ConfigTechReborn.config.save();
        if (!shouldLoad) {
            Core.logHelper.info(
                "Compat module " + moduleClass.getSimpleName()
                    + " was not loaded because it has been disabled in the config file.");
            return;
        }
        for (Object obj : objs) {
            if (obj instanceof String) {
                String modid = (String) obj;
                if (modid.startsWith("!")) {
                    if (Loader.isModLoaded(modid.replaceAll("!", ""))) {
                        Core.logHelper.info(
                            "Compat module " + moduleClass.getSimpleName()
                                + " has not been loaded because "
                                + modid.replaceAll("!", "")
                                + " is loaded!");
                        return;
                    }
                } else {
                    if (!Loader.isModLoaded(modid)) {
                        Core.logHelper.info(
                            "Compat module " + moduleClass.getSimpleName()
                                + " has not been loaded because "
                                + modid.replaceAll("!", "")
                                + " is not loaded!");
                        return;
                    }
                }
            } else if (obj instanceof Boolean) {
                Boolean boo = (Boolean) obj;
                if (boo == false) {}
                Core.logHelper.info(
                    "Compat module " + moduleClass.getSimpleName() + " has not been loaded because it was told not to");
                return;
            }
        }
        Core.logHelper.info("Compat module " + moduleClass.getSimpleName() + " has been loaded");
        try {
            compatModules.add((ICompatModule) moduleClass.newInstance());
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public boolean isForestry4() {
        try {
            Class.forName("forestry.api.arboriculture.EnumWoodType");
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

}
