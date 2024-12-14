package ru.qwertymo.kettuutils.core.chat;

import net.minecraftforge.common.MinecraftForge;
import ru.qwertymo.kettuutils.core.chat.listener.server.ServerChatListener;

public class KettuChatServer {
    public KettuChatServer(){
        System.out.println("пихуй");
        MinecraftForge.EVENT_BUS.register(new ServerChatListener());
    }
}
