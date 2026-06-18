package com.lamzi.doc.mermaid.diagram.classdiagram;

import com.lamzi.doc.mermaid.diagram.MermaidWriter;

import java.util.ArrayList;
import java.util.List;

public class ClassMethod implements ClassElement {
    private final String methodName;
    private Visibility visibility;
    private String type;
    private List<MethodParameter> parameters = new ArrayList<>();
    private Type returnType;
    private String abstractOrStatic = "";

    public ClassMethod(String methodName) {
        this.methodName = methodName;
    }

    public ClassMethod visibility(Visibility visibility) {
        this.visibility = visibility;
        return this;
    }

    public ClassMethod type(String type) {
        this.type = type;
        return this;
    }

    @Override
    public void writeTo(MermaidWriter writer, int level) {
        writer.indent(level);
        if (visibility != null) {
            writer.write(visibility.code);
        }
        if (type != null) {
            writer.write(type);
            writer.write(" ");
        }
        writer.write(methodName);
        writer.write("(");
        boolean first = true;
        for (MethodParameter parameter : parameters) {
            if (!first) {
                writer.write(", ");
            }
            first = false;
            parameter.generate(writer);
        }
        writer.write(")");
        if (returnType != null) {
            writer.write(" ");
            returnType.writeTo(writer);
        }
        writer.write(abstractOrStatic);
        writer.eol();
    }

    public ClassMethod parameter(MethodParameter parameter) {
        parameters.add(parameter);
        return this;
    }

    public ClassMethod returnType(Type type) {
        this.returnType = type;
        return this;
    }

    public ClassMethod markAbstract() {
        this.abstractOrStatic = "*";
        return this;
    }

    public ClassMethod markStatic() {
        this.abstractOrStatic = "$";
        return this;
    }
}
