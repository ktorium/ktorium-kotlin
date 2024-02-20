plugins {
    `kotlin-dsl`
    `java-gradle-plugin`
}

kotlin {
    explicitApi()

    jvmToolchain {
        val mainJvmCompiler = providers.gradleProperty("kotlin.javaToolchain.mainJvmCompiler").map(JavaLanguageVersion::of)

        languageVersion = mainJvmCompiler
    }
}

gradlePlugin {
    plugins {
        register("BuildWrapperPlugin") {
            id = "build-wrapper-plugin"
            implementationClass = "org.ktorium.kotlin.gradle.plugins.wrapper.BuildWrapperPlugin"
        }
    }
}
