package ru.qwertymo.kettuutils.core.common.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import org.lwjgl.opengl.GL11;
import ru.qwertymo.kettuutils.core.common.util.PosUtil;

import java.awt.*;

public class TextRender {
    public static int getIntFromColor(Color color) {
        int A = (color.getAlpha() << 24) & 0xFF000000;
        int R = (color.getRed() << 16) & 0x00FF0000;
        int G = (color.getGreen() << 8) & 0x0000FF00;
        int B = color.getBlue() & 0x000000FF;
        return A | R | G | B;
    }

    public static int drawText(int x, int y, String text, Color color) {
        Minecraft mc = Minecraft.getMinecraft();
        y = PosUtil.invertY(y);
        GL11.glEnable(GL11.GL_BLEND);
        mc.fontRenderer.drawStringWithShadow(
                text,
                x,
                y - mc.fontRenderer.FONT_HEIGHT,
                getIntFromColor(color)
        );
        GL11.glDisable(GL11.GL_BLEND);
        return mc.fontRenderer.FONT_HEIGHT;
    }

    public static int drawTransferableText(int x, int y, int maxWidth, String text, Color color) {
        Minecraft mc = Minecraft.getMinecraft();
        int fontHeight = mc.fontRenderer.FONT_HEIGHT;
        int lineHeight = fontHeight + 2; // Учитываем дополнительные отступы для шрифта
        int currentWidth = 0;
        StringBuilder currentLine = new StringBuilder();
        int lines = 0;

        for (char c : text.toCharArray()) {
            int charWidth = mc.fontRenderer.getCharWidth(c) + 1; // Учитываем межсимвольное расстояние

            if (currentWidth + charWidth > maxWidth) {
                // Перенос строки
                drawText(x, y + (lines * lineHeight), currentLine.toString(), color);
                currentLine = new StringBuilder();
                currentWidth = 0;
                lines++;
            }

            currentLine.append(c);
            currentWidth += charWidth;
        }

        // Рисуем оставшуюся строку
        if (currentLine.length() > 0) {
            drawText(x, y + (lines * lineHeight), currentLine.toString(), color);
            lines++;
        }

        return lines * lineHeight;
    }

    public static int getTextHeight(String text, int maxWidth) {
        Minecraft mc = Minecraft.getMinecraft();
        int fontHeight = mc.fontRenderer.FONT_HEIGHT;
        int lineHeight = fontHeight + 2; // Учитываем дополнительные отступы
        int currentWidth = 0;
        int lines = 1;

        for (char c : text.toCharArray()) {
            int charWidth = mc.fontRenderer.getCharWidth(c) + 1;

            if (currentWidth + charWidth > maxWidth) {
                currentWidth = 0;
                lines++;
            }

            currentWidth += charWidth;
        }

        return lines * lineHeight;
    }
}
