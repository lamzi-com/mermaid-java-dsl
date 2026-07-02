package com.lamzi.doc.mermaid.diagram;

import com.lamzi.doc.mermaid.diagram.classdiagram.ClassDiagramElement;
import com.lamzi.doc.mermaid.diagram.classdiagram.ClassElement;
import com.lamzi.doc.mermaid.diagram.classdiagram.NamespaceElement;
import com.lamzi.doc.mermaid.diagram.flowchart.FlowchartDiagramElement;

public class Comment implements ClassDiagramElement, FlowchartDiagramElement, NamespaceElement, ClassElement {
    private String comment;

    public Comment(String comment) {
        this.comment = comment;
    }

    @Override
    public void writeTo(MermaidWriter writer, int level) {
        writer.indent(level);
        writer.write("%% ");
        writer.write(comment);
        writer.eol();

    }
}
