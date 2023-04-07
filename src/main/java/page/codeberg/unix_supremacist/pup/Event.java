package page.codeberg.unix_supremacist.pup;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;

import techreborn.init.ModItems;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;

public class Event {

    public Item previouslyWearing;

    @SubscribeEvent(priority = EventPriority.NORMAL, receiveCanceled = true)
    public void onPlayerTick(TickEvent.PlayerTickEvent e) {
        EntityPlayer player = e.player;
        Item chestslot = player.getEquipmentInSlot(3) != null ? player.getEquipmentInSlot(3).getItem() : null;

        if (previouslyWearing != chestslot && previouslyWearing == ModItems.cloakingDevice
                && player.isInvisible()
                && !player.isPotionActive(Potion.invisibility)) {
            player.setInvisible(false);
        }

        previouslyWearing = chestslot;
    }

}
