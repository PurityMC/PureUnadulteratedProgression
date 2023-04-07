package page.codeberg.unix_supremacist.pup.api;

import java.util.HashMap;

import lombok.Getter;
import net.minecraft.item.ItemStack;

import page.codeberg.unix_supremacist.pup.PUP;

public class ItemApi {

    @Getter public static HashMap<String, ItemStack> items = new HashMap<>();

    public static ItemStack getItem(String name, int count) {
        ItemStack item = null;
        if (items.get(name) != null) {
            item = items.get(name).copy();
            item.stackSize = count;
        } else {
            PUP.LOG.fatal("NON EXISTANT ITEM: " + name + " REQUESTED");
        }
        return item;
    }

    public static ItemStack getItem(String name) {
        return getItem(name, 1);
    }

    public static boolean itemExists(String name){
        return items.get(name) != null;
    }

    public static void setItem(String name, ItemStack itemStack) {
        items.put(name, itemStack);
    }
}
