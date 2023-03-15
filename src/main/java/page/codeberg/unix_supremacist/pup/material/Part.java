package page.codeberg.unix_supremacist.pup.material;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import lombok.Getter;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

import java.util.HashMap;
import java.util.List;

public class Part extends Item {

    @Getter
    public String name;
    @Getter
    public HashMap<Integer, Material> mats = new HashMap<>();
    public String modid;
    public IIcon icon;

    public Part(String modid, String name) {
        this.setHasSubtypes(true);
        this.setCreativeTab(CreativeTabs.tabMaterials);
        this.modid = modid;
        this.name = name;
    }

    @Override
    public String getUnlocalizedName(ItemStack item) {
        if (mats.containsKey(item.getItemDamage()))
            return "item." + this.modid + "." + this.mats.get(item.getItemDamage()).getName() + "_" + this.name;
        return "item." + this.modid + ".null";
    }

    @SideOnly(Side.CLIENT)
    @Override
    public int getColorFromItemStack(ItemStack itemStack, int u) {
        if (this.mats.containsKey(itemStack.getItemDamage()))
            return this.mats.get(itemStack.getItemDamage()).getColor();
        return 0xFFFFFF;
    }

    @Override
    public void registerIcons(IIconRegister register) {
        this.icon = register.registerIcon(this.modid + ":item_" + this.name);
    }

    public Part setMats(HashMap<Integer, Material> mats) {
        this.mats = mats;
        return this;
    }

    @Override
    public IIcon getIconFromDamage(int meta) {
        return this.icon;
    }

    @Override
    public void getSubItems(Item item, CreativeTabs tabs, List itemList) {
        for (HashMap.Entry<Integer, Material> set : mats.entrySet()) itemList.add(new ItemStack(this, 1, set.getKey()));
    }
}
