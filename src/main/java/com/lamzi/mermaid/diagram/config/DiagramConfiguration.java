package com.lamzi.mermaid.diagram.config;

import com.lamzi.mermaid.diagram.MermaidWriter;
import com.lamzi.mermaid.diagram.Writable;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class DiagramConfiguration implements Writable {

    List<Config> configs = new ArrayList<>();

    public DiagramConfiguration layout(LayoutConfig.Layout layout) {
        configs.add(new LayoutConfig(layout));
        return this;
    }

    public DiagramConfiguration elk(ElkConfig elkConfig) {
        configs.add(elkConfig);
        return this;
    }


    @Override
    public void writeTo(MermaidWriter writer) {
        writer.write("config:");
        writer.eol();
        for (Config config : configs) {
            config.writeTo(writer, 1);
        }
    }
}
