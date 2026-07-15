package com.lamzi.doc.mermaid.diagram.sequencediagram;

import com.lamzi.doc.mermaid.diagram.internal.MermaidWriter;

import java.util.ArrayList;
import java.util.List;

public class SequenceBox implements SequenceDiagramElement {
    private String text;

    private final List<SequenceDiagramElement> elements = new ArrayList<>();
    private String color;

    public SequenceBox() {
    }

    public SequenceBox color(String color) {
        this.color = color;
        return this;
    }

    public SequenceBox text(String text) {
        this.text = text;
        return this;
    }

    public SequenceBox participant(SequenceParticipant participant) {
        elements.add(participant);
        return this;
    }

    public SequenceBox actor(SequenceActor actor) {
        elements.add(actor);
        return this;
    }

    public SequenceBox comment(String comment) {
        return comment(new SequenceComment(comment));
    }

    public SequenceBox comment(SequenceComment comment) {
        elements.add(comment);
        return this;
    }

    @Override
    public void writeTo(MermaidWriter writer, int level) {
        writer.indent(level);
        writer.write("box");
        if (color != null) {
            writer.write(" ");
            writer.write(color);
        }
        if (text != null && !text.isBlank()) {
            writer.write(" ");
            writer.write(text);
        }
        writer.eol();

        for (SequenceDiagramElement element : elements) {
            element.writeTo(writer, level + 1);
        }

        writer.indent(level);
        writer.write("end");
        writer.eol();
    }
}
