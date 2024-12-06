package ru.qwertymo.kettuutils.core.chat.view;

import net.minecraft.client.gui.GuiScreen;
import org.lwjgl.opengl.GL11;

public class ChatUI extends GuiScreen {
    public void drawChat(){
        GL11.glPushMatrix();
        GL11.glTranslatef(2.0F, 20.0F, 0.0F);
        GL11.glScalef(1, 1, 1.0F);
    }
}
