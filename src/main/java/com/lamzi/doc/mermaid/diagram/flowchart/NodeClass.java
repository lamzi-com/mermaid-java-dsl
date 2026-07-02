package com.lamzi.doc.mermaid.diagram.flowchart;

import com.lamzi.doc.mermaid.diagram.MermaidWriter;

public class NodeClass implements FlowchartDiagramElement {

    private final String className;
    private final String[] nodeIds;

    public NodeClass(String className, String... nodeIds) {
        this.className = className;
        this.nodeIds = nodeIds;
    }

    @Override
    public void writeTo(MermaidWriter writer, int level) {
        writer.indent(level);
        writer.write("class ");
        writer.write(String.join(",", nodeIds));
        writer.write(" ");
        writer.write(className);
        writer.eol();
    }
}
