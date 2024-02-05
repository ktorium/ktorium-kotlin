plugins {
    `kotlin-dsl`
    `java-gradle-plugin`
}

run {
    group = "org.ktorium.kotlin.gradle.plugins"
}

gradlePlugin {
    plugins {
        register("PublicationPlugin") {
            id = "publication-plugin"
            implementationClass = "org.ktorium.kotlin.gradle.plugins.publication.PublicationPlugin"
        }
    }
}

kotlin {
    explicitApi()
}
