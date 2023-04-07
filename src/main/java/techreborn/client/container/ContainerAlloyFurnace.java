package techreborn.client.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;

import reborncore.client.gui.SlotOutput;
import reborncore.common.container.RebornContainer;
import techreborn.tiles.TileAlloyFurnace;

public class ContainerAlloyFurnace extends RebornContainer {

    EntityPlayer player;

    TileAlloyFurnace tile;

    @Override
    public boolean canInteractWith(EntityPlayer player) {
        return true;
    }

    public int tickTime;

    public ContainerAlloyFurnace(TileAlloyFurnace tileAlloyfurnace, EntityPlayer player) {
        tile = tileAlloyfurnace;
        this.player = player;

        // input
        this.addSlotToContainer(new Slot(tileAlloyfurnace.inventory, 0, 47, 17));
        this.addSlotToContainer(new Slot(tileAlloyfurnace.inventory, 1, 65, 17));
        // outputs
        this.addSlotToContainer(new SlotOutput(tileAlloyfurnace.inventory, 2, 116, 35));
        // Fuel
        this.addSlotToContainer(new Slot(tileAlloyfurnace.inventory, 3, 56, 53));

        int i;

        for (i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.addSlotToContainer(new Slot(player.inventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        for (i = 0; i < 9; ++i) {
            this.addSlotToContainer(new Slot(player.inventory, i, 8 + i * 18, 142));
        }
    }

    int currentItemBurnTime;
    int burnTime;
    int cookTime;

    @Override
    public void addCraftingToCrafters(ICrafting crafting) {
        super.addCraftingToCrafters(crafting);
        crafting.sendProgressBarUpdate(this, 0, tile.currentItemBurnTime);
        crafting.sendProgressBarUpdate(this, 1, tile.burnTime);
        crafting.sendProgressBarUpdate(this, 2, tile.cookTime);
    }

    @Override
    public void detectAndSendChanges() {
        for (int i = 0; i < this.crafters.size(); i++) {
            ICrafting crafting = (ICrafting) this.crafters.get(i);
            if (this.currentItemBurnTime != tile.currentItemBurnTime) {
                crafting.sendProgressBarUpdate(this, 0, tile.currentItemBurnTime);
            }
            if (this.burnTime != tile.burnTime) {
                crafting.sendProgressBarUpdate(this, 1, tile.burnTime);
            }
            if (this.cookTime != tile.cookTime) {
                crafting.sendProgressBarUpdate(this, 2, tile.cookTime);
            }
        }
        super.detectAndSendChanges();
    }

    @Override
    public void updateProgressBar(int id, int value) {
        super.updateProgressBar(id, value);
        if (id == 0) {
            this.currentItemBurnTime = value;
        } else if (id == 1) {
            this.burnTime = value;
        } else if (id == 2) {
            this.cookTime = value;
        }
        this.tile.currentItemBurnTime = this.currentItemBurnTime;
        this.tile.burnTime = this.burnTime;
        this.tile.cookTime = this.cookTime;
    }
}
