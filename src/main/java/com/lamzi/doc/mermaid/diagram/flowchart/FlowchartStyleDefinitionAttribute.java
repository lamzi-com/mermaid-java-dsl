package com.lamzi.doc.mermaid.diagram.flowchart;

import com.lamzi.doc.mermaid.diagram.StyleDefinitionAttribute;

public enum FlowchartStyleDefinitionAttribute implements StyleDefinitionAttribute {
    FILL("fill"),
    STROKE("stroke"),
    STROKE_WIDTH("stroke-width"),
    COLOR("color"),
    STROKE_DASH_ARRAY("stroke-dasharray"),
    FONT_SIZE("font-size"),
    STROKE_DASHOFFSET("stroke-dashoffset"),
    ANIMATION("animation");

    private final String name;

    FlowchartStyleDefinitionAttribute(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}
