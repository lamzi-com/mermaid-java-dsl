package com.lamzi.doc.mermaid.diagram.flowchart;

import com.lamzi.doc.mermaid.diagram.MermaidWriter;

public class Edge implements FlowchartDiagramElement {
    String from;
    String to;

    public Edge(String from, String to) {
        this.from = from;
        this.to = to;
    }


    @Override
    public void writeTo(MermaidWriter writer, int level) {
        writer.indent(level);
        writer.write(from);
        writer.write(" --> ");
        writer.write(to);
        writer.eol();
    }
}
