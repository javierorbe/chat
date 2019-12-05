package com.javierorbe.chat.client.net;

import com.javierorbe.chat.common.net.Packet;
import com.javierorbe.net.ObjectSocketClient;
import java.security.cert.X509Certificate;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SocketClient extends ObjectSocketClient<Packet> {

    private static final Logger LOGGER = LoggerFactory.getLogger(SocketClient.class);

    // Trust manager that does not validate certificate chains.
    private static final TrustManager[] TRUST_ALL_CERTS = new TrustManager[] {
            new X509TrustManager() {
                @Override
                public void checkClientTrusted(X509Certificate[] chain, String authType) {}

                @Override
                public void checkServerTrusted(X509Certificate[] chain, String authType) {}

                @Override
                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
            }
    };

    public SocketClient(String hostname, int port) {
        super(hostname, port);
    }

    @Override
    public void receive(Packet packet) {
        LOGGER.info("Received packet: " + packet);
        PacketHandler.INSTANCE.fire(packet);
    }
}
