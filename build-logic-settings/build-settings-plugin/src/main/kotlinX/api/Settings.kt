@file:Suppress("PackageDirectoryMismatch")

package build.gradle.api

import org.gradle.api.initialization.Settings

public val Settings.CI: Boolean
    get() = System.getenv("CI") !in arrayOf(null, "0", "false", "n", "N")

public fun Settings.includeModule(name: String) {
    require(name.isNotBlank())

    include(name)
    project(":${name}").projectDir = rootDir.resolve("modules/$name")
}
