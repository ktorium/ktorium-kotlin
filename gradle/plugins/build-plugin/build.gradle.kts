plugins {
    `kotlin-dsl`
    `java-gradle-plugin`
}

run {
    group = "org.ktorium.kotlin.gradle.plugins"
}

gradlePlugin {
    plugins {
        register("BuildPlugin") {
            id = "build-plugin"
            implementationClass = "org.ktorium.kotlin.gradle.plugins.build.BuildPlugin"
        }
    }
}

kotlin {
    explicitApi()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.9.22")
}
