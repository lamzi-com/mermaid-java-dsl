package com.lamzi.doc.mermaid.diagram.flowchart;

import com.lamzi.doc.mermaid.diagram.MermaidWriter;
import com.lamzi.doc.mermaid.diagram.config.DiagramConfiguration;

import java.util.HashMap;
import java.util.Map;


public class FlowchartDiagramConfiguration extends DiagramConfiguration {

    private Map<String, String> flowchartProperties = new HashMap<>();

    public FlowchartDiagramConfiguration htmlLabels(boolean htmlLabels) {
        this.configs.add(new HtmlLabelsConfig(htmlLabels));
        return this;
    }

    public FlowchartDiagramConfiguration curve(String curve) {
        this.flowchartProperties.put("curve", curve);
        return this;
    }

    @Override
    public void writeTo(MermaidWriter writer) {
        super.writeTo(writer);
        if (!flowchartProperties.isEmpty()) {
            writer.indent(1);
            writer.write("flowchart:");
            writer.eol();
            for (Map.Entry<String, String> entry : flowchartProperties.entrySet()) {
                writer.indent(2);
                writer.write(entry.getKey());
                writer.write(": ");
                writer.write(entry.getValue());
                writer.eol();
            }
        }
    }

    public FlowchartDiagramConfiguration defaultRenderer(String defaultRenderer) {
        flowchartProperties.put("defaultRenderer", defaultRenderer);
        return this;
    }
}