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
    public String left;
    public String right;
    public String link;

    RelationType(String left, String link, String right) {
        this.left = left;
        this.right = right;
        this.link = link;
    }
}
