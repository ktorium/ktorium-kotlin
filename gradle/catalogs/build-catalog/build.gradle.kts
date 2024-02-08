plugins {
    `kotlin-dsl`
}

kotlin {
    explicitApi()
}

gradlePlugin {
    plugins {
        create("build-catalog") {
            id = "build-catalog"
            implementationClass = "org.ktorium.kotlin.gradle.catalogs.build.BuildCatalogPlugin"
        }
    }
}
