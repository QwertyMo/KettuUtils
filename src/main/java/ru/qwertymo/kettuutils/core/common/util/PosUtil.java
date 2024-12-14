package ru.qwertymo.kettuutils.core.common.util;

/*
Поскольку я дебил, и пришел в моды с багажом знаний мобильной разработки и прочей хуйни,
то у меня отсчет по Y идет не сверху вниз, как в OpenGL, а снизу вверх, как у нормальных
людей. Поэтому почти везде координата по Y будет инвертироваться, и что-бы не считать
всё самому, то тут будет небольшая хуйня для инвертирования моей хуйни в вашу хуйню
 */

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;

public class PosUtil {
    public static int invertY(int Y) {
        Minecraft mc = Minecraft.getMinecraft();
        ScaledResolution sr = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);
        return sr.getScaledHeight() - Y;
    }
}
