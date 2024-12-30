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
    private static int chatPosX = 10;
    private static int chatPosY = 40;
    private static int chatPosY2 = chatPosY;
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
        int amountLines = 1; // Изначальное количество строк
        int amountJumps = 1; // Изначальное смещение основного чата
        int maxWidth = inputWidth - padding * 2; // Максимальная ширина текста для одной строки
        StringBuilder visibleText = new StringBuilder();
        int currentWidth = 0;

        // Учитываем ширину текста и добавляем строки при необходимости
        for (char c : text.toCharArray()) {
            int charWidth = mc.fontRenderer.getCharWidth(c);

            if (currentWidth + charWidth > maxWidth) {
                // Добавляем вывод в консоль и увеличиваем количество строк
                System.out.println("Текст достиг конца строки, продолжаем перенос текста.");

                // Увеличиваем количество строк
                amountLines++;
                maxWidth = (inputWidth * amountLines) - padding * 2; // Расширяем ширину для новых строк

                // Добавляем символ в новую строку
                visibleText.append("\n");
                currentWidth = 0; // Сбрасываем текущую ширину для новой строки
            }

            visibleText.append(c);
            currentWidth += charWidth;
        }

        // Добавляем мигающий курсор
        if ((System.currentTimeMillis() / 500) % 2 == 0) {
            visibleText.append("_");
        }

        // Рассчитываем итоговую высоту ввода с учетом новых строк
        int inputHeight = (mc.fontRenderer.FONT_HEIGHT + 4) * amountLines + padding * 2;

        // Рисуем фон
        UIRender.drawSquare(
                10, 10,
                inputWidth, inputHeight,
                new Color(0, 0, 0, backgroundAlpha)
        );

        // Рисуем текст
        TextRender.drawText(10 + padding, 12, visibleText.toString(), new Color(255, 255, 255, textAlpha));

        // Перемещение основного чата вниз при достижении конца строки
        if (amountLines > 1 && amountJumps != amountLines) {
            amountJumps++;
            int offsetY = (amountLines - 1) * (mc.fontRenderer.FONT_HEIGHT + 4);  // Считаем, сколько строк было добавлено
            chatPosY2 += offsetY;  // Сдвигаем чат вниз
        }
    }
}
