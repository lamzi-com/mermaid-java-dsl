package com.lamzi.doc.mermaid.diagram.sequencediagram;

import com.lamzi.doc.mermaid.diagram.internal.MermaidWriter;

import java.util.LinkedHashMap;
import java.util.Map;

public class SequenceParticipantConfig {
    private final Map<String, String> properties = new LinkedHashMap<>();

    public enum Type {
        BOUNDARY("boundary"),
        CONTROL("control"),
        ENTITY("entity"),
        DATABASE("database"),
        COLLECTIONS("collections"),
        QUEUE("queue");

        private final String value;

        Type(String value) {
            this.value = value;
        }
    }

    public SequenceParticipantConfig type(Type type) {
        properties.put("type", type.value);
        return this;
    }

    public SequenceParticipantConfig alias(String alias) {
        properties.put("alias", alias);
        return this;
    }

    boolean isEmpty() {
        return properties.isEmpty();
    }

    void writeTo(MermaidWriter writer) {
        writer.write("@{ ");
        boolean first = true;
        for (Map.Entry<String, String> entry : properties.entrySet()) {
            if (!first) {
                writer.write(", ");
            }
            first = false;
            writer.write("\"");
            writer.write(entry.getKey());
            writer.write("\": \"");
            writer.write(entry.getValue());
            writer.write("\"");
        }
        writer.write(" }");
    }
}
