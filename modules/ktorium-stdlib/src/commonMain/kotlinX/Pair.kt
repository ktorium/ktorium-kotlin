package org.ktorium.kotlin.stdlib

public fun <A, B> Pair<A, B>.swap(): Pair<B, A> = Pair(second, first)
