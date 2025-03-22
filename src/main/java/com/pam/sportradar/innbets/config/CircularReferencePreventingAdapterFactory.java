package com.pam.sportradar.innbets.config;

import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonWriter;
import com.google.gson.stream.JsonReader;
import com.google.gson.Gson;
import com.google.gson.TypeAdapterFactory;
import java.io.IOException;
import java.util.IdentityHashMap;

public class CircularReferencePreventingAdapterFactory implements TypeAdapterFactory {
    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
        final TypeAdapter<T> delegateAdapter = gson.getDelegateAdapter(this, type);
        return new TypeAdapter<T>() {
            private final ThreadLocal<IdentityHashMap<Object, Boolean>> seenObjects = ThreadLocal.withInitial(IdentityHashMap::new);

            @Override
            public void write(JsonWriter out, T value) throws IOException {
                if (value == null) {
                    out.nullValue();
                    return;
                }
                if (seenObjects.get().containsKey(value)) {
                    out.nullValue(); // prevent circular reference
                    return;
                }
                seenObjects.get().put(value, Boolean.TRUE);
                try {
                    delegateAdapter.write(out, value);
                } finally {
                    seenObjects.get().remove(value);
                }
            }

            @Override
            public T read(JsonReader in) throws IOException {
                return delegateAdapter.read(in);
            }
        };
    }
}
