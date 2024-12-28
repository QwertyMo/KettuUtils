package ru.qwertymo.kettuutils.core.chat.listener.client;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.network.FMLNetworkEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S02PacketChat;
import net.minecraft.util.IChatComponent;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import ru.qwertymo.kettuutils.core.model.VanillaChatMessage;
import ru.qwertymo.kettuutils.proxy.KettuUtilsClientProxy;

public class VanillaChatListener {
    @SubscribeEvent
    public void chatVisibility(ClientChatReceivedEvent event){
        event.setCanceled(true);
        Minecraft.getMinecraft().ingameGUI.getChatGUI().clearChatMessages();
        KettuUtilsClientProxy ky = KettuUtilsClientProxy.getInstance();
        ky.chat.addMessage(new VanillaChatMessage(event.message.getFormattedText()));
    }
}
