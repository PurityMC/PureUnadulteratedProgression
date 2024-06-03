package page.codeberg.unix_supremacist.pup.achievement;

import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;
import net.minecraftforge.common.AchievementPage;

import techreborn.init.ModBlocks;
import techreborn.lib.ModInfo;
import cpw.mods.fml.common.FMLCommonHandler;

public class Achievements {

    public static AchievementPage pupPage;
    public static int pageIndex;

    public static Achievement ore_PickUp;
    public static Achievement thermalgen_Craft;
    public static Achievement centrifuge_Craft;

    public static void init() {
        ore_PickUp = new AchUtil("ore_PickUp", 0, 0, new ItemStack(ModBlocks.ore, 1, 0), null);
        centrifuge_Craft = new AchUtil("centrifuge_Craft", 1, 1, ModBlocks.centrifuge, ore_PickUp);
        thermalgen_Craft = new AchUtil("thermalgen_Craft", 2, 1, ModBlocks.thermalGenerator, ore_PickUp);

        pageIndex = AchievementPage.getAchievementPages().size();
        pupPage = new AchievementPage(
                ModInfo.MOD_NAME,
                AchUtil.achievements.toArray(new Achievement[AchUtil.achievements.size()]));
        AchievementPage.registerAchievementPage(pupPage);

        FMLCommonHandler.instance().bus().register(new AchTrigger());

    }

}
