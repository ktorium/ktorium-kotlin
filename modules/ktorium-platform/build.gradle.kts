plugins {
    id("java-platform")
    id("maven-publish")
}

val bomProject = project

internal fun shouldIncludeInBom(candidate: Project) = candidate.name != bomProject.name

dependencies {
    constraints {
        rootProject.subprojects
            .filter(::shouldIncludeInBom)
            .forEach { subproject ->
                if (subproject.plugins.hasPlugin("maven-publish")) {
                    subproject.publishing.publications.withType<MavenPublication> {
                        if (!artifactId.endsWith("-metadata") && !artifactId.endsWith("-kotlinMultiplatform")) {
                            api("$groupId:$artifactId:$version")
                        }
                    }
                }
            }
    }
}

publishing {
    publications {
        create<MavenPublication>("ktoriumPlatform") {
            from(components["javaPlatform"])
        }
    }
}
