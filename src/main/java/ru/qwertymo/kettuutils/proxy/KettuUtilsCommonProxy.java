package ru.qwertymo.kettuutils.proxy;

import cpw.mods.fml.common.event.*;
import ru.qwertymo.kettuutils.core.chat.KettuChatServer;
import ru.qwertymo.kettuutils.core.net.NetworkManager;

public class KettuUtilsCommonProxy {
    public final KettuChatServer chat = new KettuChatServer();

    private final NetworkManager network = new NetworkManager();

    public NetworkManager getNetwork(){
        return network;
    }

    private static volatile KettuUtilsCommonProxy _instance = new KettuUtilsCommonProxy();
    public static KettuUtilsCommonProxy getInstance(){
        return _instance;
    }

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
