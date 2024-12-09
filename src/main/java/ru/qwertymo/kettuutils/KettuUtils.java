package ru.qwertymo.kettuutils;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.util.ResourceLocation;
import ru.qwertymo.kettuutils.proxy.CommonProxy;
import ru.qwertymo.kettuutils.reference.Reference;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

@Mod(modid = Reference.MODID, name = Reference.NAME, version = Reference.VERSION, acceptableRemoteVersions = "*")
public class KettuUtils {

   @Mod.Instance(Reference.MODID)
   public static KettuUtils instance;

   @SidedProxy(
           serverSide = "ru.qwertymo.kettuutils.proxy.ServerProxy",
           clientSide = "ru.qwertymo.kettuutils.proxy.KettuUtilsClientProxy"
   )
   public static CommonProxy proxy;

   @Mod.EventHandler
   public void preInit(FMLPreInitializationEvent event) {
      proxy.preInit(event);
   }

   @Mod.EventHandler
   public void init(FMLInitializationEvent event) {
      proxy.init(event);
   }

   @Mod.EventHandler
   public void postInit(FMLPostInitializationEvent event) {
      proxy.postInit(event);
   }

   private void loadImageFromURL(String urlString, int x, int y) {
      BufferedImage image;
      DynamicTexture dynamicTexture;
      ResourceLocation textureLocation;

      try {
         URL url = new URL(urlString);
         InputStream is = url.openStream();
         image = ImageIO.read(is);
         is.close();

         // Создаем динамическую текстуру из BufferedImage
         dynamicTexture = new DynamicTexture(image);
         textureLocation = Minecraft.getMinecraft().getTextureManager().getDynamicTextureLocation(Reference.MODID, dynamicTexture);
         Minecraft.getMinecraft().getTextureManager().bindTexture(textureLocation);
         // Отрисовываем текстуру
      } catch (IOException e) {
         e.printStackTrace();
      }
   }
}
