package com.lamzi.doc.mermaid.diagram;

import java.io.StringWriter;

public class MermaidWriter {
    private final StringWriter stringWriter = new StringWriter();
    private final String indent = "    ";
    private final String eol = "\n";

    public void write(String string) {
        this.stringWriter.write(string);
    }

    public void indent(int level) {
        for (int i = 0; i < level; i++) {
            this.stringWriter.write(indent);
        }
    }

    public void eol() {
        this.stringWriter.write(eol);
    }

    public String toString() {
        return this.stringWriter.toString().trim();
    }
}
