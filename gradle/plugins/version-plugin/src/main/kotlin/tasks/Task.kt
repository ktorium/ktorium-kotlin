@file:Suppress("PackageDirectoryMismatch")

package org.ktorium.kotlin.gradle.plugins.version.tasks

import org.gradle.api.Task

internal fun Task.group(value: TaskGroup) {
    group = "$value"
}

internal fun Task.description(description: String) {
    require(description.isNotBlank()) {
        "The task '$path' shouldn't have a blank description."
    }
    this.description = description
}
