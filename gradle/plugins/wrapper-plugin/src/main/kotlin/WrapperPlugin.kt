@file:Suppress("PackageDirectoryMismatch")

package org.ktorium.kotlin.gradle.plugins.wrapper

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.api.tasks.wrapper.Wrapper
import org.gradle.api.tasks.wrapper.Wrapper.DistributionType
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.named

public class WrapperPlugin : Plugin<Project> {
    override fun apply(project: Project): Unit = with(project) {
        tasks.named<Wrapper>("wrapper") {
            val buildCatalog = getBuildCatalog(project)

            gradleVersion = buildCatalog.getVersion("gradle")
            distributionType = DistributionType.ALL

            doLast {
                println("Gradle wrapper version: $version")
            }
        }
    }

    private fun getBuildCatalog(project: Project): VersionCatalog {
        return project.extensions
            .getByType<VersionCatalogsExtension>()
            .named("buildCatalog")
    }

    private fun VersionCatalog.getVersion(key: String): String = findVersion(key).get().requiredVersion
}
