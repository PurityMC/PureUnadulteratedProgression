package techreborn.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import page.codeberg.unix_supremacist.pup.PUP;
import techreborn.client.GuiHandler;
import techreborn.tiles.fusionReactor.TileEntityFusionController;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockFusionControlComputer extends BlockMachineBase {

    @SideOnly(Side.CLIENT)
    private IIcon iconFront;

    @SideOnly(Side.CLIENT)
    private IIcon iconTop;

    @SideOnly(Side.CLIENT)
    private IIcon iconBottom;

    public BlockFusionControlComputer(Material material) {
        super(material);
        setBlockName("techreborn.fusioncontrolcomputer");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister icon) {
        this.blockIcon = icon.registerIcon("techreborn:machine/plasma_generator_side_off");
        this.iconFront = icon.registerIcon("techreborn:machine/fusion_control_computer_front");
        this.iconTop = icon.registerIcon("techreborn:machine/plasma_generator_side_off");
        this.iconBottom = icon.registerIcon("techreborn:machine/plasma_generator_side_off");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(IBlockAccess blockAccess, int x, int y, int z, int side) {
        int metadata = getTileRotation(blockAccess, x, y, z);
        if (side == metadata && blockAccess.getBlockMetadata(x, y, z) == 1) {
            return this.iconFront;
        }
        return metadata == 0 && side == 3 ? this.iconFront
                : side == 1 ? this.iconTop
                        : side == 0 ? this.iconBottom
                                : (side == 0 ? this.iconTop : (side == metadata ? this.iconFront : this.blockIcon));
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX,
            float hitY, float hitZ) {
        TileEntityFusionController tileEntityFusionController = (TileEntityFusionController) world
                .getTileEntity(x, y, z);
        tileEntityFusionController.checkCoils();
        if (!player.isSneaking()) player.openGui(PUP.pupinstance, GuiHandler.fusionID, world, x, y, z);
        return true;
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return new TileEntityFusionController();
    }
}
