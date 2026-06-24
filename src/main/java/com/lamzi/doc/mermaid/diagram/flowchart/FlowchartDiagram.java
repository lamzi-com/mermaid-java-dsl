package com.lamzi.doc.mermaid.diagram.flowchart;

import com.lamzi.doc.mermaid.diagram.CssClassDefinition;
import com.lamzi.doc.mermaid.diagram.Diagram;
import com.lamzi.doc.mermaid.diagram.DiagramFrontMatter;
import com.lamzi.doc.mermaid.diagram.MermaidWriter;
import com.lamzi.doc.mermaid.diagram.StyleDefinition;

/**
 * documentation https://mermaid.js.org/syntax/flowchart.html
 */
public class FlowchartDiagram extends Diagram<FlowchartDiagramConfiguration, FlowchartDiagramElement> {

    private FlowchartDirection direction;

    public FlowchartDiagram(DiagramFrontMatter<FlowchartDiagramConfiguration> frontMatter) {
        super(frontMatter, "flowchart");
    }

    public FlowchartDiagram() {
        this(null);
    }

    protected void generateHeadline(MermaidWriter writer) {
        super.generateHeadline(writer);
        if (direction != null) {
            writer.write(" ");
            writer.write(direction.toString());
        }
    }

    public FlowchartDiagram direction(FlowchartDirection direction) {
        this.direction = direction;
        return this;
    }

    public FlowchartDiagram addNode(String id) {
        return addNode(new Node(id));
    }


    public FlowchartDiagram addNode(Node node) {
        addElement(node);
        return this;
    }

    public FlowchartDiagram addEdge(String from, String to) {
        this.addElement(new Edge(from, to));
        return this;
    }


    public FlowchartDiagram cssClassDefinition(String className, StyleDefinition styleDefinition) {
        this.addElement(new CssClassDefinition(className, styleDefinition));
        return this;
    }

    public FlowchartDiagram nodeClass(String test, String... nodeIds) {
        this.addElement(new NodeClass(test, nodeIds));
        return this;
    }
}