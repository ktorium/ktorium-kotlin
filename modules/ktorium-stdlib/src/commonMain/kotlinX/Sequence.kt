package org.ktorium.kotlin.stdlib

public fun <T> Sequence<T>.dropUntil(predicate: (T) -> Boolean): Sequence<T> {
    return DropUntilSequence(this, predicate)
}

private class DropUntilSequence<T>(
    private val sequence: Sequence<T>,
    private val predicate: (T) -> Boolean
) : Sequence<T> {
    private enum class State { NONE, CONTINUE}
    override fun iterator(): Iterator<T> = object : Iterator<T> {
        private val iterator = sequence.iterator()
        private var dropState = State.NONE

        private fun drop() {
            while (iterator.hasNext()) {
                val item = iterator.next()
                if (!predicate(item)) {
                    dropState = State.CONTINUE
                    return
                }
            }

            dropState = State.CONTINUE
        }

        override fun next(): T {
            if (dropState == State.NONE) {
                drop()
            }

            return iterator.next()
        }

        override fun hasNext(): Boolean {
            if (dropState == State.NONE) {
                drop()
            }

            return dropState == State.CONTINUE || iterator.hasNext()
        }
    }
}

public fun <T> Sequence<T>.takeUntil(predicate: (T) -> Boolean): Sequence<T> {
    return TakeUntilSequence(this, predicate)
}

private class TakeUntilSequence<T>(
    private val sequence: Sequence<T>,
    private val predicate: (T) -> Boolean
) : Sequence<T> {
    private enum class State { NONE, DONE, CONTINUE}
    override fun iterator(): Iterator<T> = object : Iterator<T> {
        private val iterator = sequence.iterator()

        private var nextItem: T? = null
        private var nextState = State.NONE

        private fun iterateNext() {
            if (iterator.hasNext()) {
                val item = iterator.next()

                nextItem = item

                if (predicate(item)) {
                    nextState = State.CONTINUE
                    return
                }
            }

            nextState = State.DONE
        }

        override fun next(): T {
            if (nextState == State.NONE) {
                iterateNext()
            }

            if (nextState == State.DONE) {
                throw NoSuchElementException()
            }

            @Suppress("UNCHECKED_CAST")
            val result = nextItem as T

            nextItem = null
            nextState = State.NONE

            return result
        }

        override fun hasNext(): Boolean {
            if (nextState == State.NONE) {
                iterateNext()
            }

            return nextState == State.CONTINUE || nextItem != null
        }
    }
}
