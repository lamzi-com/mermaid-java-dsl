package com.lamzi.doc.mermaid.diagram.sequencediagram;

import com.lamzi.doc.mermaid.diagram.internal.MermaidWriter;

public class SequenceParticipantLink implements SequenceDiagramElement {
    private final String participant;
    private final String label;
    private final String url;

    public SequenceParticipantLink(String participant, String label, String url) {
        this.participant = participant;
        this.label = label;
        this.url = url;
    }

    @Override
    public void writeTo(MermaidWriter writer, int level) {
        writer.indent(level);
        writer.write("link ");
        writer.write(participant);
        writer.write(": ");
        writer.write(label);
        writer.write(" @ ");
        writer.write(url);
        writer.eol();
    }
}
