package ru.qwertymo.kettuutils.core.chat.component;

import net.minecraft.client.Minecraft;
import ru.qwertymo.kettuutils.core.common.render.TextRender;
import ru.qwertymo.kettuutils.core.common.render.UIRender;
import ru.qwertymo.kettuutils.core.common.render.URLImageRender;
import ru.qwertymo.kettuutils.core.model.VanillaChatMessage;

import java.awt.*;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

import static java.lang.Math.abs;

public class ChatComponent {

    private final static int chatWidth = 300;
    private final static int chatPosX = 10;
    private final static int chatPosY = 40;
    private final static int padding = 2;

    private final static int textAlpha = 255;
    private final static int backgroundAlpha = 96;
    private final static int borderAlpha = 192;

    private final static int inputWidth = 350;
    private final static int avatarSize = 16;
    private final static int avatarBorer = 1;

    private final static int messageTTL = 5000;
    private final static int messageFade = 3000;

    private final static int maxChatBlockHeight = avatarSize + (avatarBorer * 2) + (padding * 2);

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

    public static int drawChatLine(VanillaChatMessage message, int yPos, boolean fade) {
        //Степень затухания
        float f = 1;
        if(fade) {
            long currentTime = System.currentTimeMillis();
            long fading = currentTime - message.getTime() - messageTTL;
            if (currentTime - message.getTime() >= messageTTL) f = 1 - abs((float) fading / (float) messageFade);
            if (f <= 0.1) return 0;
        }
        String text = combineString(message);

        int h = TextRender.getTextHeight(text, chatWidth);
        if(h<maxChatBlockHeight) h = maxChatBlockHeight;
        UIRender.drawSquare(
                chatPosX, chatPosY + yPos,
                chatWidth, h,
                new Color(0, 0, 0, (int) (backgroundAlpha * f))
        );
        TextRender.drawTransferableText(
                avatarSize + chatPosX + padding, chatPosY + yPos, chatWidth, text,
                new Color(255, 255, 255, (int) (textAlpha * f))
        );
        UIRender.drawSquare(
                chatPosX + padding,
                chatPosY + h - (padding * 2) + yPos - avatarSize,
                avatarSize + avatarBorer * 2,
                avatarSize + avatarBorer * 2,
                new Color(255, 128,0, (int) (borderAlpha * f))); //TODO: Поменять на цвет вкладки чата
        URLImageRender.drawPlayerFace(
                "QwertyMo", //TODO: Сделать биндинг к автору сообщения
                chatPosX + padding + avatarBorer,
                chatPosY + h - (padding * 2) + yPos - avatarSize + avatarBorer,
                avatarSize, avatarSize);
        return h;
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
