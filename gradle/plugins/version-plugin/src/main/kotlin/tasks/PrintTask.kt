@file:Suppress("PackageDirectoryMismatch")

package org.ktorium.kotlin.gradle.plugins.version.tasks

import org.gradle.api.DefaultTask
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.TaskAction
import org.gradle.work.DisableCachingByDefault

/** Task that prints a [message] to the standard output stream. */
@DisableCachingByDefault(because = "Printing to the terminal is not cacheable.")
public abstract class PrintTask : DefaultTask() {
    /** The message to print. */
    @get:Input
    public abstract val message: Property<Any?>

    @TaskAction
    internal fun print(): Unit = println(message.orNull)
}

internal fun PrintTask.message(message: String){
    this.message.set(message)
}
