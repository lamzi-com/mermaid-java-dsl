package com.lamzi.doc.mermaid.diagram.internal;

public interface IndentedWritable {
    void writeTo(MermaidWriter writer, int level);
}
