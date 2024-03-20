package techreborn.client.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

import page.codeberg.unix_supremacist.pup.api.ItemApi;
import reborncore.client.gui.SlotFilteredVoid;
import reborncore.common.container.RebornContainer;
import reborncore.common.util.Inventory;

public class ContainerDestructoPack extends RebornContainer {

    private EntityPlayer player;
    private Inventory inv;

    public ContainerDestructoPack(EntityPlayer player) {
        this.player = player;
        inv = new Inventory(1, "destructopack", 64);
        buildContainer();
    }

    @Override
    public boolean canInteractWith(EntityPlayer arg0) {
        return true;
    }

    private void buildContainer() {
        this.addSlotToContainer(
            new SlotFilteredVoid(inv, 0, 80, 36, new ItemStack[] { ItemApi.getItem("thickNeutronReflector") }));
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
}
