package com.lamzi.doc.mermaid.diagram.flowchart;

import com.lamzi.doc.mermaid.diagram.BaseTest;
import com.lamzi.doc.mermaid.diagram.DiagramFrontMatter;

import com.lamzi.doc.mermaid.diagram.StyleDefinition;
import com.lamzi.doc.mermaid.diagram.flowchart.shape.ClassicNodeShape;
import com.lamzi.doc.mermaid.diagram.flowchart.shape.ExpandedNodeShape;
import com.lamzi.doc.mermaid.diagram.flowchart.shape.IconNodeShape;
import com.lamzi.doc.mermaid.diagram.flowchart.shape.ImageNodeShape;
import org.junit.jupiter.api.Test;


import java.util.List;

import static com.lamzi.doc.mermaid.diagram.flowchart.FlowchartFactory.*;
import static com.lamzi.doc.mermaid.diagram.flowchart.FlowchartFactory.hrefClick;
import static com.lamzi.doc.mermaid.diagram.flowchart.FlowchartFactory.node;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class FlowchartDiagramTest extends BaseTest {


    @Test
    public void aNodeDefault() {

        DiagramFrontMatter<FlowchartDiagramConfiguration> frontMatter = new DiagramFrontMatter<>();
        frontMatter.setTitle("Node");
        FlowchartDiagram diagram = new FlowchartDiagram(frontMatter);
        diagram
                .direction(FlowchartDirection.LR)
                .addNode("id");
        assertThat(diagram.generate()).isEqualTo(read("/flowchart/aNodeDefault.mmd"));
    }

    @Test
    public void aNodeWithText() {
        DiagramFrontMatter<FlowchartDiagramConfiguration> frontMatter = new DiagramFrontMatter<>();
        frontMatter.setTitle("Node with text");
        FlowchartDiagram diagram = new FlowchartDiagram(frontMatter);
        diagram
                .direction(FlowchartDirection.LR)
                .addNode(node("id1").shape(classicNodeShape("This is the text in the box", ClassicNodeShape.Shape.SQUARE_EDGES)));
        assertThat(diagram.generate()).isEqualTo(read("/flowchart/aNodeWithText.mmd"));
    }

    @Test
    public void unicodeText() {
        FlowchartDiagram diagram = new FlowchartDiagram();
        diagram
                .direction(FlowchartDirection.LR)
                .addNode(node("id").shape(classicNodeShape("\"This ❤ Unicode\"", ClassicNodeShape.Shape.SQUARE_EDGES)));
        assertThat(diagram.generate()).isEqualTo(read("/flowchart/unicodeText.mmd"));
    }

    @Test
    public void markdownFormatting() {
        FlowchartDiagramConfiguration configuration = new FlowchartDiagramConfiguration();
        configuration.htmlLabels(false);
        DiagramFrontMatter<FlowchartDiagramConfiguration> frontMatter = new DiagramFrontMatter<>(configuration);
        FlowchartDiagram diagram = new FlowchartDiagram(frontMatter);
        diagram
                .direction(FlowchartDirection.LR)
                .addNode(node("markdown").shape(classicNodeShape("\"`This **is** _Markdown_`\"", ClassicNodeShape.Shape.SQUARE_EDGES)))
                .addNode(node("newLines").shape(classicNodeShape("\"`Line1\n    Line 2\n    Line 3`\"", ClassicNodeShape.Shape.SQUARE_EDGES)))
                .addLink("markdown", "newLines");

        assertThat(diagram.generate()).isEqualTo(read("/flowchart/markdownFormatting.mmd"));
    }

    @Test
    public void directionTD() {
        FlowchartDiagram diagram = new FlowchartDiagram()
                .direction(FlowchartDirection.TD)
                .addLink("Start", "Stop");
        assertThat(diagram.generate()).isEqualTo(read("/flowchart/directionTD.mmd"));
    }

    @Test
    public void directionLR() {
        FlowchartDiagram diagram = new FlowchartDiagram()
                .direction(FlowchartDirection.LR)
                .addLink("Start", "Stop");
        assertThat(diagram.generate()).isEqualTo(read("/flowchart/directionLR.mmd"));
    }

    @Test
    public void nodeShapeRoundedEdges() {
        FlowchartDiagram diagram = new FlowchartDiagram()
                .direction(FlowchartDirection.LR)
                .addNode(node("id1").shape(classicNodeShape("This is the text in the box", ClassicNodeShape.Shape.ROUNDED_EDGES)));
        assertThat(diagram.generate()).isEqualTo(read("/flowchart/nodeShapeRoundedEdges.mmd"));
    }

    @Test
    public void nodeShapeStadium() {
        FlowchartDiagram diagram = new FlowchartDiagram()
                .direction(FlowchartDirection.LR)
                .addNode(node("id1").shape(classicNodeShape("This is the text in the box", ClassicNodeShape.Shape.STADIUM)));
        assertThat(diagram.generate()).isEqualTo(read("/flowchart/nodeShapeStadium.mmd"));
    }

    @Test
    public void nodeShapeSubroutine() {
        FlowchartDiagram diagram = new FlowchartDiagram()
                .direction(FlowchartDirection.LR)
                .addNode(node("id1").shape(classicNodeShape("This is the text in the box", ClassicNodeShape.Shape.SUBROUTINE)));
        assertThat(diagram.generate()).isEqualTo(read("/flowchart/nodeShapeSubroutine.mmd"));
    }

    @Test
    public void nodeShapeCylindrical() {
        FlowchartDiagram diagram = new FlowchartDiagram()
                .direction(FlowchartDirection.LR)
                .addNode(node("id1").shape(classicNodeShape("Database", ClassicNodeShape.Shape.CYLINDRICAL)));
        assertThat(diagram.generate()).isEqualTo(read("/flowchart/nodeShapeCylindrical.mmd"));
    }

    @Test
    public void nodeShapeCircle() {
        FlowchartDiagram diagram = new FlowchartDiagram()
                .direction(FlowchartDirection.LR)
                .addNode(node("id1").shape(classicNodeShape("This is the text in the circle", ClassicNodeShape.Shape.CIRCLE)));
        assertThat(diagram.generate()).isEqualTo(read("/flowchart/nodeShapeCircle.mmd"));
    }

    @Test
    public void nodeShapeAsymmetric() {
        FlowchartDiagram diagram = new FlowchartDiagram()
                .direction(FlowchartDirection.LR)
                .addNode(node("id1").shape(classicNodeShape("This is the text in the box", ClassicNodeShape.Shape.ASYMMETRIC)));
        assertThat(diagram.generate()).isEqualTo(read("/flowchart/nodeShapeAsymmetric.mmd"));
    }


    @Test
    public void nodeShapeRhombus() {
        FlowchartDiagram diagram = new FlowchartDiagram()
                .direction(FlowchartDirection.LR)
                .addNode(node("id1").shape(classicNodeShape("This is the text in the box", ClassicNodeShape.Shape.RHOMBUS)));
        assertThat(diagram.generate()).isEqualTo(read("/flowchart/nodeShapeRhombus.mmd"));
    }

    @Test
    public void nodeShapeHexagon() {
        FlowchartDiagram diagram = new FlowchartDiagram()
                .direction(FlowchartDirection.LR)
                .addNode(node("id1").shape(classicNodeShape("This is the text in the box", ClassicNodeShape.Shape.HEXAGON)));
        assertThat(diagram.generate()).isEqualTo(read("/flowchart/nodeShapeHexagon.mmd"));
    }

    @Test
    public void nodeShapeParallelogram() {
        FlowchartDiagram diagram = new FlowchartDiagram()
                .direction(FlowchartDirection.TD)
                .addNode(node("id1").shape(classicNodeShape("This is the text in the box", ClassicNodeShape.Shape.PARALLELOGRAM)));
        assertThat(diagram.generate()).isEqualTo(read("/flowchart/nodeShapeParallelogram.mmd"));
    }

    @Test
    public void nodeShapeParallelogramAlt() {
        FlowchartDiagram diagram = new FlowchartDiagram()
                .direction(FlowchartDirection.TD)
                .addNode(node("id1").shape(classicNodeShape("This is the text in the box", ClassicNodeShape.Shape.PARALLELOGRAM_ALT)));
        assertThat(diagram.generate()).isEqualTo(read("/flowchart/nodeShapeParallelogramAlt.mmd"));
    }

    @Test
    public void nodeShapeTrapezoid() {
        FlowchartDiagram diagram = new FlowchartDiagram()
                .direction(FlowchartDirection.TD)
                .addNode(node("A").shape(classicNodeShape("Christmas", ClassicNodeShape.Shape.TRAPEZOID)));
        assertThat(diagram.generate()).isEqualTo(read("/flowchart/nodeShapeTrapezoid.mmd"));
    }

    @Test
    public void nodeShapeTrapezoidAlt() {
        FlowchartDiagram diagram = new FlowchartDiagram()
                .direction(FlowchartDirection.TD)
                .addNode(node("B").shape(classicNodeShape("Go shopping", ClassicNodeShape.Shape.TRAPEZOID_ALT)));
        assertThat(diagram.generate()).isEqualTo(read("/flowchart/nodeShapeTrapezoidAlt.mmd"));
    }

    @Test
    public void nodeShapeDoubleCircle() {
        FlowchartDiagram diagram = new FlowchartDiagram()
                .direction(FlowchartDirection.TD)
                .addNode(node("id1").shape(classicNodeShape("This is the text in the circle", ClassicNodeShape.Shape.DOUBLE_CIRCLE)));
        assertThat(diagram.generate()).isEqualTo(read("/flowchart/nodeShapeDoubleCircle.mmd"));
    }

    @Test
    public void flowchartWithNewShapes() {
        FlowchartDiagram diagram = new FlowchartDiagram()
                .direction(FlowchartDirection.RL)
                .addNode(node("A").shape(expandedNodeShape("File Handling", "manual-file")))
                .addNode(node("B").shape(expandedNodeShape("User Input", "manual-input")))
                .addNode(node("C").shape(expandedNodeShape("Multiple Documents", ExpandedNodeShape.STACKED_DOCUMENTS)))
                .addNode(node("D").shape(expandedNodeShape("Process Automation", "procs")))
                .addNode(node("E").shape(expandedNodeShape("Paper Records", "paper-tape")));

        assertThat(diagram.generate()).isEqualTo(read("/flowchart/flowchartWithNewShapes.mmd"));
    }

    @Test
    public void ProcessNewShapes() {
        FlowchartDiagram diagram = new FlowchartDiagram()
                .direction(FlowchartDirection.TD)
                .addNode(node("A").shape(expandedNodeShape("This is a process", ExpandedNodeShape.RECTANGLE)));

        assertThat(diagram.generate()).isEqualTo(read("/flowchart/ProcessNewShapes.mmd"));
    }

    @Test
    public void EventNewShapes() {
        FlowchartDiagram diagram = new FlowchartDiagram()
                .direction(FlowchartDirection.TD)
                .addNode(node("A").shape(expandedNodeShape("This is an event", ExpandedNodeShape.ROUNDED_RECTANGLE)));

        assertThat(diagram.generate()).isEqualTo(read("/flowchart/EventNewShapes.mmd"));
    }

    @Test
    public void TerminalPointNewShapes() {
        FlowchartDiagram diagram = new FlowchartDiagram()
                .direction(FlowchartDirection.TD)
                .addNode(node("A").shape(expandedNodeShape("Terminal point", ExpandedNodeShape.STADIUM)));

        assertThat(diagram.generate()).isEqualTo(read("/flowchart/TerminalPointNewShapes.mmd"));
    }

    @Test
    public void SubprocessNewShapes() {
        FlowchartDiagram diagram = new FlowchartDiagram()
                .direction(FlowchartDirection.TD)
                .addNode(node("A").shape(expandedNodeShape("This is a subprocess", "subproc")));

        assertThat(diagram.generate()).isEqualTo(read("/flowchart/SubprocessNewShapes.mmd"));
    }

    @Test
    public void databaseNewShapes() {
        FlowchartDiagram diagram = new FlowchartDiagram()
                .direction(FlowchartDirection.TD)
                .addNode(node("A").shape(expandedNodeShape("Database", ExpandedNodeShape.CYLINDER)));

        assertThat(diagram.generate()).isEqualTo(read("/flowchart/databaseNewShapes.mmd"));
    }

    @Test
    public void startNewShapes() {
        FlowchartDiagram diagram = new FlowchartDiagram()
                .direction(FlowchartDirection.TD)
                .addNode(node("A").shape(expandedNodeShape("Start", ExpandedNodeShape.CIRCLE)));

        assertThat(diagram.generate()).isEqualTo(read("/flowchart/startNewShapes.mmd"));
    }

    @Test
    public void oddNewShapes() {
        FlowchartDiagram diagram = new FlowchartDiagram()
                .direction(FlowchartDirection.TD)
                .addNode(node("A").shape(expandedNodeShape("Odd shape", ExpandedNodeShape.ODD)));

        assertThat(diagram.generate()).isEqualTo(read("/flowchart/oddNewShapes.mmd"));
    }

    @Test
    public void decisionNewShapes() {
        FlowchartDiagram diagram = new FlowchartDiagram()
                .direction(FlowchartDirection.TD)
                .addNode(node("A").shape(expandedNodeShape("Decision", "diamond")));

        assertThat(diagram.generate()).isEqualTo(read("/flowchart/decisionNewShapes.mmd"));
    }

    @Test
    public void prepareConditionalNewShapes() {
        FlowchartDiagram diagram = new FlowchartDiagram()
                .direction(FlowchartDirection.TD)
                .addNode(node("A").shape(expandedNodeShape("Prepare conditional", ExpandedNodeShape.HEXAGON)));

        assertThat(diagram.generate()).isEqualTo(read("/flowchart/prepareConditionalNewShapes.mmd"));
    }

    @Test
    public void leanRightNewShapes() {
        FlowchartDiagram diagram = new FlowchartDiagram()
                .direction(FlowchartDirection.TD)
                .addNode(node("A").shape(expandedNodeShape("Input/Output", ExpandedNodeShape.LEAN_RIGHT)));

        assertThat(diagram.generate()).isEqualTo(read("/flowchart/leanRightNewShapes.mmd"));
    }

    @Test
    public void leanLeftNewShapes() {
        FlowchartDiagram diagram = new FlowchartDiagram()
                .direction(FlowchartDirection.TD)
                .addNode(node("A").shape(expandedNodeShape("Output/Input", ExpandedNodeShape.LEAN_LEFT)));

        assertThat(diagram.generate()).isEqualTo(read("/flowchart/leanLeftNewShapes.mmd"));
    }

    @Test
    public void datastoreNewShapes() {
        FlowchartDiagram diagram = new FlowchartDiagram()
                .direction(FlowchartDirection.TD)
                .addNode(node("A").shape(expandedNodeShape("Datastore", ExpandedNodeShape.DATA_STORE)));

        assertThat(diagram.generate()).isEqualTo(read("/flowchart/datastoreNewShapes.mmd"));
    }

    @Test
    public void priorityActionNewShapes() {
        FlowchartDiagram diagram = new FlowchartDiagram()
                .direction(FlowchartDirection.TD)
                .addNode(node("A").shape(expandedNodeShape("Priority action", ExpandedNodeShape.TRAPEZOID_BASE_BOTTOM)));

        assertThat(diagram.generate()).isEqualTo(read("/flowchart/priorityActionNewShapes.mmd"));
    }

    @Test
    public void manualOperationNewShapes() {
        FlowchartDiagram diagram = new FlowchartDiagram()
                .direction(FlowchartDirection.TD)
                .addNode(node("A").shape(expandedNodeShape("Manual operation", ExpandedNodeShape.TRAPEZOID_BASE_TOP)));

        assertThat(diagram.generate()).isEqualTo(read("/flowchart/manualOperationNewShapes.mmd"));
    }

    @Test
    public void stopNewShapes() {
        FlowchartDiagram diagram = new FlowchartDiagram()
                .direction(FlowchartDirection.TD)
                .addNode(node("A").shape(expandedNodeShape("Stop", ExpandedNodeShape.DOUBLE_CIRCLE)));

        assertThat(diagram.generate()).isEqualTo(read("/flowchart/stopNewShapes.mmd"));
    }

    @Test
    public void textBlockNewShapes() {
        FlowchartDiagram diagram = new FlowchartDiagram()
                .direction(FlowchartDirection.TD)
                .addNode(node("A").shape(expandedNodeShape("This is a text block", ExpandedNodeShape.TEXT_BLOCK)));

        assertThat(diagram.generate()).isEqualTo(read("/flowchart/textBlockNewShapes.mmd"));
    }

    @Test
    public void cardNewShapes() {
        FlowchartDiagram diagram = new FlowchartDiagram()
                .direction(FlowchartDirection.TD)
                .addNode(node("A").shape(expandedNodeShape("Card", ExpandedNodeShape.NOTCHED_RECTANGLE)));

        assertThat(diagram.generate()).isEqualTo(read("/flowchart/cardNewShapes.mmd"));
    }

    @Test
    public void linedProcessNewShapes() {
        FlowchartDiagram diagram = new FlowchartDiagram()
                .direction(FlowchartDirection.TD)
                .addNode(node("A").shape(expandedNodeShape("Lined process", ExpandedNodeShape.LINED_RECTANGLE)));

        assertThat(diagram.generate()).isEqualTo(read("/flowchart/linedProcessNewShapes.mmd"));
    }

    @Test
    public void smallStartNewShapes() {
        FlowchartDiagram diagram = new FlowchartDiagram()
                .direction(FlowchartDirection.TD)
                .addNode(node("A").shape(expandedNodeShape("Small start", ExpandedNodeShape.SMALL_CIRCLE)));

        assertThat(diagram.generate()).isEqualTo(read("/flowchart/smallStartNewShapes.mmd"));
    }

    @Test
    public void framedStopNewShapes() {
        FlowchartDiagram diagram = new FlowchartDiagram()
                .direction(FlowchartDirection.TD)
                .addNode(node("A").shape(expandedNodeShape("Stop", "framed-circle")));

        assertThat(diagram.generate()).isEqualTo(read("/flowchart/framedStopNewShapes.mmd"));
    }

    @Test
    public void ForkNewShapes() {
        FlowchartDiagram diagram = new FlowchartDiagram()
                .direction(FlowchartDirection.TD)
                .addNode(node("A").shape(expandedNodeShape("Fork or Join", ExpandedNodeShape.FILLED_RECTANGLE)));

        assertThat(diagram.generate()).isEqualTo(read("/flowchart/ForkNewShapes.mmd"));
    }

    @Test
    public void collateNewShapes() {
        FlowchartDiagram diagram = new FlowchartDiagram()
                .direction(FlowchartDirection.TD)
                .addNode(node("A").shape(expandedNodeShape("Collate", ExpandedNodeShape.HOURGLASS)));

        assertThat(diagram.generate()).isEqualTo(read("/flowchart/collateNewShapes.mmd"));
    }

    @Test
    public void commentNewShapes() {
        FlowchartDiagram diagram = new FlowchartDiagram()
                .direction(FlowchartDirection.TD)
                .addNode(node("A").shape(expandedNodeShape("Comment", ExpandedNodeShape.CURVY_BRACE_RIGHT)));

        assertThat(diagram.generate()).isEqualTo(read("/flowchart/commentNewShapes.mmd"));
    }

    @Test
    public void commentBothNewShapes() {
        FlowchartDiagram diagram = new FlowchartDiagram()
                .direction(FlowchartDirection.TD)
                .addNode(node("A").shape(expandedNodeShape("Comment", ExpandedNodeShape.CURVY_BRACE_BOTH)));

        assertThat(diagram.generate()).isEqualTo(read("/flowchart/commentBothNewShapes.mmd"));
    }

    @Test
    public void comLinkNewShapes() {
        FlowchartDiagram diagram = new FlowchartDiagram()
                .direction(FlowchartDirection.TD)
                .addNode(node("A").shape(expandedNodeShape("Communication link", ExpandedNodeShape.LIGHTNING_BOLT)));

        assertThat(diagram.generate()).isEqualTo(read("/flowchart/comLinkNewShapes.mmd"));
    }

    @Test
    public void documentNewShapes() {
        FlowchartDiagram diagram = new FlowchartDiagram()
                .direction(FlowchartDirection.TD)
                .addNode(node("A").shape(expandedNodeShape("Document", ExpandedNodeShape.DOCUMENT)));

        assertThat(diagram.generate()).isEqualTo(read("/flowchart/documentNewShapes.mmd"));
    }

    @Test
    public void delayNewShapes() {
        FlowchartDiagram diagram = new FlowchartDiagram()
                .direction(FlowchartDirection.TD)
                .addNode(node("A").shape(expandedNodeShape("Delay", "delay")));

        assertThat(diagram.generate()).isEqualTo(read("/flowchart/delayNewShapes.mmd"));
    }

    @Test
    public void directAccessStorageNewShapes() {
        FlowchartDiagram diagram = new FlowchartDiagram()
                .direction(FlowchartDirection.TD)
                .addNode(node("A").shape(expandedNodeShape("Direct access storage", "das")));

        assertThat(diagram.generate()).isEqualTo(read("/flowchart/directAccessStorageNewShapes.mmd"));
    }

    @Test
    public void diskStorageNewShapes() {
        FlowchartDiagram diagram = new FlowchartDiagram()
                .direction(FlowchartDirection.TD)
                .addNode(node("A").shape(expandedNodeShape("Disk storage", ExpandedNodeShape.LINED_CYLINDER)));

        assertThat(diagram.generate()).isEqualTo(read("/flowchart/diskStorageNewShapes.mmd"));
    }

    @Test
    public void displayNewShapes() {
        FlowchartDiagram diagram = new FlowchartDiagram()
                .direction(FlowchartDirection.TD)
                .addNode(node("A").shape(expandedNodeShape("Display", ExpandedNodeShape.CURVED_TRAPEZOID)));

        assertThat(diagram.generate()).isEqualTo(read("/flowchart/displayNewShapes.mmd"));
    }

    @Test
    public void dividedProcessNewShapes() {
        FlowchartDiagram diagram = new FlowchartDiagram()
                .direction(FlowchartDirection.TD)
                .addNode(node("A").shape(expandedNodeShape("Divided process", ExpandedNodeShape.DIVIDED_RECTANGLE)));

        assertThat(diagram.generate()).isEqualTo(read("/flowchart/dividedProcessNewShapes.mmd"));
    }

    @Test
    public void extractProcessNewShapes() {
        FlowchartDiagram diagram = new FlowchartDiagram()
                .direction(FlowchartDirection.TD)
                .addNode(node("A").shape(expandedNodeShape("Extract", ExpandedNodeShape.TRIANGLE)));

        assertThat(diagram.generate()).isEqualTo(read("/flowchart/extractProcessNewShapes.mmd"));
    }

    @Test
    public void internalStorageNewShapes() {
        FlowchartDiagram diagram = new FlowchartDiagram()
                .direction(FlowchartDirection.TD)
                .addNode(node("A").shape(expandedNodeShape("Internal storage", ExpandedNodeShape.WINDOW_PANE)));

        assertThat(diagram.generate()).isEqualTo(read("/flowchart/internalStorageNewShapes.mmd"));
    }

    @Test
    public void junctionNewShapes() {
        FlowchartDiagram diagram = new FlowchartDiagram()
                .direction(FlowchartDirection.TD)
                .addNode(node("A").shape(expandedNodeShape("Junction", ExpandedNodeShape.FILLED_CIRCLE)));

        assertThat(diagram.generate()).isEqualTo(read("/flowchart/junctionNewShapes.mmd"));
    }

    @Test
    public void linedDocumentNewShapes() {
        FlowchartDiagram diagram = new FlowchartDiagram()
                .direction(FlowchartDirection.TD)
                .addNode(node("A").shape(expandedNodeShape("Lined document", ExpandedNodeShape.LINED_DOCUMENT)));

        assertThat(diagram.generate()).isEqualTo(read("/flowchart/linedDocumentNewShapes.mmd"));
    }

    @Test
    public void loopLimitNewShapes() {
        FlowchartDiagram diagram = new FlowchartDiagram()
                .direction(FlowchartDirection.TD)
                .addNode(node("A").shape(expandedNodeShape("Loop limit", ExpandedNodeShape.TRAPEZOIDAL_PENTAGON)));

        assertThat(diagram.generate()).isEqualTo(read("/flowchart/loopLimitNewShapes.mmd"));
    }

    @Test
    public void manualFileNewShapes() {
        FlowchartDiagram diagram = new FlowchartDiagram()
                .direction(FlowchartDirection.TD)
                .addNode(node("A").shape(expandedNodeShape("Manual file", ExpandedNodeShape.FLIPPED_TRIANGLE)));

        assertThat(diagram.generate()).isEqualTo(read("/flowchart/manualFileNewShapes.mmd"));
    }

    @Test
    public void manualInputNewShapes() {
        FlowchartDiagram diagram = new FlowchartDiagram()
                .direction(FlowchartDirection.TD)
                .addNode(node("A").shape(expandedNodeShape("Manual input", ExpandedNodeShape.SLOPED_RECTANGLE)));

        assertThat(diagram.generate()).isEqualTo(read("/flowchart/manualInputNewShapes.mmd"));
    }

    @Test
    public void multiDocumentNewShapes() {
        FlowchartDiagram diagram = new FlowchartDiagram()
                .direction(FlowchartDirection.TD)
                .addNode(node("A").shape(expandedNodeShape("Multiple documents", ExpandedNodeShape.STACKED_DOCUMENTS)));

        assertThat(diagram.generate()).isEqualTo(read("/flowchart/multiDocumentNewShapes.mmd"));
    }

    @Test
    public void multiProcessNewShapes() {
        FlowchartDiagram diagram = new FlowchartDiagram()
                .direction(FlowchartDirection.TD)
                .addNode(node("A").shape(expandedNodeShape("Multiple processes", "processes")));

        assertThat(diagram.generate()).isEqualTo(read("/flowchart/multiProcessNewShapes.mmd"));
    }

    @Test
    public void paperTapeNewShapes() {
        FlowchartDiagram diagram = new FlowchartDiagram()
                .direction(FlowchartDirection.TD)
                .addNode(node("A").shape(expandedNodeShape("Paper tape", ExpandedNodeShape.FLAG)));

        assertThat(diagram.generate()).isEqualTo(read("/flowchart/paperTapeNewShapes.mmd"));
    }

    @Test
    public void storedDataNewShapes() {
        FlowchartDiagram diagram = new FlowchartDiagram()
                .direction(FlowchartDirection.TD)
                .addNode(node("A").shape(expandedNodeShape("Stored data", ExpandedNodeShape.BOW_TIE_RECTANGLE)));

        assertThat(diagram.generate()).isEqualTo(read("/flowchart/storedDataNewShapes.mmd"));
    }

    @Test
    public void summaryNewShapes() {
        FlowchartDiagram diagram = new FlowchartDiagram()
                .direction(FlowchartDirection.TD)
                .addNode(node("A").shape(expandedNodeShape("Summary", ExpandedNodeShape.CROSSED_CIRCLE)));

        assertThat(diagram.generate()).isEqualTo(read("/flowchart/summaryNewShapes.mmd"));
    }

    @Test
    public void taggedDocumentNewShapes() {
        FlowchartDiagram diagram = new FlowchartDiagram()
                .direction(FlowchartDirection.TD)
                .addNode(node("A").shape(expandedNodeShape("Tagged document", ExpandedNodeShape.TAGGED_DOCUMENT)));

        assertThat(diagram.generate()).isEqualTo(read("/flowchart/taggedDocumentNewShapes.mmd"));
    }

    @Test
    public void taggedProcessNewShapes() {
        FlowchartDiagram diagram = new FlowchartDiagram()
                .direction(FlowchartDirection.TD)
                .addNode(node("A").shape(expandedNodeShape("Tagged process", ExpandedNodeShape.TAGGED_RECTANGLE)));

        assertThat(diagram.generate()).isEqualTo(read("/flowchart/taggedProcessNewShapes.mmd"));
    }

    @Test
    public void nodeShapeIconNodeShape() {
        FlowchartDiagram diagram = new FlowchartDiagram()
                .direction(FlowchartDirection.TD)
                .addNode(node("A")
                        .shape(iconNodeShape("fa:user")
                                .form(IconNodeShape.Form.SQUARE)
                                .label("User Icon")
                                .position(IconNodeShape.Position.TOP)
                                .height(60)
                        )
                );

        assertThat(diagram.generate()).isEqualTo(read("/flowchart/nodeShapeIconNodeShape.mmd"));
    }


    @Test
    public void nodeShapeImageNodeShape1() {
        FlowchartDiagram diagram = new FlowchartDiagram()
                .direction(FlowchartDirection.TD)
                .addNode(node("A")
                        .shape(imageNodeShape("https://example.com/image.png")
                                .label("Image Label")
                                .position(ImageNodeShape.Position.TOP)
                                .width(60)
                                .height(60)
                                .constraint(ImageNodeShape.Constraint.OFF)
                        )
                );

        assertThat(diagram.generate()).isEqualTo(read("/flowchart/nodeShapeImageNodeShape1.mmd"));
    }

    @Test
    public void linkArrowHead() {
        FlowchartDiagram diagram = new FlowchartDiagram()
                .direction(FlowchartDirection.LR)
                .addLink("A", "B");

        assertThat(diagram.generate()).isEqualTo(read("/flowchart/linkArrowHead.mmd"));
    }

    @Test
    public void linkOpenLink() {
        FlowchartDiagram diagram = new FlowchartDiagram()
                .direction(FlowchartDirection.LR)
                .addLink(link(node("A"), linkTo(LinkTo.Type.SIMPLE_LINK, LinkTo.HeadType.INVISIBLE, node("B"))));

        assertThat(diagram.generate()).isEqualTo(read("/flowchart/linkOpenLink.mmd"));
    }

    @Test
    public void linkTextOnLinks() {
        FlowchartDiagram diagram = new FlowchartDiagram()
                .direction(FlowchartDirection.LR)
                .addLink(link(node("A"), linkTo(LinkTo.Type.SIMPLE_LINK, LinkTo.HeadType.INVISIBLE, node("B")).text("This is the text")));

        assertThat(diagram.generate()).isEqualTo(read("/flowchart/linkTextOnLinks.mmd"));
    }

    @Test
    public void linkWithArrowHeadAndText() {
        FlowchartDiagram diagram = new FlowchartDiagram()
                .direction(FlowchartDirection.LR)
                .addLink(link(node("A"), linkTo(LinkTo.Type.SIMPLE_LINK, LinkTo.HeadType.ARROW, node("B")).text("text")));

        assertThat(diagram.generate()).isEqualTo(read("/flowchart/linkWithArrowHeadAndText.mmd"));
    }

    @Test
    public void linkWithDottedLink() {
        FlowchartDiagram diagram = new FlowchartDiagram()
                .direction(FlowchartDirection.LR)
                .addLink(link(node("A"), linkTo(LinkTo.Type.DOTTED_LINK, LinkTo.HeadType.ARROW, node("B"))));

        assertThat(diagram.generate()).isEqualTo(read("/flowchart/linkWithDottedLink.mmd"));
    }

    @Test
    public void linkWithDottedLinkWithText() {
        FlowchartDiagram diagram = new FlowchartDiagram()
                .direction(FlowchartDirection.LR)
                .addLink(link(node("A"), linkTo(LinkTo.Type.DOTTED_LINK, LinkTo.HeadType.ARROW, node("B")).text("text")));

        assertThat(diagram.generate()).isEqualTo(read("/flowchart/linkWithDottedLinkWithText.mmd"));
    }

    @Test
    public void linkWithTickLink() {
        FlowchartDiagram diagram = new FlowchartDiagram()
                .direction(FlowchartDirection.LR)
                .addLink(link(node("A"), linkTo(LinkTo.Type.TICK_LINK, LinkTo.HeadType.ARROW, node("B"))));

        assertThat(diagram.generate()).isEqualTo(read("/flowchart/linkWithTickLink.mmd"));
    }

    @Test
    public void linkWithTickLinkWithText() {
        FlowchartDiagram diagram = new FlowchartDiagram()
                .direction(FlowchartDirection.LR)
                .addLink(link(node("A"), linkTo(LinkTo.Type.TICK_LINK, LinkTo.HeadType.ARROW, node("B")).text("text")));

        assertThat(diagram.generate()).isEqualTo(read("/flowchart/linkWithTickLinkWithText.mmd"));
    }


    @Test
    public void linkWithInvisibleLink() {
        FlowchartDiagram diagram = new FlowchartDiagram()
                .direction(FlowchartDirection.LR)
                .addLink(link(node("A"), linkTo(LinkTo.Type.INVISIBLE_LINK, LinkTo.HeadType.INVISIBLE, node("B"))));

        assertThat(diagram.generate()).isEqualTo(read("/flowchart/linkWithInvisibleLink.mmd"));
    }

    @Test
    public void linkChainingLinks() {
        FlowchartDiagram diagram = new FlowchartDiagram()
                .direction(FlowchartDirection.LR)
                .addLink(link(node("A"), linkTo(LinkTo.Type.SIMPLE_LINK, LinkTo.HeadType.ARROW, node("B")).text("text")
                        .linkTo(linkTo(LinkTo.Type.SIMPLE_LINK, LinkTo.HeadType.ARROW, node("C")).text("text2"))));

        assertThat(diagram.generate()).isEqualTo(read("/flowchart/linkChainingLinks.mmd"));
    }

    @Test
    public void linkChainingLinksMultipleNodes() {
        FlowchartDiagram diagram = new FlowchartDiagram()
                .direction(FlowchartDirection.LR)
                .addLink(link(node("a"), linkTo(LinkTo.Type.SIMPLE_LINK, LinkTo.HeadType.ARROW, node("b"), node("c"))
                        .linkTo(linkTo(LinkTo.Type.SIMPLE_LINK, LinkTo.HeadType.ARROW, node("d")))));

        assertThat(diagram.generate()).isEqualTo(read("/flowchart/linkChainingLinksMultipleNodes.mmd"));
    }

    @Test
    public void linkChainingLinksMultipleNodes2() {
        FlowchartDiagram diagram = new FlowchartDiagram()
                .direction(FlowchartDirection.TB)
                .addLink(link(List.of(node("A"), node("B")), linkTo(LinkTo.Type.SIMPLE_LINK, LinkTo.HeadType.ARROW, node("C"), node("D"))));

        assertThat(diagram.generate()).isEqualTo(read("/flowchart/linkChainingLinksMultipleNodes2.mmd"));
    }

    @Test
    public void linkChainingLinksMultipleNodes2Simple() {
        FlowchartDiagram diagram = new FlowchartDiagram()
                .direction(FlowchartDirection.TB)
                .addLink("A", "C")
                .addLink("A", "D")
                .addLink("B", "C")
                .addLink("B", "D");

        assertThat(diagram.generate()).isEqualTo(read("/flowchart/linkChainingLinksMultipleNodes2Simple.mmd"));
    }

    @Test
    public void linkId() {
        FlowchartDiagram diagram = new FlowchartDiagram()
                .direction(FlowchartDirection.LR)
                .addLink(link(node("A"), linkTo(LinkTo.Type.SIMPLE_LINK, LinkTo.HeadType.ARROW, node("B")).id("e1")));
        assertThat(diagram.generate()).isEqualTo(read("/flowchart/linkId.mmd"));
    }

    @Test
    public void linkAnimationAnimate() {
        FlowchartDiagram diagram = new FlowchartDiagram()
                .direction(FlowchartDirection.LR)
                .addLink(link(node("A"), linkTo(LinkTo.Type.TICK_LINK, LinkTo.HeadType.ARROW, node("B")).id("e1")))
                .addEdgeIdStyle(edgeIdStyle("e1").animate(true));
        assertThat(diagram.generate()).isEqualTo(read("/flowchart/linkAnimationAnimate.mmd"));
    }

    @Test
    public void linkAnimationAnimation() {
        FlowchartDiagram diagram = new FlowchartDiagram()
                .direction(FlowchartDirection.LR)
                .addLink(link(node("A"), linkTo(LinkTo.Type.SIMPLE_LINK, LinkTo.HeadType.ARROW, node("B")).id("e1")))
                .addEdgeIdStyle(edgeIdStyle("e1").animation(EdgeIdStyle.Animation.FAST));
        assertThat(diagram.generate()).isEqualTo(read("/flowchart/linkAnimationAnimation.mmd"));
    }

    @Test
    public void classDefAnimation() {
        FlowchartDiagram diagram = new FlowchartDiagram()
                .direction(FlowchartDirection.LR)
                .addLink(link(node("A"), linkTo(LinkTo.Type.SIMPLE_LINK, LinkTo.HeadType.ARROW, node("B")).id("e1")))
                .cssClassDefinition("animate", new StyleDefinition<FlowchartStyleDefinitionAttribute>()
                        .addAttribute(FlowchartStyleDefinitionAttribute.STROKE_DASH_ARRAY, "9,5")
                        .addAttribute(FlowchartStyleDefinitionAttribute.STROKE_DASHOFFSET, "900")
                        .addAttribute(FlowchartStyleDefinitionAttribute.ANIMATION, "dash 25s linear infinite")
                ).nodeClass("animate", "e1");
        assertThat(diagram.generate()).isEqualTo(read("/flowchart/classDefAnimation.mmd"));
    }


    @Test
    public void linkCircleEdge() {
        FlowchartDiagram diagram = new FlowchartDiagram()
                .direction(FlowchartDirection.LR)
                .addLink(link(node("A"), linkTo(LinkTo.Type.SIMPLE_LINK, LinkTo.HeadType.CIRCLE, node("B"))));
        assertThat(diagram.generate()).isEqualTo(read("/flowchart/linkCircleEdge.mmd"));
    }

    @Test
    public void linkCrossEdge() {
        FlowchartDiagram diagram = new FlowchartDiagram()
                .direction(FlowchartDirection.LR)
                .addLink(link(node("A"), linkTo(LinkTo.Type.SIMPLE_LINK, LinkTo.HeadType.CROSS, node("B"))));
        assertThat(diagram.generate()).isEqualTo(read("/flowchart/linkCrossEdge.mmd"));
    }

    @Test
    public void linkMultiDirectionalArrows() {
        FlowchartDiagram diagram = new FlowchartDiagram()
                .direction(FlowchartDirection.LR)
                .addLink(link(node("A"), linkTo(LinkTo.Type.SIMPLE_LINK, LinkTo.HeadType.CIRCLE, node("B")).multiDirectional(true)))
                .addLink(link(node("B"), linkTo(LinkTo.Type.SIMPLE_LINK, LinkTo.HeadType.ARROW, node("C")).multiDirectional(true)))
                .addLink(link(node("C"), linkTo(LinkTo.Type.SIMPLE_LINK, LinkTo.HeadType.CROSS, node("D")).multiDirectional(true)));
        assertThat(diagram.generate()).isEqualTo(read("/flowchart/linkMultiDirectionalArrows.mmd"));
    }

    @Test
    public void linkMinimumLength() {
        FlowchartDiagram diagram = new FlowchartDiagram()
                .direction(FlowchartDirection.TD)
                .addLink(
                        link(node("A")
                                        .shape(classicNodeShape("Start", ClassicNodeShape.Shape.SQUARE_EDGES)),
                                linkTo(LinkTo.Type.SIMPLE_LINK, LinkTo.HeadType.ARROW, node("B")
                                        .shape(classicNodeShape("Is it?", ClassicNodeShape.Shape.RHOMBUS))
                                )
                        )
                )
                .addLink(
                        link(node("B"),
                                linkTo(
                                        LinkTo.Type.SIMPLE_LINK,
                                        LinkTo.HeadType.ARROW,
                                        node("C").shape(classicNodeShape("OK", ClassicNodeShape.Shape.SQUARE_EDGES))
                                ).text("Yes")
                        )
                )
                .addLink(
                        link(node("C"), linkTo(
                                        LinkTo.Type.SIMPLE_LINK,
                                        LinkTo.HeadType.ARROW,
                                        node("D").shape(classicNodeShape("Rethink", ClassicNodeShape.Shape.SQUARE_EDGES)
                                        )
                                )
                        )
                )
                .addLink(
                        link(node("D"), linkTo(LinkTo.Type.SIMPLE_LINK, LinkTo.HeadType.ARROW, node("B")))
                )
                .addLink(
                        link(node("B"),
                                linkTo(
                                        LinkTo.Type.SIMPLE_LINK,
                                        LinkTo.HeadType.ARROW,
                                        node("E")
                                                .shape(classicNodeShape("End", ClassicNodeShape.Shape.SQUARE_EDGES))
                                )
                                        .length(3)
                                        .text("No")
                        )
                );


        assertThat(diagram.generate()).isEqualTo(read("/flowchart/linkMinimumLength.mmd"));
    }

    @Test
    public void specialCharactersThatBreakSyntax() {
        FlowchartDiagram diagram = new FlowchartDiagram()
                .direction(FlowchartDirection.LR)
                .addNode(node("id1").shape(classicNodeShape("\"This is the (text) in the box\"", ClassicNodeShape.Shape.SQUARE_EDGES)));
        assertThat(diagram.generate()).isEqualTo(read("/flowchart/specialCharactersThatBreakSyntax.mmd"));
    }

    @Test
    public void entityCodesToEscapeCharacters() {
        FlowchartDiagram diagram = new FlowchartDiagram()
                .direction(FlowchartDirection.LR)
                .addLink(link(
                        node("A").shape(classicNodeShape("\"A double quote:#quot;\"", ClassicNodeShape.Shape.SQUARE_EDGES)), linkTo(LinkTo.Type.SIMPLE_LINK,
                                LinkTo.HeadType.ARROW, node("B").shape(classicNodeShape("\"A dec char:#9829;\"", ClassicNodeShape.Shape.SQUARE_EDGES))
                        )));
        assertThat(diagram.generate()).isEqualTo(read("/flowchart/entityCodesToEscapeCharacters.mmd"));
    }

    @Test
    public void subgaphs() {
        FlowchartDiagram diagram = new FlowchartDiagram()
                .direction(FlowchartDirection.TB)
                .addLink("c1", "a2")
                .addSubgraph(subgraph("one")
                        .addLink("a1", "a2")
                )
                .addSubgraph(subgraph("two")
                        .addLink("b1", "b2")
                )
                .addSubgraph(subgraph("three")
                        .addLink("c1", "c2")
                );
        assertThat(diagram.generate()).isEqualTo(read("/flowchart/subgaphs.mmd"));
    }

    @Test
    public void subgaphsExplicitId() {
        FlowchartDiagram diagram = new FlowchartDiagram()
                .direction(FlowchartDirection.TB)
                .addLink("c1", "a2")
                .addSubgraph(subgraph("ide1")
                        .label("one")
                        .addLink("a1", "a2")
                );
        assertThat(diagram.generate()).isEqualTo(read("/flowchart/subgaphsExplicitId.mmd"));
    }

    @Test
    public void subgaphsLinkBetweenSubgraphs() {
        FlowchartDiagram diagram = new FlowchartDiagram()
                .direction(FlowchartDirection.TB)
                .addLink("c1", "a2")
                .addSubgraph(subgraph("one")
                        .addLink("a1", "a2")
                )
                .addSubgraph(subgraph("two")
                        .addLink("b1", "b2")
                )
                .addSubgraph(subgraph("three")
                        .addLink("c1", "c2")
                )
                .addLink("one", "two")
                .addLink("three", "two")
                .addLink("two", "c2");
        assertThat(diagram.generate()).isEqualTo(read("/flowchart/subgaphsLinkBetweenSubgraphs.mmd"));
    }

    @Test
    public void subgaphsDirection() {
        FlowchartDiagram diagram = new FlowchartDiagram()
                .direction(FlowchartDirection.LR)
                .addSubgraph(subgraph("subgraph1")
                        .direction(FlowchartDirection.TB)
                        .addLink(link(
                                node("top1").shape(classicNodeShape("top", ClassicNodeShape.Shape.SQUARE_EDGES)),
                                linkTo(LinkTo.Type.SIMPLE_LINK, LinkTo.HeadType.ARROW, node("bottom1").shape(classicNodeShape("bottom", ClassicNodeShape.Shape.SQUARE_EDGES)))
                        ))
                )
                .addSubgraph(subgraph("subgraph2")
                        .direction(FlowchartDirection.TB)
                        .addLink(link(
                                node("top2").shape(classicNodeShape("top", ClassicNodeShape.Shape.SQUARE_EDGES)),
                                linkTo(LinkTo.Type.SIMPLE_LINK, LinkTo.HeadType.ARROW, node("bottom2").shape(classicNodeShape("bottom", ClassicNodeShape.Shape.SQUARE_EDGES)))
                        ))
                )
                .addComment("^ These subgraphs are identical, except for the links to them:")
                .addComment("Link *to* subgraph1: subgraph1 direction is maintained")
                .addLink("outside", "subgraph1")
                .addComment("Link *within* subgraph2:")
                .addComment("subgraph2 inherits the direction of the top-level graph (LR)")
                .addLink(link(node("outside"), linkTo(LinkTo.Type.SIMPLE_LINK, LinkTo.HeadType.ARROW, node("top2")).length(2)));
        assertThat(diagram.generate()).isEqualTo(read("/flowchart/subgaphsDirection.mmd"));
    }

    @Test
    public void subgaphsMarkdownStrings() {
        FlowchartDiagramConfiguration config = new FlowchartDiagramConfiguration().htmlLabels(false);
        DiagramFrontMatter<FlowchartDiagramConfiguration> frontMatter = new DiagramFrontMatter<>(config);
        FlowchartDiagram diagram = new FlowchartDiagram(frontMatter)
                .direction(FlowchartDirection.LR)
                .addSubgraph(subgraph("\"One\"")
                        .addLink(link(
                                node("a").shape(classicNodeShape("\"`The **cat**\n        in the hat`\"", ClassicNodeShape.Shape.ROUNDED_EDGES)),
                                linkTo(LinkTo.Type.SIMPLE_LINK, LinkTo.HeadType.ARROW, node("b").shape(classicNodeShape("\"`The **dog** in the hog`\"", ClassicNodeShape.Shape.HEXAGON)))
                                        .text("\"edge label\"")
                        ))
                )
                .addSubgraph(subgraph("\"`**Two**`\"")
                        .addLink(link(
                                node("c").shape(classicNodeShape("\"`The **cat**\n        in the hat`\"", ClassicNodeShape.Shape.ROUNDED_EDGES)),
                                linkTo(LinkTo.Type.SIMPLE_LINK, LinkTo.HeadType.ARROW, node("d").shape(classicNodeShape("\"The dog in the hog\"", ClassicNodeShape.Shape.ROUNDED_EDGES)))
                                        .text("\"`Bold **edge label**`\"")
                        ))
                );
        assertThat(diagram.generate()).isEqualTo(read("/flowchart/subgaphsMarkdownStrings.mmd"));
    }

    @Test
    public void interactionCall() {
        FlowchartDiagram diagram = new FlowchartDiagram()
                .direction(FlowchartDirection.LR)
                .addLink("A", "B")
                .addLink("B", "C")
                .addLink("C", "D")
                .click(callbackClick("A", "callback").tooltip("Tooltip for a callback"))
                .click(hrefClick("B", "https://www.github.com").tooltip("This is a tooltip for a link"))
                .click(callbackClick("C", "callback").displayKind().tooltip("Tooltip for a callback"))
                .click(hrefClick("D", "https://www.github.com").displayKind().tooltip("This is a tooltip for a link"));
        assertThat(diagram.generate()).isEqualTo(read("/flowchart/interactionCall.mmd"));
    }

    @Test
    public void interactionLinkTarget() {
        FlowchartDiagram diagram = new FlowchartDiagram()
                .direction(FlowchartDirection.LR)
                .addLink("A", "B")
                .addLink("B", "C")
                .addLink("C", "D")
                .addLink("D", "E")
                .click(hrefClick("A", "https://www.github.com").target("_blank"))
                .click(hrefClick("B", "https://www.github.com").target("_blank").tooltip("Open this in a new tab"))
                .click(hrefClick("C", "https://www.github.com").displayKind().target("_blank"))
                .click(hrefClick("D", "https://www.github.com").displayKind().target("_blank").tooltip("Open this in a new tab"));
        assertThat(diagram.generate()).isEqualTo(read("/flowchart/interactionLinkTarget.mmd"));
    }

    @Test
    public void comment() {
        FlowchartDiagram diagram = new FlowchartDiagram()
                .direction(FlowchartDirection.LR)
                .addComment("this is a comment A -- text --> B{node}")
                .addLink(link(node("A"),
                        linkTo(LinkTo.Type.SIMPLE_LINK, LinkTo.HeadType.ARROW, node("B")
                                .linkTo(linkTo(LinkTo.Type.SIMPLE_LINK, LinkTo.HeadType.ARROW, node("C")).text("text2"))).text("text"))
                );
        assertThat(diagram.generate()).isEqualTo(read("/flowchart/comment.mmd"));
    }

    @Test
    public void linkStyle() {
        FlowchartDiagram diagram = new FlowchartDiagram()
                .direction(FlowchartDirection.TD)
                .addLink("A", "B")
                .addLink("B", "C")
                .addLink("C", "D")
                .addLink("D", "E")
                .linkStyle(new StyleDefinition<FlowchartStyleDefinitionAttribute>()
                                .addAttribute(FlowchartStyleDefinitionAttribute.STROKE, "#ff3")
                                .addAttribute(FlowchartStyleDefinitionAttribute.STROKE_WIDTH, "4px")
                                .addAttribute(FlowchartStyleDefinitionAttribute.COLOR, "red"),
                        3
                );

        assertThat(diagram.generate()).isEqualTo(read("/flowchart/linkStyle.mmd"));
    }

    @Test
    public void linkStyleMultipleLinks() {
        FlowchartDiagram diagram = new FlowchartDiagram()
                .direction(FlowchartDirection.TD)
                .addLink("A", "B")
                .addLink("B", "C")
                .addLink("C", "D")
                .addLink("D", "E")
                .addLink("E", "F")
                .addLink("F", "G")
                .addLink("G", "H")
                .addLink("H", "I")
                .linkStyle(new StyleDefinition<FlowchartStyleDefinitionAttribute>()
                                .addAttribute(FlowchartStyleDefinitionAttribute.COLOR, "blue"),
                        1,2,7
                );

        assertThat(diagram.generate()).isEqualTo(read("/flowchart/linkStyleMultipleLinks.mmd"));
    }

    @Test
    public void curveConfig() {
        FlowchartDiagramConfiguration config = new FlowchartDiagramConfiguration().curve("stepBefore");
        DiagramFrontMatter<FlowchartDiagramConfiguration> frontmatter = new DiagramFrontMatter<>(config);
        FlowchartDiagram diagram = new FlowchartDiagram(frontmatter)
                .direction(FlowchartDirection.TD)
                .addLink("A", "B");

        assertThat(diagram.generate()).isEqualTo(read("/flowchart/curveConfig.mmd"));
    }


    @Test
    public void curveStyleById() {
        FlowchartDiagram diagram = new FlowchartDiagram()
                .direction(FlowchartDirection.LR)
                .addLink(link(node("A"), linkTo(LinkTo.Type.TICK_LINK, LinkTo.HeadType.ARROW, node("B")).id("e1")))
                .addLink(link(node("A"), linkTo(LinkTo.Type.SIMPLE_LINK, LinkTo.HeadType.ARROW, node("C")).id("e2")))
                .addEdgeIdStyle(edgeIdStyle("e1").curve("linear"))
                .addEdgeIdStyle(edgeIdStyle("e2").curve("natural"));

        assertThat(diagram.generate()).isEqualTo(read("/flowchart/curveStyleById.mmd"));
    }

    @Test
    public void stylingANode() {
        FlowchartDiagram diagram = new FlowchartDiagram()
                .direction(FlowchartDirection.LR)
                .addLink(link(node("A"), linkTo(LinkTo.Type.TICK_LINK, LinkTo.HeadType.ARROW, node("B")).id("e1")))
                .addLink(link(node("A"), linkTo(LinkTo.Type.SIMPLE_LINK, LinkTo.HeadType.ARROW, node("C")).id("e2")))
                .addEdgeIdStyle(edgeIdStyle("e1").curve("linear"))
                .addEdgeIdStyle(edgeIdStyle("e2").curve("natural"));

        assertThat(diagram.generate()).isEqualTo(read("/flowchart/stylingANode.mmd"));
    }


}