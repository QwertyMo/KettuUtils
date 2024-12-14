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

    public static int drawText(int x, int y, String text, Color color){
        Minecraft mc = Minecraft.getMinecraft();
        y = PosUtil.invertY(y);
        GL11.glEnable(3042);
        mc.fontRenderer.drawStringWithShadow(
                text,
                (x),
                y - mc.fontRenderer.FONT_HEIGHT,
                getIntFromColor(color)
        );
        GL11.glDisable(3008);
        return mc.fontRenderer.FONT_HEIGHT;
    }

    public static int drawTransferableText(int x, int y, int maxWidth, String text, Color color){
        Minecraft mc = Minecraft.getMinecraft();
        int fontHeight = mc.fontRenderer.FONT_HEIGHT;

        if(mc.fontRenderer.getStringWidth(text)>maxWidth){
            int lines = (int) Math.ceil((double)mc.fontRenderer.getStringWidth(text)/(double)maxWidth);
            int chars = text.length()/lines;
            for(int i = 0; i<lines;i++){
                drawText(
                        x,
                        y + (fontHeight*(lines - i - 1)),
                        (i+1!=lines)?
                                text.substring(chars * i,chars * (i+1)):
                                text.substring(chars * i),
                        color);
            }
            return fontHeight * lines;
        }else return drawText(x,y,text,color);

    }

    public static int getTextHeight(String text, int maxWidth){
        Minecraft mc = Minecraft.getMinecraft();
        ScaledResolution sr = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);
        float scale = (float) sr.getScaledHeight() / mc.displayHeight;
        return (int) Math.ceil(
                (double)mc.fontRenderer.getStringWidth(text)/(double)(maxWidth*scale)
        ) * mc.fontRenderer.FONT_HEIGHT;
    }

    public static int drawTransferableText(int x, int y, int maxWidth, String text){
        return drawTransferableText(x,y,maxWidth,text,Color.WHITE);

    }

    public static int drawText(int x, int y, String text){
        return drawText(x, y, text, Color.WHITE);
    }
}
