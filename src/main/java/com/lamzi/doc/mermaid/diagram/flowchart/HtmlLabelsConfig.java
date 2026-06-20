package com.lamzi.doc.mermaid.diagram.flowchart;

import com.lamzi.doc.mermaid.diagram.MermaidWriter;
import com.lamzi.doc.mermaid.diagram.config.Config;

public class HtmlLabelsConfig implements Config {
    private final boolean htmlLabelsConfig;

    public HtmlLabelsConfig(boolean htmlLabelsConfig) {
        this.htmlLabelsConfig = htmlLabelsConfig;
    }

    @Override
    public void writeTo(MermaidWriter writer, int level) {
        writer.indent(level);
        writer.write("htmlLabels: ");
        writer.write(String.valueOf(htmlLabelsConfig));
        writer.eol();
    }
}
