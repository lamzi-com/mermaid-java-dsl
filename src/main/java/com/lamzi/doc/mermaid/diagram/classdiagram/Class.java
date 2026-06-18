package com.lamzi.doc.mermaid.diagram.classdiagram;

import com.lamzi.doc.mermaid.diagram.DiagramElement;
import com.lamzi.doc.mermaid.diagram.MermaidException;
import com.lamzi.doc.mermaid.diagram.MermaidWriter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Class implements DiagramElement, NamespaceElement {
    private ClassName name;
    private List<ClassElement> members = new ArrayList<>();
    private String label;
    private String cssClass;
    private Annotation inlineAnnotation;

    public Class(Type name) {
        this.name = new ClassName(name);
    }

    @Override
    public void writeTo(MermaidWriter writer, int level) {
        writer.indent(level);
        writer.write("class ");
        name.writeTo(writer);
        if (label != null) {
            writer.write("[\"");
            writer.write(label);
            writer.write("\"]");
        }
        if (inlineAnnotation != null) {
            writer.write(" ");
            inlineAnnotation.writeWithoutEOL(writer, 0);
        }
        if (cssClass != null) {
            writer.write(":::");
            writer.write(cssClass);
        }
        if (!members.isEmpty()) {
            writer.write(" {");
            writer.eol();
            for (ClassElement member : members) {
                member.writeTo(writer, level + 1);
            }
            writer.indent(level);
            writer.write("}");
        }
        writer.eol();
    }

    public Class member(ClassElement classElement) {
        this.members.add(classElement);
        return this;
    }

    public Class label(String label) {
        this.label = label;
        return this;
    }

    public Class annotation(String annotation) {
        this.members.add(new Annotation(annotation));
        return this;
    }

    public Class comment(String comment) {
        this.members.add(new Comment(comment));
        return this;
    }

    public Class cssClass(String cssClass) {
        if (!Objects.isNull(inlineAnnotation)) {
            throw new MermaidException("defining inline annotation and cssClass is not supported");
        }
        this.cssClass = cssClass;
        return this;
    }

    public Class inlineAnnotation(String annotationName) {
        if (!Objects.isNull(cssClass)) {
            throw new MermaidException("defining inline annotation and cssClass is not supported");
        }
        this.inlineAnnotation = new Annotation(annotationName);
        return this;
    }
}
