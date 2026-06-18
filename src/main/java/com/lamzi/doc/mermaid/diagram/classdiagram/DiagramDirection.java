package com.lamzi.doc.mermaid.diagram.classdiagram;

import com.lamzi.doc.mermaid.diagram.DiagramElement;
import com.lamzi.doc.mermaid.diagram.MermaidWriter;

public class DiagramDirection implements DiagramElement {
    public enum Direction {
        RL, LR, TB, BT
    }

    private Direction direction;

    public DiagramDirection(Direction direction) {
        this.direction = direction;
    }

    @Override
    public void writeTo(MermaidWriter writer, int level) {
        writer.indent(level);
        writer.write("direction ");
        writer.write(direction.name());
        writer.eol();

    }
}
