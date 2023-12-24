import org.gradle.api.tasks.wrapper.Wrapper.DistributionType.ALL

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
    named<Wrapper>("wrapper") {
        gradleVersion = project.property("gradle-wrapper.version") as String
        distributionType = ALL
    }
}
