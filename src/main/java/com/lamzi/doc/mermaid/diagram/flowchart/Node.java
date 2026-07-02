package com.lamzi.doc.mermaid.diagram.flowchart;

import com.lamzi.doc.mermaid.diagram.MermaidException;
import com.lamzi.doc.mermaid.diagram.MermaidWriter;
import com.lamzi.doc.mermaid.diagram.classdiagram.Class;
import com.lamzi.doc.mermaid.diagram.flowchart.shape.NodeShape;

import java.util.Objects;

public class Node implements FlowchartDiagramElement {
    private String id;
    private NodeShape nodeShape;
    private LinkTo linkTo;
    private String cssClass;

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

    public Node cssClass(String cssClass) {
        this.cssClass = cssClass;
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
        if (cssClass != null) {
            writer.write(":::");
            writer.write(cssClass);
        }
        if (linkTo != null) {
            linkTo.writeTo(writer);
        }
    }
}
