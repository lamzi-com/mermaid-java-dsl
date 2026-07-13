package com.lamzi.doc.mermaid.diagram.sequencediagram;

import com.lamzi.doc.mermaid.diagram.internal.MermaidWriter;

public class SequenceActorDirective implements SequenceDiagramElement {
    private final Kind kind;
    private final ActorKind actorKind;
    private final String actor;
    private String alias;

    public enum Kind {
        CREATE("create"),
        DESTROY("destroy");

        private final String keyword;

        Kind(String keyword) {
            this.keyword = keyword;
        }
    }

    public enum ActorKind {
        PARTICIPANT("participant"),
        ACTOR("actor");

        private final String keyword;

        ActorKind(String keyword) {
            this.keyword = keyword;
        }
    }

    public SequenceActorDirective(Kind kind, ActorKind actorKind, String actor) {
        this.kind = kind;
        this.actorKind = actorKind;
        this.actor = actor;
    }

    public SequenceActorDirective alias(String alias) {
        this.alias = alias;
        return this;
    }

    @Override
    public void writeTo(MermaidWriter writer, int level) {
        writer.indent(level);
        writer.write(kind.keyword);
        writer.write(" ");
        if (actorKind != null) {
            writer.write(actorKind.keyword);
            writer.write(" ");
        }
        writer.write(actor);
        if (alias != null) {
            writer.write(" as ");
            writer.write(alias);
        }
        writer.eol();
    }
}
