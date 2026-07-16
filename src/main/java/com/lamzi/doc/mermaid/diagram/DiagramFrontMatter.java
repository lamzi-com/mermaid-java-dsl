package com.lamzi.doc.mermaid.diagram;

import com.lamzi.doc.mermaid.diagram.config.DiagramConfiguration;
import com.lamzi.doc.mermaid.diagram.internal.MermaidWriter;
import com.lamzi.doc.mermaid.diagram.internal.Writable;

public class DiagramFrontMatter<T extends DiagramConfiguration> implements Writable {

    private String title;
    private T diagramConfiguration;

    public DiagramFrontMatter(T configuration) {
        this.diagramConfiguration = configuration;
    }

    public DiagramFrontMatter() {

    }

    public void title(String title) {
        this.title = title;
    }

    @Override
    public void writeTo(MermaidWriter writer) {
        writer.write("---");
        writer.eol();
        if (title != null) {
            writer.write("title: " + title);
            writer.eol();
        }
        if (diagramConfiguration != null) {
            diagramConfiguration.writeTo(writer);
        }
        writer.write("---");
        writer.eol();

    }

}
