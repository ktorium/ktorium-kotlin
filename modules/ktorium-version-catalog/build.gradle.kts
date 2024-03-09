plugins {
    id("org.gradle.version-catalog")
    id("build-project-default")
    id("build-project-publication")
}

description = "ktorium-kotlin Gradle Version Catalog"

catalog {
    versionCatalog {
        version("kotlin", kotlinCatalog.versions.kotlin.get())

        val ktoriumKotlinVersion = version("ktorium-kotlin", version.toString())
//        (ckbuild.bom.artifacts + "ktorium-kotlin-bom").forEach { name ->
//            library(
//                /* alias =    */ name.substringAfter("ktorium-"),
//                /* group =    */ "dev.whyoleg.cryptography",
//                /* artifact = */ name
//            ).versionRef(cryptographyVersion)
//        }
    }
}

publishing {
    publications {
        val versionCatalog by creating(MavenPublication::class) {
            from(components["versionCatalog"])
        }
    }
}
