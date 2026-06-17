package com.lamzi.mermaid.diagram.classdiagram;

import com.lamzi.mermaid.diagram.DiagramElement;
import com.lamzi.mermaid.diagram.MermaidWriter;

public class Comment implements DiagramElement, NamespaceElement, ClassElement {
    private String comment;

    public Comment(String comment) {
        this.comment = comment;
    }

    @Override
    public void writeTo(MermaidWriter writer, int level) {
        writer.indent(level-1);
        writer.write("%% ");
        writer.write(comment);
        writer.eol();

    }
}
