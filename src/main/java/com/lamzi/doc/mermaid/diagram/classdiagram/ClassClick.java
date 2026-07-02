package com.lamzi.doc.mermaid.diagram.classdiagram;

import com.lamzi.doc.mermaid.diagram.MermaidWriter;

public class ClassClick implements ClassDiagramElement {

    public enum Kind {HREF, CALLBACK}

    private final Kind kind;
    private final Type type;
    private final String reference;
    private String tooltip;

    public ClassClick(Kind kind, Type type, String reference) {
        this.kind = kind;
        this.type = type;
        this.reference = reference;
    }

    public ClassClick tooltip(String tooltip) {
        this.tooltip = tooltip;
        return this;
    }

    @Override
    public void writeTo(MermaidWriter writer, int level) {

        writer.indent(level);
        writer.write("click ");
        type.writeNameTo(writer);
        writer.write(" ");
        switch (kind) {
            case HREF:
                generateLink(writer);
                break;
            case CALLBACK:
                generateCallback(writer);
                break;
        }

        if (tooltip != null) {
            writer.write(" \"");
            writer.write(tooltip);
            writer.write("\"");
        }
        writer.eol();
    }

    private void generateCallback(MermaidWriter writer) {
        writer.write("call");
        writer.write(" ");
        writer.write(reference);
        writer.write("()");
    }

    private void generateLink(MermaidWriter writer) {
        writer.write("href");
        writer.write(" \"");
        writer.write(reference);
        writer.write("\"");
    }
}
