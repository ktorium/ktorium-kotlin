@file:Suppress("PackageDirectoryMismatch")

package build.gradle.plugins.settings

import org.gradle.api.Plugin
import org.gradle.api.initialization.Settings
import org.gradle.api.logging.Logger
import org.gradle.api.logging.Logging
import org.gradle.jvm.toolchain.JavaLanguageVersion
import org.gradle.kotlin.dsl.assign
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmProjectExtension
import org.jetbrains.kotlin.gradle.plugin.KotlinMultiplatformPluginWrapper

private val Settings.log: Logger by lazy { Logging.getLogger(SettingsGradlePlugin::class.java) }

public class SettingsGradlePlugin : Plugin<Settings> {
    private val defaultLanguageVersion = JavaLanguageVersion.of(17)

    override fun apply(settings: Settings): Unit = settings.run {
        val jvmVersionOverride = providers.gradleProperty("kotlin.javaToolchain.mainJvmCompiler").orNull?.takeIf(String::isNotBlank)

        if (jvmVersionOverride != null) {
            log.lifecycle("JVM version override: $jvmVersionOverride")
        }

//        pluginManager.withPlugin(KotlinMultiplatformPluginWrapper::class) {
//            configure<KotlinMultiplatformPluginWrapper::class> {
//                jvmToolchain {
//                    languageVersion = javaLanguageVersion
//                }
//            }
//        }
    }
}
