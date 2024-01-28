package org.ktorium.kotlin.stdlib

import org.ktorium.kotlin.ExperimentalKtorium

/**
 * Return a new pair with the components swapped.
 */
@ExperimentalKtorium
public fun <A, B> Pair<A, B>.swap(): Pair<B, A> = Pair(second, first)
