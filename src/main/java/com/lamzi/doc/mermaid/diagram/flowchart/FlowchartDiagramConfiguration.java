package com.lamzi.doc.mermaid.diagram.flowchart;

import com.lamzi.doc.mermaid.diagram.config.DiagramConfiguration;


public class FlowchartDiagramConfiguration extends DiagramConfiguration {

    public FlowchartDiagramConfiguration htmlLabels(boolean htmlLabels) {
        this.configs.add(new HtmlLabelsConfig(htmlLabels));
        return this;
    }
}