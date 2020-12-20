pluginManagement {
    repositories {
        gradlePluginPortal()
        jcenter()
    }
}

rootProject.name = "ktorium-kotlin"

listOf(
        "kotlin-bom",
        "kotlin-stdlib"
).forEach {
    include(it)
    project(":${it}").projectDir = File("modules/$it")
}
