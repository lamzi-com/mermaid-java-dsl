package com.lamzi.doc.mermaid.diagram.classdiagram;

import com.lamzi.doc.mermaid.diagram.DiagramElement;
import com.lamzi.doc.mermaid.diagram.MermaidWriter;

public class Style implements DiagramElement {
    private final Type type;
    private final StyleDefinition styleDefinition;

    public Style(Type type, StyleDefinition styleDefinition) {
        this.type = type;
        this.styleDefinition = styleDefinition;
    }

    @Override
    public void writeTo(MermaidWriter writer, int level) {
        writer.indent(level);
        writer.write("style ");
        type.writeNameTo(writer);
        writer.write(" ");
        styleDefinition.writeTo(writer);
        writer.eol();
    }
}
