package com.lamzi.doc.mermaid.diagram.sequencediagram;

public class SequenceLoop extends SequenceBlock<SequenceLoop> {
    @Override
    protected String kind() {
        return "loop";
    }

    public SequenceLoop text(String text) {
        return header(text);
    }
}
