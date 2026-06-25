package com.lamzi.doc.mermaid.diagram.flowchart;

import com.lamzi.doc.mermaid.diagram.Comment;
import com.lamzi.doc.mermaid.diagram.CssClassDefinition;
import com.lamzi.doc.mermaid.diagram.Diagram;
import com.lamzi.doc.mermaid.diagram.DiagramFrontMatter;
import com.lamzi.doc.mermaid.diagram.MermaidWriter;
import com.lamzi.doc.mermaid.diagram.StyleDefinition;

import static com.lamzi.doc.mermaid.diagram.flowchart.FlowchartFactory.node;

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

    public FlowchartDiagram addLink(String from, String to) {
        this.addElement(new Link(node(from), new LinkTo(LinkTo.Type.SIMPLE_LINK, LinkTo.HeadType.ARROW, node(to))));
        return this;
    }

    public FlowchartDiagram addLink(Link link) {
        this.addElement(link);
        return this;
    }


    public FlowchartDiagram cssClassDefinition(String className, StyleDefinition<FlowchartStyleDefinitionAttribute> styleDefinition) {
        this.addElement(new CssClassDefinition<>(className, styleDefinition));
        return this;
    }

    public FlowchartDiagram nodeClass(String id, String... nodeIds) {
        this.addElement(new NodeClass(id, nodeIds));
        return this;
    }

    public FlowchartDiagram addComment(String content) {
        this.addElement(new Comment(content));
        return this;
    }

    public FlowchartDiagram addLinkAnimation(LinkAnimation linkAnimation) {
        this.addElement(linkAnimation);
        return this;
    }
}