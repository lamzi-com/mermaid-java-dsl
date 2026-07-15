package com.lamzi.doc.mermaid.diagram.sequencediagram;

import com.lamzi.doc.mermaid.diagram.MermaidException;

public class SequenceNumber {
    private static final String NUMBER_PATTERN = "\\d+(\\.\\d{1,2})?";

    private final String value;

    public SequenceNumber(String value) {
        if (value == null || !value.matches(NUMBER_PATTERN)) {
            throw new MermaidException("Sequence number must be a positive integer or a decimal number with two digits");
        }
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
