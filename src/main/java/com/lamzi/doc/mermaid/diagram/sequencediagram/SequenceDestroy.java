package com.lamzi.doc.mermaid.diagram.sequencediagram;

import com.lamzi.doc.mermaid.diagram.internal.MermaidWriter;

public class SequenceDestroy implements SequenceDiagramElement {
    private final String actorId;

    public SequenceDestroy(String actorId) {
        this.actorId = actorId;
    }

    @Override
    public void writeTo(MermaidWriter writer, int level) {
        writer.indent(level);
        writer.write("destroy ");
        writer.write(actorId);
        writer.eol();
    }
}
