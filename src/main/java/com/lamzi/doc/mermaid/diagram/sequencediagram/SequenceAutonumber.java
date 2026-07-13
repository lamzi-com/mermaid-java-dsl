package com.lamzi.doc.mermaid.diagram.sequencediagram;

import com.lamzi.doc.mermaid.diagram.internal.MermaidWriter;

public class SequenceAutonumber implements SequenceDiagramElement {
    private final String start;
    private final String increment;

    public SequenceAutonumber() {
        this(null, null);
    }

    public SequenceAutonumber(String start, String increment) {
        this.start = start;
        this.increment = increment;
    }

    @Override
    public void writeTo(MermaidWriter writer, int level) {
        writer.indent(level);
        writer.write("autonumber");
        if (start != null) {
            writer.write(" ");
            writer.write(start);
        }
        if (increment != null) {
            writer.write(" ");
            writer.write(increment);
        }
        writer.eol();
    }
}
