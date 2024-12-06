package ru.qwertymo.kettuutils.core;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;

public class VanillaChatListener {
    @SubscribeEvent(priority = EventPriority.HIGHEST, receiveCanceled=true)
    public void chatVisibility(ClientChatReceivedEvent event){
        event.setCanceled(true);
    }
}
