package com.lamzi.doc.mermaid.diagram.classdiagram;

import com.lamzi.doc.mermaid.diagram.DiagramElement;
import com.lamzi.doc.mermaid.diagram.MermaidWriter;

public class CallBack implements DiagramElement {
    private final Type type;
    private final String function;
    private String tooltip;

    public CallBack(Type type, String function) {
        this.type = type;
        this.function = function;
    }

    public CallBack tooltip(String tooltip) {
        this.tooltip = tooltip;
        return this;
    }

    @Override
    public void writeTo(MermaidWriter writer, int level) {
        writer.indent(level);
        writer.write("callback ");
        type.writeNameTo(writer);
        writer.write(" \"");
        writer.write(function);
        writer.write("\"");
        if (tooltip != null) {
            writer.write(" \"");
            writer.write(tooltip);
            writer.write("\"");
        }
        writer.eol();
    }
}
