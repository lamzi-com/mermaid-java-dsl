package com.lamzi.doc.mermaid.diagram.sequencediagram;

public class SequenceRect extends SequenceBlock<SequenceRect> {
    @Override
    protected String kind() {
        return "rect";
    }

    public SequenceRect color(String color) {
        return header(color);
    }
}
