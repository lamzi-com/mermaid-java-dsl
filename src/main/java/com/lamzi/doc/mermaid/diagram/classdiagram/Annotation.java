package com.lamzi.doc.mermaid.diagram.classdiagram;

import com.lamzi.doc.mermaid.diagram.MermaidWriter;

public class Annotation implements ClassElement {
    private String name;

    public Annotation(String name) {
        this.name = name;
    }

    @Override
    public void writeTo(MermaidWriter writer, int level) {
        writeWithoutEOL(writer, level);
        writer.eol();
    }

    public void writeWithoutEOL(MermaidWriter writer, int level) {
        writer.indent(level);
        writer.write("<<");
        writer.write(name);
        writer.write(">>");
    }
}
