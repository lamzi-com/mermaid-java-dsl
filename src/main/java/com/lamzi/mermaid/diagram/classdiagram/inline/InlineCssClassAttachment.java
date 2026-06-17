package com.lamzi.mermaid.diagram.classdiagram.inline;

import com.lamzi.mermaid.diagram.MermaidWriter;
import com.lamzi.mermaid.diagram.classdiagram.Type;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class InlineCssClassAttachment implements InlineClassMember {
    private String className;
    private List<Type> types = new ArrayList<>();

    public InlineCssClassAttachment(String className, Type... types) {
        this.className = className;
        Collections.addAll(this.types, types);
    }


    @Override
    public void writeTo(MermaidWriter writer, int level) {
        writer.indent(level);
        writer.write("cssClass ");
        writer.write("\"");
        boolean first = true;
        for (Type type : types) {
            if (!first) {
                writer.write(",");
            } else {
                first = false;
            }
            type.writeNameTo(writer);
        }
        writer.write("\" ");
        writer.write(className);
        writer.write(";");
        writer.eol();
    }
}
