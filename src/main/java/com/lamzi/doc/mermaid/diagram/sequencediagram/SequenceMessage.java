package com.lamzi.doc.mermaid.diagram.sequencediagram;

import com.lamzi.doc.mermaid.diagram.internal.MermaidWriter;

public class SequenceMessage implements SequenceDiagramElement {
    private final String from;
    private final Arrow arrow;
    private final String to;
    private String text;
    private Activation activation = Activation.NONE;

    public enum Arrow {
        SOLID("->"),
        DOTTED("-->"),
        SOLID_ARROW("->>"),
        DOTTED_ARROW("-->>"),
        BIDIRECTIONAL_SOLID("<<->>"),
        BIDIRECTIONAL_DOTTED("<<-->>"),
        SOLID_CROSS("-x"),
        DOTTED_CROSS("--x"),
        SOLID_OPEN("-)"),
        DOTTED_OPEN("--)");

        private final String value;

        Arrow(String value) {
            this.value = value;
        }
    }

    public enum Activation {
        NONE(""),
        ACTIVATE("+"),
        DEACTIVATE("-");

        private final String suffix;

        Activation(String suffix) {
            this.suffix = suffix;
        }
    }

    public SequenceMessage(String from, Arrow arrow, String to, String text) {
        this.from = from;
        this.arrow = arrow;
        this.to = to;
        this.text = text;
    }

    public SequenceMessage text(String text) {
        this.text = text;
        return this;
    }

    public SequenceMessage activate() {
        this.activation = Activation.ACTIVATE;
        return this;
    }

    public SequenceMessage deactivate() {
        this.activation = Activation.DEACTIVATE;
        return this;
    }

    @Override
    public void writeTo(MermaidWriter writer, int level) {
        writer.indent(level);
        writer.write(from);
        writer.write(arrow.value);
        writer.write(activation.suffix);
        writer.write(to);
        writer.write(":");
        if (text != null) {
            writer.write(" ");
            writer.write(text);
        }
        writer.eol();
    }
}
