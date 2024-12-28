package ru.qwertymo.kettuutils.core.chat.listener.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.GameSettings;

public class ChatTransparencyHandler {

    private Minecraft mc;
    private GameSettings settings;

    public ChatTransparencyHandler() {
        // Получаем доступ к Minecraft и его настройкам
        mc = Minecraft.getMinecraft();
        settings = mc.gameSettings;
    }

    public void setChatOpacity(float opacity) {
        if (settings != null) {
            // Устанавливаем прозрачность чата в диапазоне от 0.0 до 1.0
            float opacityValue = Math.max(0.0f, Math.min(1.0f, opacity));
            settings.chatOpacity = opacityValue;

            // Сохраняем настройки, чтобы они не потерялись
            settings.saveOptions();

            // Применяем изменения (необходимо обновить интерфейс, чтобы изменения вступили в силу)
            mc.ingameGUI.getChatGUI().clearChatMessages(); // Очистка чата (по желанию)
        }
    }
}
