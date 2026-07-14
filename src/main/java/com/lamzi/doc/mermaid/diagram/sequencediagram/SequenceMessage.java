package com.lamzi.doc.mermaid.diagram.sequencediagram;

import com.lamzi.doc.mermaid.diagram.internal.MermaidWriter;

public class SequenceMessage implements SequenceDiagramElement {
    private final String from;
    private final Line line;
    private final Head head;
    private final String to;
    private String text;
    private Activation activation = Activation.NONE;
    private boolean fromCentral;
    private boolean toCentral;

    public enum Line {
        SOLID("-"),
        DOTTED("--");

        private final String value;

        Line(String value) {
            this.value = value;
        }
    }

    public enum Head {
        NONE("", ">"),
        ARROW("", ">>"),
        BIDIRECTIONAL_ARROW("<<", ">>"),
        CROSS("", "x"),
        OPEN_ARROW("", ")"),

        TOP_HALF_ARROW("", "|\\"),
        BOTTOM_HALF_ARROW("", "|/"),
        REVERSE_TOP_HALF_ARROW("/|", ""),
        REVERSE_BOTTOM_HALF_ARROW("\\|", ""),
        TOP_STICK_HALF_ARROW("", "\\\\"),
        BOTTOM_STICK_HALF_ARROW("", "//"),
        REVERSE_TOP_STICK_HALF_ARROW("//", ""),
        REVERSE_BOTTOM_STICK_HALF_ARROW("\\\\", "");

        private final String left;
        private final String right;

        Head(String left, String right) {
            this.left = left;
            this.right = right;
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

    public SequenceMessage(String from, Line line, Head head, String to, String text) {
        this.from = from;
        this.line = line;
        this.head = head;
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

    public SequenceMessage fromCentral() {
        return fromCentral(true);
    }

    public SequenceMessage fromCentral(boolean fromCentral) {
        this.fromCentral = fromCentral;
        return this;
    }

    public SequenceMessage toCentral() {
        return toCentral(true);
    }

    public SequenceMessage toCentral(boolean toCentral) {
        this.toCentral = toCentral;
        return this;
    }

    @Override
    public void writeTo(MermaidWriter writer, int level) {
        writer.indent(level);
        writer.write(from);
        if (fromCentral) {
            writer.write("()");
        }
        writer.write(head.left);
        writer.write(line.value);
        writer.write(head.right);
        writer.write(activation.suffix);
        if (toCentral) {
            writer.write("()");
        }
        writer.write(to);
        writer.write(":");
        if (text != null) {
            writer.write(" ");
            writer.write(text);
        }
        writer.eol();
    }
}
