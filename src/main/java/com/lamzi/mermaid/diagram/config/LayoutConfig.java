package com.lamzi.mermaid.diagram.config;

import com.lamzi.mermaid.diagram.MermaidWriter;

public class LayoutConfig implements Config {
    private Layout layout;

    public enum Layout {DAGRE, ELK}

    public void layout(Layout layout) {
        this.layout = layout;
    }

    public LayoutConfig(Layout layout) {
        this.layout = layout;
    }

    @Override
    public void writeTo(MermaidWriter writer, int level) {
        if (layout != null) {
            writer.indent(1);
            writer.write("layout: ");
            writer.write(layout.name().toLowerCase());
            writer.eol();
        }
    }
}
