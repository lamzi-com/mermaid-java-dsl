package com.lamzi.doc.mermaid.diagram.sequencediagram;

import com.lamzi.doc.mermaid.diagram.BaseTest;
import org.junit.jupiter.api.Test;

import static com.lamzi.doc.mermaid.diagram.sequencediagram.SequenceDiagramFactory.actor;
import static com.lamzi.doc.mermaid.diagram.sequencediagram.SequenceDiagramFactory.alt;
import static com.lamzi.doc.mermaid.diagram.sequencediagram.SequenceDiagramFactory.box;
import static com.lamzi.doc.mermaid.diagram.sequencediagram.SequenceDiagramFactory.breakBlock;
import static com.lamzi.doc.mermaid.diagram.sequencediagram.SequenceDiagramFactory.critical;
import static com.lamzi.doc.mermaid.diagram.sequencediagram.SequenceDiagramFactory.link;
import static com.lamzi.doc.mermaid.diagram.sequencediagram.SequenceDiagramFactory.loop;
import static com.lamzi.doc.mermaid.diagram.sequencediagram.SequenceDiagramFactory.message;
import static com.lamzi.doc.mermaid.diagram.sequencediagram.SequenceDiagramFactory.noteOver;
import static com.lamzi.doc.mermaid.diagram.sequencediagram.SequenceDiagramFactory.noteRightOf;
import static com.lamzi.doc.mermaid.diagram.sequencediagram.SequenceDiagramFactory.number;
import static com.lamzi.doc.mermaid.diagram.sequencediagram.SequenceDiagramFactory.opt;
import static com.lamzi.doc.mermaid.diagram.sequencediagram.SequenceDiagramFactory.par;
import static com.lamzi.doc.mermaid.diagram.sequencediagram.SequenceDiagramFactory.participant;
import static com.lamzi.doc.mermaid.diagram.sequencediagram.SequenceDiagramFactory.participantConfig;
import static com.lamzi.doc.mermaid.diagram.sequencediagram.SequenceDiagramFactory.rect;
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
                .message(message("John", DOTTED, ARROW, "Alice").text( "Great!"))
                .message(message("Alice", SOLID, OPEN_ARROW, "John").text("See you later!"));

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
                .message(message("DB", DOTTED, ARROW, "Svc").text("User data"))
                .message(message("Svc", DOTTED, ARROW, "API").text("Token"));

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
                .message(message("DB", DOTTED, ARROW, "Auth").text("User data"))
                .message(message("Auth", DOTTED, ARROW, "API").text("Access token"));

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
                .message(message("DB", DOTTED, ARROW, "API").text("Result"));

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
                .message(message("Alice", SOLID, CROSS, "Carl").text("We are too many"))
                .addDestroy("Bob")
                .message("Bob", "Alice", "I agree");

        assertThat(diagram.generate()).isEqualTo(read("/sequenceDiagram/actorCreationAndDestruction.mmd"));
    }

    @Test
    public void groupingBox() {
        SequenceDiagram diagram = new SequenceDiagram();
        diagram
                .box(box()
                        .color("Purple")
                        .text("Alice & John")
                        .participant(participant("A"))
                        .participant(participant("J")))
                .box(box()
                        .text("Another Group")
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
                .message(message("Alice", SOLID, ARROW, "John").text("Hello John").toCentral())
                .message(message("Alice", SOLID, ARROW, "John").text("How are you?").fromCentral())
                .message(message("John", SOLID, ARROW, "Alice").text("Great!").fromCentral().toCentral());

        assertThat(diagram.generate()).isEqualTo(read("/sequenceDiagram/centralConnections.mmd"));
    }

    @Test
    public void activations() {
        SequenceDiagram diagram = new SequenceDiagram();
        diagram
                .message("Alice", "John", "Hello John, how are you?")
                .activate("John")
                .message(message("John", DOTTED, ARROW, "Alice").text("Great!"))
                .deactivate("John");

        assertThat(diagram.generate()).isEqualTo(read("/sequenceDiagram/activations.mmd"));
    }

    @Test
    public void activationsInline() {
        SequenceDiagram diagram = new SequenceDiagram();
        diagram
                .message(message("Alice", SOLID, ARROW, "John").text("Hello John, how are you?").activate())
                .message(message("John", DOTTED, ARROW, "Alice").text("Great!").deactivate());

        assertThat(diagram.generate()).isEqualTo(read("/sequenceDiagram/activationsInline.mmd"));
    }

    @Test
    public void activationsInline2() {
        SequenceDiagram diagram = new SequenceDiagram();
        diagram
                .message(message("Alice", "John").text("Hello John, how are you?").activate())
                .message(message("Alice", "John").text("John, can you hear me?").activate())
                .message(message("John", DOTTED, ARROW, "Alice").text("Hi Alice, I can hear you!").deactivate())
                .message(message("John", DOTTED, ARROW, "Alice").text("I feel great!").deactivate());

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
                .message(message("Alice", SOLID, NONE, "John").text("Hello John, how are you?"))
                .note(noteOver("A typical interaction", "Alice", "John"));

        assertThat(diagram.generate()).isEqualTo(read("/sequenceDiagram/notesOver.mmd"));
    }

    @Test
    public void lineBreakNote() {
        SequenceDiagram diagram = new SequenceDiagram();
        diagram
                .message(message("Alice", SOLID, NONE, "John").text("Hello John,<br/>how are you?"))
                .note(noteOver("A typical interaction<br/>But now in two lines", "Alice", "John"));

        assertThat(diagram.generate()).isEqualTo(read("/sequenceDiagram/lineBreakNote.mmd"));
    }

    @Test
    public void lineBreakParticipant() {
        SequenceDiagram diagram = new SequenceDiagram();
        diagram
                .participant(participant("Alice").alias("Alice<br/>Johnson"))
                .message(message("Alice", SOLID, NONE, "John").text("Hello John,<br/>how are you?"))
                .note(noteOver("A typical interaction<br/>But now in two lines", "Alice", "John"));

        assertThat(diagram.generate()).isEqualTo(read("/sequenceDiagram/lineBreakParticipant.mmd"));
    }

    @Test
    public void loops() {
        SequenceDiagram diagram = new SequenceDiagram();
        diagram
                .message(message("Alice", SOLID, NONE, "John").text("Hello John, how are you?"))
                .block(loop()
                        .text("Every minute")
                        .message(message("John", DOTTED, NONE, "Alice").text("Great!")));

        assertThat(diagram.generate()).isEqualTo(read("/sequenceDiagram/loops.mmd"));
    }

    @Test
    public void altBlock() {
        SequenceDiagram diagram = new SequenceDiagram();
        diagram
                .message("Alice", "Bob", "Hello Bob, how are you?")
                .block(alt()
                        .text("is sick")
                        .message("Bob", "Alice","Not so good :(")
                        .elseBranch("is well")
                        .message(message("Bob", "Alice").text("Feeling fresh like a daisy")))
                .block(opt()
                        .text("Extra response")
                        .message("Bob", "Alice", "Thanks for asking"));

        assertThat(diagram.generate()).isEqualTo(read("/sequenceDiagram/altBlock.mmd"));
    }

    @Test
    public void parallel() {
        SequenceDiagram diagram = new SequenceDiagram();
        diagram
                .block(par()
                        .text("Alice to Bob")
                        .message("Alice", "Bob", "Hello guys!")
                        .andBranch("Alice to John")
                        .message("Alice", "John", "Hello guys!"))
                .message(message("Bob", DOTTED, ARROW, "Alice").text("Hi Alice!"))
                .message(message("John", DOTTED, ARROW, "Alice").text("Hi Alice!"));

        assertThat(diagram.generate()).isEqualTo(read("/sequenceDiagram/parallel.mmd"));
    }

    @Test
    public void nestedParallel() {
        SequenceDiagram diagram = new SequenceDiagram();
        diagram
                .block(par()
                        .text("Alice to Bob")
                        .message("Alice", "Bob", "Go help John")
                        .andBranch("Alice to John")
                        .message("Alice", "John", "I want this done today")
                        .add(par()
                                .text("John to Charlie")
                                .message("John", "Charlie", "Can we do this today?")
                                .andBranch("John to Diana")
                                .message("John", "Diana", "Can you help us today?")));

        assertThat(diagram.generate()).isEqualTo(read("/sequenceDiagram/nestedParallel.mmd"));
    }

    @Test
    public void criticalRegion() {
        SequenceDiagram diagram = new SequenceDiagram();
        diagram
                .block(critical()
                        .text("Establish a connection to the DB")
                        .message(message("Service", DOTTED, NONE, "DB").text("connect"))
                        .option("Network timeout")
                        .message(message("Service", DOTTED, NONE, "Service").text("Log error"))
                        .option("Credentials rejected")
                        .message(message("Service", DOTTED, NONE, "Service").text("Log different error")));

        assertThat(diagram.generate()).isEqualTo(read("/sequenceDiagram/criticalRegion.mmd"));
    }

    @Test
    public void criticalRegion2() {
        SequenceDiagram diagram = new SequenceDiagram();
        diagram
                .block(critical()
                        .text("Establish a connection to the DB")
                        .message(message("Service", DOTTED, NONE, "DB").text("connect")));

        assertThat(diagram.generate()).isEqualTo(read("/sequenceDiagram/criticalRegion2.mmd"));
    }

    @Test
    public void breakTest() {
        SequenceDiagram diagram = new SequenceDiagram();
        diagram
                .message(message("Consumer", DOTTED, NONE, "API").text("Book something"))
                .message(message("API", DOTTED, NONE, "BookingService").text("Start booking process"))
                .block(breakBlock()
                        .text("when the booking process fails")
                        .message(message("API", DOTTED, NONE, "Consumer").text( "show failure")))
                .message(message("API", DOTTED, NONE, "BillingService").text("Start billing process"));

        assertThat(diagram.generate()).isEqualTo(read("/sequenceDiagram/break.mmd"));
    }

    @Test
    public void backgroundHighlighting() {
        SequenceDiagram diagram = new SequenceDiagram();
        diagram
                .participant("Alice")
                .participant("John")
                .block(rect()
                        .color("rgb(191, 223, 255)")
                        .note(noteRightOf("Alice", "Alice calls John."))
                        .message(message("Alice", "John").text("Hello John, how are you?").activate())
                        .add(rect()
                                .color("rgb(200, 150, 255)")
                                .message(message("Alice", "John").text("John, can you hear me?").activate())
                                .message(message("John", DOTTED, ARROW, "Alice").text("Hi Alice, I can hear you!").deactivate()))
                        .message(message("John", DOTTED, ARROW, "Alice").text("I feel great!").deactivate()))
                .message(message("Alice", "John").text("Did you want to go to the game tonight?").activate())
                .message(message("John", DOTTED, ARROW, "Alice").text("Yeah! See you there.").deactivate());

        assertThat(diagram.generate()).isEqualTo(read("/sequenceDiagram/backgroundHighlighting.mmd"));
    }

    @Test
    public void comments() {
        SequenceDiagram diagram = new SequenceDiagram();
        diagram
                .message("Alice", "John", "Hello John, how are you?")
                .comment("this is a comment")
                .message(message("John", DOTTED, ARROW, "Alice").text("Great!"));

        assertThat(diagram.generate()).isEqualTo(read("/sequenceDiagram/comments.mmd"));
    }

    @Test
    public void entityCodesToEscapeCharacters() {
        SequenceDiagram diagram = new SequenceDiagram();
        diagram
                .message("A", "B", "I #9829; you!")
                .message("B", "A", "I #9829; you #infin; times more!");

        assertThat(diagram.generate()).isEqualTo(read("/sequenceDiagram/entityCodesToEscapeCharacters.mmd"));
    }

    @Test
    public void autonumber() {
        SequenceDiagram diagram = new SequenceDiagram();
        diagram
                .autonumber()
                .message("Alice", "John", "Hello John, how are you?")
                .block(loop()
                        .text("HealthCheck")
                        .message(message("John", "John").text("Fight against hypochondria")))
                .note(noteRightOf("John","Rational thoughts!"))
                .message(message("John", DOTTED, ARROW, "Alice").text("Great!"))
                .message("John", "Bob", "How about you?")
                .message(message("Bob", DOTTED, ARROW, "John").text("Jolly good!"));

        assertThat(diagram.generate()).isEqualTo(read("/sequenceDiagram/autonumber.mmd"));
    }

    @Test
    public void autonumber2() {
        SequenceDiagram diagram = new SequenceDiagram();
        diagram
                .autonumber(number("20.99"), number("5.5"))
                .message("Alice", "John", "Hello John, how are you?")
                .block(loop()
                        .text("HealthCheck")
                        .message("John", "John","Fight against hypochondria"))
                .note(noteRightOf("John", "Rational thoughts!"))
                .message(message("John", DOTTED, ARROW, "Alice").text("Great!"))
                .message("John", "Bob", "How about you?")
                .message(message("Bob", DOTTED, ARROW, "John").text("Jolly good!"));

        assertThat(diagram.generate()).isEqualTo(read("/sequenceDiagram/autonumber2.mmd"));
    }

    @Test
    public void actorsMenu() {
        SequenceDiagram diagram = new SequenceDiagram();
        diagram
                .participant("Alice")
                .participant("John")
                .link(link("Alice", "Dashboard", "https://dashboard.contoso.com/alice"))
                .link(link("Alice", "Wiki", "https://wiki.contoso.com/alice"))
                .link(link("John", "Dashboard", "https://dashboard.contoso.com/john"))
                .link(link("John", "Wiki", "https://wiki.contoso.com/john"))
                .message("Alice", "John", "Hello John, how are you?")
                .message(message("John", DOTTED, ARROW, "Alice").text("Great!"))
                .message(message("Alice", SOLID, OPEN_ARROW, "John").text("See you later!"));

        assertThat(diagram.generate()).isEqualTo(read("/sequenceDiagram/actorsMenu.mmd"));
    }

}
