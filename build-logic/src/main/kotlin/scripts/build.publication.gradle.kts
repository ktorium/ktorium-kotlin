import java.util.*

plugins {
    id("maven-publish")
    id("signing")
}

signing {
    val signingKey = findProperty("GPG_PRIVATE_KEY")?.toString()
    val signingPassword = findProperty("GPG_PASSPHRASE")?.toString()

    if (signingKey != null && signingPassword != null) {
        useInMemoryPgpKeys(String(Base64.getDecoder().decode(signingKey.toByteArray())), signingPassword)

        sign(publishing.publications)
    }
}

publishing {
    repositories {
        maven {
            name = "LocalRepository"
            url = uri(layout.buildDirectory.dir("local-repository"))
        }
    }

    publications {
        withType<MavenPublication> {
            pom {
                name.set(project.name)
                description.set("Kotlin extensions of official libraries")
                url.set("https://github.com/ktorium/ktorium-kotlin")

                licenses {
                    license {
                        name.set("Apache-2.0 License")
                        url.set("https://github.com/ktorium/ktorium-kotlin/blob/main/LICENSE")
                    }
                }

                scm {
                    connection.set("scm:git:https://github.com/ktorium/ktorium-kotlin.git")
                    developerConnection.set("scm:git:https://github.com/ktorium/ktorium-kotlin.git")
                    url.set("https://github.com/ktorium/ktorium-kotlin")
                }
            }
        }
    }
}

tasks {
    withType<Jar> {
        manifest {
            attributes += sortedMapOf(
                "Build-Jdk" to System.getProperty("java.version"),
                "Implementation-Version" to project.version,
                "Created-By" to "${GradleVersion.current()}",
            )
        }
    }

    val cleanMavenLocal by registering {
        group = "build"
        doLast {
            val home = System.getProperty("user.home")
            val artifactPath = project.group.toString().replace(".", "/")
            val groupRepo = file("$home/.m2/repository/$artifactPath")

            publishing.publications.filterIsInstance<MavenPublication>().forEach {
                groupRepo.resolve(it.artifactId).deleteRecursively()
            }
        }
    }
    named("clean") {
        dependsOn(cleanMavenLocal)
    }
}
