package com.lamzi.doc.mermaid.diagram.sequencediagram;

import com.lamzi.doc.mermaid.diagram.BaseTest;
import org.junit.jupiter.api.Test;

import static com.lamzi.doc.mermaid.diagram.sequencediagram.SequenceDiagramFactory.actor;
import static com.lamzi.doc.mermaid.diagram.sequencediagram.SequenceDiagramFactory.message;
import static com.lamzi.doc.mermaid.diagram.sequencediagram.SequenceDiagramFactory.participant;
import static com.lamzi.doc.mermaid.diagram.sequencediagram.SequenceDiagramFactory.participantConfig;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class SequenceDiagramTest extends BaseTest {

    @Test
    public void sequenceDiagram() {
        SequenceDiagram diagram = new SequenceDiagram();
        diagram
                .message("Alice", "John", "Hello John, how are you?")
                .message(message("John", SequenceMessage.Arrow.DOTTED_ARROW, "Alice", "Great!"))
                .message(message("Alice", SequenceMessage.Arrow.SOLID_OPEN, "John", "See you later!"));

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
                .message(message("DB", SequenceMessage.Arrow.DOTTED_ARROW, "Svc", "User data"))
                .message(message("Svc", SequenceMessage.Arrow.DOTTED_ARROW, "API", "Token"));

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
                .message(message("DB", SequenceMessage.Arrow.DOTTED_ARROW, "Auth", "User data"))
                .message(message("Auth", SequenceMessage.Arrow.DOTTED_ARROW, "API", "Access token"));

        assertThat(diagram.generate()).isEqualTo(read("/sequenceDiagram/inlineAlias.mmd"));
    }
}
