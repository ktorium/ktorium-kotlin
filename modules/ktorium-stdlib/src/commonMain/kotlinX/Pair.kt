package org.ktorium.kotlin.stdlib

import org.ktorium.kotlin.ExperimentalSince
import org.ktorium.kotlin.KtoriumVersion.Unreleased

/**
 * Return a new pair with the components swapped.
 */
@ExperimentalSince(Unreleased)
public fun <A, B> Pair<A, B>.swap(): Pair<B, A> = Pair(second, first)
