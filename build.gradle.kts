import io.gitlab.arturbosch.detekt.Detekt
import org.jetbrains.kotlin.gradle.targets.js.yarn.YarnLockMismatchReport
import org.jetbrains.kotlin.gradle.targets.js.yarn.YarnRootExtension

plugins {
    alias(buildCatalog.plugins.org.jetbrains.kotlin.multiplatform) apply false
    alias(buildCatalog.plugins.io.gitlab.arturbosch.detekt)
    //alias(buildCatalog.plugins.org.jetbrains.dokka)
    alias(buildCatalog.plugins.org.jetbrains.kotlinx.binary.compatibility.validator)

    id("wrapper-plugin")
    id("version-plugin")
    id("publication-plugin")
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
//    tasks.withType<DokkaTaskPartial>().configureEach {
//        dokkaSourceSets.configureEach {
//            documentedVisibilities.set(Visibility.values().toSet())
//        }
//        failOnWarning.set(true)
//        offlineMode.set(true)
//    }
}

tasks {
    //dokkaHtmlMultiModule.configure {
    //    moduleName.set(rootProject.name)
    //}

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
