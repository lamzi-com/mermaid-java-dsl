package com.lamzi.doc.mermaid.diagram.classdiagram;

import com.lamzi.doc.mermaid.diagram.MermaidWriter;
import com.lamzi.doc.mermaid.diagram.StyleDefinition;

public class Style implements ClassDiagramElement {
    private final Type type;
    private final StyleDefinition<ClassStyleDefinitionAttribute> styleDefinition;

    public Style(Type type, StyleDefinition<ClassStyleDefinitionAttribute> styleDefinition) {
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
