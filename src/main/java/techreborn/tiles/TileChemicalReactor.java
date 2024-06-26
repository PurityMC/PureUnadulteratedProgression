package techreborn.tiles;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.ForgeDirection;

import reborncore.common.util.Inventory;
import techreborn.api.recipe.RecipeCrafter;
import techreborn.init.ModBlocks;
import techreborn.lib.Reference;
import techreborn.powerSystem.TilePowerAcceptor;
import ic2.api.item.ElectricItem;
import ic2.api.item.IElectricItem;
import ic2.api.tile.IWrenchable;

public class TileChemicalReactor extends TilePowerAcceptor implements IWrenchable, IInventory, ISidedInventory {

    public int tickTime;
    public Inventory inventory = new Inventory(8, "TileChemicalReactor", 64);
    public RecipeCrafter crafter;

    public TileChemicalReactor() {
        super(2);
        // Input slots
        int[] inputs = new int[2];
        inputs[0] = 0;
        inputs[1] = 1;
        int[] outputs = new int[1];
        outputs[0] = 2;
        crafter = new RecipeCrafter(Reference.chemicalReactorRecipe, this, 2, 2, inventory, inputs, outputs);
    }

    @Override
    public void updateEntity() {
        super.updateEntity();
        crafter.updateEntity();
        charge(3);
    }

    public void charge(int slot) {
        if (getStackInSlot(slot) != null) {
            if (getStackInSlot(slot).getItem() instanceof IElectricItem) {
                if (getEnergy() != getMaxPower()) {
                    ItemStack stack = inventory.getStackInSlot(slot);
                    double MaxCharge = ((IElectricItem) stack.getItem()).getMaxCharge(stack);
                    double CurrentCharge = ElectricItem.manager.getCharge(stack);
                    if (CurrentCharge != 0) {
                        ElectricItem.manager.discharge(stack, 5, 4, false, false, false);
                        addEnergy(5);
                    }
                }
            }
        }
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
        return new ItemStack(ModBlocks.ChemicalReactor, 1);
    }

    public boolean isComplete() {
        return false;
    }

    @Override
    public void readFromNBT(NBTTagCompound tagCompound) {
        super.readFromNBT(tagCompound);
        inventory.readFromNBT(tagCompound);
        crafter.readFromNBT(tagCompound);
    }

    @Override
    public void writeToNBT(NBTTagCompound tagCompound) {
        super.writeToNBT(tagCompound);
        inventory.writeToNBT(tagCompound);
        crafter.writeToNBT(tagCompound);
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
        return side == ForgeDirection.DOWN.ordinal() ? new int[] { 0, 1, 2 } : new int[] { 0, 1, 2 };
    }

    @Override
    public boolean canInsertItem(int slotIndex, ItemStack itemStack, int side) {
        if (slotIndex == 2) return false;
        return isItemValidForSlot(slotIndex, itemStack);
    }

    @Override
    public boolean canExtractItem(int slotIndex, ItemStack itemStack, int side) {
        return slotIndex == 2;
    }

    // @Override
    // public void addWailaInfo(List<String> info)
    // {
    // super.addWailaInfo(info);
    // info.add("Power Stored " + energy.getEnergyStored() +" EU");
    // if(crafter.currentRecipe !=null){
    // info.add("Power Usage " + crafter.currentRecipe.euPerTick() + " EU/t");
    // }
    // }

    public int getProgressScaled(int scale) {
        if (crafter.currentTickTime != 0) {
            return crafter.currentTickTime * scale / crafter.currentNeededTicks;
        }
        return 0;
    }

    @Override
    public double getMaxPower() {
        return 10000;
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
        return 128;
    }
}
