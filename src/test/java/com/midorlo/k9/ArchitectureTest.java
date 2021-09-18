package com.midorlo.k9;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.testng.annotations.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

class ArchitectureTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {
        JavaClasses importedClasses = new ClassFileImporter()
                .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
                .importPackages("com.midorlo.k9");

        noClasses()
                .that()
                .resideInAnyPackage("com.midorlo.k9.service..")
                .or()
                .resideInAnyPackage("com.midorlo.k9.repository..")
                .should()
                .dependOnClassesThat()
                .resideInAnyPackage("..com.midorlo.k9.web..")
                .because("Services and repositories should not depend on web layer")
                .check(importedClasses);

    }
}
