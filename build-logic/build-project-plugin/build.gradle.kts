plugins {
    `kotlin-dsl`
    `java-gradle-plugin`
}

configurations.all {
    resolutionStrategy {
        failOnNonReproducibleResolution()
    }
}

sourceSets {
    main {
        kotlin {
            srcDirs("src/main/kotlinX")
        }
    }
}

kotlin {
    explicitApi()

    jvmToolchain {
        val mainJvmCompiler = providers.gradleProperty("kotlin.javaToolchain.mainJvmCompiler").map(JavaLanguageVersion::of)

        languageVersion = mainJvmCompiler
    }
}

dependencies {
    implementation(kotlinCatalog.gradle.plugin)
}

gradlePlugin {
    plugins {
        register("ProjectDefaultPlugin") {
            id = "build-project-default"
            implementationClass = "build.gradle.plugins.build.ProjectDefaultPlugin"
        }
    }
    plugins {
        register("ProjectWrapperPlugin") {
            id = "build-project-wrapper"
            implementationClass = "build.gradle.plugins.build.ProjectWrapperPlugin"
        }
    }
    plugins {
        register("ProjectRootDefaultPlugin") {
            id = "build-project-root-default"
            implementationClass = "build.gradle.plugins.build.ProjectRootDefaultPlugin"
        }
    }
    plugins {
        register("ProjectPublicationPlugin") {
            id = "build-project-publication"
            implementationClass = "build.gradle.plugins.build.ProjectPublicationPlugin"
        }
    }
}

tasks.withType<ValidatePlugins>().configureEach {
    failOnWarning.set(true)
    enableStricterValidation.set(true)
}
