package ru.qwertymo.kettuutils.core.chat.listener;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;

public class KettuRenderListener{

    @SubscribeEvent
    public void renderOverlay(RenderGameOverlayEvent event){
        System.out.println("fghgh");
    }
}
