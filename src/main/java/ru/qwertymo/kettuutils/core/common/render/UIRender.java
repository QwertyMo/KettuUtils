package ru.qwertymo.kettuutils.core.common.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import ru.qwertymo.kettuutils.core.common.util.PosUtil;

import java.awt.*;


public class UIRender {
    public static void drawSquare(int x, int y, int width, int height, Color color){
        y = PosUtil.invertY(y);
        Gui.drawRect(
                (x),
                (y),
                (x + width),
                (y - height),
                TextRender.getIntFromColor(color));
    }
}
