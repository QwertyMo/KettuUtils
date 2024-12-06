package ru.qwertymo.kettuutils.proxy;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import ru.qwertymo.kettuutils.core.chat.ChatOverlay;
import ru.qwertymo.kettuutils.core.binding.KeyBindings;

public class ClientProxy extends CommonProxy {
    @Override
    public void preInit(FMLPreInitializationEvent event) {
        super.preInit(event);
        new KeyBindings();
        new ChatOverlay();
    }
}
