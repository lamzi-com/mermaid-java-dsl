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

    public static SequenceLoop loop() {
        return new SequenceLoop();
    }

    public static SequenceAlt alt() {
        return new SequenceAlt();
    }

    public static SequenceOpt opt() {
        return new SequenceOpt();
    }


    public static SequencePar par() {
        return new SequencePar();
    }


    public static SequenceCritical critical() {
        return new SequenceCritical();
    }


    public static SequenceBreak breakBlock() {
        return new SequenceBreak();
    }

    public static SequenceRect rect() {
        return new SequenceRect();
    }

    public static SequenceBox box() {
        return new SequenceBox();
    }
}
