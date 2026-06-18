package com.lamzi.doc.mermaid.diagram.classdiagram;

import com.lamzi.doc.mermaid.diagram.CssClassDefinition;
import com.lamzi.doc.mermaid.diagram.DiagramFrontMatter;
import com.lamzi.doc.mermaid.diagram.MermaidWriter;
import com.lamzi.doc.mermaid.diagram.classdiagram.inline.InlineCssClassAttachment;
import com.lamzi.doc.mermaid.diagram.classdiagram.relation.Direction;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static com.lamzi.doc.mermaid.diagram.classdiagram.ClassDiagramFactory.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class ClassDiagramTest {

    @Test
    public void animalExample() {
        DiagramFrontMatter<ClassDiagramConfiguration> frontMatter = new DiagramFrontMatter<>();
        frontMatter.setTitle("Animal example");
        ClassDiagram classDiagram = new ClassDiagram(frontMatter);
        classDiagram
                .note("From Duck till Zebra")
                .relation(inheritance(type("Animal"), type("Duck")))
                .note(type("Duck"), "can fly<br>can swim<br>can dive<br>can help in debugging")
                .relation(inheritance(type("Animal"), type("Fish")))
                .relation(inheritance(type("Animal"), type("Zebra")))
                .classMember(attribute(type("Animal"), "age").visibility(Visibility.PUBLIC).type(type("int")))
                .classMember(attribute(type("Animal"), "gender").visibility(Visibility.PUBLIC).type(type("String")))
                .classMember(method(type("Animal"), "isMammal").visibility(Visibility.PUBLIC))
                .classMember(method(type("Animal"), "mate").visibility(Visibility.PUBLIC))
                .classElement(aClass("Duck")
                        .member(attribute("beakColor").type(type("String")).visibility(Visibility.PUBLIC))
                        .member(method("swim").visibility(Visibility.PUBLIC))
                        .member(method("quack").visibility(Visibility.PUBLIC))
                )
                .classElement(aClass("Fish")
                        .member(attribute("sizeInFeet").type(type("int")).visibility(Visibility.PRIVATE))
                        .member(method("canEat").visibility(Visibility.PRIVATE))
                )
                .classElement(aClass("Zebra")
                        .member(attribute("is_wild").type(type("bool")).visibility(Visibility.PUBLIC))
                        .member(method("run").visibility(Visibility.PUBLIC))
                );

        assertThat(classDiagram.generate()).isEqualTo(read("/animalExample.mmd"));
    }


    @Test
    public void bankExample() {
        DiagramFrontMatter<ClassDiagramConfiguration> frontMatter = new DiagramFrontMatter<>();
        frontMatter.setTitle("Bank example");
        ClassDiagram classDiagram = new ClassDiagram(frontMatter);
        classDiagram
                .classElement(aClass("BankAccount"))
                .classMember(attribute(type("BankAccount"), "owner").type(type("String")).visibility(Visibility.PUBLIC))
                .classMember(attribute(type("BankAccount"), "balance").type(type("Bigdecimal")).visibility(Visibility.PUBLIC))
                .classMember(method(type("BankAccount"), "deposit").visibility(Visibility.PUBLIC)
                        .parameter(parameter("amount")))
                .classMember(method(type("BankAccount"), "withdrawal").visibility(Visibility.PUBLIC)
                        .parameter(parameter("amount")));
        assertThat(classDiagram.generate()).isEqualTo(read("/banqueExample.mmd"));
    }

    @Test
    public void defineAClass() {
        ClassDiagram classDiagram = new ClassDiagram();
        classDiagram
                .classElement(aClass("Animal"))
                .relation(inheritance(type("Vehicle"), type("Car")));

        assertThat(classDiagram.generate()).isEqualTo(read("/defineAClass.mmd"));
    }

    @Test
    public void classLabel() {
        ClassDiagram classDiagram = new ClassDiagram();
        classDiagram
                .classElement(aClass("Animal").label("Animal with a label"))
                .classElement(aClass("Car").label("Car with *! symbols"))
                .relation(association(type("Animal"), type("Car")));

        assertThat(classDiagram.generate()).isEqualTo(read("/classLabel.mmd"));
    }

    @Test
    public void escapeLabel() {
        ClassDiagram classDiagram = new ClassDiagram();
        classDiagram
                .classElement(aClass("Animal Class!"))
                .classElement(aClass("Car Class"))
                .relation(association(type("Animal Class!"), type("Car Class")));

        assertThat(classDiagram.generate()).isEqualTo(read("/escapeLabel.mmd"));
    }

    @Test
    public void associateMemberUsingColon() {

        ClassDiagram classDiagram = new ClassDiagram();
        classDiagram
                .classElement(aClass("BankAccount"))
                .classMember(attribute(type("BankAccount"), "owner").type(type("String")).visibility(Visibility.PUBLIC))
                .classMember(attribute(type("BankAccount"), "balance").type(type("BigDecimal")).visibility(Visibility.PUBLIC))
                .classMember(method(type("BankAccount"), "deposit").visibility(Visibility.PUBLIC)
                        .parameter(parameter("amount")))
                .classMember(method(type("BankAccount"), "withdrawal").visibility(Visibility.PUBLIC)
                        .parameter(parameter("amount")));

        assertThat(classDiagram.generate()).isEqualTo(read("/associateMemberUsingColon.mmd"));
    }

    @Test
    public void associateMemberUsingBrackets() {
        ClassDiagram classDiagram = new ClassDiagram();
        classDiagram
                .classElement(aClass("BankAccount")
                        .member(attribute("owner").type(type("String")).visibility(Visibility.PUBLIC))
                        .member(attribute("balance").type(type("BigDecimal")).visibility(Visibility.PUBLIC))
                        .member(method("deposit").visibility(Visibility.PUBLIC).parameter(parameter("amount")))
                        .member(method("withdrawal").visibility(Visibility.PUBLIC).parameter(parameter("amount")))
                );

        assertThat(classDiagram.generate()).isEqualTo(read("/associateMemberUsingBrackets.mmd"));
    }

    @Test
    public void returnType() {
        ClassDiagram classDiagram = new ClassDiagram();
        classDiagram
                .classElement(aClass(type("BankAccount"))
                        .member(attribute("owner").type(type("String")).visibility(Visibility.PUBLIC))
                        .member(attribute("balance").type(type("BigDecimal")).visibility(Visibility.PUBLIC))
                        .member(method("deposit").visibility(Visibility.PUBLIC).parameter(parameter("amount")).returnType(type("bool")))
                        .member(method("withdrawal").visibility(Visibility.PUBLIC).parameter(parameter("amount")).returnType(type("int")))
                );

        assertThat(classDiagram.generate()).isEqualTo(read("/returnType.mmd"));
    }

    @Test
    public void genericTypes() {
        ClassDiagram classDiagram = new ClassDiagram();
        Type square = type("Square").genericType(type("Shape"));
        Type intList = type("List").genericType(type("int"));
        Type stringList = type("List").genericType(type("string"));
        classDiagram
                .classElement(aClass(square)
                        .member(attribute("id").type(type("int")))
                        .member(attribute("position").type(intList))
                        .member(method("setPoints").parameter(parameter("points").type(intList)))
                        .member(method("getPoints").returnType(intList))
                )
                .classMember(attribute(square, "messages").type(stringList).visibility(Visibility.PRIVATE))
                .classMember(method(square, "setMessages")
                        .parameter(parameter("messages").type(stringList))
                        .visibility(Visibility.PUBLIC)
                )
                .classMember(method(square, "getMessages")
                        .returnType(stringList)
                        .visibility(Visibility.PUBLIC)
                )
                .classMember(method(square, "getDistanceMatrix")
                        .visibility(Visibility.PUBLIC)
                        .returnType(type("List").genericType(type("List").genericType(type("int"))))
                );

        assertThat(classDiagram.generate()).isEqualTo(read("/genericTypes.mmd"));
    }


    @Test
    public void visibility() {
        ClassDiagram classDiagram = new ClassDiagram();
        Type a = type("A");

        classDiagram
                .classMember(method(a, "publicInlineMethod").visibility(Visibility.PUBLIC))
                .classMember(attribute(a, "publicInlineAttribute").visibility(Visibility.PUBLIC))
                .classMember(method(a, "privateInlineMethod").visibility(Visibility.PRIVATE))
                .classMember(attribute(a, "privateInlineAttribute").visibility(Visibility.PRIVATE))
                .classMember(method(a, "protectedInlineMethod").visibility(Visibility.PROTECTED))
                .classMember(attribute(a, "protectedInlineAttribute").visibility(Visibility.PROTECTED))
                .classMember(method(a, "packageInlineMethod").visibility(Visibility.PACKAGE))
                .classMember(attribute(a, "packageInlineAttribute").visibility(Visibility.PACKAGE))
                .classElement(aClass("B")
                        .member(method("publicClassMethod").visibility(Visibility.PUBLIC))
                        .member(method("privateClassMethod").visibility(Visibility.PRIVATE))
                        .member(method("protectedClassMethod").visibility(Visibility.PROTECTED))
                        .member(method("packageClassMethod").visibility(Visibility.PACKAGE))
                        .member(attribute("publicClassAttribute").visibility(Visibility.PUBLIC))
                        .member(attribute("privateClassAttribute").visibility(Visibility.PRIVATE))
                        .member(attribute("protectedClassAttribute").visibility(Visibility.PROTECTED))
                        .member(attribute("packageClassAttribute").visibility(Visibility.PACKAGE))
                );

        assertThat(classDiagram.generate()).isEqualTo(read("/visibility.mmd"));

    }


    @Test
    public void abstractOrStaticMethod() {
        ClassDiagram classDiagram = new ClassDiagram();
        Type a = type("A");

        classDiagram
                .classMember(method(a, "someAbstractMethod").markAbstract())
                .classMember(method(a, "someAbstractMethodWithReturn").returnType(type("int")).markAbstract())
                .classMember(method(a, "someStaticMethod").markStatic())
                .classMember(method(a, "someStaticMethodWithReturn").returnType(type("int")).markStatic())
                .classMember(method(a, "someStaticAbstractMethodWithReturn").returnType(type("int")).markStatic().markAbstract())
                .classElement(aClass("B")
                        .member(method("someAbstractMethod").markAbstract())
                        .member(method("someAbstractMethodWithReturn").returnType(type("int")).markAbstract())
                        .member(method("someStaticMethod").markStatic())
                        .member(method("someStaticMethodWithReturn").returnType(type("int")).markStatic())
                        .member(method("someStaticAbstractMethodWithReturn").returnType(type("int")).markStatic().markAbstract())
                );

        assertThat(classDiagram.generate()).isEqualTo(read("/abstractOrStaticMethod.mmd"));

    }


    @Test
    public void relationship() {
        ClassDiagram classDiagram = new ClassDiagram();

        classDiagram
                .relation(inheritance(type("classA"), type("classB")))
                .relation(composition(type("classC"), type("classD")))
                .relation(aggregation(type("classE"), type("classF")))
                .relation(association(type("classG"), type("classH")).direction(Direction.LEFT))
                .relation(solidLink(type("classI"), type("classJ")))
                .relation(dependency(type("classK"), type("classL")).direction(Direction.LEFT))
                .relation(realization(type("classM"), type("classN")).direction(Direction.LEFT))
                .relation(dashedLink(type("classO"), type("classP")))
        ;

        assertThat(classDiagram.generate()).isEqualTo(read("/relationship.mmd"));

    }

    @Test
    public void relationshipLabel() {
        ClassDiagram classDiagram = new ClassDiagram();

        classDiagram
                .relation(inheritance(type("classA"), type("classB")).label("Inheritance").direction(Direction.RIGHT))
                .relation(composition(type("classC"), type("classD")).label("Composition").direction(Direction.RIGHT))
                .relation(aggregation(type("classE"), type("classF")).label("Aggregation").direction(Direction.RIGHT))
                .relation(association(type("classG"), type("classH")).label("Association"))
                .relation(solidLink(type("classI"), type("classJ")).label("Link(Solid)"))
                .relation(dependency(type("classK"), type("classL")).label("Dependency"))
                .relation(realization(type("classM"), type("classN")).label("Realization"))
                .relation(dashedLink(type("classO"), type("classP")).label("Link(Dashed)"))
        ;

        assertThat(classDiagram.generate()).isEqualTo(read("/relationshipLabel.mmd"));
    }

    @Test
    public void relationshipLabel2() {
        ClassDiagram classDiagram = new ClassDiagram();

        classDiagram
                .relation(inheritance(type("classA"), type("classB")).label("implements"))
                .relation(composition(type("classC"), type("classD")).label("composition"))
                .relation(aggregation(type("classE"), type("classF")).label("aggregation"))
        ;

        assertThat(classDiagram.generate()).isEqualTo(read("/relationshipLabel2.mmd"));
    }

    @Test
    public void twoWayRelation() {
        ClassDiagram classDiagram = new ClassDiagram();

        classDiagram
                .relation(inheritance(type("Animal"), type("Zebra")).direction(Direction.BOTH));

        assertThat(classDiagram.generate()).isEqualTo(read("/twoWayRelation.mmd"));

    }

    @Test
    public void lolipopRelation() {
        ClassDiagram classDiagram = new ClassDiagram();

        classDiagram
                .relation(lolipop(type("bar"), type("foo")));

        assertThat(classDiagram.generate()).isEqualTo(read("/lolipopRelation.mmd"));

    }

    @Test
    public void lolipopRelation2() {
        ClassDiagram classDiagram = new ClassDiagram();

        classDiagram
                .classElement(aClass(type("Class01"))
                        .member(attribute("amount").type(type("int")))
                        .member(method("draw"))
                )
                .relation(lolipop(type("Class01"), type("bar")).direction(Direction.RIGHT))
                .relation(lolipop(type("Class02"), type("bar")).direction(Direction.RIGHT))
                .relation(lolipop(type("foo"), type("Class01")).direction(Direction.LEFT));

        assertThat(classDiagram.generate()).isEqualTo(read("/lolipopRelation2.mmd"));

    }

    @Test
    public void namespaceElement() {
        ClassDiagram classDiagram = new ClassDiagram();

        classDiagram
                .namespace(namespace("BaseShapes")
                        .addclass(aClass(type("Triangle")))
                        .addclass(aClass(type("Rectangle"))
                                .member(attribute("width").type(type("double")))
                                .member(attribute("height").type(type("double")))
                        )
                );

        assertThat(classDiagram.generate()).isEqualTo(read("/namespace.mmd"));

    }

    @Test
    public void cardinality() {
        ClassDiagram classDiagram = new ClassDiagram();

        classDiagram
                .relation(association(type("Customer"), type("Ticket")).leftCardinality("1").rightCardinality("*"))
                .relation(association(type("Student"), type("Course")).leftCardinality("1").rightCardinality("1..*"))
                .relation(association(type("Galaxy"), type("Star")).label("Contains").rightCardinality("many"));

        assertThat(classDiagram.generate()).isEqualTo(read("/cardinality.mmd"));

    }

    @Test
    public void inlineAnnotation() {
        ClassDiagram classDiagram = new ClassDiagram();

        classDiagram
                .classElement(aClass("Shape"))
                .annotation(type("Shape"), "interface")
                .classMember(attribute(type("Shape"), "noOfVertices"))
                .classMember(method(type("Shape"), "draw"));

        assertThat(classDiagram.generate()).isEqualTo(read("/inlineAnnotation.mmd"));
    }

    @Test
    public void classAnnotation() {
        ClassDiagram classDiagram = new ClassDiagram();

        classDiagram
                .classElement(aClass("Shape")
                        .annotation("interface")
                        .member(attribute("noOfVertices"))
                        .member(method("draw"))
                )
                .classElement(aClass("Color")
                        .annotation("enumeration")
                        .member(attribute("RED"))
                        .member(attribute("BLUE"))
                        .member(attribute("GREEN"))
                        .member(attribute("WHITE"))
                        .member(attribute("BLACK"))
                );

        assertThat(classDiagram.generate()).isEqualTo(read("/classAnnotation.mmd"));
    }

    @Test
    public void comments() {
        ClassDiagram classDiagram = new ClassDiagram();

        classDiagram
                .comment("This whole line is a comment classDiagram class Shape <<interface>>")
                .classElement(aClass("Shape")
                        .annotation("interface")
                        .member(attribute("noOfVertices"))
                        .member(method("draw"))
                );

        assertThat(classDiagram.generate()).isEqualTo(read("/comments.mmd"));
    }

    @Test
    public void comments2() {
        ClassDiagram classDiagram = new ClassDiagram();

        classDiagram
                .comment("Comment at the diagram level")
                .namespace(namespace("n")
                        .addclass(aClass("Shape")
                                .annotation("interface")
                                .member(attribute("noOfVertices"))
                                .comment("Comment at the class level")
                                .member(method("draw"))
                        )
                        .comment("Comment at the namespace level")
                );

        assertThat(classDiagram.generate()).isEqualTo(read("/comments2.mmd"));
    }

    @Test
    public void direction() {
        ClassDiagram classDiagram = new ClassDiagram();

        classDiagram
                .direction(DiagramDirection.Direction.RL)
                .classElement(aClass("Student")
                        .member(attribute("idCard").type(type("IdCard")).visibility(Visibility.PRIVATE).colon())
                )
                .classElement(aClass("IdCard")
                        .member(attribute("id").type(type("int")).visibility(Visibility.PRIVATE).colon())
                        .member(attribute("name").type(type("string")).visibility(Visibility.PRIVATE).colon())
                )
                .classElement(aClass("Bike")
                        .member(attribute("id").type(type("int")).visibility(Visibility.PRIVATE).colon())
                        .member(attribute("name").type(type("string")).visibility(Visibility.PRIVATE).colon())
                )
                .relation(aggregation(type("Student"), type("IdCard")).rightCardinality("1").leftCardinality("1").direction(Direction.RIGHT).label("carries"))
                .relation(aggregation(type("Student"), type("Bike")).rightCardinality("1").leftCardinality("1").direction(Direction.RIGHT).label("rides"))

        ;

        assertThat(classDiagram.generate()).isEqualTo(read("/direction.mmd"));
    }

    @Test
    public void notes() {
        ClassDiagram classDiagram = new ClassDiagram();
        classDiagram
                .note("This is a general note")
                .note(type("MyClass"), "This is a note for a class")
                .classElement(aClass("MyClass"));

        assertThat(classDiagram.generate()).isEqualTo(read("/notes.mmd"));

    }

    @Test
    public void linkExample() {
        ClassDiagram classDiagram = new ClassDiagram();
        classDiagram
                .classElement(aClass("Shape"))
                .link(link(type("Shape"), "https://www.github.com").tooltip("This is a tooltip for a link"))
                .classElement(aClass("Shape2"))
                .click(click(Click.Kind.HREF, type("Shape2"), "https://www.github.com").tooltip("This is a tooltip for a link"))
        ;

        assertThat(classDiagram.generate()).isEqualTo(read("/link.mmd"));
    }

    @Test
    public void callbackExample() {
        ClassDiagram classDiagram = new ClassDiagram();
        classDiagram
                .classElement(aClass("Shape"))
                .callback(callBack(type("Shape"), "callbackFunction").tooltip("This is a tooltip for a callback"))
                .classElement(aClass("Shape2"))
                .click(click(Click.Kind.CALLBACK, type("Shape2"), "callbackFunction").tooltip("This is a tooltip for a callback"))
        ;

        assertThat(classDiagram.generate()).isEqualTo(read("/callback.mmd"));
    }


    @Test
    public void actionExample() {
        ClassDiagram classDiagram = new ClassDiagram();
        classDiagram
                .classElement(aClass("Class01"))
                .classElement(aClass("Class02"))
                .callback(callBack(type("Class01"), "callbackFunction").tooltip("Callback tooltip"))
                .link(link(type("Class02"), "https://www.github.com").tooltip("This is a link"))
                .classElement(aClass("Class03"))
                .classElement(aClass("Class04"))
                .click(click(Click.Kind.CALLBACK, type("Class03"), "callbackFunction").tooltip("Callback tooltip"))
                .click(click(Click.Kind.HREF, type("Class04"), "https://www.github.com").tooltip("This is a link"))
        ;
        System.out.println(classDiagram.generate());
        assertThat(classDiagram.generate()).isEqualTo(read("/actionExample.mmd"));
    }

    @Test
    public void actionExample2() {
        ClassDiagram classDiagram = new ClassDiagram();
        Type animal = type("Animal");
        Type duck = type("Duck");
        Type fish = type("Fish");
        Type zebra = type("Zebra");

        classDiagram
                .relation(inheritance(animal, duck))
                .relation(inheritance(animal, fish))
                .relation(inheritance(animal, zebra))
                .classMember(attribute(animal, "age").visibility(Visibility.PUBLIC).type(type("int")))
                .classMember(attribute(animal, "gender").visibility(Visibility.PUBLIC).type(type("String")))
                .classMember(method(animal, "isMammal").visibility(Visibility.PUBLIC))
                .classMember(method(animal, "mate").visibility(Visibility.PUBLIC))
                .classElement(aClass("Duck")
                        .member(attribute("beakColor").visibility(Visibility.PUBLIC).type(type("String")))
                        .member(method("swim").visibility(Visibility.PUBLIC))
                        .member(method("quack").visibility(Visibility.PUBLIC))
                )
                .classElement(aClass("Fish")
                        .member(attribute("sizeInFeet").visibility(Visibility.PRIVATE).type(type("int")))
                        .member(method("canEat").visibility(Visibility.PRIVATE))
                )
                .classElement(aClass("Zebra")
                        .member(attribute("is_wild").type(type("bool")).visibility(Visibility.PUBLIC))
                        .member(method("run").visibility(Visibility.PUBLIC))
                )
                .callback(callBack(duck, "callback").tooltip("Tooltip"))
                .link(link(zebra, "https://www.github.com").tooltip("This is a link"))

        ;
        System.out.println(classDiagram.generate());
        assertThat(classDiagram.generate()).isEqualTo(read("/actionExample2.mmd"));
    }


    @Test
    public void stylingANode() {
        ClassDiagram classDiagram = new ClassDiagram();
        classDiagram
                .classElement(aClass("Animal"))
                .classElement(aClass("Mineral"))
                .style(style(type("Animal"), new StyleDefinition()
                        .addAttribute(StyleDefinition.FILL, "#f9f")
                        .addAttribute(StyleDefinition.STROKE, "#333")
                        .addAttribute(StyleDefinition.STROKE_WIDTH, "4px")
                ))
                .style(style(type("Mineral"), new StyleDefinition()
                        .addAttribute(StyleDefinition.FILL, "#bbf")
                        .addAttribute(StyleDefinition.STROKE, "#f66")
                        .addAttribute(StyleDefinition.STROKE_WIDTH, "2px")
                        .addAttribute(StyleDefinition.COLOR, "#fff")
                        .addAttribute(StyleDefinition.STROKE_DASH_ARRAY, "5 5")
                ))
        ;
        System.out.println(classDiagram.generate());
        assertThat(classDiagram.generate()).isEqualTo(read("/stylingANode.mmd"));
    }

    @Test
    public void simpleCssClassDefinition() {
        MermaidWriter writer = new MermaidWriter();
        new CssClassDefinition("className", new StyleDefinition()
                .addAttribute(StyleDefinition.FILL, "#f9f")
                .addAttribute(StyleDefinition.STROKE, "#333")
                .addAttribute(StyleDefinition.STROKE_WIDTH, "4px")
        ).writeTo(writer, 0);
        assertThat(writer.toString().trim()).isEqualTo("classDef className fill:#f9f,stroke:#333,stroke-width:4px;");
    }

    @Test
    public void multipleCssClassDefinition() {
        MermaidWriter writer = new MermaidWriter();
        new CssClassDefinition(List.of("firstClassName", "secondClassName"), new StyleDefinition()
                .addAttribute(StyleDefinition.FONT_SIZE, "12pt")
        ).writeTo(writer, 0);
        assertThat(writer.toString().trim()).isEqualTo("classDef firstClassName,secondClassName font-size:12pt;");
    }

    @Test
    public void addingCssClass() {
        MermaidWriter writer = new MermaidWriter();
        new InlineCssClassAttachment("className", type("nodeId1")).writeTo(writer, 0);
        assertThat(writer.toString().trim()).isEqualTo("cssClass \"nodeId1\" className;");
    }

    @Test
    public void addingCssClassToMultipleNodes() {
        MermaidWriter writer = new MermaidWriter();
        new InlineCssClassAttachment("className", type("nodeId1"), type("nodeId2")).writeTo(writer, 0);
        assertThat(writer.toString().trim()).isEqualTo("cssClass \"nodeId1,nodeId2\" className;");
    }

    @Test
    public void attachCssClassToNode() {
        ClassDiagram classDiagram = new ClassDiagram();
        classDiagram
                .classElement(aClass("Animal").cssClass("someclass"))
                .cssClassDefinition("someclass", new StyleDefinition().addAttribute(StyleDefinition.FILL, "#f96"))
        ;
        System.out.println(classDiagram.generate());
        assertThat(classDiagram.generate()).isEqualTo(read("/attachCssClassToNode.mmd"));
    }

    @Test
    public void attachCssClassToNode2() {
        ClassDiagram classDiagram = new ClassDiagram();
        classDiagram
                .classElement(aClass("Animal")
                        .cssClass("someclass")
                        .member(attribute("sizeInFeet").type(type("int")).visibility(Visibility.PRIVATE))
                        .member(method("canEat").visibility(Visibility.PRIVATE))

                )
                .cssClassDefinition("someclass", new StyleDefinition().addAttribute(StyleDefinition.FILL, "#f96"))
        ;
        System.out.println(classDiagram.generate());
        assertThat(classDiagram.generate()).isEqualTo(read("/attachCssClassToNode2.mmd"));
    }

    @Test
    public void defaultCssClass() {
        ClassDiagram classDiagram = new ClassDiagram();
        classDiagram
                .classElement(aClass("Animal").cssClass("pink"))
                .classElement(aClass("Mineral"))
                .cssClassDefinition("default", new StyleDefinition().addAttribute(StyleDefinition.FILL, "#f96").addAttribute(StyleDefinition.COLOR, "red"))
                .cssClassDefinition("pink", new StyleDefinition().addAttribute(StyleDefinition.COLOR, "#f9f"))
        ;
        System.out.println(classDiagram.generate());
        assertThat(classDiagram.generate()).isEqualTo(read("/defaultCssClass.mmd"));
    }

    @Test
    public void externalCssClass() {
        ClassDiagram classDiagram = new ClassDiagram();
        classDiagram
                .classElement(aClass("Animal").cssClass("styleClass"));
        assertThat(classDiagram.generate()).isEqualTo(read("/externalCssClass.mmd"));
    }


    @Test
    public void hideEmptyMembersBox() {
        ClassDiagramConfiguration config = new ClassDiagramConfiguration();
        config.hideEmptyMembersBox(true);
        DiagramFrontMatter<ClassDiagramConfiguration> frontMatter = new DiagramFrontMatter<>(config);
        ClassDiagram classDiagram = new ClassDiagram(frontMatter);
        classDiagram
                .classElement(aClass("Duck"));
        assertThat(classDiagram.generate()).isEqualTo(read("/hideEmptyMembersBox.mmd"));
    }

    private String read(String fileName) {
        try {
            File file = new File(ClassDiagramTest.class.getResource(fileName).getFile());
            return FileUtils.readFileToString(file, StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}