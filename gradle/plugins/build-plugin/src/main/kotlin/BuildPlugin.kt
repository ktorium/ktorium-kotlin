@file:Suppress("PackageDirectoryMismatch")

package org.ktorium.kotlin.gradle.plugins.build

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.create

public class BuildPlugin : Plugin<Project> {
    override fun apply(project: Project): Unit = with(project) {
        extensions.create<BuildProperties>(BuildProperties.EXTENSION_NAME)
    }
}
