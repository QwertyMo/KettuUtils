package ru.qwertymo.kettuutils.proxy;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import ru.qwertymo.kettuutils.core.chat.KettuChat;
import ru.qwertymo.kettuutils.core.binding.KeyBindings;
import ru.qwertymo.kettuutils.core.common.util.KeycodeUtil;

public class KettuUtilsClientProxy extends CommonProxy {

    private static volatile KettuUtilsClientProxy _instance = new KettuUtilsClientProxy();

    public static KettuUtilsClientProxy getInstance(){
        return _instance;
    }

    public final KettuChat chat = new KettuChat();

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        super.preInit(event);
        new KeyBindings();
        new KettuChat();
        KeycodeUtil.init();
    }
}
