package ru.qwertymo.kettuutils.core.net.client;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import ru.qwertymo.kettuutils.reference.Reference;

public class ClientNetworkManager {
    public static SimpleNetworkWrapper network;
    public ClientNetworkManager(){
        network = NetworkRegistry.INSTANCE.newSimpleChannel(Reference.MODID);

    }
}
