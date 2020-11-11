package com.gypsyapp.jhipster;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {

        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("com.gypsyapp.jhipster");

        noClasses()
            .that()
                .resideInAnyPackage("com.gypsyapp.jhipster.service..")
            .or()
                .resideInAnyPackage("com.gypsyapp.jhipster.repository..")
            .should().dependOnClassesThat()
                .resideInAnyPackage("..com.gypsyapp.jhipster.web..")
        .because("Services and repositories should not depend on web layer")
        .check(importedClasses);
    }
}
