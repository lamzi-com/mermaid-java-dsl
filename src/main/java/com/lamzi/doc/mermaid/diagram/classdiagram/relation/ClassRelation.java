package com.lamzi.doc.mermaid.diagram.classdiagram.relation;

import com.lamzi.doc.mermaid.diagram.DiagramElement;
import com.lamzi.doc.mermaid.diagram.MermaidWriter;
import com.lamzi.doc.mermaid.diagram.classdiagram.ClassDiagramElement;
import com.lamzi.doc.mermaid.diagram.classdiagram.ClassName;
import com.lamzi.doc.mermaid.diagram.classdiagram.Type;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ClassRelation implements ClassDiagramElement {

    private ClassName from;
    private ClassName to;
    private RelationType relationType;
    private Direction direction;
    private String label;
    private String leftCardinality;
    private String rightCardinality;

    public ClassRelation(Type from, RelationType relationType, Direction direction, Type to) {
        this.from = new ClassName(from);
        this.relationType = relationType;
        this.direction = direction;
        this.to = new ClassName(to);
    }

    public ClassRelation leftCardinality(String leftCardinality) {
        this.leftCardinality = leftCardinality;
        return this;
    }

    public ClassRelation rightCardinality(String rightCardinality) {
        this.rightCardinality = rightCardinality;
        return this;
    }

    @Override
    public void writeTo(MermaidWriter writer, int level) {
        writer.indent(level);
        from.writeTo(writer);
        writer.write(" ");
        if (leftCardinality != null) {
            writer.write("\"");
            writer.write(leftCardinality);
            writer.write("\"");
            writer.write(" ");
        }
        if (direction.equals(Direction.LEFT) || direction.equals(Direction.BOTH)) {
            writer.write(relationType.left);
        }
        writer.write(relationType.link);
        if (direction.equals(Direction.RIGHT) || direction.equals(Direction.BOTH)) {
            writer.write(relationType.right);
        }
        if (rightCardinality != null) {
            writer.write(" ");
            writer.write("\"");
            writer.write(rightCardinality);
            writer.write("\"");
        }
        writer.write(" ");
        to.writeTo(writer);
        if (label != null) {
            writer.write(": ");
            writer.write(label);
        }
        writer.eol();
    }

    public ClassRelation direction(Direction direction) {
        this.direction = direction;
        return this;
    }

    public ClassRelation label(String label) {
        this.label = label;
        return this;
    }
}
