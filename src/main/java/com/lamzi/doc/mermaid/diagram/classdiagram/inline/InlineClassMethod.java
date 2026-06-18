package com.lamzi.doc.mermaid.diagram.classdiagram.inline;

import com.lamzi.doc.mermaid.diagram.MermaidWriter;
import com.lamzi.doc.mermaid.diagram.classdiagram.ClassMethod;
import com.lamzi.doc.mermaid.diagram.classdiagram.ClassName;
import com.lamzi.doc.mermaid.diagram.classdiagram.MethodParameter;
import com.lamzi.doc.mermaid.diagram.classdiagram.Type;
import com.lamzi.doc.mermaid.diagram.classdiagram.Visibility;
import com.lamzi.doc.mermaid.diagram.classdiagram.*;

public class InlineClassMethod implements InlineClassMember {
    private ClassName className;
    private ClassMethod classMethod;

    public InlineClassMethod(Type className, String classMethodName) {
        this.className = new ClassName(className);
        this.classMethod = new ClassMethod(classMethodName);
    }

    @Override
    public void writeTo(MermaidWriter writer, int level) {
        writer.indent(level);
        className.generateReference(writer);
        writer.write(": ");
        classMethod.writeTo(writer, 0);
    }

    public InlineClassMethod visibility(Visibility visibility) {
        classMethod.visibility(visibility);
        return this;
    }

    public InlineClassMethod parameter(MethodParameter parameter) {
        this.classMethod.parameter(parameter);
        return this;
    }

    public InlineClassMethod returnType(Type type) {
        this.classMethod.returnType(type);
        return this;
    }

    public InlineClassMethod markAbstract() {
        this.classMethod.markAbstract();
        return this;
    }
    public InlineClassMethod markStatic() {
        this.classMethod.markStatic();
        return this;
    }
}
