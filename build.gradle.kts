import io.gitlab.arturbosch.detekt.Detekt
import org.jetbrains.dokka.DokkaConfiguration.Visibility
import org.jetbrains.dokka.gradle.DokkaTaskPartial
import org.jetbrains.kotlin.gradle.targets.js.yarn.YarnLockMismatchReport
import org.jetbrains.kotlin.gradle.targets.js.yarn.YarnRootExtension

plugins {
    kotlin("multiplatform") apply false
    id("io.gitlab.arturbosch.detekt")
    id("org.jetbrains.dokka")
    id("org.jetbrains.kotlinx.binary-compatibility-validator")

    id("org.ktorium.kotlin.gradle.plugins.version")
    id("org.ktorium.kotlin.gradle.plugins.wrapper")
    id("org.ktorium.kotlin.gradle.plugins.publication")
}

allprojects {
    group = "org.ktorium.kotlin"
    version = "1.0.0-SNAPSHOT"

    configurations.all {
        resolutionStrategy {
            failOnNonReproducibleResolution()
        }
    }
}

apiValidation {
    nonPublicMarkers.add("org.ktorium.kotlin.InternalKtorium")
}

subprojects {
    tasks.withType<DokkaTaskPartial>().configureEach {
        dokkaSourceSets.configureEach {
            documentedVisibilities.set(
                setOf(Visibility.PUBLIC, Visibility.PROTECTED)
            )
        }
        failOnWarning.set(true)
        offlineMode.set(true)
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

extensions.findByType<YarnRootExtension>()?.run {
    yarnLockMismatchReport = YarnLockMismatchReport.WARNING
    reportNewYarnLock = true
    yarnLockAutoReplace = false
}
