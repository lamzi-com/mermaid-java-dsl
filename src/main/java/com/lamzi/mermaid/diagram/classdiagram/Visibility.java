package com.lamzi.mermaid.diagram.classdiagram;

public enum Visibility {
    PUBLIC("+"), PRIVATE("-"), PROTECTED("#"), PACKAGE("~");

    public final String code;

    Visibility(String code) {
        this.code = code;
    }
}
