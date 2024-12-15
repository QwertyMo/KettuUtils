package ru.qwertymo.kettuutils.core.net.packet;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import io.netty.buffer.ByteBuf;

public class ChatMessagePacket implements IMessage {
    private String message;

    public ChatMessagePacket(){}

    public ChatMessagePacket(String message){
        this.message = message;
    }

    @Override
    public void fromBytes(ByteBuf byteBuf) {
        int length = byteBuf.readInt();
        byte[] messageBytes = new byte[length];
        byteBuf.readBytes(messageBytes);
        message = new String(messageBytes);
    }

    @Override
    public void toBytes(ByteBuf byteBuf) {
        byte[] messageBytes = message.getBytes();
        byteBuf.writeInt(messageBytes.length);
        byteBuf.writeBytes(messageBytes);
    }

    public String getMessage(){
        return message;
    }
}
