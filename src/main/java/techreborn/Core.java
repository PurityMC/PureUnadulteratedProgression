package techreborn;

import java.io.File;

import net.minecraftforge.common.MinecraftForge;

import org.apache.commons.lang3.time.StopWatch;

import page.codeberg.unix_supremacist.pup.PUP;
import reborncore.common.multiblock.MultiblockEventHandler;
import reborncore.common.multiblock.MultiblockServerTickHandler;
import reborncore.common.packets.AddDiscriminatorEvent;
import reborncore.common.util.LogHelper;
import reborncore.common.util.VersionChecker;
import page.codeberg.unix_supremacist.pup.achievement.Achievements;
import techreborn.api.recipe.RecipeHandler;
import techreborn.api.recipe.recipeConfig.RecipeConfigManager;
import techreborn.client.GuiHandler;
import techreborn.command.TechRebornDevCommand;
import techreborn.compat.CompatManager;
import techreborn.compat.ICompatModule;
import techreborn.config.ConfigTechReborn;
import page.codeberg.unix_supremacist.pup.Event;
import techreborn.init.*;
import techreborn.lib.ModInfo;
import techreborn.packets.PacketAesu;
import techreborn.packets.PacketIdsu;
import techreborn.proxies.CommonProxy;
import techreborn.tiles.idsu.IDSUManager;
import techreborn.world.TROreGen;
import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(
        modid = ModInfo.MOD_ID,
        name = ModInfo.MOD_NAME,
        version = ModInfo.MOD_VERSION,
        dependencies = ModInfo.MOD_DEPENDENCUIES,
        guiFactory = ModInfo.GUI_FACTORY_CLASS)
public class Core {

    public static ConfigTechReborn config;

    @SidedProxy(clientSide = ModInfo.CLIENT_PROXY_CLASS, serverSide = ModInfo.SERVER_PROXY_CLASS)
    public static CommonProxy proxy;

    public VersionChecker versionChecker;

    public static LogHelper logHelper = new LogHelper(new ModInfo());

    @Mod.EventHandler
    public void preinit(FMLPreInitializationEvent event) {
        event.getModMetadata().version = ModInfo.MOD_VERSION;
        FMLCommonHandler.instance().bus().register(this);
        MinecraftForge.EVENT_BUS.register(this);

        String path = event.getSuggestedConfigurationFile().getAbsolutePath().replace(ModInfo.MOD_ID, "TechReborn");

        config = ConfigTechReborn.initialize(new File(path));

        for (ICompatModule compatModule : CompatManager.INSTANCE.compatModules) {
            compatModule.preInit(event);
        }

        RecipeConfigManager.load(event.getModConfigurationDirectory());
        versionChecker = new VersionChecker("TechReborn", new ModInfo());
        versionChecker.checkVersionThreaded();
        logHelper.info("PreInitialization Complete");
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) throws IllegalAccessException, InstantiationException {
        // Register ModBlocks
        ModBlocks.init();
        // Register Fluids
        ModFluids.init();
        // Register ModItems
        ModItems.init();
        // Multiparts
        ModParts.init();
        // Recipes
        StopWatch watch = new StopWatch();
        watch.start();
        ModRecipes.init();
        logHelper.all(watch + " : main recipes");
        watch.stop();
        // Client only init, needs to be done before parts system
        proxy.init();
        // Compat
        for (ICompatModule compatModule : CompatManager.INSTANCE.compatModules) {
            compatModule.init(event);
        }
        // WorldGen
        GameRegistry.registerWorldGenerator(new TROreGen(), 0);
        // DungeonLoot.init();
        // Register Gui Handler
        NetworkRegistry.INSTANCE.registerGuiHandler(PUP.pupinstance, new GuiHandler());

        // Achievements
        Achievements.init();
        // Multiblock events
        MinecraftForge.EVENT_BUS.register(new MultiblockEventHandler());
        // IDSU manager
        IDSUManager.INSTANCE = new IDSUManager();
        MinecraftForge.EVENT_BUS.register(IDSUManager.INSTANCE);
        FMLCommonHandler.instance().bus().register(new MultiblockServerTickHandler());
        FMLCommonHandler.instance().bus().register(new Event());
        logHelper.info("Initialization Complete");
    }

    @Mod.EventHandler
    public void postinit(FMLPostInitializationEvent event) throws Exception {
        // Has to be done here as Buildcraft registers their recipes late
        for (ICompatModule compatModule : CompatManager.INSTANCE.compatModules) {
            compatModule.postInit(event);
        }
        logHelper.info(RecipeHandler.recipeList.size() + " recipes loaded");

        // RecipeHandler.scanForDupeRecipes();

        // RecipeConfigManager.save();
    }

    @Mod.EventHandler
    public void serverStarting(FMLServerStartingEvent event) {
        event.registerServerCommand(new TechRebornDevCommand());
        for (ICompatModule compatModule : CompatManager.INSTANCE.compatModules) {
            compatModule.serverStarting(event);
        }
    }

    @SubscribeEvent
    public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent cfgChange) {
        if (cfgChange.modID.equals("TechReborn")) {
            ConfigTechReborn.Configs();
        }
    }

    @SubscribeEvent
    public void addDiscriminator(AddDiscriminatorEvent event) {
        event.getPacketHandler().addDiscriminator(event.getPacketHandler().nextDiscriminator, PacketAesu.class);
        event.getPacketHandler().addDiscriminator(event.getPacketHandler().nextDiscriminator, PacketIdsu.class);
    }
}
