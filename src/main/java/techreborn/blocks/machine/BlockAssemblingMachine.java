package techreborn.blocks.machine;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import page.codeberg.unix_supremacist.pup.PUP;
import page.codeberg.unix_supremacist.pup.Tags;
import techreborn.blocks.BlockMachineBase;
import techreborn.client.GuiHandler;
import techreborn.tiles.TileAssemblingMachine;

public class BlockAssemblingMachine extends BlockMachineBase {

    @SideOnly(Side.CLIENT)
    private IIcon iconFront;

    @SideOnly(Side.CLIENT)
    private IIcon iconTop;

    @SideOnly(Side.CLIENT)
    private IIcon iconFrontOn;

    @SideOnly(Side.CLIENT)
    private IIcon iconBottom;

    public BlockAssemblingMachine(Material material) {
        super(material);
        setBlockName("techreborn.assemblingmachine");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister icon) {
        this.blockIcon = icon.registerIcon(Tags.MODID + ":machine/machine_side");
        this.iconFront = icon.registerIcon(Tags.MODID + ":machine/assembling_machine_front_off");
        this.iconFrontOn = icon.registerIcon(Tags.MODID + ":machine/assembling_machine_front_on");
        this.iconTop = icon.registerIcon(Tags.MODID + ":machine/assembling_machine_top");
        this.iconBottom = icon.registerIcon(Tags.MODID + ":machine/machine_bottom");
    }

    @Override
    public TileEntity createNewTileEntity(World world, int p_149915_2_) {
        return new TileAssemblingMachine();
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX,
        float hitY, float hitZ) {
        if (!player.isSneaking()) player.openGui(PUP.pupinstance, GuiHandler.assemblingmachineID, world, x, y, z);
        return true;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(IBlockAccess blockAccess, int x, int y, int z, int side) {
        int metadata = getTileRotation(blockAccess, x, y, z);
        if (side == metadata && blockAccess.getBlockMetadata(x, y, z) == 1) {
            return this.iconFrontOn;
        }
        return metadata == 0 && side == 3 ? this.iconFront
            : side == 1 ? this.iconTop
                : side == 0 ? this.iconBottom
                    : (side == 0 ? this.iconTop : (side == metadata ? this.iconFront : this.blockIcon));
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
}
