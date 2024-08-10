
import io.gitlab.arturbosch.detekt.Detekt
import org.gradle.api.tasks.wrapper.Wrapper.DistributionType
import org.jetbrains.dokka.gradle.DokkaMultiModuleTask
import org.jetbrains.kotlin.gradle.targets.js.yarn.YarnLockMismatchReport
import org.jetbrains.kotlin.gradle.targets.js.yarn.YarnPlugin
import org.jetbrains.kotlin.gradle.targets.js.yarn.yarn

plugins {
    alias(libraries.plugins.kotlin.serialization) apply false
    alias(libraries.plugins.kotlinx.kover) apply false
    alias(libraries.plugins.kotlinx.bcv)
    alias(libraries.plugins.kotlin.dokka)
    alias(libraries.plugins.detekt)
}

description = "Root Project"

allprojects {
    group = "org.ktorium.kotlin"
    version = "0.1.0"

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

plugins.withType<YarnPlugin> {
    yarn.apply {
        lockFileDirectory = rootDir.resolve("gradle/js")
        yarnLockMismatchReport = YarnLockMismatchReport.FAIL
        yarnLockAutoReplace = true
        reportNewYarnLock = true
        ignoreScripts = false
    }
}

tasks {
    val dokkaHtmlMultiModule by getting(DokkaMultiModuleTask::class) {
        moduleName.set(rootProject.name)
        moduleVersion.set("0.1.0") // TODO
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

    named<Wrapper>("wrapper") {
        gradleVersion = libraries.versions.gradle.asProvider().get()
        distributionType = DistributionType.ALL

        doLast {
            println("Gradle wrapper version: $gradleVersion")
        }
    }
}
