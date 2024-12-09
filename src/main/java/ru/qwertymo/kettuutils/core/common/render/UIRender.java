package ru.qwertymo.kettuutils.core.common.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;

import java.awt.*;


public class UIRender {
    public static void drawSquare(int x, int y, int width, int height, Color color){
        Minecraft mc = Minecraft.getMinecraft();
        ScaledResolution sr = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);

        Gui.drawRect(
                (x),
                (sr.getScaledHeight() - y),
                (x + width),
                (sr.getScaledHeight() - y - height),
                TextRender.getIntFromColor(color));
    }
}
