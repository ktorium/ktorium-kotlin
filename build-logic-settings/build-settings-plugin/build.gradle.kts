import org.jetbrains.kotlin.gradle.plugin.getKotlinPluginVersion

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
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:${getKotlinPluginVersion()}")
    implementation(libraryCatalog.build.gradle.foojay)
}

gradlePlugin {
    plugins {
        register("SettingsDefaultPlugin") {
            id = "build-settings-default"
            implementationClass = "build.gradle.plugins.settings.SettingsDefaultPlugin"
        }
    }
    plugins {
        register("SettingsRepositoryPlugin") {
            id = "build-settings-repository"
            implementationClass = "build.gradle.plugins.settings.SettingsRepositoryPlugin"
        }
    }
    plugins {
        register("SettingsKotlinVersionCatalogPlugin") {
            id = "build-settings-kotlin-version-catalog"
            implementationClass = "build.gradle.plugins.settings.SettingsKotlinVersionCatalogPlugin"
        }
    }
    plugins {
        register("SettingsGradlePlugin") {
            id = "build-settings-gradle"
            implementationClass = "build.gradle.plugins.settings.SettingsGradlePlugin"
        }
    }
}

tasks.withType<ValidatePlugins>().configureEach {
    failOnWarning.set(true)
    enableStricterValidation.set(true)
}
