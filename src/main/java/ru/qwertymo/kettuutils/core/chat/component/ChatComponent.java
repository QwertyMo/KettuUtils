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
import static java.lang.Math.max;

public class ChatComponent {

    private final static int chatWidth = 300;
    private final static int chatPosX = 10;
    private final static int chatPosY = 40;
    private final static int padding = 2;

    private final static int textAlpha = 255;
    private final static int backgroundAlpha = 96;
    private final static int borderAlpha = 192;

    private final static int inputWidth = 300;
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
        float f = 1.0f;
        if (fade) {
            long currentTime = System.currentTimeMillis();
            long fading = currentTime - message.getTime() - messageTTL;
            if (currentTime - message.getTime() >= messageTTL) {
                f = 1 - Math.abs((float) fading / (float) messageFade);
            }
            if (f <= 0.1) return 0;
        }

        String text = combineString(message);
        int textHeight = TextRender.getTextHeight(text, chatWidth);
        int blockHeight = Math.max(textHeight, maxChatBlockHeight);

        UIRender.drawSquare(
                chatPosX, chatPosY + yPos,
                chatWidth, blockHeight,
                new Color(0, 0, 0, (int) (backgroundAlpha * f))
        );

        TextRender.drawTransferableText(
                avatarSize + chatPosX + padding,
                chatPosY + yPos,
                chatWidth,
                text,
                new Color(255, 255, 255, (int) (textAlpha * f))
        );

        UIRender.drawSquare(
                chatPosX + padding,
                chatPosY + blockHeight - avatarSize - padding * 2 + yPos,
                avatarSize + avatarBorer * 2,
                avatarSize + avatarBorer * 2,
                new Color(255, 128, 0, (int) (borderAlpha * f))
        );

        URLImageRender.drawPlayerFace(
                "QwertyMo",
                chatPosX + padding + avatarBorer,
                chatPosY + blockHeight - avatarSize - padding * 2 + yPos + avatarBorer,
                avatarSize, avatarSize
        );

        return blockHeight;
    }

    public static int calculateInputFieldHeight(String text, int maxWidth) {
        int textHeight = TextRender.getTextHeight(text, maxWidth);
        return textHeight + padding * 2; // Учитываем отступы
    }


    public static void drawInputField(String text) {
        Minecraft mc = Minecraft.getMinecraft();
        int amountLines = 1; // изначальное кол-во строк
        int maxWidth = (inputWidth * amountLines) - padding * 2; // Максимальная ширина текста
        StringBuilder visibleText = new StringBuilder();
        int currentWidth = 0;

        // Учитываем ширину текста, чтобы не выходить за пределы поля
        for (char c : text.toCharArray()) {
            int charWidth = mc.fontRenderer.getCharWidth(c);

            if (currentWidth + charWidth > maxWidth) {
                // Добавляем вывод в консоль
                System.out.println("Текст достиг конца строки, продолжаем перенос текста.");

                break; // Прекращаем добавление текста, если он выходит за пределы поля
            }

            visibleText.append(c);
            currentWidth += charWidth;
        }

        // Добавляем мигающий курсор
        if ((System.currentTimeMillis() / 500) % 2 == 0) {
            visibleText.append("_");
        }

        // Рисуем фон
        UIRender.drawSquare(
                10, 10,
                inputWidth, mc.fontRenderer.FONT_HEIGHT + 4,
                new Color(0, 0, 0, backgroundAlpha)
        );

        // Рисуем текст
        TextRender.drawText(10 + padding, 12, visibleText.toString(), new Color(255, 255, 255, textAlpha));
    }

}
