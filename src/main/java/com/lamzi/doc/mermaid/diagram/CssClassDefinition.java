package com.lamzi.doc.mermaid.diagram;

import com.lamzi.doc.mermaid.diagram.classdiagram.ClassDiagramElement;
import com.lamzi.doc.mermaid.diagram.flowchart.FlowchartDiagramElement;

import java.util.ArrayList;
import java.util.List;

public class CssClassDefinition implements ClassDiagramElement, FlowchartDiagramElement {

    private final List<String> classNames = new ArrayList<>();
    private final StyleDefinition styleDefinition;

    public CssClassDefinition(String className, StyleDefinition styleDefinition) {
        this.classNames.add(className);
        this.styleDefinition = styleDefinition;
    }

    public CssClassDefinition(List<String> classNames, StyleDefinition styleDefinition) {
        this.classNames.addAll(classNames);
        this.styleDefinition = styleDefinition;
    }

    @Override
    public void writeTo(MermaidWriter writer, int level) {
        writer.indent(level);
        writer.write("classDef ");
        writer.write(String.join(",", classNames));
        writer.write(" ");
        styleDefinition.writeTo(writer);
        writer.write(";");
        writer.eol();
    }
}
