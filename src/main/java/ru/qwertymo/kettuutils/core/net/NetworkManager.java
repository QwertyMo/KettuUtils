package ru.qwertymo.kettuutils.core.net;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;
import ru.qwertymo.kettuutils.core.net.client.handler.ClientMessageReceiveHandler;
import ru.qwertymo.kettuutils.core.net.packet.ChatMessagePacket;
import ru.qwertymo.kettuutils.core.net.server.handler.ServerMessageReceiveHandler;
import ru.qwertymo.kettuutils.reference.Reference;

public class NetworkManager {
    public static SimpleNetworkWrapper network;
    public NetworkManager(){
        network = NetworkRegistry.INSTANCE.newSimpleChannel(Reference.MODID);
        network.registerMessage(ClientMessageReceiveHandler.class, ChatMessagePacket.class, 0, Side.CLIENT);
        network.registerMessage(ServerMessageReceiveHandler.class, ChatMessagePacket.class, 1, Side.SERVER);
    }
}
