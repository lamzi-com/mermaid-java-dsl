package com.lamzi.mermaid.diagram.classdiagram;

import com.lamzi.mermaid.diagram.MermaidWriter;


public class ClassAttribute implements ClassElement {
    private final String attributeName;
    private Visibility visibility;
    private Type type;
    private boolean renderUsingColon = false;

    public ClassAttribute(String attributeName) {
        this.attributeName = attributeName;
    }

    public ClassAttribute visibility(Visibility visibility) {
        this.visibility = visibility;
        return this;
    }

    public ClassAttribute type(Type type) {
        this.type = type;
        return this;
    }

    @Override
    public void writeTo(MermaidWriter writer, int level) {
        writer.indent(level);
        if (visibility != null) {
            writer.write(visibility.code);
        }
        if (renderUsingColon) {
            writer.write(attributeName);
            if (type != null) {
                writer.write(": ");
                type.writeTo(writer);
            }

        } else {
            if (type != null) {
                type.writeTo(writer);
                writer.write(" ");
            }
            writer.write(attributeName);
        }
        writer.eol();
    }

    public ClassAttribute colon() {
        renderUsingColon = true;
        return this;
    }
}
