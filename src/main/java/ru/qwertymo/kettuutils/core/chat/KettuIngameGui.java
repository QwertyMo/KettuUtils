package ru.qwertymo.kettuutils.core.chat;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.GuiNewChat;

public class KettuIngameGui extends GuiIngame {
    public KettuIngameGui(Minecraft p_i1036_1_) {
        super(p_i1036_1_);
    }

    @Override
    public GuiNewChat getChatGUI() {
        return null;
    }
}
