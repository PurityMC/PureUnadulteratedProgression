package page.codeberg.unix_supremacist.pup;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import page.codeberg.unix_supremacist.pup.machine.BasicMachine;

@Mod(modid = Tags.MODID, version = Tags.VERSION, name = Tags.MODNAME, acceptedMinecraftVersions = "[1.7.10]")
public class PUP {
    @Mod.Instance(Tags.MODID)
    public static PUP pupinstance;
    public static final Logger LOG = LogManager.getLogger(Tags.MODID);
    public static final Item testHammer = new ToolItem("testHammer", Tags.MODID, 255);

    @Mod.EventHandler
    // preInit "Run before anything else. Read your config, create blocks, items, etc, and register them with the
    // GameRegistry." (Remove if not needed)
    public void preInit(FMLPreInitializationEvent event) {
        GameRegistry.registerTileEntity(BasicMachine.BasicTile.class, "tmatile");
        GameRegistry.registerBlock(new BasicMachine(), "gay");
        ItemIterator.load();
        GameRegistry.registerItem(testHammer, "testHammer");
        Oredict.load();
    }

    @Mod.EventHandler
    // load "Do your mod setup. Build whatever data structures you care about. Register recipes." (Remove if not needed)
    public void init(FMLInitializationEvent event) {
        NetworkRegistry.INSTANCE.registerGuiHandler(pupinstance, new GuiHandler());
        Recipes.load();
    }

    @Mod.EventHandler
    // postInit "Handle interaction with other mods, complete your setup based on this." (Remove if not needed)
    public void postInit(FMLPostInitializationEvent event) {
        Recipes.bulkRemoveByRecipe();
    }

    @Mod.EventHandler
    // register server commands in this event handler (Remove if not needed)
    public void serverStarting(FMLServerStartingEvent event) {}
}
