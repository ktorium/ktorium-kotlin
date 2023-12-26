<h1 style="text-align: center;">ktorium-kotlin</h1>

<p style="text-align: center;">
    <a href="https://kotlinlang.org">
        <img alt="Kotlin Version" src="https://img.shields.io/badge/kotlin-1.9.22-blue.svg?logo=kotlin">
    </a>
    <a href="https://github.com/ktorium/ktorium-kotlin/blob/main/LICENSE">
        <img alt="License" src="https://img.shields.io/github/license/ktorium/ktorium-kotlin" />
    </a>
</p>

## Overview

Kotlin extensions of official libraries.

## Dependency

Include the configuration in your Gradle settings.gradle.kts

```kotlin
import java.net.URI

sourceControl {
    gitRepository(URI("https://github.com/ktorium/ktorium-kotlin.git")) {
        producesModule("com.ktorium.kotlin:ktorium-kotlin")
    }
}
```

and declare the dependency

```kotlin
dependencies {
    implementation("org.ktorium.kotlin:ktorium-kotlin:$VERSION")
}
```

## Compatibility

<table>
    <thead>
        <tr>
            <th><strong>Kotlin API Version</strong></th>
            <th><strong>ktorium-stdlib</strong></th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td>1.9</td>
            <td>1.0.0</td>
        </tr>
        <tr>
            <td>1.8</td>
            <td>1.0.0</td>
        </tr>
        <tr>
            <td>1.7</td>
            <td>1.0.0</td>
        </tr>
        <tr>
            <td>1.6</td>
            <td>1.0.0</td>
        </tr>
    </tbody>
</table>

## Contributions

Please feel free to submit a pull request. Contributions are welcome!

## Acknowledgements

JetBrains for making [Kotlin](https://kotlinlang.org).

## License

The source code is distributed under [Apache License 2.0](LICENSE).
