@file:Suppress("PackageDirectoryMismatch")

package org.ktorium.kotlin.gradle.api.initialization

import org.gradle.api.initialization.Settings
import java.io.File

public fun Settings.includeModule(name: String) {
    require(name.isNotBlank())

    include(name)
    project(":${name}").projectDir = rootDir.resolve("modules/$name")
}

public fun Settings.includeBuild(directory: String, name: String) {
    require(name.isNotBlank())

    val buildModuleDirectory: File = rootDir.resolve("$directory/$name")

    includeBuild(buildModuleDirectory)
}
