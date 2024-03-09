package build.gradle.plugins.build

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.util.GradleVersion

public class ProjectRootDefaultPlugin : Plugin<Project> {
    override fun apply(project: Project): Unit = project.run {
        checkCompatibility()
    }

    // TODO config for Gradle version
    private fun checkCompatibility() {
        if (GradleVersion.current().baseVersion < GradleVersion.version("8.6")) {
            throw IllegalStateException("This version of the Wrapper Upgrade Gradle plugin is not compatible with Gradle < 8.6")
        }
    }
}
