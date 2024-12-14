package ru.qwertymo.kettuutils.core.chat.listener.client;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent;
import net.minecraft.client.Minecraft;
import ru.qwertymo.kettuutils.core.binding.KeyBindings;
import ru.qwertymo.kettuutils.core.chat.view.ChatGUI;
import ru.qwertymo.kettuutils.proxy.KettuUtilsClientProxy;

public class KettuChatListener {
    @SubscribeEvent
    public void onBindPress(InputEvent.KeyInputEvent event){
        KettuUtilsClientProxy ky = KettuUtilsClientProxy.getInstance();
        if(KeyBindings.CHAT_KEY.isPressed()){
            if(!ky.chat.isChatOpened()) {
                ky.chat.openChat();
                Minecraft.getMinecraft().displayGuiScreen(new ChatGUI());
            }
        }
    }
}
