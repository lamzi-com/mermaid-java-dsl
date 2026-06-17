package com.lamzi.mermaid.diagram;

public interface IndentedWritable {
    void writeTo(MermaidWriter writer, int level);
}
