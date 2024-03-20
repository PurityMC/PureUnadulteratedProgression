package techreborn.compat.waila;

import cpw.mods.fml.common.event.*;
import mcp.mobius.waila.api.IWailaRegistrar;
import techreborn.compat.ICompatModule;
import techreborn.tiles.TileMachineBase;

public class CompatModuleWaila implements ICompatModule {

    @Override
    public void preInit(FMLPreInitializationEvent event) {

    }

    public void init(FMLInitializationEvent event) {
        FMLInterModComms.sendMessage("Waila", "register", getClass().getName() + ".callbackRegister");
    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {

    }

    @Override
    public void serverStarting(FMLServerStartingEvent event) {

    }

    public static void callbackRegister(IWailaRegistrar registrar) {
        registrar.registerBodyProvider(new WailaProviderMachines(), TileMachineBase.class);
    }
}
