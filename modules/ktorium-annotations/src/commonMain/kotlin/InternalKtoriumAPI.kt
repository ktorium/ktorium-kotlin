@file:Suppress("PackageDirectoryMismatch")

package org.ktorium.kotlin

import kotlin.annotation.AnnotationRetention.BINARY
import kotlin.annotation.AnnotationTarget.CLASS
import kotlin.annotation.AnnotationTarget.FUNCTION

/**
 * An API for internal use only. It may be changed or deleted at any time and compatibility is not guaranteed.
 */
@MustBeDocumented
@Retention(BINARY)
@Target(CLASS, FUNCTION)
public annotation class InternalKtoriumAPI
