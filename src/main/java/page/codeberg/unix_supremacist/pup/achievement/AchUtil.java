package page.codeberg.unix_supremacist.pup.achievement;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;
import page.codeberg.unix_supremacist.pup.Tags;

public class AchUtil extends Achievement {

    public static List<Achievement> achievements = new ArrayList();

    public AchUtil(String name, int x, int y, ItemStack icon, Achievement parent) {
        super("achievement."+Tags.MODID+":" + name, Tags.MODID + name, x, y, icon, parent);
        achievements.add(this);
        registerStat();
    }

    public AchUtil(String name, int x, int y, Item icon, Achievement parent) {
        this(name, x, y, new ItemStack(icon), parent);
    }

    public AchUtil(String name, int x, int y, Block icon, Achievement parent) {
        this(name, x, y, new ItemStack(icon), parent);
    }

}
