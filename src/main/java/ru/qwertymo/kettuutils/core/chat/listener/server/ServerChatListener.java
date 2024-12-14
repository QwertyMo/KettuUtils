package ru.qwertymo.kettuutils.core.chat.listener.server;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.event.ServerChatEvent;

public class ServerChatListener {
    @SubscribeEvent
    public void chatEvent(ServerChatEvent event){
        System.out.println("пезда");
        event.setCanceled(true);
    }
}
