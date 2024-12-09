package ru.qwertymo.kettuutils.core.common.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.lwjgl.opengl.GL11;

public class URLImageRender {
    private static Map<String, ResourceLocation> textureCache = new HashMap<>();
    private static Map<String, BufferedImage> imageCache = new HashMap<>();


    private static void loadImageFromURL(String urlString) {
        // Проверяем, загружено ли изображение
        if (textureCache.containsKey(urlString)) {
            System.out.println("Texture " + urlString + " is already loaded.");
            return; // Если текстура уже загружена, выходим из метода
        }

        try {
            URL url = new URL(urlString);
            InputStream is = url.openStream();
            BufferedImage image = ImageIO.read(is);
            is.close();

            // Сохраняем изображение в кэше
            imageCache.put(urlString, image);

            // Создаем динамическую текстуру из BufferedImage
            DynamicTexture dynamicTexture = new DynamicTexture(image);
            ResourceLocation textureLocation = Minecraft.getMinecraft().getTextureManager().getDynamicTextureLocation(urlString, dynamicTexture);
            textureCache.put(urlString, textureLocation);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void drawTexture(String textureName, int x, int y) {
        loadImageFromURL(textureName);
        ResourceLocation textureLocation = textureCache.get(textureName);
        if (textureLocation != null) {
            // Привязываем текстуру
            Minecraft.getMinecraft().getTextureManager().bindTexture(textureLocation);
            // Отрисовываем текстуру
            BufferedImage image = imageCache.get(textureName);
            drawTexturedModalRect(x, y, 0, 0, image.getWidth(), image.getHeight());
        }
    }

    private static void drawTexturedModalRect(int x, int y, int u, int v, int width, int height) {
        // Настройка OpenGL
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F); // Устанавливаем цвет (белый)

        // Начинаем отрисовку
        GL11.glBegin(GL11.GL_QUADS);
        {
            // Указываем координаты текстуры и вершин
            GL11.glTexCoord2f(u / (float) width, v / (float) height);
            GL11.glVertex2f(x, y);

            GL11.glTexCoord2f(u / (float) width, (v + height) / (float) height);
            GL11.glVertex2f(x, y + height);

            GL11.glTexCoord2f((u + width) / (float) width, (v + height) / (float) height);
            GL11.glVertex2f(x + width, y + height);

            GL11.glTexCoord2f((u + width) / (float) width, v / (float) height);
            GL11.glVertex2f(x + width, y);
        }
        GL11.glEnd();

        // Отключаем текстуры
        GL11.glDisable(GL11.GL_TEXTURE_2D);
    }


}
