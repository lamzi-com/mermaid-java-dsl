package com.lamzi.doc.mermaid.diagram.flowchart;

import com.lamzi.doc.mermaid.diagram.MermaidWriter;

import java.util.ArrayList;
import java.util.List;

public class Link implements FlowchartDiagramElement, SubgraphElement{
    private final List<Node> from = new ArrayList<>();
    private final LinkTo to;

    public Link(Node from, LinkTo linkTo) {
        this.from.add(from);
        to = linkTo;
    }

    public Link(List<Node> from, LinkTo linkTo) {
        this.from.addAll(from);
        to = linkTo;
    }

    @Override
    public void writeTo(MermaidWriter writer, int level) {
        writer.indent(level);
        for (int i = 0; i < from.size(); i++) {
            from.get(i).writeToInline(writer);
            if (i < from.size() - 1) {
                writer.write(" & ");
            }
        }
        to.writeTo(writer);
        writer.eol();
    }
}
