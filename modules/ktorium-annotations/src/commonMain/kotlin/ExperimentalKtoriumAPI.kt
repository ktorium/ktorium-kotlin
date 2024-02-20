@file:Suppress("PackageDirectoryMismatch")

package org.ktorium.kotlin

import kotlin.RequiresOptIn.Level
import kotlin.annotation.AnnotationRetention.BINARY
import kotlin.annotation.AnnotationTarget.CLASS
import kotlin.annotation.AnnotationTarget.FUNCTION
import kotlin.annotation.AnnotationTarget.PROPERTY

/**
 * An API that is still in the experimental stage.
 */
@RequiresOptIn(
    message = "An API that is still in the experimental stage.",
    level = Level.WARNING,
)
@Retention(BINARY)
@Target(CLASS, FUNCTION, PROPERTY)
public annotation class ExperimentalKtoriumAPI
