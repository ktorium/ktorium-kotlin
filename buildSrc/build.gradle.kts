import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSet

buildscript {
    repositories {
        jcenter()
    }
}

plugins {
    `kotlin-dsl`
}

repositories {
    jcenter()
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
