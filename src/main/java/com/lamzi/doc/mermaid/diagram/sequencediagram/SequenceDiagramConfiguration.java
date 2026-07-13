package com.lamzi.doc.mermaid.diagram.sequencediagram;

import com.lamzi.doc.mermaid.diagram.config.DiagramConfiguration;
import com.lamzi.doc.mermaid.diagram.internal.MermaidWriter;

import java.util.LinkedHashMap;
import java.util.Map;

public class SequenceDiagramConfiguration extends DiagramConfiguration {
    private final Map<String, String> sequenceProperties = new LinkedHashMap<>();

    public SequenceDiagramConfiguration showSequenceNumbers(boolean showSequenceNumbers) {
        sequenceProperties.put("showSequenceNumbers", Boolean.toString(showSequenceNumbers));
        return this;
    }

    public SequenceDiagramConfiguration mirrorActors(boolean mirrorActors) {
        sequenceProperties.put("mirrorActors", Boolean.toString(mirrorActors));
        return this;
    }

    public SequenceDiagramConfiguration actorMargin(int actorMargin) {
        sequenceProperties.put("actorMargin", Integer.toString(actorMargin));
        return this;
    }

    public SequenceDiagramConfiguration messageMargin(int messageMargin) {
        sequenceProperties.put("messageMargin", Integer.toString(messageMargin));
        return this;
    }

    public SequenceDiagramConfiguration boxMargin(int boxMargin) {
        sequenceProperties.put("boxMargin", Integer.toString(boxMargin));
        return this;
    }

    public SequenceDiagramConfiguration noteMargin(int noteMargin) {
        sequenceProperties.put("noteMargin", Integer.toString(noteMargin));
        return this;
    }

    public SequenceDiagramConfiguration rightAngles(boolean rightAngles) {
        sequenceProperties.put("rightAngles", Boolean.toString(rightAngles));
        return this;
    }

    @Override
    public void writeTo(MermaidWriter writer) {
        super.writeTo(writer);
        if (!sequenceProperties.isEmpty()) {
            writer.indent(1);
            writer.write("sequence:");
            writer.eol();
            for (Map.Entry<String, String> entry : sequenceProperties.entrySet()) {
                writer.indent(2);
                writer.write(entry.getKey());
                writer.write(": ");
                writer.write(entry.getValue());
                writer.eol();
            }
        }
    }
}
