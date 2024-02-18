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
        register("WrapperUpgradePlugin") {
            id = "build-wrapper-upgrade"
            implementationClass = "org.ktorium.kotlin.gradle.plugins.wrapper.WrapperUpgradePlugin"
        }
    }
}
