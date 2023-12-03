package org.ktorium.kotlin.stdlib.collections

public inline fun <T> Collection<T>.ifNotEmpty(body: Collection<T>.() -> T?): T? =
    if(isNotEmpty()) body() else null
