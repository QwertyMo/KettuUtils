package ru.qwertymo.kettuutils.core.chat.view;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import org.lwjgl.Sys;
import org.lwjgl.opengl.GL11;
import ru.qwertymo.kettuutils.core.chat.component.ChatComponent;
import ru.qwertymo.kettuutils.core.common.component.KettuButton;
import ru.qwertymo.kettuutils.core.common.util.KeycodeUtil;
import ru.qwertymo.kettuutils.core.model.VanillaChatMessage;
import ru.qwertymo.kettuutils.proxy.KettuUtilsClientProxy;

import java.awt.*;
import java.util.ArrayList;

public class ChatGUI extends GuiScreen {
    private final static int maxHeight = 200;

    KettuUtilsClientProxy ku = KettuUtilsClientProxy.getInstance();
    private String text = "";
    private KettuButton buttonGlobal;
    private KettuButton buttonLocal;
    private KettuButton buttonTrade;
    private KettuButton buttonPersonal;

    @Override
    public void initGui() {
        buttonGlobal = new KettuButton(0, 10, maxHeight + 10, 40,10, "Global");
        buttonGlobal.setButtonColor(new Color(159, 25, 25));
        buttonLocal = new KettuButton(0, 55, maxHeight + 10, 40,10, "Local");
        buttonLocal.setButtonColor(new Color(119, 33, 202));
        buttonTrade = new KettuButton(0, 100, maxHeight + 10, 40,10, "Trade");
        buttonTrade.setButtonColor(new Color(109, 227, 31));
        buttonPersonal = new KettuButton(0, 145, maxHeight + 10, 60,10, "Personal");
        buttonPersonal.setButtonColor(new Color(255, 193, 39));
        buttonList.add(buttonGlobal); // Добавление кнопки
        buttonList.add(buttonLocal); // Добавление кнопки
        buttonList.add(buttonTrade); // Добавление кнопки
        buttonList.add(buttonPersonal); // Добавление кнопки
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

        Minecraft mc = Minecraft.getMinecraft();
        ScaledResolution sr = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);
        float scale = (float) sr.getScaledHeight() / mc.displayHeight;
        ArrayList<VanillaChatMessage> msg = ku.chat.getMessages();
        int pos = 0;
        // Включаем тест обрезки
        GL11.glEnable(GL11.GL_SCISSOR_TEST);

        // Устанавливаем область обрезки
        GL11.glScissor(0,0,Minecraft.getMinecraft().displayWidth, (int)(maxHeight/scale));
        for(int k = msg.size()-1; k>=0;k--){
            pos += ChatComponent.drawChatLine(msg.get(k),pos, false);
        }
        ChatComponent.drawInputField(text);
        // Отключаем тест обрезки
        GL11.glDisable(GL11.GL_SCISSOR_TEST);
        // Отрисовка кнопок
        super.drawScreen(i, j, f);
    }

    @Override
    public void onGuiClosed() {
        super.onGuiClosed();
        ku.chat.closeChat();
    }
}
