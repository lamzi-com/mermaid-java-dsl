package com.lamzi.doc.mermaid.diagram.flowchart;

import com.lamzi.doc.mermaid.diagram.MermaidWriter;
import com.lamzi.doc.mermaid.diagram.classdiagram.relation.Direction;

import java.util.ArrayList;
import java.util.List;

import static com.lamzi.doc.mermaid.diagram.flowchart.FlowchartFactory.node;

public class Subgraph implements FlowchartDiagramElement {

    private final List<SubgraphElement> elements = new ArrayList<>();
    private final String name;
    private String label;
    private FlowchartDirection direction;

    public Subgraph(String name) {
        this.name = name;
    }

    @Override
    public void writeTo(MermaidWriter writer, int level) {
        writer.indent(level);
        writer.write("subgraph ");
        writer.write(name);
        if (label != null) {
            writer.write(" [");
            writer.write(label);
            writer.write("]");
        }
        writer.eol();
        if (direction != null) {
            writer.indent(level + 1);
            writer.write("direction ");
            writer.write(direction.toString());
            writer.eol();
        }
        for (var element : elements) {
            element.writeTo(writer, level + 1);
        }
        writer.indent(level);
        writer.write("end");
        writer.eol();
    }

    public Subgraph addLink(String from, String to) {
        this.elements.add(new Link(node(from), new LinkTo(LinkTo.Type.SIMPLE_LINK, LinkTo.HeadType.ARROW, node(to))));
        return this;
    }

    public Subgraph addLink(Link link) {
        this.elements.add(link);
        return this;
    }

    public Subgraph label(String label) {
        this.label = label;
        return this;
    }

    public Subgraph direction(FlowchartDirection direction) {
        this.direction = direction;
        return this;
    }
}
