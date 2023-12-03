plugins {
    `kotlin-dsl`
}

configurations.all {
    resolutionStrategy {
        failOnNonReproducibleResolution()
    }
}

sourceSets {
    main {
        kotlin {
            srcDirs("src/main/kotlin", "src/main/kotlinX")
        }
    }
}
