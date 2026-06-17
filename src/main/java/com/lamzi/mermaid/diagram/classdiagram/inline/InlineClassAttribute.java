package com.lamzi.mermaid.diagram.classdiagram.inline;


import com.lamzi.mermaid.diagram.MermaidWriter;
import com.lamzi.mermaid.diagram.classdiagram.ClassAttribute;
import com.lamzi.mermaid.diagram.classdiagram.ClassName;
import com.lamzi.mermaid.diagram.classdiagram.Type;
import com.lamzi.mermaid.diagram.classdiagram.Visibility;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class InlineClassAttribute implements InlineClassMember {
    private ClassName aclass;
    private ClassAttribute classAttribute;


    public InlineClassAttribute(Type aclass, String attributeName) {
        this.aclass = new ClassName(aclass);
        this.classAttribute = new ClassAttribute(attributeName);
    }

    public InlineClassAttribute visibility(Visibility visibility) {
        this.classAttribute.visibility(visibility);
        return this;
    }

    public InlineClassAttribute type(Type type) {
        this.classAttribute.type(type);
        return this;
    }


    @Override
    public void writeTo(MermaidWriter writer, int level) {
        writer.indent(level);
        aclass.generateReference(writer);
        writer.write(": ");
        classAttribute.writeTo(writer, 0);
    }

    public InlineClassAttribute colon(){
        this.classAttribute.colon();
        return this;
    }
}
