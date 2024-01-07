import io.gitlab.arturbosch.detekt.Detekt
import org.gradle.api.tasks.wrapper.Wrapper.DistributionType.ALL

plugins {
    kotlin("multiplatform") apply false
    id("io.gitlab.arturbosch.detekt")
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

tasks {
    val detektAll by registering(Detekt::class) {
        parallel = true
        setSource(files(projectDir))
        include("**/*.kt")
        include("**/*.kts")
        exclude("**/resources/**", "**/build/**", "**/build.gradle.kts/**", "**/settings.gradle.kts/**")
        config.setFrom(project.file("./config/detekt/detekt.yml"))
        buildUponDefaultConfig = true
    }

    named<Wrapper>("wrapper") {
        gradleVersion = project.property("gradle-wrapper.version") as String
        distributionType = ALL
    }
}
