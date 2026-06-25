package com.lamzi.doc.mermaid.diagram.flowchart;

import com.lamzi.doc.mermaid.diagram.MermaidWriter;

public class LinkAnimation implements FlowchartDiagramElement {

    private Animation animation;

    public enum Animation {FAST, SLOW}

    private final String id;

    private Boolean animate;

    public LinkAnimation(String id) {
        this.id = id;
    }

    public LinkAnimation animate(boolean animate) {
        this.animate = animate;
        return this;
    }

    @Override
    public void writeTo(MermaidWriter writer, int level) {
        writer.indent(level);
        writer.write(id);
        writer.write("@{");
        if (animate != null) {
            writer.write(" animate: ");
            writer.write(animate.toString());
        }
        if (animation != null) {
            writer.write(" animation: ");
            writer.write(animation.name().toLowerCase());
        }
        writer.write(" }");
        writer.eol();
    }

    public LinkAnimation animation(Animation animation) {
        this.animation = animation;
        return this;
    }
}
