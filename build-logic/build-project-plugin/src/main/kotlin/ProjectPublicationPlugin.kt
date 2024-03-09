package build.gradle.plugins.build

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.ExtensionContainer
import org.gradle.api.publish.PublishingExtension
import org.gradle.api.publish.maven.MavenPublication
import org.gradle.api.publish.maven.plugins.MavenPublishPlugin
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.the
import org.gradle.kotlin.dsl.withType
import org.gradle.plugins.signing.SigningExtension
import org.gradle.plugins.signing.SigningPlugin

public class ProjectPublicationPlugin : Plugin<Project> {
    private val Project.signPublication: Boolean
        get() = project.providers.gradleProperty("signingRequired").orNull?.toBoolean() == true

    override fun apply(project: Project): Unit {
        project.apply<MavenPublishPlugin>()
        project.configureMavenPublish()

        if (project.signPublication) {
            project.apply<SigningPlugin>()
            project.configureSigning()
        }

//        val cleanMavenLocal = project.registerCleanMavenLocal()
//        project.tasks.named("clean") {
//            dependsOn(cleanMavenLocal)
//        }

        // https://github.com/gradle/gradle/issues/26091
//        project.tasks.withType<AbstractPublishToMaven>().configureEach {
//            val signingTasks = project.tasks.withType<Sign>()
//            mustRunAfter(signingTasks)
//        }
    }

//    val cleanLocalRepository by tasks.registering(Delete::class) {
//        description = "Clears local-maven-repo so timestamp-based snapshot artifacts do not consume space"
//        delete(localRepoDir)
//    }

    private fun Project.registerCleanMavenLocal() = tasks.register("cleanMavenLocal") {
        group = "build"
        doLast {
            val home = System.getProperty("user.home")
            val artifactPath = project.group.toString().replace(".", "/")
            val groupRepository = file("$home/.m2/repository/$artifactPath")

            val publishing = the<PublishingExtension>()
            publishing.publications.filterIsInstance<MavenPublication>().forEach {
                groupRepository.resolve(it.artifactId).deleteRecursively()
            }
        }
    }

    private inline fun <reified T> ExtensionContainer.configure(crossinline receiver: T.() -> Unit) {
        configure(T::class.java) { receiver() }
    }

    private fun Project.configureMavenPublish() {
        plugins.withId("maven-publish") {
            val localRepositoryDir = layout.buildDirectory.dir("local-maven-repository")

            project.extensions.configure<PublishingExtension> {
                repositories {
                    maven {
                        name = "local-maven-repository"
                        url = uri(localRepositoryDir)
                    }
                }

                publications.withType<MavenPublication>().configureEach {

                    pom {
                        name.set(project.name)
//                        description.set(provider {
//                            checkNotNull(project.description) { "Project description isn't set for project: ${project.path}" }
//                        })
                        version = project.version.toString()

                        val gitRepository = "https://github.com/ktorium/ktorium-kotlin"
                        url.set(gitRepository)

                        licenses {
                            license {
                                name.set("Apache-2.0 License")
                                url.set("$gitRepository/blob/main/LICENSE")
                            }
                        }

                        issueManagement {
                            system.set("GitHub")
                            url.set("$gitRepository/issues")
                        }

                        scm {
                            connection.set("scm:git:$gitRepository.git")
                            developerConnection.set("scm:git:$gitRepository.git")
                            url.set(gitRepository)
                        }
                    }
                }
            }
        }
    }

    private fun Project.configureSigning() = extensions.configure<SigningExtension> {
        val signKeyId = project.getPropertyOf("signKeyId")
        if (!signKeyId.isNullOrBlank()) {
            val signKeyPrivate = project.getPropertyOf("signKeyPrivate") ?: error("Parameter `signKeyPrivate` not found")
            val signKeyPassphrase = project.getPropertyOf("signKeyPassphrase") ?: error("Parameter `signKeyPassphrase` not found")

            useInMemoryPgpKeys(signKeyId, signKeyPrivate, signKeyPassphrase)
        } else {
            useGpgCmd()
        }

        val publications = extensions.getByType<PublishingExtension>().publications
        sign(publications)
    }

    private fun Project.getPropertyOf(name: String): String? = project.findProperty(name) as? String ?: System.getenv(name)
}
