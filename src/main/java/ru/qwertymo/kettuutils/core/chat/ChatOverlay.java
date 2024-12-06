package ru.qwertymo.kettuutils.core.chat;

import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraftforge.common.MinecraftForge;
import ru.qwertymo.kettuutils.core.chat.listener.KettuChatListener;
import ru.qwertymo.kettuutils.core.chat.listener.KettuRenderListener;
import ru.qwertymo.kettuutils.core.chat.listener.VanillaChatListener;

public class ChatOverlay {
    public ChatOverlay(){
        MinecraftForge.EVENT_BUS.register(new VanillaChatListener());
        FMLCommonHandler.instance().bus().register(new KettuChatListener());
        MinecraftForge.EVENT_BUS.register(new KettuRenderListener());
    }
}
