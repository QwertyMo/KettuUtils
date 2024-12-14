package ru.qwertymo.kettuutils.core.common.component;

import cpw.mods.fml.client.config.GuiButtonExt;
import cpw.mods.fml.client.config.GuiUtils;
import net.minecraft.client.Minecraft;
import ru.qwertymo.kettuutils.core.common.render.UIRender;
import ru.qwertymo.kettuutils.core.common.util.PosUtil;

import java.awt.*;

public class KettuButton extends GuiButtonExt {

    private Color _buttonColor = new Color(0,0,0);
    private Color _textColor = new Color(255,255,255);

    public void setButtonColor(Color color){
        _buttonColor = color;
    }

    public void setTextColor(Color color) {
        _textColor = color;
    }

    public KettuButton(int id, int xPos, int yPos, String displayString) {
        super(id, xPos, yPos, displayString);
    }

    public KettuButton(int id, int xPos, int yPos, int width, int height, String displayString) {
        super(id, xPos, yPos, width, height, displayString);
    }
    public void drawButton(Minecraft mc, int mouseX, int mouseY) {
        if (this.visible) {
            if(hovered(mouseX, mouseY)) onHover();
            else onNormal();

            String buttonText = this.displayString;
            int strWidth = mc.fontRenderer.getStringWidth(buttonText);
            int ellipsisWidth = mc.fontRenderer.getStringWidth("...");
            if (strWidth > this.width - 6 && strWidth > ellipsisWidth) {
                buttonText = mc.fontRenderer.trimStringToWidth(buttonText, this.width - 6 - ellipsisWidth).trim() + "...";
            }

            this.drawCenteredString(
                    mc.fontRenderer,
                    buttonText,
                    this.xPosition + this.width / 2,
                    PosUtil.invertY(this.yPosition) - (this.height+ mc.fontRenderer.FONT_HEIGHT)/2,
                    _textColor.getRGB()
            );
        }
    }

    private boolean hovered(int mouseX, int mouseY){
        mouseY = PosUtil.invertY(mouseY);
        return mouseX >= this.xPosition && mouseY >= this.yPosition && mouseX < this.xPosition + this.width && mouseY < this.yPosition + this.height;
    }

    public void onHover(){
        UIRender.drawSquare(this.xPosition, this.yPosition, this.width, this.height, new Color(_buttonColor.getRed(),_buttonColor.getGreen(),_buttonColor.getBlue(),192));
    }

    public void onClicked(){
        UIRender.drawSquare(this.xPosition, this.yPosition, this.width, this.height, new Color(_buttonColor.getRed(),_buttonColor.getGreen(),_buttonColor.getBlue(),128));
    }

    public void onNormal(){
        UIRender.drawSquare(this.xPosition, this.yPosition, this.width, this.height, new Color(_buttonColor.getRed(),_buttonColor.getGreen(),_buttonColor.getBlue(),64));
    }
}
