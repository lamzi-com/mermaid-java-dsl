package com.lamzi.mermaid.diagram.classdiagram;

import com.lamzi.mermaid.diagram.classdiagram.inline.InlineClassAttribute;
import com.lamzi.mermaid.diagram.classdiagram.inline.InlineClassMethod;
import com.lamzi.mermaid.diagram.classdiagram.inline.InlineCssClassAttachment;
import com.lamzi.mermaid.diagram.classdiagram.relation.ClassRelation;
import com.lamzi.mermaid.diagram.classdiagram.relation.Direction;
import com.lamzi.mermaid.diagram.classdiagram.relation.RelationType;

public class ClassDiagramFactory {
    public static ClassRelation inheritance(Type from, Type to) {
        return new ClassRelation(from, RelationType.INHERITANCE, Direction.LEFT, to);
    }

    public static ClassRelation composition(Type from, Type to) {
        return new ClassRelation(from, RelationType.COMPOSITION, Direction.LEFT, to);
    }

    public static ClassRelation aggregation(Type from, Type to) {
        return new ClassRelation(from, RelationType.AGGREGATION, Direction.LEFT, to);
    }

    public static ClassRelation association(Type from, Type to) {
        return new ClassRelation(from, RelationType.ASSOCIATION, Direction.RIGHT, to);
    }

    public static ClassRelation solidLink(Type from, Type to) {
        return new ClassRelation(from, RelationType.SOLID_LINK, Direction.NONE, to);
    }

    public static ClassRelation dependency(Type from, Type to) {
        return new ClassRelation(from, RelationType.DEPENDENCY, Direction.RIGHT, to);
    }

    public static ClassRelation realization(Type from, Type to) {
        return new ClassRelation(from, RelationType.REALIZATION, Direction.RIGHT, to);
    }

    public static ClassRelation dashedLink(Type from, Type to) {
        return new ClassRelation(from, RelationType.DASHED_LINK, Direction.NONE, to);
    }

    public static ClassRelation lolipop(Type from, Type to) {
        return new ClassRelation(from, RelationType.LOLIPOP, Direction.LEFT, to);
    }

    public static Type type(String typeName) {
        return new Type(typeName);
    }

    public static InlineClassAttribute attribute(Type classType, String attributeName) {
        return new InlineClassAttribute(classType, attributeName);
    }

    public static ClassAttribute attribute(String attributeName) {
        return new ClassAttribute(attributeName);
    }

    public static InlineClassMethod method(Type classType, String attributeName) {
        return new InlineClassMethod(classType, attributeName);
    }

    public static ClassMethod method(String methodName) {
        return new ClassMethod(methodName);
    }

    public static MethodParameter parameter(String parameterName) {
        return new MethodParameter(parameterName);
    }

    public static Class aClass(String className) {
        return new Class(type(className));
    }

    public static Class aClass(Type type) {
        return new Class(type);
    }

    public static Namespace namespace(String name) {
        return new Namespace(name);
    }

    public static Link link(Type type, String url) {
        return new Link(type, url);
    }

    public static CallBack callBack(Type type, String function) {
        return new CallBack(type, function);
    }

    public static Click click(Click.Kind kind, Type type, String reference) {
        return new Click(kind, type, reference);
    }

    public static Style style(Type type, StyleDefinition styleDefinition) {
        return new Style(type, styleDefinition);
    }

    public static InlineCssClassAttachment cssClassAttachment(String className, Type... types) {
        return new InlineCssClassAttachment(className, types);
    }
}
