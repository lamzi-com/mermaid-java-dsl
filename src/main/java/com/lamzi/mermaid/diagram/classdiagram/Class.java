package com.lamzi.mermaid.diagram.classdiagram;

import com.lamzi.mermaid.diagram.DiagramElement;
import com.lamzi.mermaid.diagram.MermaidWriter;

import java.util.ArrayList;
import java.util.List;

public class Class implements DiagramElement, NamespaceElement {
    private ClassName name;
    private List<ClassElement> members = new ArrayList<>();
    private String label;
    private String cssClass;

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
        this.cssClass = cssClass;
        return this;
    }
}
