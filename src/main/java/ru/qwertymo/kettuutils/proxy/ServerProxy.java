package ru.qwertymo.kettuutils.proxy;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.server.MinecraftServer;

public class ServerProxy extends CommonProxy {
    @Override
    public void preInit(FMLPreInitializationEvent event) {
        super.preInit(event);

    }
}
