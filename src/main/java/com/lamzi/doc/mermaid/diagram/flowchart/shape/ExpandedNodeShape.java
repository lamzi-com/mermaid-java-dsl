package com.lamzi.doc.mermaid.diagram.flowchart.shape;

import com.lamzi.doc.mermaid.diagram.MermaidWriter;

public class ExpandedNodeShape implements NodeShape {

    private final String shape;
    private String label;

    public static final String BANG = "bang";
    public static final String NOTCHED_RECTANGLE = "notch-rect";
    public static final String CLOUD = "cloud";
    public static final String HOURGLASS = "hourglass";
    public static final String LIGHTNING_BOLT = "bolt";
    public static final String CURVY_BRACE_LEFT = "brace";
    public static final String CURVY_BRACE_RIGHT = "brace-r";
    public static final String CURVY_BRACE_BOTH = "braces";
    public static final String LEAN_RIGHT = "lean-r";
    public static final String LEAN_LEFT = "lean-l";
    public static final String DATA_STORE = "datastore";
    public static final String CYLINDER = "cyl";
    public static final String DIAMOND = "diam";
    public static final String HALF_ROUNDED_RECTANGLE = "delay";
    public static final String HORIZONTAL_CYLINDER = "h-cyl";
    public static final String LINED_CYLINDER = "lin-cyl";
    public static final String CURVED_TRAPEZOID = "curv-trap";
    public static final String DIVIDED_RECTANGLE = "div-rect";
    public static final String DOCUMENT = "doc";
    public static final String ROUNDED_RECTANGLE = "rounded";
    public static final String TRIANGLE = "tri";
    public static final String FILLED_RECTANGLE = "fork";
    public static final String WINDOW_PANE = "win-pane";
    public static final String FILLED_CIRCLE = "f-circ";
    public static final String LINED_DOCUMENT = "lin-doc";
    public static final String LINED_RECTANGLE = "lin-rect";
    public static final String TRAPEZOIDAL_PENTAGON = "notch-pent";
    public static final String FLIPPED_TRIANGLE = "flip-tri";
    public static final String SLOPED_RECTANGLE = "sl-rect";
    public static final String TRAPEZOID_BASE_TOP = "trap-t";
    public static final String STACKED_DOCUMENTS = "docs";
    public static final String STACKED_RECTANGLE = "st-rect";
    public static final String ODD = "odd";
    public static final String FLAG = "flag";
    public static final String HEXAGON = "hex";
    public static final String TRAPEZOID_BASE_BOTTOM = "trap-b";
    public static final String RECTANGLE = "rect";
    public static final String CIRCLE = "circle";
    public static final String SMALL_CIRCLE = "sm-circ";
    public static final String DOUBLE_CIRCLE = "dbl-circ";
    public static final String FRAMED_CIRCLE = "fr-circ";
    public static final String BOW_TIE_RECTANGLE = "bow-rect";
    public static final String FRAMED_RECTANGLE = "fr-rect";
    public static final String CROSSED_CIRCLE = "cross-circ";
    public static final String TAGGED_DOCUMENT = "tag-doc";
    public static final String TAGGED_RECTANGLE = "tag-rect";
    public static final String STADIUM = "stadium";
    public static final String TEXT_BLOCK = "text";

    public ExpandedNodeShape(String label, String shape) {
        this.shape = shape;
        this.label = label;
    }

    @Override
    public void writeTo(MermaidWriter writer) {
        writer.write("@{ shape: ");
        writer.write(shape);
        if (label != null) {
            writer.write(", label: \"");
            writer.write(label);
            writer.write("\"");
        }
        writer.write(" }");
    }
}
