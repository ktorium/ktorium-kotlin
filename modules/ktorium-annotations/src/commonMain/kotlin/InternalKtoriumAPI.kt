@file:Suppress("PackageDirectoryMismatch")

package org.ktorium.kotlin

import kotlin.RequiresOptIn.Level
import kotlin.annotation.AnnotationRetention.BINARY
import kotlin.annotation.AnnotationTarget.CLASS
import kotlin.annotation.AnnotationTarget.FUNCTION
import kotlin.annotation.AnnotationTarget.PROPERTY

/**
 * An API for internal use only. It may be changed or deleted at any time and compatibility is not guaranteed.
 */
@RequiresOptIn(
    message = "An API for internal use only. It may be changed or deleted at any time and compatibility is not guaranteed.",
    level = Level.WARNING,
)
@Retention(BINARY)
@Target(CLASS, FUNCTION, PROPERTY)
public annotation class InternalKtoriumAPI
