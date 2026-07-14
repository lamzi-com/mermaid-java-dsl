package com.lamzi.doc.mermaid.diagram.sequencediagram;

import com.lamzi.doc.mermaid.diagram.Diagram;
import com.lamzi.doc.mermaid.diagram.DiagramFrontMatter;

public class SequenceDiagram extends Diagram<SequenceDiagramConfiguration, SequenceDiagramElement> {
    public SequenceDiagram(DiagramFrontMatter<SequenceDiagramConfiguration> frontMatter) {
        super(frontMatter, "sequenceDiagram");
    }

    public SequenceDiagram() {
        this(null);
    }

    public SequenceDiagram participant(SequenceParticipant participant) {
        addElement(participant);
        return this;
    }

    public SequenceDiagram actor(SequenceActor actor) {
        addElement(actor);
        return this;
    }

    public SequenceDiagram participant(String id) {
        return participant(new SequenceParticipant(id));
    }

    public SequenceDiagram actor(String id) {
        return actor(new SequenceActor(id));
    }

    public SequenceDiagram message(SequenceMessage message) {
        addElement(message);
        return this;
    }

    public SequenceDiagram message(String from, String to, String text) {
        return message(new SequenceMessage(from, SequenceMessage.Line.SOLID, SequenceMessage.Head.ARROW, to, text));
    }

    public SequenceDiagram note(SequenceNote note) {
        addElement(note);
        return this;
    }

    public SequenceDiagram activate(String actor) {
        addElement(new SequenceActivation(SequenceActivation.Kind.ACTIVATE, actor));
        return this;
    }

    public SequenceDiagram deactivate(String actor) {
        addElement(new SequenceActivation(SequenceActivation.Kind.DEACTIVATE, actor));
        return this;
    }

    public SequenceDiagram addCreate(BaseSequenceParticipant<?> actor) {
        addElement(new SequenceCreate(actor));
        return this;
    }

    public SequenceDiagram addDestroy(String actorId) {
        addElement(new SequenceDestroy(actorId));
        return this;
    }

    public SequenceDiagram autonumber() {
        addElement(new SequenceAutonumber());
        return this;
    }

    public SequenceDiagram autonumber(String start, String increment) {
        addElement(new SequenceAutonumber(start, increment));
        return this;
    }

    public SequenceDiagram link(SequenceActorLink link) {
        addElement(link);
        return this;
    }

    public SequenceDiagram block(SequenceBlock block) {
        addElement(block);
        return this;
    }

    public SequenceDiagram box(SequenceBox box) {
        addElement(box);
        return this;
    }

    public SequenceDiagram comment(String comment) {
        addElement(new SequenceComment(comment));
        return this;
    }
}
