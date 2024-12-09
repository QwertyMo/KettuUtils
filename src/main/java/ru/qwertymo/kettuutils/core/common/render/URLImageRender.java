package ru.qwertymo.kettuutils.core.common.render;

import com.google.gson.JsonObject;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.util.ResourceLocation;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import com.google.gson.JsonParser;

import org.apache.commons.io.IOUtils;
import org.lwjgl.opengl.GL11;
import ru.qwertymo.kettuutils.core.common.util.MojangApiUtil;

public class URLImageRender {
    private static Map<String, ResourceLocation> textureCache = new HashMap<>();
    private static Map<String, BufferedImage> imageCache = new HashMap<>();
    private static ExecutorService executorService = Executors.newFixedThreadPool(2); // Создаем пул потоков
    private static ArrayList<String> loading = new ArrayList<>();

    private static void loadImageFromURL(String urlString) {
        // Проверяем, загружено ли изображение
        if (textureCache.containsKey(urlString) || loading.contains(urlString)) return;

        executorService.submit(() -> { // Запускаем загрузку в отдельном потоке
            try {
                loading.add(urlString);
                URL url = new URL(urlString);
                InputStream is = url.openStream();
                BufferedImage image = ImageIO.read(is);
                is.close();
                imageCache.put(urlString, image);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private static void loadPlayerSkin(String name) {
        // Проверяем, загружено ли изображение
        if (textureCache.containsKey(name) || loading.contains(name)) return;

        executorService.submit(() -> { // Запускаем загрузку в отдельном потоке
            try {
                loading.add(name);
                URL url = new URL(MojangApiUtil.getPlayerSkinURL(MojangApiUtil.getPlayerUUID(name)));
                InputStream is = url.openStream();
                BufferedImage image = ImageIO.read(is);
                is.close();
                imageCache.put(name, image);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private static void loadImages(){
        for(Map.Entry<String, BufferedImage> image: imageCache.entrySet()){
            DynamicTexture dynamicTexture = new DynamicTexture(image.getValue());
            ResourceLocation textureLocation = Minecraft.getMinecraft().getTextureManager().getDynamicTextureLocation(image.getKey(), dynamicTexture);

            textureCache.put(image.getKey(), textureLocation);
            loading.remove(image.getKey());
        }
        imageCache.clear();
    }

    private static void drawTexturedModalRect(int x, int y, float  u, float  v, int width, int height) {

        Minecraft mc = Minecraft.getMinecraft();
        ScaledResolution sr = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);

        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F); // Устанавливаем цвет (белый)

        GL11.glBegin(GL11.GL_QUADS);{
            GL11.glTexCoord2f(u / (float) width, v / (float) height);
            GL11.glVertex2f(x, sr.getScaledHeight() - y - height);

            GL11.glTexCoord2f(u / (float) width, (v + height) / (float) height);
            GL11.glVertex2f(x, sr.getScaledHeight() - y);

            GL11.glTexCoord2f((u + width) / (float) width, (v + height) / (float) height);
            GL11.glVertex2f(x + width, sr.getScaledHeight() - y);

            GL11.glTexCoord2f((u + width) / (float) width, v / (float) height);
            GL11.glVertex2f(x + width, sr.getScaledHeight() - y - height);
        }
        GL11.glEnd();

        GL11.glDisable(GL11.GL_TEXTURE_2D);
    }

    private static void drawTextureFace(int x, int y, int width, int height) {
        int u = 2;
        int v = 2;
        Minecraft mc = Minecraft.getMinecraft();
        ScaledResolution sr = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);

        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F); // Устанавливаем цвет (белый)

        GL11.glBegin(GL11.GL_QUADS);{
            GL11.glTexCoord2f(u / (float) width, v / (float) height);
            GL11.glVertex2f(x, sr.getScaledHeight() - y - height);

            GL11.glTexCoord2f(u / (float) width, (v + 2) / (float) height);
            GL11.glVertex2f(x, sr.getScaledHeight() - y);

            GL11.glTexCoord2f((u + 2) / (float) width, (v + 2) / (float) height);
            GL11.glVertex2f(x + width, sr.getScaledHeight() - y);

            GL11.glTexCoord2f((u + 2) / (float) width, v / (float) height);
            GL11.glVertex2f(x + width, sr.getScaledHeight() - y - height);
        }
        GL11.glEnd();

        GL11.glDisable(GL11.GL_TEXTURE_2D);
    }

    private static void drawTexturedModalRect(int x, int y, int width, int height) {
        drawTexturedModalRect(x,y,0,0,width,height);
    }

    public static void drawTexture(String textureName, int x, int y, int width, int height) {
        loadImageFromURL(textureName);
        loadImages();
        ResourceLocation textureLocation = textureCache.get(textureName);
        if (textureLocation != null) {
            Minecraft.getMinecraft().getTextureManager().bindTexture(textureLocation);
            drawTexturedModalRect(
                    x,
                    y,
                    width,
                    height);
        }
        else{
            UIRender.drawSquare(x,y,width,height, Color.BLACK);
        }
    }

    public static void drawPlayerFace(String name, int x, int y, int width, int height){
        loadPlayerSkin(name);
        loadImages();
        ResourceLocation textureLocation = textureCache.get(name);
        if (textureLocation != null) {
            Minecraft.getMinecraft().getTextureManager().bindTexture(textureLocation);
            drawTextureFace(
                    x,
                    y,
                    width,
                    height);
        }
        else{
            UIRender.drawSquare(x,y,width,height, Color.BLACK);
        }
    }


}
