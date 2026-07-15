package com.lamzi.doc.mermaid.diagram.sequencediagram;

public class SequenceCritical extends SequenceBlock<SequenceCritical> {
    @Override
    protected String kind() {
        return "critical";
    }

    public SequenceCritical text(String text) {
        return header(text);
    }

    public SequenceCritical option(String text) {
        return addSection("option", text);
    }
}
