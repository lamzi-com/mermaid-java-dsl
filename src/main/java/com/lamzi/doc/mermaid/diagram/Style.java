package com.lamzi.doc.mermaid.diagram;

import com.lamzi.doc.mermaid.diagram.classdiagram.ClassDiagramElement;
import com.lamzi.doc.mermaid.diagram.flowchart.FlowchartDiagramElement;

public class Style<T extends StyleDefinitionAttribute> implements ClassDiagramElement, FlowchartDiagramElement {
    private final String id;
    private final StyleDefinition<T> styleDefinition;

    public Style(String id, StyleDefinition<T> styleDefinition) {
        this.id = id;
        this.styleDefinition = styleDefinition;
    }

    @Override
    public void writeTo(MermaidWriter writer, int level) {
        writer.indent(level);
        writer.write("style ");
        writer.write(id);
        writer.write(" ");
        styleDefinition.writeTo(writer);
        writer.eol();
    }
}
