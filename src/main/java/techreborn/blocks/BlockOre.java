package techreborn.blocks;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import page.codeberg.unix_supremacist.pup.api.ItemApi;
import reborncore.common.util.OreDrop;
import reborncore.common.util.OreDropSet;
import techreborn.client.TechRebornCreativeTabMisc;
import techreborn.config.ConfigTechReborn;
import techreborn.init.ModBlocks;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockOre extends Block {

    public static ItemStack getOreByName(String name, int count) {
        int index = -1;
        for (int i = 0; i < types.length; i++) {
            if (types[i].equals(name)) {
                index = i;
                break;
            }
        }
        return new ItemStack(ModBlocks.ore, count, index);
    }

    public static ItemStack getOreByName(String name) {
        return getOreByName(name, 1);
    }

    public static final String[] types = new String[] { "Galena", "Iridium", "Ruby", "Sapphire", "Bauxite", "Pyrite",
            "Cinnabar", "Sphalerite", "Tungston", "Sheldonite", "Peridot", "Sodalite", "Tetrahedrite", "Cassiterite",
            "Lead", "Silver" };

    private IIcon[] textures;

    public BlockOre(Material material) {
        super(material);
        setBlockName("techreborn.ore");
        setCreativeTab(TechRebornCreativeTabMisc.instance);
        setHardness(2.0f);
        ModBlocks.blocksToCut.add(this);
        setHarvestLevel("pickaxe", 2);
    }

    @Override
    public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
        // Ruby
        if (metadata == 2) {
            OreDrop ruby = new OreDrop(
                    ItemApi.getItem("gemRuby"),
                    ConfigTechReborn.FortuneSecondaryOreMultiplierPerLevel);
            OreDrop redGarnet = new OreDrop(ItemApi.getItem("gemRedGarnet"), 0.02);
            OreDropSet set = new OreDropSet(ruby, redGarnet);
            return set.drop(fortune, world.rand);
        }

        // Sapphire
        if (metadata == 3) {
            OreDrop sapphire = new OreDrop(
                    ItemApi.getItem("gemSapphire"),
                    ConfigTechReborn.FortuneSecondaryOreMultiplierPerLevel);
            OreDrop peridot = new OreDrop(ItemApi.getItem("gemPeridot"), 0.03);
            OreDropSet set = new OreDropSet(sapphire, peridot);
            return set.drop(fortune, world.rand);
        }

        // Pyrite
        if (metadata == 5) {
            OreDrop pyriteDust = new OreDrop(
                    ItemApi.getItem("dustPyrite"),
                    ConfigTechReborn.FortuneSecondaryOreMultiplierPerLevel);
            OreDropSet set = new OreDropSet(pyriteDust);
            return set.drop(fortune, world.rand);
        }

        // Sodolite
        if (metadata == 11) {
            OreDrop sodalite = new OreDrop(
                    ItemApi.getItem("dustSodalite", 6),
                    ConfigTechReborn.FortuneSecondaryOreMultiplierPerLevel);
            OreDrop aluminum = new OreDrop(ItemApi.getItem("dustAluminium"), 0.50);
            OreDropSet set = new OreDropSet(sodalite, aluminum);
            return set.drop(fortune, world.rand);
        }

        // Cinnabar
        if (metadata == 6) {
            OreDrop cinnabar = new OreDrop(
                    ItemApi.getItem("dustCinnabar"),
                    ConfigTechReborn.FortuneSecondaryOreMultiplierPerLevel);
            OreDrop redstone = new OreDrop(new ItemStack(Items.redstone), 0.25);
            OreDropSet set = new OreDropSet(cinnabar, redstone);
            return set.drop(fortune, world.rand);
        }

        // Sphalerite 1, 1/8 yellow garnet
        if (metadata == 7) {
            OreDrop sphalerite = new OreDrop(
                    ItemApi.getItem("dustSphalerite"),
                    ConfigTechReborn.FortuneSecondaryOreMultiplierPerLevel);
            OreDrop yellowGarnet = new OreDrop(ItemApi.getItem("gemYellowGarnet"), 0.125);
            OreDropSet set = new OreDropSet(sphalerite, yellowGarnet);
            return set.drop(fortune, world.rand);
        }

        ArrayList<ItemStack> block = new ArrayList<ItemStack>();
        block.add(new ItemStack(Item.getItemFromBlock(this), 1, metadata));
        return block;
    }

    @Override
    protected boolean canSilkHarvest() {
        return true;
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
        if (metaData == 2) {
            return 0;
        } else if (metaData == 3) {
            return 1;
        } else if (metaData == 5) {
            return 60;
        }
        return metaData;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister) {
        this.textures = new IIcon[types.length];

        for (int i = 0; i < types.length; i++) {
            textures[i] = iconRegister.registerIcon("techreborn:" + "ore/ore" + types[i]);
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int metaData) {
        metaData = MathHelper.clamp_int(metaData, 0, types.length - 1);

        if (ForgeDirection.getOrientation(side) == ForgeDirection.UP
                || ForgeDirection.getOrientation(side) == ForgeDirection.DOWN) {
            return textures[metaData];
        } else {
            return textures[metaData];
        }
    }

    @Override
    public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z, EntityPlayer player) {
        return new ItemStack(ModBlocks.ore, 1, world.getBlockMetadata(x, y, z));
    }
}
