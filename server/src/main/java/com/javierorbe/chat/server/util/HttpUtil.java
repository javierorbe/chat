package com.javierorbe.chat.server.util;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

public class HttpUtil {

    private static final HttpClient HTTP_CLIENT = HttpClient.newBuilder().build();

    public static CompletableFuture<String> httpGet(String uri) {
        HttpRequest request = HttpRequest
                .newBuilder(URI.create(uri))
                .build();
        return HTTP_CLIENT
                .sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body);
    }

    private HttpUtil() {
        throw new AssertionError();
    }
}
