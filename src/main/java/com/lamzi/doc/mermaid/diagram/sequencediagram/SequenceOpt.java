package com.lamzi.doc.mermaid.diagram.sequencediagram;

public class SequenceOpt extends SequenceBlock<SequenceOpt> {
    public SequenceOpt() {
        super(Type.OPT);
    }

    public SequenceOpt text(String text) {
        return header(text);
    }
}
