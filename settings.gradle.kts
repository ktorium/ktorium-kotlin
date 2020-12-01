pluginManagement {
    repositories {
        gradlePluginPortal()
        jcenter()
    }
}

rootProject.name = "ktorium-sdk"

listOf(
        "ktorium-bom",
        "ktorium-lang"
).forEach {
    include(it)
    project(":${it}").projectDir = File("modules/$it")
}
