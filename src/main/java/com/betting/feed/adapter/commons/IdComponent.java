package com.betting.feed.adapter.commons;

public class IdComponent {
    private final String prefix;
    private final String type;
    private final String id;

    public IdComponent(String prefix, String type, String id) {
        this.prefix = prefix;
        this.type = type;
        this.id = id;
    }

    public String getPrefix() {
        return prefix;
    }

    public String getType() {
        return type;
    }

    public String getId() {
        return id;
    }

}

