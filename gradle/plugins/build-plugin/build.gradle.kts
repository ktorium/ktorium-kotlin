import org.jetbrains.kotlin.gradle.plugin.getKotlinPluginVersion

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

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:${getKotlinPluginVersion()}")
}

gradlePlugin {
    plugins {
        register("BuildPlugin") {
            id = "build-plugin"
            implementationClass = "org.ktorium.kotlin.gradle.plugins.build.BuildPlugin"
        }
    }
}
