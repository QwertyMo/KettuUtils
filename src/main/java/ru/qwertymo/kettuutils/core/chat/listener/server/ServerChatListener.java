package ru.qwertymo.kettuutils.core.chat.listener.server;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraftforge.event.ServerChatEvent;
import ru.qwertymo.kettuutils.core.net.ServerNetworking;
import ru.qwertymo.kettuutils.core.net.packet.ChatMessagePacket;

public class ServerChatListener {
//    @SideOnly(Side.SERVER)
//    @SubscribeEvent
//    public void chatEvent(ServerChatEvent event){
//        ChatMessagePacket packet = new ChatMessagePacket("ClientReqNickname",  "1");
//
//        ServerNetworking.network.sendTo(packet, event.player);
//        System.out.println("{server} сигнал отправлен");
//    }
}
