@file:Suppress("PackageDirectoryMismatch")

package org.ktorium.kotlin.gradle.plugins.wrapper

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.wrapper.Wrapper
import org.gradle.api.tasks.wrapper.Wrapper.DistributionType
import org.gradle.kotlin.dsl.named

public class WrapperPlugin : Plugin<Project> {
    private val gradleWrapperPropertyName = "gradle-wrapper.version"

    override fun apply(project: Project): Unit = with(project) {
        val version = project.property(gradleWrapperPropertyName) as String

        tasks.named<Wrapper>("wrapper") {
            gradleVersion = version
            distributionType = DistributionType.ALL

            doLast {
                println("Gradle wrapper version: $version")
            }
        }
    }
}
