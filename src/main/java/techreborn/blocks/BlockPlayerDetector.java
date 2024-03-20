package techreborn.blocks;

import java.util.List;
import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import techreborn.client.TechRebornCreativeTab;
import techreborn.tiles.TilePlayerDectector;

public class BlockPlayerDetector extends BlockMachineBase {

    public BlockPlayerDetector() {
        super(Material.rock);
        setBlockName("techreborn.playerDetector");
        setCreativeTab(TechRebornCreativeTab.instance);
        setHardness(2f);
    }

    public static final String[] types = new String[] { "all", "others", "you" };

    private IIcon[] textures;

    @Override
    public Item getItemDropped(int par1, Random random, int par2) {
        return Item.getItemFromBlock(this);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item item, CreativeTabs creativeTabs, List list) {
        for (int meta = 0; meta < types.length; meta++) {
            list.add(new ItemStack(item, 1, meta));
        }
    }

    @Override
    public int damageDropped(int metaData) {
        return metaData;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister) {
        this.textures = new IIcon[types.length];

        for (int i = 0; i < types.length; i++) {
            textures[i] = iconRegister.registerIcon("techreborn:" + "machine/player_detector_" + types[i]);
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int metaData) {
        return textures[MathHelper.clamp_int(metaData, 0, types.length - 1)];
    }

    @Override
    public void onBlockAdded(World world, int x, int y, int z) {

    }

    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return new TilePlayerDectector();
    }

    @Override
    public boolean canConnectRedstone(IBlockAccess world, int x, int y, int z, int side) {
        return true;
    }

    @Override
    public boolean canProvidePower() {
        return true;
    }

    @Override
    public int isProvidingStrongPower(IBlockAccess blockAccess, int x, int y, int z, int side) {
        TileEntity entity = blockAccess.getTileEntity(x, y, z);
        if (entity instanceof TilePlayerDectector) {
            return ((TilePlayerDectector) entity).isProvidingPower() ? 15 : 0;
        }
        return 0;
    }

    @Override
    public int isProvidingWeakPower(IBlockAccess blockAccess, int x, int y, int z, int side) {
        TileEntity entity = blockAccess.getTileEntity(x, y, z);
        if (entity instanceof TilePlayerDectector) {
            return ((TilePlayerDectector) entity).isProvidingPower() ? 15 : 0;
        }
        return 0;
    }

    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase player, ItemStack itemstack) {
        super.onBlockPlacedBy(world, x, y, z, player, itemstack);
        TileEntity tile = world.getTileEntity(x, y, z);
        if (tile instanceof TilePlayerDectector) {
            ((TilePlayerDectector) tile).owenerUdid = player.getUniqueID()
                .toString();
        }
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer entityplayer, int side, float hitX,
        float hitY, float hitZ) {
        if (super.onBlockActivated(world, x, y, z, entityplayer, side, hitX, hitY, hitZ)) {
            return true;
        }
        int newMeta = (world.getBlockMetadata(x, y, z) + 1) % 3;
        String message = "";
        switch (newMeta) {
            case 0:
                message = EnumChatFormatting.GREEN + "Detects all Players";
                break;
            case 1:
                message = EnumChatFormatting.RED + "Detects only other Players";
                break;
            case 2:
                message = EnumChatFormatting.BLUE + "Detects only you";
        }
        if (!world.isRemote) {
            entityplayer.addChatComponentMessage(new ChatComponentText(message));
            world.setBlockMetadataWithNotify(x, y, z, newMeta, 2);
        }
        return true;
    }
}
