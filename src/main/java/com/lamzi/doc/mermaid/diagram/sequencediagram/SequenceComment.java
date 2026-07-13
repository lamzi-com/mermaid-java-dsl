package com.lamzi.doc.mermaid.diagram.sequencediagram;

import com.lamzi.doc.mermaid.diagram.internal.MermaidWriter;

public class SequenceComment implements SequenceDiagramElement {
    private final String comment;

    public SequenceComment(String comment) {
        this.comment = comment;
    }

    @Override
    public void writeTo(MermaidWriter writer, int level) {
        writer.indent(level);
        writer.write("%% ");
        writer.write(comment);
        writer.eol();
    }
}
