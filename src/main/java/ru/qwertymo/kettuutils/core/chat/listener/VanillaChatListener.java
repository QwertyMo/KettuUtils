package ru.qwertymo.kettuutils.core.chat.listener;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.client.event.ClientChatReceivedEvent;

public class VanillaChatListener {
    @SubscribeEvent(priority = EventPriority.HIGHEST, receiveCanceled=true)
    public void chatVisibility(ClientChatReceivedEvent event){
        event.setCanceled(true);
    }
}
