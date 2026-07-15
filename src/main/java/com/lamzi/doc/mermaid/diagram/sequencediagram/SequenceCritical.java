package com.lamzi.doc.mermaid.diagram.sequencediagram;

public class SequenceCritical extends SequenceBlock<SequenceCritical> {
    public SequenceCritical() {
        super(Type.CRITICAL);
    }

    public SequenceCritical text(String text) {
        return header(text);
    }

    public SequenceCritical option(String text) {
        return addSection("option", text);
    }
}
