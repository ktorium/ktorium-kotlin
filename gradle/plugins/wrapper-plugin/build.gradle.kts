plugins {
    `kotlin-dsl`
    `java-gradle-plugin`
}

run {
    group = "org.ktorium.kotlin.gradle.plugins"
}

gradlePlugin {
    plugins {
        register("WrapperPlugin") {
            id = "wrapper-plugin"
            implementationClass = "org.ktorium.kotlin.gradle.plugins.wrapper.WrapperPlugin"
        }
    }
}

kotlin {
    explicitApi()
}
