package ru.qwertymo.kettuutils.core;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.RenderGameOverlayEvent;

public class KettuChatListener {
    @SubscribeEvent
    public void onBindPress(InputEvent.KeyInputEvent event){
        System.out.println("пизда");
    }

}
