package com.lamzi.mermaid.diagram;

import com.lamzi.mermaid.diagram.classdiagram.StyleDefinition;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CssClassDefinition implements DiagramElement {

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
