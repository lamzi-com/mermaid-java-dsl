package com.lamzi.doc.mermaid.diagram.flowchart;

import com.lamzi.doc.mermaid.diagram.MermaidWriter;
import com.lamzi.doc.mermaid.diagram.classdiagram.ClassDiagramElement;

public abstract class NodeClick<T extends NodeClick<T>> implements FlowchartDiagramElement {

    private final String nodeId;
    private String tooltip;

    public NodeClick(String nodeId) {
        this.nodeId = nodeId;
    }


    public T tooltip(String tooltip) {
        this.tooltip = tooltip;
        return (T) this;
    }

    @Override
    public void writeTo(MermaidWriter writer, int level) {

        writer.indent(level);
        writer.write("click ");
        writer.write(nodeId);
        writer.write(" ");
        writeAction(writer);

        if (tooltip != null) {
            writer.write(" \"");
            writer.write(tooltip);
            writer.write("\"");
        }
        writeTarget(writer);
        writer.eol();
    }

    abstract protected void writeAction(MermaidWriter writer);

    abstract protected void writeTarget(MermaidWriter writer);
}
