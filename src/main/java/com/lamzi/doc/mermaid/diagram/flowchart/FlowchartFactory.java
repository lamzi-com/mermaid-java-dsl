package com.lamzi.doc.mermaid.diagram.flowchart;

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
}
