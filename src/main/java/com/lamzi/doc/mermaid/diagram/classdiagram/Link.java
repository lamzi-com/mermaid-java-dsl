package com.lamzi.doc.mermaid.diagram.classdiagram;

import com.lamzi.doc.mermaid.diagram.DiagramElement;
import com.lamzi.doc.mermaid.diagram.MermaidWriter;

public class Link implements DiagramElement {
    private final Type type;
    private final String url;
    private String tooltip;

    public Link(Type type, String url) {
        this.type = type;
        this.url = url;
    }

    public Link tooltip(String tooltip) {
        this.tooltip = tooltip;
        return this;
    }

    @Override
    public void writeTo(MermaidWriter writer, int level) {
        writer.indent(level);
        writer.write("link ");
        type.writeNameTo(writer);
        writer.write(" \"");
        writer.write(url);
        writer.write("\"");
        if (tooltip != null) {
            writer.write(" \"");
            writer.write(tooltip);
            writer.write("\"");
        }
        writer.eol();
    }
}
