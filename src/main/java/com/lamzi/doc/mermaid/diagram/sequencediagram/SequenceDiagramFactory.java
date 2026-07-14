package com.lamzi.doc.mermaid.diagram.sequencediagram;

public class SequenceDiagramFactory {
    public static SequenceParticipant participant(String id) {
        return new SequenceParticipant(id);
    }

    public static SequenceActor actor(String id) {
        return new SequenceActor(id);
    }

    public static SequenceParticipantConfig participantConfig() {
        return new SequenceParticipantConfig();
    }

    public static SequenceMessage message(String from, String to, String text) {
        return new SequenceMessage(from, SequenceMessage.Line.SOLID, SequenceMessage.Head.ARROW, to, text);
    }

    public static SequenceMessage message(String from, SequenceMessage.Line line, SequenceMessage.Head head, String to, String text) {
        return new SequenceMessage(from, line, head, to, text);
    }

    public static SequenceNote noteRightOf(String actor, String text) {
        return SequenceNote.rightOf(actor, text);
    }

    public static SequenceNote noteLeftOf(String actor, String text) {
        return SequenceNote.leftOf(actor, text);
    }

    public static SequenceNote noteOver(String text, String... actors) {
        return SequenceNote.over(text, actors);
    }

    public static SequenceActivation activate(String actor) {
        return new SequenceActivation(SequenceActivation.Kind.ACTIVATE, actor);
    }

    public static SequenceActivation deactivate(String actor) {
        return new SequenceActivation(SequenceActivation.Kind.DEACTIVATE, actor);
    }

    public static SequenceAutonumber autonumber() {
        return new SequenceAutonumber();
    }

    public static SequenceAutonumber autonumber(String start, String increment) {
        return new SequenceAutonumber(start, increment);
    }

    public static SequenceActorLink link(String actor, String label, String url) {
        return new SequenceActorLink(actor, label, url);
    }

    public static SequenceBlock loop(String text) {
        return new SequenceBlock(SequenceBlock.Type.LOOP, text);
    }

    public static SequenceBlock alt(String text) {
        return new SequenceBlock(SequenceBlock.Type.ALT, text);
    }

    public static SequenceBlock opt(String text) {
        return new SequenceBlock(SequenceBlock.Type.OPT, text);
    }

    public static SequenceBlock par(String text) {
        return new SequenceBlock(SequenceBlock.Type.PAR, text);
    }

    public static SequenceBlock critical(String text) {
        return new SequenceBlock(SequenceBlock.Type.CRITICAL, text);
    }

    public static SequenceBlock breakBlock(String text) {
        return new SequenceBlock(SequenceBlock.Type.BREAK, text);
    }

    public static SequenceBlock rect(String color) {
        return new SequenceBlock(SequenceBlock.Type.RECT, color);
    }

    public static SequenceBox box(String text) {
        return new SequenceBox(text);
    }
}
