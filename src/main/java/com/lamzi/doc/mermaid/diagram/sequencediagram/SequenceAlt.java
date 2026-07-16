package com.lamzi.doc.mermaid.diagram.sequencediagram;

public class SequenceAlt extends SequenceBlock<SequenceAlt> {
    @Override
    protected String kind() {
        return "alt";
    }

    public SequenceAlt text(String text) {
        return header(text);
    }

    public SequenceAlt otherwise() {
        return elseBranch(null);
    }

    public SequenceAlt elseBranch(String text) {
        return addSection("else", text);
    }
}
