package com.lamzi.doc.mermaid.diagram.flowchart;

import com.lamzi.doc.mermaid.diagram.MermaidWriter;
import com.lamzi.doc.mermaid.diagram.Writable;

public class ClassicNodeShape implements NodeShape {


    public enum Shape {

        ROUNDED_EDGES("(", ")"),
        SQUARE_EDGES("[", "]"),
        STADIUM("([", "])"),
        SUBROUTINE("[[", "]]"),
        CYLINDRICAL("[(", ")]"),
        CIRCLE("((", "))"),
        ASYMMETRIC(">", "]"),
        RHOMBUS("{", "}"),
        HEXAGON("{{", "}}"),
        PARALLELOGRAM("[/", "/]"),
        PARALLELOGRAM_ALT("[\\", "\\]"),
        TRAPEZOID("[/", "\\]"),
        TRAPEZOID_ALT("[\\", "/]"),
        DOUBLE_CIRCLE("(((", ")))");

        private final String left;
        private final String right;


        Shape(String left, String right) {
            this.left = left;
            this.right = right;
        }

        public void writeTo(MermaidWriter writer, String text) {
            writer.write(left);
            writer.write(text);
            writer.write(right);
        }
    }

    private String text;
    private Shape shape;

    public ClassicNodeShape(String text, Shape shape) {
        this.text = text;
        this.shape = shape;
    }

    @Override
    public void writeTo(MermaidWriter writer) {
        shape.writeTo(writer, text);
    }
}
