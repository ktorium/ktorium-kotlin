@file:Suppress("PackageDirectoryMismatch", "UnstableApiUsage")

package build.gradle.plugins.settings

import org.gradle.api.Plugin
import org.gradle.api.initialization.Settings
import org.gradle.api.initialization.resolve.RepositoriesMode
import org.gradle.kotlin.dsl.assign

public class SettingsRepositoryPlugin : Plugin<Settings> {
    override fun apply(settings: Settings): Unit = settings.run {
        pluginManagement {
            repositories {
                gradlePluginPortal {
                    content {
                        includeGroupAndSubgroups("com.gradle")
                        includeGroupAndSubgroups("org.gradle")
                    }
                }
                mavenCentral()
            }
        }

        dependencyResolutionManagement {
            repositoriesMode = RepositoriesMode.PREFER_PROJECT

            repositories {
                gradlePluginPortal {
                    content {
                        includeGroupAndSubgroups("com.gradle")
                        includeGroupAndSubgroups("org.gradle")
                    }
                }
                mavenCentral()
            }
        }
    }
}
