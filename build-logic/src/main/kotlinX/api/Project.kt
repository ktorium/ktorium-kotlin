@file:Suppress("PackageDirectoryMismatch")

package org.ktorium.kotlin.gradle.api

import org.gradle.api.Project

val Project.isCI
    get() = System.getenv("CI") !in arrayOf(null, "0", "false", "n", "N")
