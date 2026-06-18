package com.lamzi.doc.mermaid.diagram;

public interface IndentedWritable {
    void writeTo(MermaidWriter writer, int level);
}
