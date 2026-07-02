package com.lamzi.doc.mermaid.diagram;


import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.domain.JavaField;
import com.tngtech.archunit.core.domain.JavaModifier;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchCondition;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.ConditionEvents;
import com.tngtech.archunit.lang.SimpleConditionEvent;
import org.junit.jupiter.api.Test;


import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.fields;

public class ArchUnitTests {

    private JavaClasses importedClasses = new ClassFileImporter().importPackages("com.lamzi.doc.mermaid");

    private static final ArchCondition<JavaField> BE_PRIVATE_OR_PROTECTED =
            new ArchCondition<>("be private or protected") {
                @Override
                public void check(JavaField field, ConditionEvents events) {
                    boolean ok = field.getModifiers().contains(JavaModifier.PRIVATE)
                            || field.getModifiers().contains(JavaModifier.PROTECTED);

                    if(!ok) {

                        events.add(SimpleConditionEvent.violated(
                                field,
                                "Field " + field.getFullName() + " must be private or protected"
                        ));
                    }
                }
            };
    @Test
    public void privateFields(){

        ArchRule myRule = fields()
                .that().doNotHaveModifier(JavaModifier.STATIC)
                .and().doNotHaveModifier(JavaModifier.FINAL)
                .and().areDeclaredInClassesThat().areNotEnums()
                .should(BE_PRIVATE_OR_PROTECTED);

        myRule.check(importedClasses);
    }
}
