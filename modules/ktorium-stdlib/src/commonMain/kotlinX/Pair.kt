package org.ktorium.kotlin.stdlib

/**
 * Return a new pair with the components swapped.
 */
public fun <A, B> Pair<A, B>.swap(): Pair<B, A> = Pair(second, first)
