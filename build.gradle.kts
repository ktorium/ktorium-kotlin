import org.gradle.api.tasks.wrapper.Wrapper.DistributionType.ALL

buildscript {
    repositories {
        jcenter()
    }
}

plugins {
    kotlin("multiplatform") version "1.4.20" apply false
    id("io.gitlab.arturbosch.detekt") version "1.14.2" apply false
}

allprojects {
    group = "com.ktorium.sdk"
    version = "0.0.1"

    repositories {
        jcenter()
    }

    configurations.all {
        resolutionStrategy {
            failOnNonReproducibleResolution()
        }
    }
}

tasks {
    named<Wrapper>("wrapper") {
        gradleVersion = rootProject.property("gradle-wrapper.version") as String
        distributionType = ALL
    }
}
