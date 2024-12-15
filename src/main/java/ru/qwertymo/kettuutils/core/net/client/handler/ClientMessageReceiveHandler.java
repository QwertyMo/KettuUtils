package ru.qwertymo.kettuutils.core.net.client.handler;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import ru.qwertymo.kettuutils.core.model.VanillaChatMessage;
import ru.qwertymo.kettuutils.core.net.packet.ChatMessagePacket;
import ru.qwertymo.kettuutils.proxy.KettuUtilsClientProxy;

public class ClientMessageReceiveHandler implements IMessageHandler<ChatMessagePacket, IMessage> {
    @Override
    public IMessage onMessage(ChatMessagePacket message, MessageContext ctx) {
        if (ctx.side == Side.CLIENT) {
            KettuUtilsClientProxy.getInstance().chat.addMessage(new VanillaChatMessage(message.getMessage()));
        } else {

        }
        return null;
    }
}
