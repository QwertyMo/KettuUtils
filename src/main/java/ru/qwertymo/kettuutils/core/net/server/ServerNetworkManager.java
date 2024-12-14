package ru.qwertymo.kettuutils.core.net.server;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import ru.qwertymo.kettuutils.reference.Reference;

public class ServerNetworkManager {
    public static SimpleNetworkWrapper network;
    public ServerNetworkManager(){
        network = NetworkRegistry.INSTANCE.newSimpleChannel(Reference.MODID);

    }
}
