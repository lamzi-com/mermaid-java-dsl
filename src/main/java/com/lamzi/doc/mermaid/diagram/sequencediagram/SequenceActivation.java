package com.lamzi.doc.mermaid.diagram.sequencediagram;

import com.lamzi.doc.mermaid.diagram.internal.MermaidWriter;

public class SequenceActivation implements SequenceDiagramElement {
    private final Kind kind;
    private final String actor;

    public enum Kind {
        ACTIVATE("activate"),
        DEACTIVATE("deactivate");

        private final String keyword;

        Kind(String keyword) {
            this.keyword = keyword;
        }
    }

    public SequenceActivation(Kind kind, String actor) {
        this.kind = kind;
        this.actor = actor;
    }

    @Override
    public void writeTo(MermaidWriter writer, int level) {
        writer.indent(level);
        writer.write(kind.keyword);
        writer.write(" ");
        writer.write(actor);
        writer.eol();
    }
}
