package com.lamzi.doc.mermaid.diagram.flowchart;

import com.lamzi.doc.mermaid.diagram.MermaidWriter;

public class Node implements FlowchartDiagramElement {
    private String id;
    private NodeShape nodeShape;

    public Node(String id) {
        this.id = id;
    }

    public Node shape(NodeShape nodeShape) {
        this.nodeShape = nodeShape;
        return this;
    }

    @Override
    public void writeTo(MermaidWriter writer, int level) {
        writer.indent(level);
        writer.write(id);
        if (nodeShape != null) {
            nodeShape.writeTo(writer);
        }
        writer.eol();
    }
}
