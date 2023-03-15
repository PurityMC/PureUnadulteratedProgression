package page.codeberg.unix_supremacist.pup;

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

public class MetaItem extends Item {

    @Getter
    final String modid;
    public static final HashMap<Integer, String> items = new HashMap<>();
    public static final HashMap<Integer, IIcon> icons = new HashMap<>();

    public MetaItem(String modid) {
        this.modid = modid;
        this.setHasSubtypes(true);
        this.setCreativeTab(CreativeTabs.tabMaterials);
    }

    public void addItem(int meta, String name) {
        items.put(meta, name);
    }

    public String getItemName(ItemStack item) {
        return this.items.get(item.getItemDamage()).toLowerCase();
    }

    @Override
    public String getUnlocalizedName(ItemStack item) {
        if (this.getItemName(item) == null) return "item.null";
        return "item." + this.getModid() + "." + this.getItemName(item);
    }

    @Override
    public void getSubItems(Item item, CreativeTabs tabs, List itemList) {
        for (Integer id : this.items.keySet()) itemList.add(new ItemStack(this, 1, id));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister register) {
        int i = 0;
        for (String item : this.items.values()) {
            icons.put(i, register.registerIcon(this.getModid() + ":" + item.toLowerCase()));
            i++;
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(int meta) {
        return icons.get(meta);
    }
}
