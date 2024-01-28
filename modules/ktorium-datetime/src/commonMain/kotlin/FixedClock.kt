@file:Suppress("PackageDirectoryMismatch")

package org.ktorium.datetime

import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import org.ktorium.kotlin.ExperimentalKtorium

@ExperimentalKtorium
public open class FixedClock(
    private val instant: Instant,
) : Clock {
    override fun now(): Instant = instant

    public fun nowOrNull(): Instant = instant

    public companion object
}
