package com.javierorbe.chat.server;

import com.javierorbe.chat.common.util.JsonConfigHandler;
import com.javierorbe.chat.server.net.Server;
import com.javierorbe.chat.server.translate.TranslationService;
import com.javierorbe.chat.server.translate.YandexTranslationService;
import java.io.IOException;
import java.nio.file.Path;

public class ServerMain {

    private static final Path CONFIG_PATH = Path.of("./config.json");

    public static void main(String[] args) {
        Config config;
        try {
            if (CONFIG_PATH.toFile().exists()) {
                config = JsonConfigHandler.load(Config.class, CONFIG_PATH);
            } else {
                config = new Config();
                JsonConfigHandler.save(config, CONFIG_PATH);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        TranslationService translationService = new YandexTranslationService(config.getYandexApiKey());
        new Server(config.getPort(), translationService).run();
    }

    private ServerMain() {
        throw new AssertionError();
    }
}
