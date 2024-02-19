package org.ktorium.kotlin.stdlib

import org.ktorium.kotlin.ExperimentalKtoriumApi

/**
 * Return a new pair with the components swapped.
 */
@ExperimentalKtoriumApi
public fun <A, B> Pair<A, B>.swap(): Pair<B, A> = Pair(second, first)
