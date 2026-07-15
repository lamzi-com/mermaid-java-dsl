package com.lamzi.doc.mermaid.diagram.sequencediagram;

import com.lamzi.doc.mermaid.diagram.internal.MermaidWriter;

import java.util.ArrayList;
import java.util.List;

public abstract class SequenceBlock<T extends SequenceBlock<T>> implements SequenceDiagramElement {
    private final Type type;
    private String header;
    private final List<Section> sections = new ArrayList<>();
    private Section currentSection;

    public enum Type {
        LOOP("loop"),
        ALT("alt"),
        OPT("opt"),
        PAR("par"),
        CRITICAL("critical"),
        BREAK("break"),
        RECT("rect");

        private final String keyword;

        Type(String keyword) {
            this.keyword = keyword;
        }
    }

    private static class Section {
        private final String keyword;
        private final String text;
        private final List<SequenceDiagramElement> elements = new ArrayList<>();

        private Section(String keyword, String text) {
            this.keyword = keyword;
            this.text = text;
        }
    }

    protected SequenceBlock(Type type) {
        this.type = type;
        this.currentSection = new Section(null, null);
        this.sections.add(currentSection);
    }

    protected T header(String header) {
        this.header = header;
        return self();
    }

    public T add(SequenceDiagramElement element) {
        currentSection.elements.add(element);
        return self();
    }

    public T participant(SequenceParticipant participant) {
        return add(participant);
    }

    public T message(SequenceMessage message) {
        return add(message);
    }

    public T note(SequenceNote note) {
        return add(note);
    }

    public T activate(String actor) {
        return add(new SequenceActivation(SequenceActivation.Kind.ACTIVATE, actor));
    }

    public T deactivate(String actor) {
        return add(new SequenceActivation(SequenceActivation.Kind.DEACTIVATE, actor));
    }

    protected T addSection(String keyword, String text) {
        currentSection = new Section(keyword, text);
        sections.add(currentSection);
        return self();
    }

    @Override
    public void writeTo(MermaidWriter writer, int level) {
        writer.indent(level);
        writer.write(type.keyword);
        if (header != null && !header.isBlank()) {
            writer.write(" ");
            writer.write(header);
        }
        writer.eol();

        for (Section section : sections) {
            if (section.keyword != null) {
                writer.indent(level);
                writer.write(section.keyword);
                if (section.text != null && !section.text.isBlank()) {
                    writer.write(" ");
                    writer.write(section.text);
                }
                writer.eol();
            }
            for (SequenceDiagramElement element : section.elements) {
                element.writeTo(writer, level + 1);
            }
        }

        writer.indent(level);
        writer.write("end");
        writer.eol();
    }

    @SuppressWarnings("unchecked")
    private T self() {
        return (T) this;
    }
}
