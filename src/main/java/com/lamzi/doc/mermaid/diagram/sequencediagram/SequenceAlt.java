package com.lamzi.doc.mermaid.diagram.sequencediagram;

public class SequenceAlt extends SequenceBlock<SequenceAlt> {
    public SequenceAlt() {
        super(Type.ALT);
    }

    public SequenceAlt otherwise() {
        return elseBranch(null);
    }

    public SequenceAlt elseBranch(String text) {
        return addSection("else", text);
    }
}
