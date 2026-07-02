package com.lamzi.doc.mermaid.diagram.flowchart;

import com.lamzi.doc.mermaid.diagram.MermaidWriter;

public class NodeHrefClick extends NodeClick<NodeHrefClick> {

    private final String reference;
    private boolean displayKind;
    private String target;

    public NodeHrefClick(String nodeId, String reference) {
        super(nodeId);
        this.reference = reference;
    }

    public NodeHrefClick displayKind() {
        this.displayKind = true;
        return this;
    }

    public NodeHrefClick target(String target) {
        this.target = target;
        return this;
    }

    @Override
    protected void writeAction(MermaidWriter writer) {
        if (displayKind) {
            writer.write("href ");
        }
        writer.write("\"");
        writer.write(reference);
        writer.write("\"");
    }

    @Override
    protected void writeTarget(MermaidWriter writer) {
        if (target != null) {
            writer.write(" ");
            writer.write(target);
        }
    }
}
