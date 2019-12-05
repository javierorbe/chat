package com.javierorbe.chat.client.net;

import com.javierorbe.chat.common.event.EventHandler;
import com.javierorbe.chat.common.net.Packet;

public class PacketHandler extends EventHandler<Packet> {

    public static final PacketHandler INSTANCE = new PacketHandler();

    private PacketHandler() {}
}
