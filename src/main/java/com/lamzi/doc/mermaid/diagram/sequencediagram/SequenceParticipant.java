package com.lamzi.doc.mermaid.diagram.sequencediagram;

public class SequenceParticipant extends BaseSequenceParticipant<SequenceParticipant> {
    public SequenceParticipant(String id) {
        super(id);
    }

    @Override
    protected String keyword() {
        return "participant";
    }
}
