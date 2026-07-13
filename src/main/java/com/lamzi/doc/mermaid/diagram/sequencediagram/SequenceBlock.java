package com.lamzi.doc.mermaid.diagram.sequencediagram;

import com.lamzi.doc.mermaid.diagram.internal.MermaidWriter;

import java.util.ArrayList;
import java.util.List;

public class SequenceBlock implements SequenceDiagramElement {
    private final Type type;
    private final String text;
    private final List<Section> sections = new ArrayList<>();
    private Section currentSection;

    public enum Type {
        LOOP("loop"),
        ALT("alt"),
        OPT("opt"),
        PAR("par"),
        CRITICAL("critical"),
        BREAK("break"),
        RECT("rect"),
        BOX("box");

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

    public SequenceBlock(Type type, String text) {
        this.type = type;
        this.text = text;
        this.currentSection = new Section(null, null);
        this.sections.add(currentSection);
    }

    public SequenceBlock add(SequenceDiagramElement element) {
        currentSection.elements.add(element);
        return this;
    }

    public SequenceBlock participant(SequenceParticipant participant) {
        return add(participant);
    }

    public SequenceBlock message(SequenceMessage message) {
        return add(message);
    }

    public SequenceBlock note(SequenceNote note) {
        return add(note);
    }

    public SequenceBlock activate(String actor) {
        return add(new SequenceActivation(SequenceActivation.Kind.ACTIVATE, actor));
    }

    public SequenceBlock deactivate(String actor) {
        return add(new SequenceActivation(SequenceActivation.Kind.DEACTIVATE, actor));
    }

    public SequenceBlock otherwise() {
        return elseBranch(null);
    }

    public SequenceBlock elseBranch(String text) {
        return addSection("else", text);
    }

    public SequenceBlock andBranch(String text) {
        return addSection("and", text);
    }

    public SequenceBlock option(String text) {
        return addSection("option", text);
    }

    private SequenceBlock addSection(String keyword, String text) {
        currentSection = new Section(keyword, text);
        sections.add(currentSection);
        return this;
    }

    @Override
    public void writeTo(MermaidWriter writer, int level) {
        writer.indent(level);
        writer.write(type.keyword);
        if (text != null && !text.isBlank()) {
            writer.write(" ");
            writer.write(text);
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
}
