@file:Suppress("PackageDirectoryMismatch")

package org.ktorium.datetime

import org.ktorium.kotlin.ExperimentalKtoriumAPI

@ExperimentalKtoriumAPI
public class Year private constructor() {
    public companion object {
        public fun isLeap(year: Int): Boolean =
            year % 4 == 0 && (year % 100 != 0 || year % 400 == 0)
    }
}
