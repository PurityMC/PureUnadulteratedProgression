package page.codeberg.unix_supremacist.pup.Interfaces;

import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;

public interface ITSidedInventory extends ISidedInventory {

    @Override
    default public boolean canInsertItem(int slot, ItemStack item, int side) {
        return this.isItemValidForSlot(slot, item);
    }

    @Override
    default public boolean canExtractItem(int slot, ItemStack item, int side) {
        return true;
    }
}
