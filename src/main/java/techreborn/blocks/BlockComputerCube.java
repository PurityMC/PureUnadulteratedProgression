package techreborn.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import page.codeberg.unix_supremacist.pup.PUP;
import techreborn.client.GuiHandler;

public class BlockComputerCube extends BlockMachineBase {

    public BlockComputerCube(Material material) {
        super(material);
        setBlockName("techreborn.computercube");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister icon) {
        this.blockIcon = icon.registerIcon("techreborn:machine/computer_cube");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(IBlockAccess blockAccess, int x, int y, int z, int side) {
        return this.blockIcon;
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX,
        float hitY, float hitZ) {
        if (!player.isSneaking()) player.openGui(PUP.pupinstance, GuiHandler.pdaID, world, x, y, z);
        return true;
    }

}
