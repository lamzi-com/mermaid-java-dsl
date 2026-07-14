package com.lamzi.doc.mermaid.diagram.sequencediagram;

import com.lamzi.doc.mermaid.diagram.MermaidException;
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

    private SequenceNote(Position position, String text, String... actors) {
        if (!Position.OVER.equals(position) && actors.length != 1) {
            throw new MermaidException("Only over notes support multiple actors");
        }
        this.position = position;
        this.text = text;
        this.actors = Arrays.asList(actors);
    }

    public static SequenceNote rightOf(String actor, String text) {
        return new SequenceNote(Position.RIGHT_OF, text, actor);
    }

    public static SequenceNote leftOf(String actor, String text) {
        return new SequenceNote(Position.LEFT_OF, text, actor);
    }

    public static SequenceNote over(String text, String... actors) {
        return new SequenceNote(Position.OVER, text, actors);
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
