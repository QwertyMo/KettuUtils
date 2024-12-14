package ru.qwertymo.kettuutils.core.chat.view;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import org.lwjgl.opengl.GL11;
import ru.qwertymo.kettuutils.core.chat.component.ChatComponent;
import ru.qwertymo.kettuutils.core.model.VanillaChatMessage;
import ru.qwertymo.kettuutils.proxy.KettuUtilsClientProxy;
import java.util.ArrayList;

public class ChatWidget {

    private final static int maxHeight = 200;
    public void drawChat(){
        Minecraft mc = Minecraft.getMinecraft();
        ScaledResolution sr = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);
        float scale = (float) sr.getScaledHeight() / mc.displayHeight;
        KettuUtilsClientProxy ku = KettuUtilsClientProxy.getInstance();
        ArrayList<VanillaChatMessage> msg = ku.chat.getMessages();
        int pos = 0;
        // Включаем тест обрезки
        GL11.glEnable(GL11.GL_SCISSOR_TEST);

        // Устанавливаем область обрезки
        GL11.glScissor(0,0, Minecraft.getMinecraft().displayWidth, (int)(maxHeight/scale));
        for(int i = msg.size()-1; i>=0;i--){
            if(!ku.chat.isChatOpened())pos += ChatComponent.drawChatLine(msg.get(i),pos,true);
        }
        // Отключаем тест обрезки
        GL11.glDisable(GL11.GL_SCISSOR_TEST);
    }
}
