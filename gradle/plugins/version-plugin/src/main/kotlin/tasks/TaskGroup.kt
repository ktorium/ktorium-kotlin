@file:Suppress("PackageDirectoryMismatch")

package org.ktorium.kotlin.gradle.plugins.version.tasks

import java.util.*

internal enum class TaskGroup {
    HELP;

    override fun toString(): String = name.lowercase(Locale.getDefault())
}
