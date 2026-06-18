package com.lamzi.doc.mermaid.diagram.classdiagram;

import com.lamzi.doc.mermaid.diagram.config.DiagramConfiguration;
import com.lamzi.doc.mermaid.diagram.MermaidWriter;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class ClassDiagramConfiguration extends DiagramConfiguration {

    private Map<String, Boolean> classProperties = new LinkedHashMap<>();

    public ClassDiagramConfiguration hideEmptyMembersBox(boolean hideEmptyMembersBox) {
        this.classProperties.put("hideEmptyMembersBox", hideEmptyMembersBox);
        return this;
    }

    public ClassDiagramConfiguration hierarchicalNamespaces(boolean hierarchicalNamespaces) {
        this.classProperties.put("hierarchicalNamespaces", hierarchicalNamespaces);
        return this;
    }

    @Override
    public void writeTo(MermaidWriter writer) {
        super.writeTo(writer);
        if (!classProperties.isEmpty()) {
            writer.indent(1);
            writer.write("class:");
            writer.eol();
            for (Map.Entry<String, Boolean> entry : classProperties.entrySet()) {
                writer.indent(2);
                writer.write(entry.getKey());
                writer.write(": ");
                writer.write(String.valueOf(entry.getValue()));
                writer.eol();
            }
        }
    }
}