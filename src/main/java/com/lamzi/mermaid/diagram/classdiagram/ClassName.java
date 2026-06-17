package com.lamzi.mermaid.diagram.classdiagram;

import com.lamzi.mermaid.diagram.MermaidWriter;

public class ClassName {
    public Type name;

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
