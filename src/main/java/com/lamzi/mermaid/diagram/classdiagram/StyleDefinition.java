package com.lamzi.mermaid.diagram.classdiagram;

import com.lamzi.mermaid.diagram.MermaidWriter;
import com.lamzi.mermaid.diagram.Writable;

import java.util.*;
import java.util.stream.Collectors;

public class StyleDefinition implements Writable {

    public static final String FILL = "fill";
    public static final String STROKE = "stroke";
    public static final String STROKE_WIDTH = "stroke-width";
    public static final String COLOR = "color";
    public static final String STROKE_DASH_ARRAY = "stroke-dasharray";
    public static final String FONT_SIZE = "font-size";

    private final Map<String, String> attributes = new LinkedHashMap<>();

    public StyleDefinition addAttribute(String key, String value) {
        attributes.put(key, value);
        return this;
    }

    @Override
    public void writeTo(MermaidWriter writer) {
        writer.write(attributes.entrySet().stream().map(entry -> entry.getKey() + ":" + entry.getValue()).collect(Collectors.joining(",")));
    }
}