package com.javierorbe.chat.server.util;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

public class HttpUtil {

    public static CompletableFuture<String> httpGet(String uri) {
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest
                .newBuilder(URI.create(uri))
                .build();
        return httpClient
                .sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body);
    }

    private HttpUtil() {
        throw new AssertionError();
    }
}
