package org.ktorium.kotlin.gradle.plugins.build

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.util.GradleVersion

public class BuildRootPlugin : Plugin<Project> {
    override fun apply(project: Project): Unit = with(project) {
        checkCompatibility()
    }

    private fun checkCompatibility() {
        if (GradleVersion.current().baseVersion < GradleVersion.version("8.0")) {
            throw IllegalStateException("This version of the Wrapper Upgrade Gradle plugin is not compatible with Gradle < 8.0")
        }
    }
}
