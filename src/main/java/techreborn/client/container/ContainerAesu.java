package techreborn.client.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;

import reborncore.common.container.RebornContainer;
import techreborn.tiles.TileAesu;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ContainerAesu extends RebornContainer {

    EntityPlayer player;

    TileAesu tile;

    @Override
    public boolean canInteractWith(EntityPlayer player) {
        return true;
    }

    public int euOut;
    public int storedEu;
    public int euChange;

    public ContainerAesu(TileAesu tileaesu, EntityPlayer player) {
        tile = tileaesu;
        this.player = player;

        // input
        // this.addSlotToContainer(new Slot(tileaesu.inventory, 0, 116, 23));
        // this.addSlotToContainer(new Slot(tileaesu.inventory, 1, 116, 59));

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

    @Override
    public void detectAndSendChanges() {
        super.detectAndSendChanges();
        for (int i = 0; i < this.crafters.size(); i++) {
            ICrafting icrafting = (ICrafting) this.crafters.get(i);
            if (this.euOut != tile.getMaxOutput()) {
                icrafting.sendProgressBarUpdate(this, 0, (int) tile.getMaxOutput());
            }
            if (this.storedEu != tile.getEnergy()) {
                icrafting.sendProgressBarUpdate(this, 1, (int) tile.getEnergy());
            }
            if (this.euChange != tile.getEuChange() && tile.getEuChange() != -1) {
                icrafting.sendProgressBarUpdate(this, 2, (int) tile.getEuChange());
            }
        }
    }

    @Override
    public void addCraftingToCrafters(ICrafting crafting) {
        super.addCraftingToCrafters(crafting);
        crafting.sendProgressBarUpdate(this, 0, (int) tile.getMaxOutput());
        crafting.sendProgressBarUpdate(this, 1, (int) tile.getEnergy());
        crafting.sendProgressBarUpdate(this, 2, (int) tile.getEuChange());
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void updateProgressBar(int id, int value) {
        if (id == 0) {
            this.euOut = value;
        } else if (id == 1) {
            this.storedEu = value;
        } else if (id == 2) {
            this.euChange = value;
        }
    }

}
