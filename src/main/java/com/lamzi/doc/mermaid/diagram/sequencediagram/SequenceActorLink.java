package com.lamzi.doc.mermaid.diagram.sequencediagram;

import com.lamzi.doc.mermaid.diagram.internal.MermaidWriter;

public class SequenceActorLink implements SequenceDiagramElement {
    private final String actor;
    private final String label;
    private final String url;

    public SequenceActorLink(String actor, String label, String url) {
        this.actor = actor;
        this.label = label;
        this.url = url;
    }

    @Override
    public void writeTo(MermaidWriter writer, int level) {
        writer.indent(level);
        writer.write("link ");
        writer.write(actor);
        writer.write(": ");
        writer.write(label);
        writer.write(" @ ");
        writer.write(url);
        writer.eol();
    }
}
