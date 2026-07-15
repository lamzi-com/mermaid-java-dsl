package com.lamzi.doc.mermaid.diagram.sequencediagram;

public class SequenceBreak extends SequenceBlock<SequenceBreak> {
    public SequenceBreak() {
        super(Type.BREAK);
    }

    public SequenceBreak text(String text) {
        return header(text);
    }
}
