package ru.qwertymo.kettuutils.core;

import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraftforge.common.MinecraftForge;

public class ChatOverlay {
    public ChatOverlay(){
        MinecraftForge.EVENT_BUS.register(new VanillaChatListener());
        FMLCommonHandler.instance().bus().register(new KettuChatListener());
        MinecraftForge.EVENT_BUS.register(new KettuRenderListener());
    }
}
