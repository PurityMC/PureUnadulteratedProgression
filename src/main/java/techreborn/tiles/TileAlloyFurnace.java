package techreborn.tiles;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.*;
import net.minecraft.nbt.NBTTagCompound;

import cpw.mods.fml.common.registry.GameRegistry;
import reborncore.common.util.Inventory;
import reborncore.common.util.ItemUtils;
import techreborn.api.recipe.IBaseRecipeType;
import techreborn.api.recipe.RecipeHandler;
import techreborn.init.ModBlocks;
import techreborn.lib.Reference;

public class TileAlloyFurnace extends TileMachineBase implements IInventory {

    public int tickTime;
    public Inventory inventory = new Inventory(4, "TileAlloyFurnace", 64);

    int input1 = 0;
    int input2 = 1;

    int output = 2;

    int fuel = 3;

    public int burnTime;

    public int currentItemBurnTime;
    public int cookTime;

    public TileAlloyFurnace() {

    }

    @Override
    public void updateEntity() {
        super.updateEntity();
        boolean flag = this.burnTime > 0;
        boolean flag1 = false;
        if (this.burnTime > 0) {
            --this.burnTime;
        }
        if (!this.worldObj.isRemote) {
            if (this.burnTime != 0 || getStackInSlot(input1) != null && getStackInSlot(fuel) != null) {
                if (this.burnTime == 0 && this.canSmelt()) {
                    this.currentItemBurnTime = this.burnTime = getItemBurnTime(getStackInSlot(fuel));
                    if (this.burnTime > 0) {
                        flag1 = true;
                        if (this.getStackInSlot(fuel) != null) {
                            decrStackSize(fuel, 1);
                        }
                    }
                }
                if (this.isBurning() && this.canSmelt()) {
                    ++this.cookTime;
                    if (this.cookTime == 200) {
                        this.cookTime = 0;
                        this.smeltItem();
                        flag1 = true;
                    }
                } else {
                    this.cookTime = 0;
                }
            }
            if (flag != this.burnTime > 0) {
                flag1 = true;
                // TODO sync on/off
            }
        }
        if (flag1) {
            this.markDirty();
        }
    }

    public boolean hasAllInputs(IBaseRecipeType recipeType) {
        if (recipeType == null) {
            return false;
        }
        for (ItemStack input : recipeType.getInputs()) {
            Boolean hasItem = false;
            for (int inputslot = 0; inputslot < 2; inputslot++) {
                if (ItemUtils
                    .isItemEqual(input, inventory.getStackInSlot(inputslot), true, true, recipeType.useOreDic())
                    && inventory.getStackInSlot(inputslot).stackSize >= input.stackSize) {
                    hasItem = true;
                }
            }
            if (!hasItem) return false;
        }
        return true;
    }

    private boolean canSmelt() {
        if (this.getStackInSlot(input1) == null || this.getStackInSlot(input2) == null) {
            return false;
        } else {
            ItemStack itemstack = null;
            for (IBaseRecipeType recipeType : RecipeHandler.getRecipeClassFromName(Reference.alloySmelteRecipe)) {
                if (hasAllInputs(recipeType)) {
                    itemstack = recipeType.getOutput(0);
                    break;
                }
            }

            if (itemstack == null) return false;
            if (this.getStackInSlot(output) == null) return true;
            if (!this.getStackInSlot(output)
                .isItemEqual(itemstack)) return false;
            int result = getStackInSlot(output).stackSize + itemstack.stackSize;
            return result <= getInventoryStackLimit() && result <= this.getStackInSlot(output)
                .getMaxStackSize(); // Forge
                                    // BugFix:
                                    // Make
                                    // it
                                    // respect
                                    // stack
                                    // sizes
                                    // properly.
        }
    }

