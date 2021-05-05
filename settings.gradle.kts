pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
    }
}

rootProject.name = "ktorium-kotlin"

listOf(
        "ktorium-bom",
        "ktorium-stdlib"
).forEach {
    include(it)
    project(":${it}").projectDir = File("modules/$it")
}
