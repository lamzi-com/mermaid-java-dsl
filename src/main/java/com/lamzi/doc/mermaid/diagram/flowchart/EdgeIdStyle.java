package com.lamzi.doc.mermaid.diagram.flowchart;

import com.lamzi.doc.mermaid.diagram.MermaidWriter;

import java.util.HashMap;
import java.util.Map;

public class EdgeIdStyle implements FlowchartDiagramElement {

    private Map<String, String> properties = new HashMap<>();

    public EdgeIdStyle curve(String curve) {
        properties.put("curve", curve);
        return this;
    }

    public enum Animation {FAST, SLOW}

    private final String id;


    public EdgeIdStyle(String id) {
        this.id = id;
    }

    public EdgeIdStyle animate(boolean animate) {
        properties.put("animate", String.valueOf(animate));
        return this;
    }

    @Override
    public void writeTo(MermaidWriter writer, int level) {
        writer.indent(level);
        writer.write(id);
        writer.write("@{ ");
        writer.write(String.join(", ", properties.entrySet().stream().map(e -> e.getKey() + ": " + e.getValue()).toList()));
        writer.write(" }");
        writer.eol();
    }

    public EdgeIdStyle animation(Animation animation) {
        properties.put("animation", animation.name().toLowerCase());
        return this;
    }
}
