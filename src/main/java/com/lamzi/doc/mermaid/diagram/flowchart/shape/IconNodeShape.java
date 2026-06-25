package com.lamzi.doc.mermaid.diagram.flowchart.shape;

import com.lamzi.doc.mermaid.diagram.MermaidWriter;

/**
 * https://mermaid.js.org/syntax/flowchart.html#icon-shape
 */
public class IconNodeShape implements NodeShape {

    public enum Form {
        SQUARE, CIRCLE, ROUNDED;
    }

    public enum Position {
        TOP("t"), BOTTOM("b");

        public final String pos;

        Position(String pos) {
            this.pos = pos;
        }
    }

    private final String icon;
    private Form form;
    private String label;
    private Position position;
    private Integer h;

    public IconNodeShape(String icon) {
        this.icon = icon;
    }

    public IconNodeShape form(Form form) {
        this.form = form;
        return this;
    }

    public IconNodeShape label(String label) {
        this.label = label;
        return this;
    }

    public IconNodeShape position(Position position) {
        this.position = position;
        return this;
    }

    public IconNodeShape height(Integer h) {
        this.h = h;
        return this;
    }


    @Override
    public void writeTo(MermaidWriter writer) {
        /// A@{ icon: "fa:user", form: "square", label: "User Icon", pos: "t", h: 60 }

        writer.write("@{ icon: \"");
        writer.write(icon);
        writer.write("\"");
        if (form != null) {
            writer.write(", form: \"");
            writer.write(form.toString().toLowerCase());
            writer.write("\"");
        }
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
        if (h != null) {
            writer.write(", h: ");
            writer.write(h.toString());
        }
        writer.write(" }");
    }
}
