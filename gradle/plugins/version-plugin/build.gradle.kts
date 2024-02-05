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
            id = "version-plugin"
            implementationClass = "org.ktorium.kotlin.gradle.plugins.version.VersionPlugin"
        }
    }
}

kotlin {
    explicitApi()
}
