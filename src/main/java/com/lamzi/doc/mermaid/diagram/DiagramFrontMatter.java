package com.lamzi.doc.mermaid.diagram;

import com.lamzi.doc.mermaid.diagram.config.DiagramConfiguration;
import lombok.Setter;

public class DiagramFrontMatter<T extends DiagramConfiguration> implements Writable {

    @Setter
    private String title;
    private T diagramConfiguration;

    public DiagramFrontMatter(T configuration) {
        this.diagramConfiguration = configuration;
    }

    public DiagramFrontMatter() {

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
