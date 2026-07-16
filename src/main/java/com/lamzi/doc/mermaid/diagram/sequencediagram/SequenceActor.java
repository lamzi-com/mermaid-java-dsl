package com.lamzi.doc.mermaid.diagram.sequencediagram;

public class SequenceActor extends BaseSequenceParticipant<SequenceActor> {
    public SequenceActor(String id) {
        super(id);
    }

    @Override
    protected String keyword() {
        return "actor";
    }
}
