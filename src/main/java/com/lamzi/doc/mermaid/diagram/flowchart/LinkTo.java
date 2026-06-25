package com.lamzi.doc.mermaid.diagram.flowchart;

import com.lamzi.doc.mermaid.diagram.MermaidException;
import com.lamzi.doc.mermaid.diagram.MermaidWriter;
import com.lamzi.doc.mermaid.diagram.Writable;

public class LinkTo implements Writable {
    private final Type type;
    private final HeadType headType;
    Node[] to;
    boolean biDirectional;
    private String text;
    private LinkTo nextLink;
    private String id;
    private int length = 1;

    public LinkTo text(String text) {
        this.text = text;
        return this;
    }

    public LinkTo id(String id) {
        this.id = id;
        return this;
    }

    public LinkTo length(int length) {
        if (length < 1) {
            throw new MermaidException("Lenght must be bigger than 0");
        }
        this.length = length;
        return this;
    }

    public enum HeadType {
        ARROW("<", ">"),
        INVISIBLE("", ""),
        CIRCLE("o", "o"),
        CROSS("x", "x");

        public final String left;
        public final String right;

        HeadType(String left, String right) {
            this.left = left;
            this.right = right;
        }
    }

    public enum Type {
        SIMPLE_LINK("-", "-", "", "-"),
        DOTTED_LINK("-", ".", "-", ""),
        TICK_LINK("=", "=", "", "="),
        INVISIBLE_LINK("~", "~", "", "~");

        public final String lineLeft;
        private final String lineRight;
        public final String repeatable;
        public final String withoutRightArrow;

        Type(String lineLeft, String repeatable, String lineRight, String withoutRightArrow) {
            this.lineLeft = lineLeft;
            this.repeatable = repeatable;
            this.lineRight = lineRight;
            this.withoutRightArrow = withoutRightArrow;
        }
    }

    public LinkTo(Type type, HeadType headType, Node... to) {
        if (Type.INVISIBLE_LINK.equals(type) && !HeadType.INVISIBLE.equals(headType)) {
            throw new MermaidException("Invisible link only supports invisible head type");
        }
        this.to = to;
        this.headType = headType;
        this.type = type;
    }

    public LinkTo multiDirectional(boolean biDirectional) {
        this.biDirectional = biDirectional;
        return this;
    }

    public LinkTo linkTo(LinkTo linkTo) {
        this.nextLink = linkTo;
        return this;
    }


    @Override
    public void writeTo(MermaidWriter writer) {
        writer.write(" ");
        if (id != null) {
            writer.write(id);
            writer.write("@");
        }
        if (biDirectional) {
            writer.write(headType.left);
        }
        writer.write(type.lineLeft);
        for (int i = 1; i <= length; i++) {
            writer.write(type.repeatable);
        }
        writer.write(type.lineRight);
        if (headType.equals(HeadType.INVISIBLE)) {
            writer.write(type.withoutRightArrow);
        }
        writer.write(headType.right);
        if (text != null) {
            writer.write("|");
            writer.write(text);
            writer.write("|");
        }
        writer.write(" ");
        for (int i = 0; i < to.length; i++) {
            to[i].writeToInline(writer);
            if (i < to.length - 1) {
                writer.write(" & ");
            }

        }
        if (nextLink != null) {
            nextLink.writeTo(writer);
        }
    }
}
