plugins {
    `java-platform`
}

description = "Bill of materials to make sure a consistent set of versions are used."

dependencies {
    constraints {
        api(project(":ktorium-lang"))
    }
}
