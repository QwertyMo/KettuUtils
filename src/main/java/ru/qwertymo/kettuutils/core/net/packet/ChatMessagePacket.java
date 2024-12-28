package ru.qwertymo.kettuutils.core.net.packet;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import ru.qwertymo.kettuutils.core.model.VanillaChatMessage;
import ru.qwertymo.kettuutils.proxy.KettuUtilsClientProxy;

import static ru.qwertymo.kettuutils.core.net.ServerNetworking.network;

public class ChatMessagePacket implements IMessage {
    private String type;
    private String value;

    public ChatMessagePacket() {}

    public ChatMessagePacket(String type, String value) {
        this.type = type;
        this.value = value;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        type = ByteBufUtils.readUTF8String(buf);
        value = ByteBufUtils.readUTF8String(buf);
    }

    @Override
    public void toBytes(ByteBuf buf) {
        ByteBufUtils.writeUTF8String(buf, type);
        ByteBufUtils.writeUTF8String(buf, value);
    }

    public static class Handler implements IMessageHandler<ChatMessagePacket, IMessage> {
        @Override
        public IMessage onMessage(ChatMessagePacket message, MessageContext ctx) {
            if (ctx.side.isClient()) { //Client
                if (message.type.equals("ClientReqNickname")) {
                    String nickname = Minecraft.getMinecraft().getSession().getUsername();

                    ChatMessagePacket packet = new ChatMessagePacket("ServerRecNickname", nickname);
                    network.sendToServer(packet);
                } else if (message.type.equals("ServerMessageToClient")) {
                    KettuUtilsClientProxy ky = KettuUtilsClientProxy.getInstance();
                    IChatComponent text = new ChatComponentText(message.value);
                    ky.chat.addMessage(new VanillaChatMessage(text.getFormattedText()));
                }
            } else { //Server
                if (message.type.equals("ServerRecNickname")) {
                    EntityPlayerMP serverPlayer = ctx.getServerHandler().playerEntity;
                    serverPlayer.addChatMessage(new ChatComponentText("Сервер получил: " + message.value));
                }
            }
            return null;
        }
    }
}
