package ru.qwertymo.kettuutils.core.chat.view;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import ru.qwertymo.kettuutils.core.common.util.KeycodeUtil;
import ru.qwertymo.kettuutils.core.model.VanillaChatMessage;
import ru.qwertymo.kettuutils.proxy.KettuUtilsClientProxy;

import java.util.ArrayList;

public class ChatGUI extends GuiScreen {


    KettuUtilsClientProxy ku = KettuUtilsClientProxy.getInstance();
    private String text = "";

    @Override
    public void initGui() {

    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }

    @Override
    protected void keyTyped(char c, int keyCode) {
        super.keyTyped(c, keyCode);
        switch (keyCode){
            case 14:
                if(!text.isEmpty())
                    text = text.substring(0,text.length()-1);
                break;
            case 28:
                this.mc.displayGuiScreen(null);
                this.mc.setIngameFocus();
                Minecraft.getMinecraft().thePlayer.sendChatMessage(text);
            default:
                if(KeycodeUtil.getCharKeys().contains(keyCode)) text +=c;
                break;
        }
    }

    @Override
    public void drawScreen(int i, int j, float f) {
        ArrayList<VanillaChatMessage> msg = ku.chat.getMessages();
        int pos = 0;
        for(int k = msg.size()-1; k>=0;k--){
            pos += ChatComponent.drawChatLine(msg.get(k),pos);
        }

        ChatComponent.drawInputField(text);
    }

    @Override
    public void onGuiClosed() {
        super.onGuiClosed();
        ku.chat.closeChat();
    }
}
