package techreborn.blocks.machine;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import page.codeberg.unix_supremacist.pup.PUP;
import techreborn.blocks.BlockMachineBase;
import techreborn.client.GuiHandler;
import techreborn.tiles.TileVacuumFreezer;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockVacuumFreezer extends BlockMachineBase {

    @SideOnly(Side.CLIENT)
    private IIcon iconFront;

    @SideOnly(Side.CLIENT)
    private IIcon iconTop;

    @SideOnly(Side.CLIENT)
    private IIcon iconBottom;

    public BlockVacuumFreezer(Material material) {
        super(material);
        setBlockName("techreborn.vacuumfreezer");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister icon) {
        this.blockIcon = icon.registerIcon("techreborn:machine/machine_side");
        this.iconFront = icon.registerIcon("techreborn:machine/vacuum_freezer_front");
        this.iconTop = icon.registerIcon("techreborn:machine/vacuum_freezer_top");
        this.iconBottom = icon.registerIcon("techreborn:machine/machine_bottom");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(IBlockAccess blockAccess, int x, int y, int z, int side) {
        int metadata = getTileRotation(blockAccess, x, y, z);
        return metadata == 0 && side == 3 ? this.iconFront
                : side == 1 ? this.iconTop
                        : side == 0 ? this.iconBottom
                                : (side == 0 ? this.iconTop : (side == metadata ? this.iconFront : this.blockIcon));
    }

    @Override
    public boolean isAdvanced() {
        return true;
    }

    @Override
    public IIcon getIcon(int side, int meta) {
        if (side == 1) {
            return this.iconTop;
        } else if (side == 3) {
            return this.iconFront;
        } else {
            return this.blockIcon;
        }
    }

    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return new TileVacuumFreezer();
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX,
            float hitY, float hitZ) {

        TileVacuumFreezer tileVacuumFreezer = (TileVacuumFreezer) world.getTileEntity(x, y, z);
        tileVacuumFreezer.multiBlockStatus = tileVacuumFreezer.checkMachine() ? 1 : 0;

        if (!player.isSneaking()) player.openGui(PUP.pupinstance, GuiHandler.vacuumFreezerID, world, x, y, z);
        return true;
    }
}
