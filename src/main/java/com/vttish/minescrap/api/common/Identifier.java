package com.vttish.minescrap.api.common;

public record Identifier(String namespace, String value) {
    public static final String DEFAULT_NAMESPACE = "minecraft";

    public static Identifier of(String value) {
        int separatorIdx = value.indexOf(':');

        if (separatorIdx == -1) {
            return new Identifier(DEFAULT_NAMESPACE, value);
        }

        return new Identifier(value.substring(0, separatorIdx), value.substring(separatorIdx + 1));
    }

    public static Identifier of(String namespace, String value) {
        return new Identifier(namespace, value);
    }

    @Override
    public String toString() {
        return namespace + ":" + value;
    }
}
