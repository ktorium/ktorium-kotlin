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
        register("VersionPlugin") {
            id = "version-plugin"
            implementationClass = "org.ktorium.kotlin.gradle.plugins.version.VersionPlugin"
        }
    }
}
