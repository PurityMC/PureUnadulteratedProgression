package techreborn.client.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import reborncore.client.gui.SlotFake;
import reborncore.client.gui.SlotOutput;
import reborncore.common.container.RebornContainer;
import techreborn.tiles.TileDieselGenerator;

public class ContainerDieselGenerator extends RebornContainer {

    public TileDieselGenerator tiledieselGenerator;
    public EntityPlayer player;
    public int energy;
    public int fluid;

    public ContainerDieselGenerator(TileDieselGenerator tiledieselGenerator, EntityPlayer player) {
        super();
        this.tiledieselGenerator = tiledieselGenerator;
        this.player = player;

        this.addSlotToContainer(new Slot(tiledieselGenerator.inventory, 0, 80, 17));
        this.addSlotToContainer(new SlotOutput(tiledieselGenerator.inventory, 1, 80, 53));
        this.addSlotToContainer(new SlotFake(tiledieselGenerator.inventory, 2, 59, 42, false, false, 1));

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
    public boolean canInteractWith(EntityPlayer player) {
        return true;
    }

    @Override
    public void detectAndSendChanges() {
        super.detectAndSendChanges();
        for (int i = 0; i < this.crafters.size(); i++) {
            ICrafting icrafting = (ICrafting) this.crafters.get(i);
            if (this.energy != (int) tiledieselGenerator.getEnergy()) {
                icrafting.sendProgressBarUpdate(this, 0, (int) tiledieselGenerator.getEnergy());
            }
            if (this.fluid != tiledieselGenerator.tank.getFluidAmount()) {
                icrafting.sendProgressBarUpdate(this, 1, tiledieselGenerator.tank.getFluidAmount());
            }
        }
    }

    @Override
    public void addCraftingToCrafters(ICrafting crafting) {
        super.addCraftingToCrafters(crafting);
        crafting.sendProgressBarUpdate(this, 0, (int) tiledieselGenerator.getEnergy());
        crafting.sendProgressBarUpdate(this, 1, tiledieselGenerator.tank.getFluidAmount());
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void updateProgressBar(int id, int value) {
        if (id == 0) {
            this.energy = value;
        } else if (id == 1) {
            this.fluid = value;
        }
    }
}
