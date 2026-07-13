package com.lamzi.doc.mermaid.diagram.sequencediagram;

import com.lamzi.doc.mermaid.diagram.internal.MermaidWriter;

import java.util.Arrays;
import java.util.List;

public class SequenceNote implements SequenceDiagramElement {
    private final Position position;
    private final List<String> actors;
    private String text;

    public enum Position {
        RIGHT_OF("right of"),
        LEFT_OF("left of"),
        OVER("over");

        private final String value;

        Position(String value) {
            this.value = value;
        }
    }

    public SequenceNote(Position position, String text, String... actors) {
        this.position = position;
        this.text = text;
        this.actors = Arrays.asList(actors);
    }

    public SequenceNote text(String text) {
        this.text = text;
        return this;
    }

    @Override
    public void writeTo(MermaidWriter writer, int level) {
        writer.indent(level);
        writer.write("Note ");
        writer.write(position.value);
        writer.write(" ");
        writer.write(String.join(",", actors));
        writer.write(": ");
        writer.write(text);
        writer.eol();
    }
}
