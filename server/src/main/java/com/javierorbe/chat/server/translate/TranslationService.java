package com.javierorbe.chat.server.translate;

import com.javierorbe.chat.common.Language;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public interface TranslationService {

    CompletableFuture<String> translate(String text, Language from, Language to);

    CompletableFuture<String> translate(String text, Language to);

    CompletableFuture<Optional<Language>> detectLanguage(String text);
}
