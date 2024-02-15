plugins {
    `kotlin-dsl`
    `kotlin-dsl-precompiled-script-plugins`
}

kotlin {
    explicitApi()

    jvmToolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}
