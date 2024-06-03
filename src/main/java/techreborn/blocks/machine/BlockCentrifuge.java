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
import techreborn.tiles.TileCentrifuge;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockCentrifuge extends BlockMachineBase {

    @SideOnly(Side.CLIENT)
    private IIcon iconFront;

    @SideOnly(Side.CLIENT)
    private IIcon iconFrontOn;

    @SideOnly(Side.CLIENT)
    private IIcon iconTop;

    @SideOnly(Side.CLIENT)
    private IIcon iconTopOn;

    @SideOnly(Side.CLIENT)
    private IIcon iconBottom;

    public BlockCentrifuge() {
        super(Material.rock);
        setBlockName("techreborn.centrifuge");
    }

    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return new TileCentrifuge();
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX,
            float hitY, float hitZ) {
        if (!player.isSneaking()) player.openGui(PUP.pupinstance, GuiHandler.centrifugeID, world, x, y, z);
        return true;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister icon) {
        this.iconFront = icon.registerIcon("techreborn:machine/industrial_centrifuge_side_off");
        this.iconFrontOn = icon.registerIcon("techreborn:machine/industrial_centrifuge_side_on");
        this.iconTop = icon.registerIcon("techreborn:machine/industrial_centrifuge_top_off");
        this.iconTopOn = icon.registerIcon("techreborn:machine/industrial_centrifuge_top_on");
        this.iconBottom = icon.registerIcon("techreborn:machine/industrial_centrifuge_bottom");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(IBlockAccess blockAccess, int x, int y, int z, int side) {
        int metadata = getTileRotation(blockAccess, x, y, z);
        if (blockAccess.getBlockMetadata(x, y, z) == 1) {
            if (side == 1) {
                return this.iconTopOn;
            } else if (side == 0) {
                return this.iconBottom;
            }
            return this.iconFrontOn;
        } else {
            if (side == 1) {
                return this.iconTop;
            } else if (side == 0) {
                return this.iconBottom;
            }
            return this.iconFront;
        }

    }

    @Override
    public IIcon getIcon(int side, int meta) {
        if (side == 1) {
            return this.iconTop;
        } else if (side == 3) {
            return this.iconFront;
        } else {
            return this.iconFront;
        }
    }

}
