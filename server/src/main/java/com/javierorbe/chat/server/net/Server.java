package com.javierorbe.chat.server.net;

import com.javierorbe.chat.common.Language;
import com.javierorbe.chat.common.net.DisconnectPacket;
import com.javierorbe.chat.common.net.MessagePacket;
import com.javierorbe.chat.common.net.Packet;
import com.javierorbe.chat.server.translate.TranslationService;
import com.javierorbe.net.ISocketHandler;
import com.javierorbe.net.ObjectSocketServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Server extends ObjectSocketServer<Packet> {

    private static final Logger LOGGER = LoggerFactory.getLogger(Server.class);

    private final TranslationService translationService;

    public Server(int port, TranslationService translationService) {
        super(port);
        this.translationService = translationService;
    }

    @Override
    public void receive(ISocketHandler<Packet> client, Packet packet) {
        LOGGER.info("Received packet: " + packet);

        if (packet instanceof MessagePacket) {
            MessagePacket msg = (MessagePacket) packet;
            translationService.translate(msg.getContent(), Language.ENGLISH)
                    .thenAcceptAsync(translation -> broadcast(new MessagePacket(msg.getUsername(), translation)));
        } else if (packet instanceof DisconnectPacket) {
            removeClient(client);
        }
    }
}
