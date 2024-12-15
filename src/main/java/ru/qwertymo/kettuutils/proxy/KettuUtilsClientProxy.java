package ru.qwertymo.kettuutils.proxy;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import ru.qwertymo.kettuutils.core.chat.KettuChatClient;
import ru.qwertymo.kettuutils.core.binding.KeyBindings;
import ru.qwertymo.kettuutils.core.common.util.KeycodeUtil;
import ru.qwertymo.kettuutils.core.net.NetworkManager;

public class KettuUtilsClientProxy extends KettuUtilsCommonProxy {

    private static volatile KettuUtilsClientProxy _instance = new KettuUtilsClientProxy();

    public static KettuUtilsClientProxy getInstance(){
        return _instance;
    }

    private final NetworkManager network = new NetworkManager();

    public NetworkManager getNetwork(){
        return network;
    }

    public final KettuChatClient chat = new KettuChatClient();

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        super.preInit(event);
        new KeyBindings();
        KeycodeUtil.init();
    }
}
