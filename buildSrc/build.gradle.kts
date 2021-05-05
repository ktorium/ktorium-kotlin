import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSet

buildscript {
    repositories {
        mavenCentral()
    }
}

plugins {
    `kotlin-dsl`
}

repositories {
    mavenCentral()
}

kotlinDslPluginOptions {
    experimentalWarning.set(false)
}

sourceSets {
    named("main") {
        withConvention(KotlinSourceSet::class) {
            kotlin.srcDirs("src/main/kotlinX")
        }
    }
    named("test") {
        withConvention(KotlinSourceSet::class) {
            kotlin.srcDirs("src/test/kotlinX")
        }
    }
}

dependencies {
    subprojects.forEach {
        runtimeOnly(project(it.path))
    }
}
