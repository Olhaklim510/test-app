package com.clearsolutions.testapp.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

public class JsonReaderUtils {

    public static byte[] readJson(final String path) throws IOException {
        try (InputStream stream = JsonReaderUtils.class.getClassLoader().getResourceAsStream(path)) {
            return Objects.requireNonNull(stream).readAllBytes();
        }
    }
}
