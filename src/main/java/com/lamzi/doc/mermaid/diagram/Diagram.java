package com.lamzi.doc.mermaid.diagram;

import com.lamzi.doc.mermaid.diagram.config.DiagramConfiguration;

import java.util.ArrayList;
import java.util.List;

public abstract class Diagram<T extends DiagramConfiguration, E extends DiagramElement> {
    private final DiagramFrontMatter<T> frontMatter;
    private final String diagramName;
    private final List<E> elements = new ArrayList<>();

    public Diagram(DiagramFrontMatter<T> frontMatter, String diagramName) {
        this.frontMatter = frontMatter;
        this.diagramName = diagramName;
    }

    public final String generate() {
        MermaidWriter writer = new MermaidWriter();
        generateConfiguration(writer);
        generateHeadline(writer);
        writer.eol();
        for (DiagramElement element : elements) {
            element.writeTo(writer, 1);
        }
        return writer.toString();
    }

    protected void generateHeadline(MermaidWriter writer) {
        writer.write(diagramName);
    }


    private void generateConfiguration(MermaidWriter writer) {
        if (frontMatter != null) {
            frontMatter.writeTo(writer);
        }
    }

    public void addElement(E element) {
        this.elements.add(element);
    }

}
