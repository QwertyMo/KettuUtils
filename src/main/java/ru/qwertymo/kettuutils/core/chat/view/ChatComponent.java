package ru.qwertymo.kettuutils.core.chat.view;

import com.mojang.authlib.GameProfile;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.client.renderer.texture.TextureManager;
import org.lwjgl.opengl.GL11;
import ru.qwertymo.kettuutils.KettuUtils;
import ru.qwertymo.kettuutils.core.common.render.TextRender;
import ru.qwertymo.kettuutils.core.common.render.UIRender;
import ru.qwertymo.kettuutils.core.common.render.URLImageRender;
import ru.qwertymo.kettuutils.core.model.VanillaChatMessage;
import net.minecraft.util.ResourceLocation;
import ru.qwertymo.kettuutils.reference.Reference;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import net.minecraft.client.gui.Gui;

import static java.lang.Math.abs;

public class ChatComponent {

    private final static int maxHeight = 600;
    private final static int chatWidth = 300;
    private final static int chatPosX = 10;
    private final static int chatPosY = 40;
    private final static int padding = 2;
    private final static int textAlpha = 255;
    private final static int backgroundAlpha = 96;

    private final static int inputWidth = 350;
    private final static int avatarSize = 16;
    private final static int avatarBorer = 1;

    private final static int messageTTL = 5000;
    private final static int messageFade = 3000;

    private static String combineString(VanillaChatMessage message){
        StringBuilder text = new StringBuilder();
        Date date = new Date(message.getTime());
        Format format = new SimpleDateFormat("HH:mm");
        text.append("[");
        text.append(format.format(date));
        text.append("] ");
        text.append(message.getText());
        return text.toString();
    }

    public static int drawChatLine(VanillaChatMessage message, int yPos) {
        String text = combineString(message);


        UIRender.drawSquare(
                chatPosX, chatPosY + yPos,
                chatWidth, TextRender.getTextHeight(text, chatWidth),
                new Color(0, 0, 0, backgroundAlpha)
        );
        return TextRender.drawTransferableText(
                chatPosX + padding, chatPosY + yPos, chatWidth, text,
                new Color(255, 255, 255, textAlpha)
        );

    }

    public static int drawFadeChatLine(VanillaChatMessage message, int yPos) {
        //Степень затухания
        float fade = 1;
        long currentTime = System.currentTimeMillis();
        long fading = currentTime - message.getTime() - messageTTL;
        if (currentTime - message.getTime() >= messageTTL) fade = 1 - abs((float) fading / (float) messageFade);
        if (fade <= 0.1) return 0;

        String text = combineString(message);
        UIRender.drawSquare(
                chatPosX, chatPosY + yPos,
                chatWidth, TextRender.getTextHeight(text, chatWidth),
                new Color(0, 0, 0, (int) (backgroundAlpha * (fade)))
        );
        TextRender.drawTransferableText(
                chatPosX + padding + avatarSize, chatPosY + yPos, chatWidth, text,
                new Color(255, 255, 255, (int) (textAlpha * (fade)))
        );
        UIRender.drawSquare(
                chatPosX - avatarBorer,
                chatPosY + yPos - avatarBorer,
                avatarSize + avatarBorer * 2,
                avatarSize + avatarBorer * 2,
                Color.GREEN);

        URLImageRender.drawPlayerFace(
                "QwertyMo",
                chatPosX,
                chatPosY + yPos,
                avatarSize, avatarSize);
        return TextRender.getTextHeight(text, chatWidth);
    }


    public static void drawInputField(String text){
        Minecraft mc = Minecraft.getMinecraft();
        UIRender.drawSquare(
                10, 10,
                inputWidth, mc.fontRenderer.FONT_HEIGHT + 4,
                new Color(0, 0, 0, (backgroundAlpha))
        );
        TextRender.drawTransferableText(
                10 + padding, 12, chatWidth, text + (((System.currentTimeMillis() / 500)%2==0) ? "":"_"),
                new Color(255, 255, 255, (textAlpha))
        );
    }

}
