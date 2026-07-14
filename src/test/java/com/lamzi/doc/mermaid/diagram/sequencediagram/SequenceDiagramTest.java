package com.lamzi.doc.mermaid.diagram.sequencediagram;

import com.lamzi.doc.mermaid.diagram.BaseTest;
import org.junit.jupiter.api.Test;

import static com.lamzi.doc.mermaid.diagram.sequencediagram.SequenceDiagramFactory.actor;
import static com.lamzi.doc.mermaid.diagram.sequencediagram.SequenceDiagramFactory.box;
import static com.lamzi.doc.mermaid.diagram.sequencediagram.SequenceDiagramFactory.loop;
import static com.lamzi.doc.mermaid.diagram.sequencediagram.SequenceDiagramFactory.message;
import static com.lamzi.doc.mermaid.diagram.sequencediagram.SequenceDiagramFactory.noteOver;
import static com.lamzi.doc.mermaid.diagram.sequencediagram.SequenceDiagramFactory.noteRightOf;
import static com.lamzi.doc.mermaid.diagram.sequencediagram.SequenceDiagramFactory.participant;
import static com.lamzi.doc.mermaid.diagram.sequencediagram.SequenceDiagramFactory.participantConfig;
import static com.lamzi.doc.mermaid.diagram.sequencediagram.SequenceMessage.Head.ARROW;
import static com.lamzi.doc.mermaid.diagram.sequencediagram.SequenceMessage.Head.CROSS;
import static com.lamzi.doc.mermaid.diagram.sequencediagram.SequenceMessage.Head.NONE;
import static com.lamzi.doc.mermaid.diagram.sequencediagram.SequenceMessage.Head.OPEN_ARROW;
import static com.lamzi.doc.mermaid.diagram.sequencediagram.SequenceMessage.Line.DOTTED;
import static com.lamzi.doc.mermaid.diagram.sequencediagram.SequenceMessage.Line.SOLID;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class SequenceDiagramTest extends BaseTest {

    @Test
    public void sequenceDiagram() {
        SequenceDiagram diagram = new SequenceDiagram();
        diagram
                .message("Alice", "John", "Hello John, how are you?")
                .message(message("John", DOTTED, ARROW, "Alice", "Great!"))
                .message(message("Alice", SOLID, OPEN_ARROW, "John", "See you later!"));

        assertThat(diagram.generate()).isEqualTo(read("/sequenceDiagram/sequenceDiagram.mmd"));
    }

    @Test
    public void actors() {
        SequenceDiagram diagram = new SequenceDiagram();
        diagram
                .actor("Alice")
                .actor("Bob")
                .message("Alice", "Bob", "Hi Bob")
                .message("Bob", "Alice", "Hi Alice");

        assertThat(diagram.generate()).isEqualTo(read("/sequenceDiagram/actors.mmd"));
    }

    @Test
    public void boundary() {
        SequenceDiagram diagram = new SequenceDiagram();
        diagram
                .participant(participant("Alice").type(SequenceParticipantConfig.Type.BOUNDARY))
                .participant(participant("Bob"))
                .message("Alice", "Bob", "Request from boundary")
                .message("Bob", "Alice", "Response to boundary");

        assertThat(diagram.generate()).isEqualTo(read("/sequenceDiagram/boundary.mmd"));
    }

    @Test
    public void control() {
        SequenceDiagram diagram = new SequenceDiagram();
        diagram
                .participant(participant("Alice").type(SequenceParticipantConfig.Type.CONTROL))
                .participant(participant("Bob"))
                .message("Alice", "Bob", "Control request")
                .message("Bob", "Alice", "Control response");

        assertThat(diagram.generate()).isEqualTo(read("/sequenceDiagram/control.mmd"));
    }

    @Test
    public void entity() {
        SequenceDiagram diagram = new SequenceDiagram();
        diagram
                .participant(participant("Alice").type(SequenceParticipantConfig.Type.ENTITY))
                .participant(participant("Bob"))
                .message("Alice", "Bob", "Entity request")
                .message("Bob", "Alice", "Entity response");

        assertThat(diagram.generate()).isEqualTo(read("/sequenceDiagram/entity.mmd"));
    }

    @Test
    public void database() {
        SequenceDiagram diagram = new SequenceDiagram();
        diagram
                .participant(participant("Alice").type(SequenceParticipantConfig.Type.DATABASE))
                .participant(participant("Bob"))
                .message("Alice", "Bob", "DB query")
                .message("Bob", "Alice", "DB result");

        assertThat(diagram.generate()).isEqualTo(read("/sequenceDiagram/database.mmd"));
    }

    @Test
    public void collections() {
        SequenceDiagram diagram = new SequenceDiagram();
        diagram
                .participant(participant("Alice").type(SequenceParticipantConfig.Type.COLLECTIONS))
                .participant(participant("Bob"))
                .message("Alice", "Bob", "Collections request")
                .message("Bob", "Alice", "Collections response");

        assertThat(diagram.generate()).isEqualTo(read("/sequenceDiagram/collections.mmd"));
    }

    @Test
    public void queue() {
        SequenceDiagram diagram = new SequenceDiagram();
        diagram
                .participant(participant("Alice").type(SequenceParticipantConfig.Type.QUEUE))
                .participant(participant("Bob"))
                .message("Alice", "Bob", "Queue message")
                .message("Bob", "Alice", "Queue response");

        assertThat(diagram.generate()).isEqualTo(read("/sequenceDiagram/queue.mmd"));
    }

    @Test
    public void externalAlias() {
        SequenceDiagram diagram = new SequenceDiagram();
        diagram
                .participant(participant("A").alias("Alice"))
                .participant(participant("J").alias("John"))
                .message("A", "J", "Hello John, how are you?")
                .message("J", "A", "Great!");

        assertThat(diagram.generate()).isEqualTo(read("/sequenceDiagram/externalAlias.mmd"));
    }

    @Test
    public void externalAlias2() {
        SequenceDiagram diagram = new SequenceDiagram();
        diagram
                .participant(participant("API").type(SequenceParticipantConfig.Type.BOUNDARY).alias("Public API"))
                .actor(actor("DB").type(SequenceParticipantConfig.Type.DATABASE).alias("User Database"))
                .participant(participant("Svc").type(SequenceParticipantConfig.Type.CONTROL).alias("Auth Service"))
                .message("API", "Svc", "Authenticate")
                .message("Svc", "DB", "Query user")
                .message(message("DB", DOTTED, ARROW, "Svc", "User data"))
                .message(message("Svc", DOTTED, ARROW, "API", "Token"));

        assertThat(diagram.generate()).isEqualTo(read("/sequenceDiagram/externalAlias2.mmd"));
    }

    @Test
    public void inlineAlias() {
        SequenceDiagram diagram = new SequenceDiagram();
        diagram
                .participant(participant("API")
                        .config(participantConfig()
                                .type(SequenceParticipantConfig.Type.BOUNDARY)
                                .alias("Public API")))
                .participant(participant("Auth")
                        .config(participantConfig()
                                .type(SequenceParticipantConfig.Type.CONTROL)
                                .alias("Auth Service")))
                .participant(participant("DB")
                        .config(participantConfig()
                                .type(SequenceParticipantConfig.Type.DATABASE)
                                .alias("User Database")))
                .message("API", "Auth", "Login request")
                .message("Auth", "DB", "Query user")
                .message(message("DB", DOTTED, ARROW, "Auth", "User data"))
                .message(message("Auth", DOTTED, ARROW, "API", "Access token"));

        assertThat(diagram.generate()).isEqualTo(read("/sequenceDiagram/inlineAlias.mmd"));
    }

    @Test
    public void aliasPrecedence() {
        SequenceDiagram diagram = new SequenceDiagram();
        diagram
                .participant(participant("API")
                        .config(participantConfig()
                                .type(SequenceParticipantConfig.Type.BOUNDARY)
                                .alias("Internal Name"))
                        .alias("External Name"))
                .participant(participant("DB")
                        .config(participantConfig()
                                .type(SequenceParticipantConfig.Type.DATABASE)
                                .alias("Internal DB"))
                        .alias("External DB"))
                .message("API", "DB", "Query")
                .message(message("DB", DOTTED, ARROW, "API", "Result"));

        assertThat(diagram.generate()).isEqualTo(read("/sequenceDiagram/aliasPrecedence.mmd"));
    }

    @Test
    public void actorCreationAndDestruction() {
        SequenceDiagram diagram = new SequenceDiagram();
        diagram
                .message("Alice", "Bob", "Hello Bob, how are you ?")
                .message("Bob", "Alice", "Fine, thank you. And you?")
                .addCreate(participant("Carl"))
                .message("Alice", "Carl", "Hi Carl!")
                .addCreate(actor("D").alias("Donald"))
                .message("Carl", "D", "Hi!")
                .addDestroy("Carl")
                .message(message("Alice", SOLID, CROSS, "Carl", "We are too many"))
                .addDestroy("Bob")
                .message("Bob", "Alice", "I agree");

        assertThat(diagram.generate()).isEqualTo(read("/sequenceDiagram/actorCreationAndDestruction.mmd"));
    }

    @Test
    public void groupingBox() {
        SequenceDiagram diagram = new SequenceDiagram();
        diagram
                .box(box("Alice & John")
                        .color("Purple")
                        .participant(participant("A"))
                        .participant(participant("J")))
                .box(box("Another Group")
                        .participant(participant("B"))
                        .participant(participant("C")))
                .message("A", "J", "Hello John, how are you?")
                .message("J", "A", "Great!")
                .message("A", "B", "Hello Bob, how is Charley?")
                .message("B", "C", "Hello Charley, how are you?");

        assertThat(diagram.generate()).isEqualTo(read("/sequenceDiagram/groupingBox.mmd"));
    }

    @Test
    public void centralConnections() {
        SequenceDiagram diagram = new SequenceDiagram();
        diagram
                .participant("Alice")
                .participant("John")
                .message(message("Alice", SOLID, ARROW, "John", "Hello John").toCentral())
                .message(message("Alice", SOLID, ARROW, "John", "How are you?").fromCentral())
                .message(message("John", SOLID, ARROW, "Alice", "Great!").fromCentral().toCentral());

        assertThat(diagram.generate()).isEqualTo(read("/sequenceDiagram/centralConnections.mmd"));
    }

    @Test
    public void activations() {
        SequenceDiagram diagram = new SequenceDiagram();
        diagram
                .message("Alice", "John", "Hello John, how are you?")
                .activate("John")
                .message(message("John", DOTTED, ARROW, "Alice", "Great!"))
                .deactivate("John");

        assertThat(diagram.generate()).isEqualTo(read("/sequenceDiagram/activations.mmd"));
    }

    @Test
    public void activationsInline() {
        SequenceDiagram diagram = new SequenceDiagram();
        diagram
                .message(message("Alice", SOLID, ARROW, "John", "Hello John, how are you?").activate())
                .message(message("John", DOTTED, ARROW, "Alice", "Great!").deactivate());

        assertThat(diagram.generate()).isEqualTo(read("/sequenceDiagram/activationsInline.mmd"));
    }

    @Test
    public void activationsInline2() {
        SequenceDiagram diagram = new SequenceDiagram();
        diagram
                .message(message("Alice", "John", "Hello John, how are you?").activate())
                .message(message("Alice", "John", "John, can you hear me?").activate())
                .message(message("John", DOTTED, ARROW, "Alice", "Hi Alice, I can hear you!").deactivate())
                .message(message("John", DOTTED, ARROW, "Alice", "I feel great!").deactivate());

        assertThat(diagram.generate()).isEqualTo(read("/sequenceDiagram/activationsInline2.mmd"));
    }

    @Test
    public void notes() {
        SequenceDiagram diagram = new SequenceDiagram();
        diagram
                .participant("John")
                .note(noteRightOf("John", "Text in note"));

        assertThat(diagram.generate()).isEqualTo(read("/sequenceDiagram/notes.mmd"));
    }

    @Test
    public void notesOver() {
        SequenceDiagram diagram = new SequenceDiagram();
        diagram
                .message(message("Alice", SOLID, NONE, "John", "Hello John, how are you?"))
                .note(noteOver("A typical interaction", "Alice", "John"));

        assertThat(diagram.generate()).isEqualTo(read("/sequenceDiagram/notesOver.mmd"));
    }

    @Test
    public void lineBreakNote() {
        SequenceDiagram diagram = new SequenceDiagram();
        diagram
                .message(message("Alice", SOLID, NONE, "John", "Hello John,<br/>how are you?"))
                .note(noteOver("A typical interaction<br/>But now in two lines", "Alice", "John"));

        assertThat(diagram.generate()).isEqualTo(read("/sequenceDiagram/lineBreakNote.mmd"));
    }

    @Test
    public void lineBreakParticipant() {
        SequenceDiagram diagram = new SequenceDiagram();
        diagram
                .participant(participant("Alice").alias("Alice<br/>Johnson"))
                .message(message("Alice", SOLID, NONE, "John", "Hello John,<br/>how are you?"))
                .note(noteOver("A typical interaction<br/>But now in two lines", "Alice", "John"));

        assertThat(diagram.generate()).isEqualTo(read("/sequenceDiagram/lineBreakParticipant.mmd"));
    }

    @Test
    public void loops() {
        SequenceDiagram diagram = new SequenceDiagram();
        diagram
                .message(message("Alice", SOLID, NONE, "John", "Hello John, how are you?"))
                .block(loop("Every minute")
                        .message(message("John", DOTTED, NONE, "Alice", "Great!")));

        assertThat(diagram.generate()).isEqualTo(read("/sequenceDiagram/loops.mmd"));
    }

}