package techreborn.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockHighlyAdvancedMachine extends BlockMachineBase {

    @SideOnly(Side.CLIENT)
    private IIcon iconFront;

    @SideOnly(Side.CLIENT)
    private IIcon iconTop;

    @SideOnly(Side.CLIENT)
    private IIcon iconBottom;

    public BlockHighlyAdvancedMachine(Material material) {
        super(material);
        setBlockName("techreborn.highlyAdvancedMachine");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister icon) {
        this.blockIcon = icon.registerIcon("techreborn:machine/highlyadvancedmachine");
        this.iconFront = icon.registerIcon("techreborn:machine/highlyadvancedmachine");
        this.iconTop = icon.registerIcon("techreborn:machine/highlyadvancedmachine");
        this.iconBottom = icon.registerIcon("techreborn:machine/highlyadvancedmachine");
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

}
