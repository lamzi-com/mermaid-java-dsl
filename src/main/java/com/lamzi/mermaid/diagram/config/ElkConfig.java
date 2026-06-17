package com.lamzi.mermaid.diagram.config;

import com.lamzi.mermaid.diagram.MermaidWriter;

public class ElkConfig implements Config {

    private Boolean mergeEdges;

    public enum NodePlacementStrategy {
        SIMPLE, NETWORK_SIMPLEX, LINEAR_SEGMENTS, BRANDES_KOEPF;
    }

    private NodePlacementStrategy nodePlacementStrategy;

    public ElkConfig mergeEdges(Boolean mergeEdges) {
        this.mergeEdges = mergeEdges;
        return this;
    }

    public ElkConfig nodePlacementStrategy(NodePlacementStrategy nodePlacementStrategy) {
        this.nodePlacementStrategy = nodePlacementStrategy;
        return this;
    }


    @Override
    public void writeTo(MermaidWriter writer, int level) {
        writer.indent(level);
        writer.write("elk:");
        writer.eol();
        if (mergeEdges != null) {
            writer.indent(level + 1);
            writer.write("mergeEdges: ");
            writer.write(Boolean.toString(mergeEdges));
            writer.eol();
        }
        if (nodePlacementStrategy != null) {
            writer.indent(level + 1);
            writer.write("nodePlacementStrategy: ");
            writer.write(nodePlacementStrategy.name());
            writer.eol();
        }
    }
}
