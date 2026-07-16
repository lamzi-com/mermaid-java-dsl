package com.lamzi.doc.mermaid.diagram.sequencediagram;

import com.lamzi.doc.mermaid.diagram.internal.MermaidWriter;

public class SequenceCreate implements SequenceDiagramElement {
    private final BaseSequenceParticipant<?> actor;

    public SequenceCreate(BaseSequenceParticipant<?> actor) {
        this.actor = actor;
    }

    @Override
    public void writeTo(MermaidWriter writer, int level) {
        writer.indent(level);
        writer.write("create ");
        actor.writeDeclarationTo(writer);
        writer.eol();
    }
}
