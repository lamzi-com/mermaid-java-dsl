package com.lamzi.doc.mermaid.diagram.sequencediagram;

public class SequenceOpt extends SequenceBlock<SequenceOpt> {
    @Override
    protected String kind() {
        return "opt";
    }

    public SequenceOpt text(String text) {
        return header(text);
    }
}
