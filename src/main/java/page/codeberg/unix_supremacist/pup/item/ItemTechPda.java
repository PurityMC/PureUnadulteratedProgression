package page.codeberg.unix_supremacist.pup.item;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import page.codeberg.unix_supremacist.pup.PUP;
import techreborn.client.GuiHandler;
import techreborn.client.TechRebornCreativeTab;

public class ItemTechPda extends Item {

    public ItemTechPda() {
        setCreativeTab(TechRebornCreativeTab.instance);
        setUnlocalizedName("techreborn.pda");
        setMaxStackSize(1);
    }

    @Override
    public void registerIcons(IIconRegister iconRegister) {
        itemIcon = iconRegister.registerIcon("techreborn:" + "tool/pda");
    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player) {
        player.openGui(
                PUP.pupinstance,
                GuiHandler.pdaID,
                world,
                (int) player.posX,
                (int) player.posY,
                (int) player.posY);
        return itemStack;
    }

}
