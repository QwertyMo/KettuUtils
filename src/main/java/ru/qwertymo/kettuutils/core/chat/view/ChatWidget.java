package ru.qwertymo.kettuutils.core.chat.view;

import ru.qwertymo.kettuutils.core.model.VanillaChatMessage;
import ru.qwertymo.kettuutils.proxy.KettuUtilsClientProxy;
import java.util.ArrayList;

public class ChatWidget {

    public void drawChat(){
        KettuUtilsClientProxy ku = KettuUtilsClientProxy.getInstance();
        ArrayList<VanillaChatMessage> msg = ku.chat.getMessages();
        int pos = 0;
        for(int i = msg.size()-1; i>=0;i--){
            if(!ku.chat.isChatOpened())pos += ChatComponent.drawFadeChatLine(msg.get(i),pos);
        }
    }
}
