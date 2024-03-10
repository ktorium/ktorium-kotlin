@file:Suppress("PackageDirectoryMismatch")

package org.ktorium.datetime

import org.ktorium.kotlin.ExperimentalSince
import org.ktorium.kotlin.KtoriumVersion

@ExperimentalSince(KtoriumVersion.Unreleased)
public class Year private constructor() {
    public companion object {
        public fun isLeap(year: Int): Boolean =
            year % 4 == 0 && (year % 100 != 0 || year % 400 == 0)
    }
}
