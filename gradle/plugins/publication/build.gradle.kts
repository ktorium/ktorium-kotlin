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
            id = "org.ktorium.kotlin.gradle.plugins.publication"
            implementationClass = "org.ktorium.kotlin.gradle.plugins.publication.PublicationPlugin"
        }
    }
}
