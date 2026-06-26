package com.lamzi.doc.mermaid.diagram.classdiagram;

import com.lamzi.doc.mermaid.diagram.Comment;
import com.lamzi.doc.mermaid.diagram.CssClassDefinition;
import com.lamzi.doc.mermaid.diagram.Diagram;
import com.lamzi.doc.mermaid.diagram.DiagramFrontMatter;
import com.lamzi.doc.mermaid.diagram.StyleDefinition;
import com.lamzi.doc.mermaid.diagram.classdiagram.inline.InlineAnnotation;
import com.lamzi.doc.mermaid.diagram.classdiagram.inline.InlineClassMember;
import com.lamzi.doc.mermaid.diagram.classdiagram.inline.InlineCssClassAttachment;
import com.lamzi.doc.mermaid.diagram.classdiagram.relation.ClassRelation;

/**
 * documentation https://mermaid.js.org/syntax/classDiagram.html
 */
public class ClassDiagram extends Diagram<ClassDiagramConfiguration, ClassDiagramElement> {
    public ClassDiagram(DiagramFrontMatter<ClassDiagramConfiguration> frontMatter) {
        super(frontMatter, "classDiagram");
    }

    public ClassDiagram() {
        this(null);
    }

    public ClassDiagram note(String content) {
        this.addElement(new Note(content));
        return this;
    }

    public ClassDiagram note(Type target, String content) {
        this.addElement(new Note(target, content));
        return this;
    }

    public ClassDiagram relation(ClassRelation classRelation) {
        this.addElement(classRelation);
        return this;
    }

    public ClassDiagram classMember(InlineClassMember inlineClassMember) {
        this.addElement(inlineClassMember);
        return this;
    }


    public ClassDiagram classElement(Class aClass) {
        this.addElement(aClass);
        return this;
    }

    public ClassDiagram namespace(Namespace namespace) {
        this.addElement(namespace);
        return this;
    }

    public ClassDiagram annotation(Type classType, String annotationName) {
        //TODO add an error if class is not yet defined.
        this.addElement(new InlineAnnotation(classType, annotationName));
        return this;
    }

    public ClassDiagram comment(String comment) {
        this.addElement(new Comment(comment));
        return this;
    }

    public ClassDiagram direction(DiagramDirection.Direction direction) {
        this.addElement(new DiagramDirection(direction));
        return this;
    }

    public ClassDiagram link(Link link) {
        this.addElement(link);
        return this;
    }

    public ClassDiagram callback(CallBack callBack) {
        this.addElement(callBack);
        return this;
    }

    public ClassDiagram click(ClassClick click) {
        this.addElement(click);
        return this;
    }

    public ClassDiagram style(Style style) {
        this.addElement(style);
        return this;
    }

    public ClassDiagram cssClassDefinition(String className, StyleDefinition<ClassStyleDefinitionAttribute> styleDefinition) {
        this.addElement(new CssClassDefinition<>(className, styleDefinition));
        return this;
    }

    public ClassDiagram cssClassAttachment(InlineCssClassAttachment inlineCssClassAttachment) {
        this.addElement(inlineCssClassAttachment);
        return this;
    }
}