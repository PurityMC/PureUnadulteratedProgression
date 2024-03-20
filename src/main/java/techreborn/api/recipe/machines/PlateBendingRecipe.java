package techreborn.api.recipe.machines;

import net.minecraft.item.ItemStack;

import techreborn.api.recipe.BaseRecipe;
import techreborn.lib.Reference;

public class PlateBendingRecipe extends BaseRecipe {

    public PlateBendingRecipe(ItemStack input1, ItemStack input2, ItemStack output1, ItemStack output2,
        ItemStack output3, ItemStack output4, int tickTime, int euPerTick) {
        super(Reference.plateBenderRecipe, tickTime, euPerTick);
        if (input1 != null) inputs.add(input1);
        if (input2 != null) inputs.add(input2);
        if (output1 != null) addOutput(output1);
        if (output2 != null) addOutput(output2);
        if (output3 != null) addOutput(output3);
        if (output4 != null) addOutput(output4);
    }

    @Override
    public String getUserFreindlyName() {
        return "Centrifuge";
    }
}
