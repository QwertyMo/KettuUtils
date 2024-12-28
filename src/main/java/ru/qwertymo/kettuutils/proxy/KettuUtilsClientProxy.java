package ru.qwertymo.kettuutils.proxy;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.common.MinecraftForge;
import ru.qwertymo.kettuutils.core.chat.KettuChatClient;
import ru.qwertymo.kettuutils.core.binding.KeyBindings;
import ru.qwertymo.kettuutils.core.common.util.KeycodeUtil;

public class KettuUtilsClientProxy extends KettuUtilsCommonProxy {

    private static volatile KettuUtilsClientProxy _instance = new KettuUtilsClientProxy();

    public static KettuUtilsClientProxy getInstance(){
        return _instance;
    }

    public final KettuChatClient chat = new KettuChatClient();

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        super.preInit(event);
        new KeyBindings();
        KeycodeUtil.init();
    }

    @Override
    public void init(FMLInitializationEvent event) {
        super.init(event);
    }
}
