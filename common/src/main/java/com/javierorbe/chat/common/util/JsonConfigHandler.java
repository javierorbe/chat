package com.javierorbe.chat.common.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;

public class JsonConfigHandler {

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    public static <T> T load(Class<T> objectClass, Path filepath) throws IOException {
        File file = filepath.toFile();
        if (!file.exists() || !file.isFile()) {
            throw new IllegalArgumentException("The provided file doesn't exist.");
        }
        try (Reader reader = Files.newBufferedReader(filepath)) {
            return GSON.fromJson(reader, objectClass);
        }
    }

    public static void save(Object config, Path filepath) throws IOException {
        try (Writer writer = Files.newBufferedWriter(filepath)) {
            GSON.toJson(config, writer);
            writer.flush();
        }
    }

    private JsonConfigHandler() {
        throw new AssertionError();
    }
}
