package com.lamzi.doc.mermaid.diagram.classdiagram.inline;


import com.lamzi.doc.mermaid.diagram.MermaidWriter;
import com.lamzi.doc.mermaid.diagram.classdiagram.Annotation;
import com.lamzi.doc.mermaid.diagram.classdiagram.ClassName;
import com.lamzi.doc.mermaid.diagram.classdiagram.Type;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class InlineAnnotation implements InlineClassMember {
    private ClassName aclass;
    private Annotation annotation;


    public InlineAnnotation(Type aclass, String annotationName) {
        this.aclass = new ClassName(aclass);
        this.annotation = new Annotation(annotationName);
    }

    @Override
    public void writeTo(MermaidWriter writer, int level) {
        annotation.writeWithoutEOL(writer, level);
        writer.write(" ");
        aclass.generateReference(writer);
        writer.eol();
    }
}
