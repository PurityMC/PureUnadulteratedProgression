package page.codeberg.unix_supremacist.pup;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class ToolItem extends Item {

    @SideOnly(Side.CLIENT)
    public IIcon icon;
    public String modid;
    public String name;

    public ToolItem(String name, String modid, int damage) {
        this.modid = modid;
        this.name = name;
        this.setMaxStackSize(1);
        this.setMaxDamage(damage);
        // this.setCreativeTab(CommonProxy.pftab);
    }

    public boolean hasContainerItem(ItemStack stack) {
        return this.getDamage(stack) != this.getMaxDamage();
    }

    public ItemStack getContainerItem(ItemStack stack) {
        if (!hasContainerItem(stack)) return null;
        // stack.attemptDamageItem(stack.getItemDamage()+1, ); this should be used with a random call instead for
        // enchantments
        stack.setItemDamage(stack.getItemDamage() + 1);
        return stack;
    }

    public boolean doesContainerItemLeaveCraftingGrid(ItemStack p_77630_1_) {
        return false;
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(int meta) {
        return icon;
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconRegister) {
        super.registerIcons(iconRegister);
        this.icon = iconRegister.registerIcon(modid + ":" + name.toLowerCase());
    }
}
