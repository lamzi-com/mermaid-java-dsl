package com.lamzi.doc.mermaid.diagram.flowchart.shape;

import com.lamzi.doc.mermaid.diagram.MermaidWriter;

/**
 * https://mermaid.js.org/syntax/flowchart.html#image-shape
 */
public class ImageNodeShape implements NodeShape {


    public enum Position {
        TOP("t"), BOTTOM("b");

        public final String pos;

        Position(String pos) {
            this.pos = pos;
        }
    }

    private final String image;
    private String label;
    private Position position;
    private Integer w;
    private Integer h;
    private Constraint constraint;

    public ImageNodeShape(String image) {
        this.image = image;
    }


    public ImageNodeShape label(String label) {
        this.label = label;
        return this;
    }

    public ImageNodeShape position(Position position) {
        this.position = position;
        return this;
    }

    public ImageNodeShape width(Integer w) {
        this.w = w;
        return this;
    }

    public ImageNodeShape height(Integer h) {
        this.h = h;
        return this;
    }

    public ImageNodeShape constraint(Constraint constraint){
        this.constraint = constraint;
        return this;
    }

    public enum Constraint{ON, OFF};


    @Override
    public void writeTo(MermaidWriter writer) {
        ///   A@{ img: "https://mermaid.js.org/favicon.svg", label: "My example image label", pos: "t", h: 60, constraint: "on" }

        writer.write("@{ img: \"");
        writer.write(image);
        writer.write("\"");
        if (label != null) {
            writer.write(", label: \"");
            writer.write(label);
            writer.write("\"");
        }
        if (position != null) {
            writer.write(", pos: \"");
            writer.write(position.pos);
            writer.write("\"");
        }
        if (w != null) {
            writer.write(", w: ");
            writer.write(w.toString());
        }
        if (h != null) {
            writer.write(", h: ");
            writer.write(h.toString());
        }
        if(constraint!=null){
            writer.write(", constraint: \"");
            writer.write(constraint.name().toLowerCase());
            writer.write("\"");
        }
        writer.write(" }");
    }
}
