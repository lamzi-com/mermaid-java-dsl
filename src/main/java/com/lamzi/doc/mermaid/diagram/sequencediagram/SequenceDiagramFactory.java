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
        return new SequenceMessage(from, SequenceMessage.Arrow.SOLID_ARROW, to, text);
    }

    public static SequenceMessage message(String from, SequenceMessage.Arrow arrow, String to, String text) {
        return new SequenceMessage(from, arrow, to, text);
    }

    public static SequenceNote note(SequenceNote.Position position, String actor, String text) {
        return new SequenceNote(position, text, actor);
    }

    public static SequenceNote noteOver(String text, String... actors) {
        return new SequenceNote(SequenceNote.Position.OVER, text, actors);
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

    public static SequenceBlock box(String text) {
        return new SequenceBlock(SequenceBlock.Type.BOX, text);
    }
}
