package org.ktorium.kotlin.stdlib

import org.ktorium.kotlin.ExperimentalKtoriumAPI

/**
 * Return a new pair with the components swapped.
 */
@ExperimentalKtoriumAPI
public fun <A, B> Pair<A, B>.swap(): Pair<B, A> = Pair(second, first)
