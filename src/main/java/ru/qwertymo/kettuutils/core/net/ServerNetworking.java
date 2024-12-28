package ru.qwertymo.kettuutils.core.net;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;
import ru.qwertymo.kettuutils.core.net.packet.ChatMessagePacket;
import ru.qwertymo.kettuutils.reference.Reference;

public class ServerNetworking {
    public static SimpleNetworkWrapper network = NetworkRegistry.INSTANCE.newSimpleChannel(Reference.MODID);
    public static void register(){
        network.registerMessage(ChatMessagePacket.Handler.class, ChatMessagePacket.class, 0, Side.CLIENT);
        network.registerMessage(ChatMessagePacket.Handler.class, ChatMessagePacket.class, 1, Side.SERVER);
    }
}
