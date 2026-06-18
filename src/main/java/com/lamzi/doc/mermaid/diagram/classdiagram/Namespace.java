package com.lamzi.doc.mermaid.diagram.classdiagram;

import com.lamzi.doc.mermaid.diagram.DiagramElement;
import com.lamzi.doc.mermaid.diagram.MermaidException;
import com.lamzi.doc.mermaid.diagram.MermaidWriter;

import java.util.ArrayList;
import java.util.List;


public class Namespace implements DiagramElement {

    private final String name;
    List<NamespaceElement> elements = new ArrayList<>();
    private String label;

    public Namespace(String name) {
        if (name == null || name.isEmpty()) {
            throw new MermaidException("Namespace name cannot be null or empty");
        }
        if (name.equals("namespace")) {
            throw new MermaidException("Namespace cannot be named namespace");
        }
        this.name = name;
    }

    public Namespace addclass(Class clazz) {
        this.elements.add(clazz);
        return this;
    }

    @Override
    public void writeTo(MermaidWriter writer, int level) {
        writer.indent(level);
        writer.write("namespace ");
        writer.write(name);
        if (label != null) {
            writer.write("[\"");
            writer.write(label);
            writer.write("\"]");
        }
        writer.write(" {");
        writer.eol();
        for (NamespaceElement element : elements) {
            element.writeTo(writer, level + 1);
        }
        writer.indent(level);
        writer.write("}");
    }

    public Namespace comment(String comment) {
        this.elements.add(new Comment(comment));
        return this;
    }

    public Namespace label(String label) {
        this.label = label;
        return this;
    }
}
