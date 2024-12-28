package ru.qwertymo.kettuutils.proxy;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.event.*;
import cpw.mods.fml.relauncher.Side;
import net.minecraftforge.common.MinecraftForge;
import ru.qwertymo.kettuutils.core.chat.KettuChatServer;
import ru.qwertymo.kettuutils.core.net.ServerNetworking;

public class KettuUtilsCommonProxy {
    public final KettuChatServer chat = new KettuChatServer();

    public void preInit(FMLPreInitializationEvent event) {
        ServerNetworking.register();
    }

    public void init(FMLInitializationEvent event) {
    }

    public void postInit(FMLPostInitializationEvent event) {
        // Общая логика
    }

    public void serverStarting(FMLServerStartingEvent event) {
        // Пустая реализация, чтобы избежать ошибок
    }
}
