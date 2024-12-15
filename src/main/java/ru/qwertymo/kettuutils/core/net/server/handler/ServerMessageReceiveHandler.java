package ru.qwertymo.kettuutils.core.net.server.handler;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.server.MinecraftServer;
import ru.qwertymo.kettuutils.core.net.NetworkManager;
import ru.qwertymo.kettuutils.core.net.packet.ChatMessagePacket;

public class ServerMessageReceiveHandler  implements IMessageHandler<ChatMessagePacket, IMessage> {
    @Override
    public IMessage onMessage(ChatMessagePacket message, MessageContext ctx) {
        if (ctx.side == Side.SERVER) {
            NetworkManager.network.sendToAll(message);
            MinecraftServer.getServer().getCommandManager().executeCommand(ctx.getServerHandler().playerEntity, message.getMessage());
        } else {

        }
        return null;
    }
}
