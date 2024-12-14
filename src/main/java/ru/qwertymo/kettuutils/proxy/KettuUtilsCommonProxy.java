package ru.qwertymo.kettuutils.proxy;

import cpw.mods.fml.common.event.*;
import ru.qwertymo.kettuutils.core.chat.KettuChatServer;

public class KettuUtilsCommonProxy {
    public final KettuChatServer chat = new KettuChatServer();

    public void preInit(FMLPreInitializationEvent event) {
        // Общая логика
    }

    public void init(FMLInitializationEvent event) {
        // Общая логика
    }

    public void postInit(FMLPostInitializationEvent event) {
        // Общая логика
    }

    public void serverStarting(FMLServerStartingEvent event) {
        // Пустая реализация, чтобы избежать ошибок
    }
}
