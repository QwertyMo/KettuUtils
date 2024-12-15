package ru.qwertymo.kettuutils.core.chat.listener.client;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import ru.qwertymo.kettuutils.core.model.VanillaChatMessage;
import ru.qwertymo.kettuutils.proxy.KettuUtilsClientProxy;

public class VanillaChatListener {
    @SubscribeEvent
    public void chatVisibility(ClientChatReceivedEvent event){
        KettuUtilsClientProxy ky = KettuUtilsClientProxy.getInstance();
        ky.chat.addMessage(new VanillaChatMessage(event.message.getUnformattedText()));

    }
}
