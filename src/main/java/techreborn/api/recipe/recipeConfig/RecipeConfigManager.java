package techreborn.api.recipe.recipeConfig;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import net.minecraft.item.ItemStack;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import techreborn.api.recipe.IBaseRecipeType;

public class RecipeConfigManager {

    public static ArrayList<RecipeConfig> configs = new ArrayList<RecipeConfig>();

    static File configFile = null;

    public static void load(File configDir) {
        if (configFile == null) {
            configFile = new File(configDir, "techRebornRecipes.json");
        }
    }

    public static void save() {
        if (configFile.exists()) {
            configFile.delete();
        }
        Gson gson = new GsonBuilder().setPrettyPrinting()
            .create();
        String json = gson.toJson(configs);
        try {
            FileWriter writer = new FileWriter(configFile);
            writer.write(json);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean canLoadRecipe(IBaseRecipeType recipeType) {
        RecipeConfig config = new RecipeConfig();
        for (ItemStack stack : recipeType.getInputs()) {
            config.addInputs(itemToConfig(stack));
        }
        for (ItemStack stack : recipeType.getOutputs()) {
            config.addOutputs(itemToConfig(stack));
        }
        config.enabled = true;
        config.setMachine(recipeType.getRecipeName());
        configs.add(config);
        return config.enabled;
    }

    public static ConfigItem itemToConfig(ItemStack stack) {
        ConfigItem newItem = new ConfigItem();
        newItem.setItemName(
            stack.getItem()
                .getUnlocalizedName());
        newItem.setMeta(stack.getItemDamage());
        newItem.setStackSize(stack.stackSize);
        newItem.setLocalName(stack.getDisplayName());
        return newItem;
    }

}
