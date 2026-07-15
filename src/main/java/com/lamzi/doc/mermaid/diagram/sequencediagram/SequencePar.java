package com.lamzi.doc.mermaid.diagram.sequencediagram;

public class SequencePar extends SequenceBlock<SequencePar> {
    public SequencePar() {
        super(Type.PAR);
    }

    public SequencePar text(String text) {
        return header(text);
    }

    public SequencePar andBranch(String text) {
        return addSection("and", text);
    }
}
