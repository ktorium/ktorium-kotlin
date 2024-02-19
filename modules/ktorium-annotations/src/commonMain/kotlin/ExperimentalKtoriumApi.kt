@file:Suppress("PackageDirectoryMismatch")

package org.ktorium.kotlin

import kotlin.annotation.AnnotationRetention.BINARY
import kotlin.annotation.AnnotationTarget.CLASS
import kotlin.annotation.AnnotationTarget.FUNCTION
import kotlin.annotation.AnnotationTarget.PROPERTY

@RequiresOptIn(message = "This API is experimental. It may be changed in the future without notice.")
@Retention(BINARY)
@Target(CLASS, FUNCTION, PROPERTY)
public annotation class ExperimentalKtoriumApi
