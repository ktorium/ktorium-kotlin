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
            srcDirs("src/main/kotlinX")
        }
    }
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.9.22")
}
