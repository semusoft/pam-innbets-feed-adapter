package com.pam.sportradar.innbets.utils;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;

public class ThreadLocalAdapter extends TypeAdapter<ThreadLocal<?>> {
    @Override
    public void write(JsonWriter out, ThreadLocal<?> value) {
        try {
            out.nullValue();
        } catch (IOException e) {
            throw new RuntimeException("Failed to write ThreadLocal field", e);
        }
    }

    @Override
    public ThreadLocal<?> read(JsonReader in) {
        return null;
    }
}
