@file:Suppress("PackageDirectoryMismatch")

package org.ktorium.kotlin.gradle.plugins.version

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.register
import org.ktorium.kotlin.gradle.plugins.version.tasks.PrintTask
import org.ktorium.kotlin.gradle.plugins.version.tasks.TaskGroup
import org.ktorium.kotlin.gradle.plugins.version.tasks.description
import org.ktorium.kotlin.gradle.plugins.version.tasks.group

public class VersionPlugin : Plugin<Project> {
    override fun apply(project: Project): Unit = with(project) {
        tasks.register<PrintTask>("version")
            .configure {
                group(TaskGroup.HELP)
                description("Prints the version of the project.")
                message.set(project.version)
            }
    }
}
