
import io.gitlab.arturbosch.detekt.Detekt
import org.jetbrains.dokka.DokkaConfiguration.Visibility
import org.jetbrains.dokka.gradle.DokkaTaskPartial
import org.jetbrains.kotlin.gradle.targets.js.nodejs.NodeJsRootPlugin
import org.jetbrains.kotlin.gradle.targets.js.nodejs.NodeJsRootPlugin.Companion.kotlinNodeJsExtension
import org.jetbrains.kotlin.gradle.targets.js.npm.tasks.KotlinNpmInstallTask
import org.jetbrains.kotlin.gradle.targets.js.yarn.YarnLockMismatchReport
import org.jetbrains.kotlin.gradle.targets.js.yarn.YarnPlugin
import org.jetbrains.kotlin.gradle.targets.js.yarn.yarn

plugins {
    id(buildCatalog.plugins.kotlin.multiplatform.get().pluginId) apply false
    alias(buildCatalog.plugins.kotlin.serialization) apply false
    alias(buildCatalog.plugins.kotlinx.kover) apply false
    alias(buildCatalog.plugins.kotlinx.bcv)
    alias(buildCatalog.plugins.kotlin.dokka)
    alias(buildCatalog.plugins.detekt)
}

allprojects {
    group = "org.ktorium.kotlin"

    configurations.all {
        resolutionStrategy {
            failOnNonReproducibleResolution()
        }
    }
}

apiValidation {
    publicMarkers.add("org.ktorium.kotlin.ExperimentalKtoriumAPI")
    nonPublicMarkers.add("org.ktorium.kotlin.InternalKtorium")
}

subprojects {
    tasks.withType<DokkaTaskPartial>().configureEach {
        dokkaSourceSets.configureEach {
            documentedVisibilities.set(Visibility.values().toSet())
        }
        failOnWarning.set(true)
        offlineMode.set(true)
    }
}

plugins.withType<NodeJsRootPlugin> {
    kotlinNodeJsExtension.apply {
        nodeVersion = "21.0.0-v8-canary202310177990572111"
        nodeDownloadBaseUrl = "https://nodejs.org/download/v8-canary"
    }
    tasks.withType<KotlinNpmInstallTask>().configureEach {
        args.add("--ignore-engines")
    }
}

plugins.withType<YarnPlugin> {
    yarn.apply {
        lockFileDirectory = rootDir.resolve("gradle/js")
        yarnLockMismatchReport = YarnLockMismatchReport.FAIL
        yarnLockAutoReplace = true
    }
}

tasks {
    dokkaHtmlMultiModule.configure {
        moduleName.set(rootProject.name)
    }

    val detektAll by registering(Detekt::class) {
        description = "Run detekt on whole project"

        buildUponDefaultConfig = true
        parallel = true
        setSource(projectDir)
        config.setFrom(project.file("./config/detekt/detekt.yml"))
        include("**/*.kt")
        include("**/*.kts")
        exclude("**/resources/**", "**/build/**", "**/build.gradle.kts/**", "**/settings.gradle.kts/**")
    }
}
