package com.lamzi.mermaid.diagram.classdiagram;

import com.lamzi.mermaid.diagram.config.DiagramConfiguration;
import com.lamzi.mermaid.diagram.MermaidWriter;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClassDiagramConfiguration extends DiagramConfiguration {

    private boolean hideEmptyMembersBox;

    public void hideEmptyMembersBox(boolean hideEmptyMembersBox) {
        this.hideEmptyMembersBox = hideEmptyMembersBox;
    }

    @Override
    public void writeTo(MermaidWriter writer) {
        super.writeTo(writer);
        if (hideEmptyMembersBox) {
            writer.indent(1);
            writer.write("class:");
            writer.eol();
            writer.indent(2);
            writer.write("hideEmptyMembersBox: ");
            writer.write(String.valueOf(hideEmptyMembersBox));
            writer.eol();
        }
    }
}