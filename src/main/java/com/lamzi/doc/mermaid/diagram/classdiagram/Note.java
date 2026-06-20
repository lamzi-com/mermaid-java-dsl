package com.lamzi.doc.mermaid.diagram.classdiagram;

import com.lamzi.doc.mermaid.diagram.DiagramElement;
import com.lamzi.doc.mermaid.diagram.MermaidWriter;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class Note implements ClassDiagramElement {
    private Type target;
    private String content;

    public Note(String content) {
        this.content = content;
    }

    public Note(Type target, String content) {
        this.target = target;
        this.content = content;
    }

    @Override
    public void writeTo(MermaidWriter writer, int level) {
        writer.indent(level);
        writer.write("note ");
        if (target != null) {
            writer.write("for ");
            target.writeNameTo(writer);
            writer.write(" ");
        }
        writer.write("\"");
        writer.write(this.content);
        writer.write("\"");
        writer.eol();
    }
}
