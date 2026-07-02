package com.lamzi.doc.mermaid.diagram;

import java.util.*;
import java.util.stream.Collectors;

public class StyleDefinition<T extends StyleDefinitionAttribute> implements Writable {




    private final Map<T, String> attributes = new LinkedHashMap<>();

    public StyleDefinition<T> addAttribute(T key, String value) {
        attributes.put(key, value);
        return this;
    }

    @Override
    public void writeTo(MermaidWriter writer) {
        writer.write(attributes.entrySet().stream().map(entry -> entry.getKey().getName() + ":" + entry.getValue()).collect(Collectors.joining(",")));
    }
}