package com.lamzi.doc.mermaid.diagram.config;

import com.lamzi.doc.mermaid.diagram.MermaidWriter;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class DiagramConfigurationTest {


    /***
     * When no configuration option is defined, only config: is written
     */
    @Test
    public void noConfigDefined() {
        MermaidWriter writer = new MermaidWriter();
        DiagramConfiguration diagramConfig = new DiagramConfiguration();
        diagramConfig.writeTo(writer);
        assertThat(writer.toString()).isEqualTo("config:");
    }

    @Test
    public void LayoutDagre() {
        MermaidWriter writer = new MermaidWriter();
        DiagramConfiguration diagramConfig = new DiagramConfiguration();
        diagramConfig.layout(LayoutConfig.Layout.DAGRE);
        diagramConfig.writeTo(writer);
        assertThat(writer.toString()).isEqualTo("config:\n    layout: dagre");
    }

    @Test
    public void LayoutElk() {
        MermaidWriter writer = new MermaidWriter();
        DiagramConfiguration diagramConfig = new DiagramConfiguration();
        diagramConfig.layout(LayoutConfig.Layout.ELK);
        diagramConfig.writeTo(writer);
        assertThat(writer.toString()).isEqualTo("config:\n    layout: elk");
    }

    @Test
    public void LayoutElkConfig() {
        MermaidWriter writer = new MermaidWriter();
        DiagramConfiguration diagramConfig = new DiagramConfiguration();
        diagramConfig.layout(LayoutConfig.Layout.ELK);
        diagramConfig.elk(new ElkConfig().mergeEdges(true).nodePlacementStrategy(ElkConfig.NodePlacementStrategy.SIMPLE));
        diagramConfig.writeTo(writer);
        assertThat(writer.toString()).isEqualTo("config:\n" +
                "    layout: elk\n" +
                "    elk:\n" +
                "        mergeEdges: true\n" +
                "        nodePlacementStrategy: SIMPLE");
    }
}