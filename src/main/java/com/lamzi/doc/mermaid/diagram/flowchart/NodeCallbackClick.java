package com.lamzi.doc.mermaid.diagram.flowchart;

import com.lamzi.doc.mermaid.diagram.MermaidWriter;

public class NodeCallbackClick extends NodeClick<NodeCallbackClick> {

    private final String reference;
    private boolean displayKind;

    public NodeCallbackClick(String nodeId, String reference) {
        super(nodeId);
        this.reference = reference;
    }

    public NodeCallbackClick displayKind() {
        this.displayKind = true;
        return this;
    }

    @Override
    protected void writeAction(MermaidWriter writer) {
        if (displayKind) {
            writer.write("call ");
        }
        writer.write(reference);
        if (displayKind) {
            writer.write("()");
        }

    }

    @Override
    protected void writeTarget(MermaidWriter writer) {

    }
}
