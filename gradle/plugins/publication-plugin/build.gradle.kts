plugins {
    `kotlin-dsl`
    `java-gradle-plugin`
}

kotlin {
    explicitApi()

    jvmToolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

gradlePlugin {
    plugins {
        register("PublicationPlugin") {
            id = "publication-plugin"
            implementationClass = "org.ktorium.kotlin.gradle.plugins.publication.PublicationPlugin"
        }
    }
}
