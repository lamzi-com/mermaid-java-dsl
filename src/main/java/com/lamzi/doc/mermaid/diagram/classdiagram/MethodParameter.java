package com.lamzi.doc.mermaid.diagram.classdiagram;

import com.lamzi.doc.mermaid.diagram.MermaidWriter;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MethodParameter {
    private String name;
    private Type type;

    MethodParameter(String name) {
        this.name = name;
    }

    public void generate(MermaidWriter writer) {
        if(type != null) {
            type.writeTo(writer);
            writer.write(" ");
        }
        writer.write(name);
    }

    public MethodParameter type(Type type) {
        this.type = type;
        return this;
    }
}
