@file:Suppress("PackageDirectoryMismatch")

package org.ktorium.kotlin

import kotlin.RequiresOptIn.Level
import kotlin.annotation.AnnotationRetention.BINARY
import kotlin.annotation.AnnotationTarget.CLASS
import kotlin.annotation.AnnotationTarget.FUNCTION
import kotlin.annotation.AnnotationTarget.PROPERTY
import kotlin.annotation.AnnotationTarget.TYPEALIAS

@RequiresOptIn(
    message = "An API that is still in the experimental stage.",
    level = Level.WARNING,
)
@MustBeDocumented
@InternalKtoriumAPI
@Retention(BINARY)
@Target(CLASS, FUNCTION, PROPERTY, TYPEALIAS)
public annotation class ExperimentalSince(val version: KtoriumVersion)
