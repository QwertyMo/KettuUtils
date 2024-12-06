package ru.qwertymo.kettuutils.core.binding;

import cpw.mods.fml.client.registry.ClientRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import org.apache.commons.lang3.ArrayUtils;
import org.lwjgl.input.Keyboard;

import java.util.*;

public class KeyBindings {
    public static List<KeyBinding> keyBindings = new ArrayList<>();

    public static KeyBinding CHAT_KEY = new KeyBinding("kettu.key.chat.open", Keyboard.KEY_T, "kettu.key.category.chat");
    public static KeyBinding COMMAND_KEY = new KeyBinding("kettu.key.chat.open", Keyboard.KEY_T, "kettu.key.category.chat");

    public KeyBindings(){
        //Добавление биндов
        keyBindings.add(CHAT_KEY);
        keyBindings.add(COMMAND_KEY);

        //Удаление биндов
        removeBinging("key.chat");
        removeBinging("key.command");

        for(KeyBinding binding: keyBindings){
            ClientRegistry.registerKeyBinding(binding);
        }
    }

    public static void removeBinging(String keyDesc){
        KeyBinding[] bindings = Minecraft.getMinecraft().gameSettings.keyBindings;
        Optional<KeyBinding> key = Arrays.stream(bindings)
                .filter(it -> Objects.equals(it.getKeyDescription(), keyDesc))
                .findAny();
        if(!key.isPresent()) return;
        int pos = ArrayUtils.indexOf(bindings, key.get());
        Minecraft.getMinecraft().gameSettings.keyBindings = ArrayUtils.remove(bindings, pos);
    }
}

