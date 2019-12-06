package com.javierorbe.chat.server.translate;

import static com.javierorbe.chat.server.util.HttpUtil.httpGet;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.javierorbe.chat.common.Language;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public class YandexTranslationService implements TranslationService {

    private static final Gson GSON = new Gson();

    private static final String TRANSLATE_URL = "https://translate.yandex.net/api/v1.5/tr.json/translate?key=%s&lang=%s&text=%s";
    private static final String DETECT_LANGUAGE_URL = "https://translate.yandex.net/api/v1.5/tr.json/detect?key=%s&text=%s";

    private final String apiKey;

    public YandexTranslationService(String apiKey) {
        this.apiKey = apiKey;
    }

    @Override
    public CompletableFuture<String> translate(String text, Language from, Language to) {
        return translate(text, from.getCode() + "-" + to.getCode());
    }

    @Override
    public CompletableFuture<String> translate(String text, Language to) {
        return translate(text, to.getCode());
    }

    private CompletableFuture<String> translate(String text, String lang) {
        String encodedText = URLEncoder.encode(text, StandardCharsets.UTF_8);
        String url = String.format(TRANSLATE_URL, apiKey, lang, encodedText);
        return httpGet(url)
                .thenApply(s -> GSON.fromJson(s, JsonObject.class))
                .thenApply(json -> json.get("text").getAsString());
    }

    @Override
    public CompletableFuture<Optional<Language>> detectLanguage(String text) {
        String encodedText = URLEncoder.encode(text, StandardCharsets.UTF_8);
        String url = String.format(DETECT_LANGUAGE_URL, apiKey, encodedText);
        return httpGet(url)
                .thenApply(s -> GSON.fromJson(s, JsonObject.class))
                .thenApply(json -> json.get("lang").getAsString())
                .thenApply(Language::fromCode);
    }
}
