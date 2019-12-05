package com.javierorbe.chat.server.translate;

import static org.junit.jupiter.api.Assertions.*;

import com.javierorbe.chat.common.Language;
import com.javierorbe.chat.common.util.JsonConfigHandler;
import com.javierorbe.chat.server.Config;
import java.io.IOException;
import java.nio.file.Path;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class YandexTranslationServiceTest {

    private static final Path CONFIG_PATH = Path.of("./config.json");

    private static TranslationService service;

    @BeforeAll
    static void setup() {
        Config config;
        try {
            config = JsonConfigHandler.load(Config.class, CONFIG_PATH);
        } catch (IOException e) {
            fail(e);
            return;
        }
        service = new YandexTranslationService(config.getYandexApiKey());
    }

    @SuppressWarnings("SpellCheckingInspection")
    @ParameterizedTest
    @CsvSource({
            "'en', 'Hello, how are you?'",
            "'es', 'Hola, ¿qué tal estás?'",
    })
    void detectLanguage(String langCode, String text) {
        Language lang = Language.fromCode(langCode).orElseThrow();
        service.detectLanguage(text)
                .thenAccept(detectedLang -> assertEquals(lang, detectedLang.orElseThrow()));
    }
}
