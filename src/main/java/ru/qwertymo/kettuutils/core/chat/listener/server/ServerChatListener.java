package ru.qwertymo.kettuutils.core.chat.listener.server;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.event.ServerChatEvent;

public class ServerChatListener {
    @SubscribeEvent
    public void chatEvent(ServerChatEvent event){
        System.out.println("пезда");
        event.setCanceled(true);
    }
}
