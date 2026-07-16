package com.lamzi.doc.mermaid.diagram.sequencediagram;

import com.lamzi.doc.mermaid.diagram.internal.MermaidWriter;

public class SequenceAutonumber implements SequenceDiagramElement {
    private final SequenceNumber start;
    private final SequenceNumber increment;

    public SequenceAutonumber() {
        this(null, null);
    }

    public SequenceAutonumber(SequenceNumber start) {
        this(start, null);
    }

    public SequenceAutonumber(SequenceNumber start, SequenceNumber increment) {
        this.start = start;
        this.increment = increment;
    }

    @Override
    public void writeTo(MermaidWriter writer, int level) {
        writer.indent(level);
        writer.write("autonumber");
        if (start != null) {
            writer.write(" ");
            writer.write(start.toString());
        }
        if (increment != null) {
            writer.write(" ");
            writer.write(increment.toString());
        }
        writer.eol();
    }
}
