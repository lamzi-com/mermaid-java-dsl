package com.lamzi.doc.mermaid.diagram.classdiagram.relation;

public class RelationType {

    public static final RelationType LOLIPOP = new RelationType("()", "--", "()");
    public static RelationType INHERITANCE = new RelationType("<|", "--", "|>");
    public static RelationType COMPOSITION = new RelationType("*", "--", "*");
    public static RelationType AGGREGATION = new RelationType("o", "--", "o");
    public static RelationType ASSOCIATION = new RelationType("<", "--", ">");
    public static RelationType SOLID_LINK = new RelationType("", "--", "");
    public static RelationType DEPENDENCY = new RelationType("<", "..", ">");
    public static RelationType REALIZATION = new RelationType("<|", "..", "|>");
    public static RelationType DASHED_LINK = new RelationType("", "..", "");

    private final String left;
    private final String right;
    private final String link;

    RelationType(String left, String link, String right) {
        this.left = left;
        this.right = right;
        this.link = link;
    }

    public String left(){
        return left;
    }

    public String right(){
        return right;
    }

    public String link(){
        return link;
    }
}
