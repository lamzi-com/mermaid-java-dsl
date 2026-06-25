package com.lamzi.doc.mermaid.diagram.classdiagram;

import com.lamzi.doc.mermaid.diagram.StyleDefinitionAttribute;

public enum ClassStyleDefinitionAttribute implements StyleDefinitionAttribute {
    FILL("fill"), STROKE("stroke"), STROKE_WIDTH("stroke-width"), COLOR("color"), STROKE_DASH_ARRAY("stroke-dasharray"), FONT_SIZE("font-size");

    private final String name;

    ClassStyleDefinitionAttribute(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}
