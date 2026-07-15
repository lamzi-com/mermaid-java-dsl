package com.lamzi.doc.mermaid.diagram.sequencediagram;

public class SequenceBreak extends SequenceBlock<SequenceBreak> {
    @Override
    protected String kind() {
        return "break";
    }

    public SequenceBreak text(String text) {
        return header(text);
    }
}
