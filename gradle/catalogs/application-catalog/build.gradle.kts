plugins {
    `kotlin-dsl`
}

kotlin {
    explicitApi()
}

gradlePlugin {
    plugins {
        create("application-catalog") {
            id = "application-catalog"
            implementationClass = "org.ktorium.kotlin.gradle.catalogs.application.ApplicationCatalogPlugin"
        }
    }
}
