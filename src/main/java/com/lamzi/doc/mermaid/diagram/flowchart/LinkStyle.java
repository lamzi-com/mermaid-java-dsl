package com.lamzi.doc.mermaid.diagram.flowchart;

import com.lamzi.doc.mermaid.diagram.MermaidWriter;
import com.lamzi.doc.mermaid.diagram.StyleDefinition;

import java.util.ArrayList;
import java.util.List;

public class LinkStyle implements FlowchartDiagramElement {

    private final StyleDefinition<FlowchartStyleDefinitionAttribute> styleDefinition;
    private List<Integer> linkNumbers = new ArrayList<>();

    public LinkStyle(StyleDefinition<FlowchartStyleDefinitionAttribute> styleDefinition, Integer... linkNumbers) {
        this.styleDefinition = styleDefinition;
        this.linkNumbers.addAll(List.of(linkNumbers));
    }

    @Override
    public void writeTo(MermaidWriter writer, int level) {
        writer.indent(level);
        writer.write("linkStyle ");
        writer.write(String.join(",", linkNumbers.stream().map(String::valueOf).toList()));
        writer.write(" ");
        styleDefinition.writeTo(writer);
        writer.write(";");
        writer.eol();

    }
}
