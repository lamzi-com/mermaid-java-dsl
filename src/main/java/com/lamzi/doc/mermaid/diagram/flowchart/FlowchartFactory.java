package com.lamzi.doc.mermaid.diagram.flowchart;

import com.lamzi.doc.mermaid.diagram.CssClassDefinition;
import com.lamzi.doc.mermaid.diagram.Style;
import com.lamzi.doc.mermaid.diagram.StyleDefinition;
import com.lamzi.doc.mermaid.diagram.flowchart.shape.ClassicNodeShape;
import com.lamzi.doc.mermaid.diagram.flowchart.shape.ExpandedNodeShape;
import com.lamzi.doc.mermaid.diagram.flowchart.shape.IconNodeShape;
import com.lamzi.doc.mermaid.diagram.flowchart.shape.ImageNodeShape;

import java.util.List;

public class FlowchartFactory {
    public static Node node(String id) {
        return new Node(id);
    }

    public static ClassicNodeShape classicNodeShape(String text, ClassicNodeShape.Shape shape) {
        return new ClassicNodeShape(text, shape);
    }

    public static ExpandedNodeShape expandedNodeShape(String label, String shape) {
        return new ExpandedNodeShape(label, shape);
    }

    public static IconNodeShape iconNodeShape(String icon) {
        return new IconNodeShape(icon);
    }

    public static ImageNodeShape imageNodeShape(String image) {
        return new ImageNodeShape(image);
    }

    public static Link link(Node from, LinkTo to) {
        return new Link(from, to);
    }

    public static Link link(List<Node> from, LinkTo to) {
        return new Link(from, to);
    }

    public static LinkTo linkTo(LinkTo.Type type, LinkTo.HeadType headType, Node... to) {
        return new LinkTo(type, headType, to);
    }

    public static EdgeIdStyle edgeIdStyle(String id) {
        return new EdgeIdStyle(id);
    }

    public static Subgraph subgraph(String name) {
        return new Subgraph(name);
    }

    public static NodeHrefClick hrefClick(String nodeId, String reference) {
        return new NodeHrefClick(nodeId, reference);
    }

    public static NodeCallbackClick callbackClick(String nodeId, String reference) {
        return new NodeCallbackClick(nodeId, reference);
    }

    public static Style<FlowchartStyleDefinitionAttribute> style(String id, StyleDefinition<FlowchartStyleDefinitionAttribute> styleDefinition) {
        return new Style<>(id, styleDefinition);
    }

    public static CssClassDefinition<FlowchartStyleDefinitionAttribute> cssClassDefinition(StyleDefinition<FlowchartStyleDefinitionAttribute> styleDefinition, String... classNames) {
        return new CssClassDefinition<>(List.of(classNames), styleDefinition);
    }
}
