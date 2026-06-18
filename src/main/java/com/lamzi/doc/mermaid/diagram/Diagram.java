package com.lamzi.doc.mermaid.diagram;

import com.lamzi.doc.mermaid.diagram.config.DiagramConfiguration;

import java.util.ArrayList;
import java.util.List;

public abstract class Diagram<T extends DiagramConfiguration> {
    public DiagramFrontMatter<T> frontMatter;
    String diagramName;
    List<DiagramElement> elements = new ArrayList<>();

    public Diagram(DiagramFrontMatter<T> frontMatter, String diagramName) {
        this.frontMatter = frontMatter;
        this.diagramName = diagramName;
    }

    public final String generate() {
        MermaidWriter writer = new MermaidWriter();
        generateConfiguration(writer);
        writer.write(diagramName);
        writer.eol();
        for (DiagramElement element : elements) {
            element.writeTo(writer, 1);
        }
        return writer.toString();
    }


    private void generateConfiguration(MermaidWriter writer) {
        if (frontMatter != null) {
            frontMatter.writeTo(writer);
        }
    }

    public void addElement(DiagramElement element) {
        this.elements.add(element);
    }

}
