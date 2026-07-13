package com.lamzi.doc.mermaid.diagram.sequencediagram;

import com.lamzi.doc.mermaid.diagram.internal.MermaidWriter;

public abstract class BaseSequenceParticipant<T extends BaseSequenceParticipant<T>> implements SequenceDiagramElement {
    private final String id;
    private String alias;
    private SequenceParticipantConfig config;

    protected BaseSequenceParticipant(String id) {
        this.id = id;
    }

    protected abstract String keyword();

    public T alias(String alias) {
        this.alias = alias;
        return self();
    }

    public T config(SequenceParticipantConfig config) {
        this.config = config;
        return self();
    }

    public T type(SequenceParticipantConfig.Type type) {
        if (config == null) {
            config = new SequenceParticipantConfig();
        }
        config.type(type);
        return self();
    }

    @Override
    public void writeTo(MermaidWriter writer, int level) {
        writer.indent(level);
        writer.write(keyword());
        writer.write(" ");
        writer.write(id);
        if (config != null && !config.isEmpty()) {
            config.writeTo(writer);
        }
        if (alias != null) {
            writer.write(" as ");
            writer.write(alias);
        }
        writer.eol();
    }

    @SuppressWarnings("unchecked")
    private T self() {
        return (T) this;
    }
}
