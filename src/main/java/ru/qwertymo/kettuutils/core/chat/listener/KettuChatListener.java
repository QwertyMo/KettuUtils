package ru.qwertymo.kettuutils.core.chat.listener;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent;

public class KettuChatListener {
    @SubscribeEvent
    public void onBindPress(InputEvent.KeyInputEvent event){
        System.out.println("пизда");
    }

}
