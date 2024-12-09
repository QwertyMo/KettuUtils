package ru.qwertymo.kettuutils.core.chat;

import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraftforge.common.MinecraftForge;
import ru.qwertymo.kettuutils.core.chat.listener.KettuChatListener;
import ru.qwertymo.kettuutils.core.chat.listener.KettuRenderListener;
import ru.qwertymo.kettuutils.core.chat.listener.VanillaChatListener;
import ru.qwertymo.kettuutils.core.chat.view.ChatWidget;
import ru.qwertymo.kettuutils.core.model.VanillaChatMessage;

import java.util.ArrayList;

public class KettuChat {
    private ArrayList<VanillaChatMessage> _messages = new ArrayList<>();
    private ChatWidget _chatView = new ChatWidget();
    private boolean _isChatOpened = false;

    public KettuChat(){
        addMessage(new VanillaChatMessage("Пезда хуй член"));

        MinecraftForge.EVENT_BUS.register(new VanillaChatListener());
        FMLCommonHandler.instance().bus().register(new KettuChatListener());
        MinecraftForge.EVENT_BUS.register(new KettuRenderListener());
    }

    public void addMessage(VanillaChatMessage message){
        _messages.add(message);
    }

    public ChatWidget getChatView(){
        return _chatView;
    }

    public ArrayList<VanillaChatMessage> getMessages(){
        return _messages;
    }

    public void openChat(){
        _isChatOpened = true;
    }

    public void closeChat(){
        _isChatOpened = false;
    }

    public boolean isChatOpened(){
        return _isChatOpened;
    }
}
