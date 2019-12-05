package com.javierorbe.chat.common.net;

import com.javierorbe.chat.common.Language;
import java.util.Optional;

public class MessagePacket extends Packet {

    private final String username;
    private final String content;
    private final Language language;

    public MessagePacket(String username, String content, Language language) {
        this.username = username;
        this.content = content;
        this.language = language;
    }

    public MessagePacket(String username, String content) {
        this(username, content, null);
    }

    public String getUsername() {
        return username;
    }

    public String getContent() {
        return content;
    }

    public Optional<Language> getLanguage() {
        return Optional.ofNullable(language);
    }
}
