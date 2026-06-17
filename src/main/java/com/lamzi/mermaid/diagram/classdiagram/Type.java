package com.lamzi.mermaid.diagram.classdiagram;

import com.lamzi.mermaid.diagram.MermaidWriter;

public class Type {
    String name;
    Type genericType;

    Type(String name) {
        this.name = name;
    }

    public Type genericType(Type genericType) {
        this.genericType = genericType;
        return this;
    }

    public void writeTo(MermaidWriter writer) {
        writer.write(name);
        if (genericType != null) {
            writer.write("~");
            genericType.writeTo(writer);
            writer.write("~");
        }
    }

    public boolean isvalid() {
        for (char c : name.toCharArray()) {
            if (!(Character.isAlphabetic(c) || Character.isDigit(c) || c == '_' || c == '-')) {
                return false;
            }
        }
        if (genericType != null) {
            if (!genericType.isvalid()) {
                return false;
            }
        }
        return true;
    }

    public void writeNameTo(MermaidWriter writer) {
        writer.write(name);
    }
}
