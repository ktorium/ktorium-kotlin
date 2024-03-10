@file:Suppress("PackageDirectoryMismatch")

package org.ktorium.kotlin

import kotlin.annotation.AnnotationRetention.SOURCE
import kotlin.annotation.AnnotationTarget.CLASS
import kotlin.annotation.AnnotationTarget.FUNCTION
import kotlin.annotation.AnnotationTarget.PROPERTY
import kotlin.annotation.AnnotationTarget.TYPEALIAS

@InternalKtoriumAPI
@MustBeDocumented
@Retention(SOURCE)
@Target(CLASS, FUNCTION, PROPERTY, TYPEALIAS)
public annotation class DeprecatedSince(
    val warningSince: KtoriumVersion,
    val errorSince: KtoriumVersion = KtoriumVersion.Unreleased,
    val hiddenSince: KtoriumVersion = KtoriumVersion.Unreleased
)
