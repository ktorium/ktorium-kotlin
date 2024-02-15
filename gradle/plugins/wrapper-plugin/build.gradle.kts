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
        register("WrapperPlugin") {
            id = "wrapper-plugin"
            implementationClass = "org.ktorium.kotlin.gradle.plugins.wrapper.WrapperPlugin"
        }
    }
}
