plugins {
    `kotlin-dsl`
    `java-gradle-plugin`
}

run {
    group = "org.ktorium.kotlin.gradle.plugins"
}

gradlePlugin {
    plugins {
        register("VersionPlugin") {
            id = "org.ktorium.kotlin.gradle.plugins.version"
            implementationClass = "org.ktorium.kotlin.gradle.plugins.version.VersionPlugin"
        }
    }
}
