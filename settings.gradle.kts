pluginManagement {
    repositories {
        gradlePluginPortal()
        jcenter()
    }
}

rootProject.name = "ktorium-sdk"

listOf(
        "ktorium-bom"
).forEach {
    include(it)
    project(":${it}").projectDir = File("modules/$it")
}