    /**
     * Turn one item from the furnace source stack into the appropriate smelted item in the furnace result stack
     */
    public void smeltItem() {
        ItemStack itemstack = null;
        if (this.canSmelt()) {
            for (IBaseRecipeType recipeType : RecipeHandler.getRecipeClassFromName(Reference.alloySmelteRecipe)) {
                if (hasAllInputs(recipeType)) {
                    itemstack = recipeType.getOutput(0);
                    break;
                }
                if (itemstack != null) {
                    break;
                }
            }
        }

        if (this.getStackInSlot(output) == null) {
            setInventorySlotContents(output, itemstack.copy());
        } else if (this.getStackInSlot(output)
            .getItem() == itemstack.getItem()) {
                decrStackSize(output, -itemstack.stackSize);
            }

        for (IBaseRecipeType recipeType : RecipeHandler.getRecipeClassFromName(Reference.alloySmelteRecipe)) {
            boolean hasAllRecipes = true;
            if (hasAllInputs(recipeType)) {} else {
                hasAllRecipes = false;
            }
            if (hasAllRecipes) {
                for (ItemStack input : recipeType.getInputs()) {
                    for (int inputSlot = 0; inputSlot < 2; inputSlot++) {
                        if (ItemUtils.isItemEqual(
                            input,
                            inventory.getStackInSlot(inputSlot),
                            true,
                            true,
                            recipeType.useOreDic())) {
                            inventory.decrStackSize(inputSlot, input.stackSize);
                            break;
                        }
                    }
                }
            }
        }
    }

    /**
     * Furnace isBurning
     */
    public boolean isBurning() {
        return this.burnTime > 0;
    }

    public int getBurnTimeRemainingScaled(int scale) {
        if (this.currentItemBurnTime == 0) {
            this.currentItemBurnTime = 200;
        }

        return this.burnTime * scale / this.currentItemBurnTime;
    }

    public int getCookProgressScaled(int scale) {
        return this.cookTime * scale / 200;
    }

    /**
     * Returns the number of ticks that the supplied fuel item will keep the furnace burning, or 0 if the item isn't
     * fuel
     */
    public static int getItemBurnTime(ItemStack stack) {
        if (stack == null) {
            return 0;
        } else {
            Item item = stack.getItem();

            if (item instanceof ItemBlock && Block.getBlockFromItem(item) != Blocks.air) {
                Block block = Block.getBlockFromItem(item);

                if (block == Blocks.wooden_slab) {
                    return 150;
                }

                if (block.getMaterial() == Material.wood) {
                    return 300;
                }

                if (block == Blocks.coal_block) {
                    return 16000;
                }
            }

            if (item instanceof ItemTool && ((ItemTool) item).getToolMaterialName()
                .equals("WOOD")) return 200;
            if (item instanceof ItemSword && ((ItemSword) item).getToolMaterialName()
                .equals("WOOD")) return 200;
            if (item instanceof ItemHoe && ((ItemHoe) item).getToolMaterialName()
                .equals("WOOD")) return 200;
            if (item == Items.stick) return 100;
            if (item == Items.coal) return 1600;
            if (item == Items.lava_bucket) return 20000;
            if (item == Item.getItemFromBlock(Blocks.sapling)) return 100;
            if (item == Items.blaze_rod) return 2400;
            return GameRegistry.getFuelValue(stack);
        }
    }

    public boolean isComplete() {
        return false;
    }

    @Override
    public void readFromNBT(NBTTagCompound tagCompound) {
        super.readFromNBT(tagCompound);
        inventory.readFromNBT(tagCompound);

    }

    @Override
    public void writeToNBT(NBTTagCompound tagCompound) {
        super.writeToNBT(tagCompound);
        inventory.writeToNBT(tagCompound);

    }

    @Override
    public int getSizeInventory() {
        return inventory.getSizeInventory();
    }

    @Override
    public ItemStack getStackInSlot(int slot) {
        return inventory.getStackInSlot(slot);
    }

    @Override
    public ItemStack decrStackSize(int slot, int amount) {
        return inventory.decrStackSize(slot, amount);
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int slot) {
        return inventory.getStackInSlotOnClosing(slot);
    }

    @Override
    public void setInventorySlotContents(int slot, ItemStack stack) {
        inventory.setInventorySlotContents(slot, stack);
    }

    @Override
    public String getInventoryName() {
        return inventory.getInventoryName();
    }

    @Override
    public boolean hasCustomInventoryName() {
        return inventory.hasCustomInventoryName();
    }

    @Override
    public int getInventoryStackLimit() {
        return inventory.getInventoryStackLimit();
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer player) {
        return inventory.isUseableByPlayer(player);
    }

    @Override
    public void openInventory() {
        inventory.openInventory();
    }

    @Override
    public void closeInventory() {
        inventory.closeInventory();
    }

    @Override
    public boolean isItemValidForSlot(int slot, ItemStack stack) {
        return inventory.isItemValidForSlot(slot, stack);
    }
}
