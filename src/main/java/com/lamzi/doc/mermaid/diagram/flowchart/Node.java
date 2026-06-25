package com.lamzi.doc.mermaid.diagram.flowchart;

import com.lamzi.doc.mermaid.diagram.MermaidWriter;
import com.lamzi.doc.mermaid.diagram.flowchart.shape.NodeShape;

public class Node implements FlowchartDiagramElement {
    private String id;
    private NodeShape nodeShape;
    private LinkTo linkTo;

    public Node(String id) {
        this.id = id;
    }

    public Node shape(NodeShape nodeShape) {
        this.nodeShape = nodeShape;
        return this;
    }

    public Node linkTo(LinkTo linkTo) {
        this.linkTo = linkTo;
        return this;
    }

    @Override
    public void writeTo(MermaidWriter writer, int level) {
        writer.indent(level);
        writeToInline(writer);
        writer.eol();
    }

    public void writeToInline(MermaidWriter writer) {
        writer.write(id);
        if (nodeShape != null) {
            nodeShape.writeTo(writer);
        }
        if (linkTo != null) {
            linkTo.writeTo(writer);
        }
    }
}
