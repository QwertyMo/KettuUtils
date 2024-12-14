package ru.qwertymo.kettuutils;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.*;
import ru.qwertymo.kettuutils.proxy.KettuUtilsCommonProxy;
import ru.qwertymo.kettuutils.reference.Reference;

@Mod(modid = Reference.MODID, name = Reference.NAME, version = Reference.VERSION, acceptableRemoteVersions = "*")
public class KettuUtils {

   @Mod.Instance(Reference.MODID)
   public static KettuUtils instance;

   @SidedProxy(
           serverSide = "ru.qwertymo.kettuutils.proxy.KettuUtilsServerProxy",
           clientSide = "ru.qwertymo.kettuutils.proxy.KettuUtilsClientProxy"
   )
   public static KettuUtilsCommonProxy proxy;

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
}
