package com.javierorbe.chat.server;

public class Config {

    private int port;
    private String yandexApiKey;

    public Config(int port, String yandexApiKey) {
        this.port = port;
        this.yandexApiKey = yandexApiKey;
    }

    public Config() {
        this(9315, "put-your-key");
    }

    public int getPort() {
        return port;
    }

    public String getYandexApiKey() {
        return yandexApiKey;
    }
}
