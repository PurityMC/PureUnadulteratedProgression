package page.codeberg.unix_supremacist.pup;

import net.minecraft.client.gui.Gui;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import page.codeberg.unix_supremacist.pup.machine.BasicMachine;
import cpw.mods.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity tile = world.getTileEntity(x, y, z);
        Container container = null;
        if (tile instanceof BasicMachine.BasicTile)
            container = ((BasicMachine.BasicTile) tile).getContainer(player.inventory);
        return container;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity tile = world.getTileEntity(x, y, z);
        Gui gui = null;
        if (tile instanceof BasicMachine.BasicTile) gui = ((BasicMachine.BasicTile) tile).getGui(player.inventory);
        return gui;
    }
}
