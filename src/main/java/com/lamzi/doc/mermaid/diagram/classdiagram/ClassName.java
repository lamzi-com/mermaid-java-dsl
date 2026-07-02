package com.lamzi.doc.mermaid.diagram.classdiagram;

import com.lamzi.doc.mermaid.diagram.MermaidWriter;

public class ClassName {
    private final Type name;

    public ClassName(Type name) {
        this.name = name;
    }

    public void writeTo(MermaidWriter writer) {
        String delimiter = name.isvalid() ? "" : "`";
        writer.write(delimiter);
        name.writeTo(writer);
        writer.write(delimiter);
    }

    public void generateReference(MermaidWriter writer) {
        String delimiter = name.isvalid() ? "" : "`";
        writer.write(delimiter);
        name.writeNameTo(writer);
        writer.write(delimiter);
    }
}
