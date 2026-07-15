package com.lamzi.doc.mermaid.diagram.sequencediagram;

public class SequenceLoop extends SequenceBlock<SequenceLoop> {
    public SequenceLoop() {
        super(Type.LOOP);
    }

    public SequenceLoop text(String text) {
        return header(text);
    }
}
