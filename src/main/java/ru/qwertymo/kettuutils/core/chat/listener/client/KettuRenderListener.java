package ru.qwertymo.kettuutils.core.chat.listener.client;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import ru.qwertymo.kettuutils.core.chat.view.ChatWidget;


public class KettuRenderListener {

    @SubscribeEvent
    public void renderOverlay(RenderGameOverlayEvent.Text event){
        if(event.type == RenderGameOverlayEvent.ElementType.TEXT){
            new ChatWidget().drawChat();
            event.setCanceled(true);
        }

    }
}
