package techreborn.tiles;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.ForgeDirection;

import reborncore.common.util.Inventory;
import reborncore.common.util.ItemUtils;
import techreborn.init.ModBlocks;
import techreborn.init.ModItems;
import techreborn.powerSystem.TilePowerAcceptor;
import ic2.api.recipe.RecipeOutput;
import ic2.api.recipe.Recipes;
import ic2.api.tile.IWrenchable;

public class TileMatterFabricator extends TilePowerAcceptor implements IWrenchable, IInventory, ISidedInventory {

    public static int fabricationRate = 2666656;
    public int tickTime;
    public Inventory inventory = new Inventory(7, "TileMatterFabricator", 64);
    private int amplifier = 0;
    public int progresstime = 0;

    public TileMatterFabricator() {
        super(6);
        // TODO configs
    }

    @Override
    public boolean wrenchCanSetFacing(EntityPlayer entityPlayer, int side) {
        return false;
    }

    @Override
    public short getFacing() {
        return 0;
    }

    @Override
    public void setFacing(short facing) {}

    @Override
    public boolean wrenchCanRemove(EntityPlayer entityPlayer) {
        if (entityPlayer.isSneaking()) {
            return true;
        }
        return false;
    }

    @Override
    public float getWrenchDropRate() {
        return 1.0F;
    }

    @Override
    public ItemStack getWrenchDrop(EntityPlayer entityPlayer) {
        return new ItemStack(ModBlocks.MatterFabricator, 1);
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

    // ISidedInventory
    @Override
    public int[] getAccessibleSlotsFromSide(int side) {
        return side == ForgeDirection.DOWN.ordinal() ? new int[] { 0, 1, 2, 3, 4, 5, 6 }
                : new int[] { 0, 1, 2, 3, 4, 5, 6 };
    }

    @Override
    public boolean canInsertItem(int slotIndex, ItemStack itemStack, int side) {
        if (slotIndex == 6) return false;
        return isItemValidForSlot(slotIndex, itemStack);
    }

    @Override
    public boolean canExtractItem(int slotIndex, ItemStack itemStack, int side) {
        return slotIndex == 6;
    }

    public int maxProgresstime() {
        return fabricationRate;
    }

    @Override
    public void updateEntity() {
        super.updateEntity();

        if (!super.worldObj.isRemote) {
            for (int i = 0; i < 6; i++) {
                ItemStack stack = inventory.getStackInSlot(i);
                if (this.amplifier < 100000 && stack != null) {
                    int amp = (int) ((long) (getValue(stack) / 32));
                    if (ItemUtils.isItemEqual(stack, inventory.getStackInSlot(i), true, true)) {
                        if (canUseEnergy(1)) {
                            useEnergy(1);
                            this.amplifier += amp;
                            inventory.decrStackSize(i, 1);
                        }
                    }
                }
            }

            if (this.amplifier > 0) {
                if (this.amplifier > this.getEnergy()) {
                    this.progresstime += this.getEnergy();
                    this.amplifier -= this.getEnergy();
                    this.decreaseStoredEnergy(this.getEnergy(), true);
                } else {
                    this.progresstime += this.amplifier;
                    this.decreaseStoredEnergy(this.amplifier, true);
                    this.amplifier = 0;
                }
            }
            if (this.progresstime > this.maxProgresstime() && this.spaceForOutput()) {
                this.progresstime -= this.maxProgresstime();
                this.addOutputProducts();
            }

        }

    }

    private boolean spaceForOutput() {
        return inventory.getStackInSlot(6) == null
                || ItemUtils.isItemEqual(inventory.getStackInSlot(6), new ItemStack(ModItems.uuMatter), true, true)
                        && inventory.getStackInSlot(6).stackSize < 64;
    }

    private void addOutputProducts() {

        if (inventory.getStackInSlot(6) == null) {
            inventory.setInventorySlotContents(6, new ItemStack(ModItems.uuMatter));
        } else if (ItemUtils.isItemEqual(inventory.getStackInSlot(6), new ItemStack(ModItems.uuMatter), true, true)) {
            inventory.getStackInSlot(6).stackSize = Math.min(64, 1 + inventory.getStackInSlot(6).stackSize);
        }
    }

    public boolean decreaseStoredEnergy(double aEnergy, boolean aIgnoreTooLessEnergy) {
        if (this.getEnergy() - aEnergy < 0 && !aIgnoreTooLessEnergy) {
            return false;
        } else {
            setEnergy(this.getEnergy() - aEnergy);
            if (this.getEnergy() < 0) {
                setEnergy(0);
                return false;
            } else {
                return true;
            }
        }
    }

    public int getValue(ItemStack itemStack) {
        int value = getValue(Recipes.matterAmplifier.getOutputFor(itemStack, false));
        return value;
    }

    private static Integer getValue(RecipeOutput output) {
        if (output != null && output.metadata != null) {
            return output.metadata.getInteger("amplification");
        }
        return 0;
    }

    @Override
    public double getMaxPower() {
        return 100000000;
    }

    @Override
    public boolean canAcceptEnergy(ForgeDirection direction) {
        return true;
    }

    @Override
    public boolean canProvideEnergy(ForgeDirection direction) {
        return false;
    }

    @Override
    public double getMaxOutput() {
        return 0;
    }

    @Override
    public double getMaxInput() {
        return 4096;
    }
}
