package com.github.ajsnarr98.ktexperimentation

import java.io.FileNotFoundException
import java.io.FileWriter
import java.lang.IllegalArgumentException
import kotlin.reflect.KClass
import kotlin.reflect.full.isSubclassOf

object _try {
    operator fun <R> rangeTo(call: TryWithResources<R>) = call()
}

fun <R> with(vararg closeables: Lazy<AutoCloseable>, action: () -> R): TryWithResources<R>
    = TryWithResources(closeables, action)

/**
 * Use "_try..with()". Do not call directly
 */
class TryWithResources<R> internal constructor(
    private val closeables: Array<out Lazy<AutoCloseable>>,
    private val action: () -> R,
) {
    // catchBlocks and error types should be parallel lists
    private val catchBlocks = mutableListOf<(e: Throwable) -> R>()
    private val errorTypes = mutableListOf<Array<out KClass<out Throwable>>>()

    /**
     * Add a catch block to this try for the given types of errors.
     */
    fun catch(vararg types: KClass<out Throwable>, action: (e: Throwable) -> R): TryWithResources<R> {
        catchBlocks.add(action)
        errorTypes.add(types)
        return this
    }

    operator fun invoke(): R {
        var exception: Throwable? = null
        return try {
            // init all the closeables first
            closeables.forEach { c -> c.value }
            action()
        } catch (e: Throwable) {
            exception = e
            var catchAction: ((e: Throwable) -> R)? = null
            for (i in catchBlocks.indices) {
                if (errorTypes[i].any { errClz -> e::class.isSubclassOf(errClz) }) {
                    catchAction = catchBlocks[i]
                    break
                }
            }
            if (catchAction != null) {
                catchAction(e)
            } else {
                throw e
            }
        } finally {
            closeables.forEach { c ->
                if (exception == null) {
                    c.value.close()
                } else {
                    try {
                        c.value.close()
                    } catch (t: Throwable) {
                        // no op
                    }
                }
            }
        }
    }
}

/**
 * Example usage.
 */
fun usage() {
    val someResource = lazy { FileWriter("hello.txt") }
    val otherResource = lazy { FileWriter("goodbye.txt") }
    _try..with(someResource, otherResource) {
        // do stuff
    }.catch(FileNotFoundException::class, AccessDeniedException::class) { e ->
        // do stuff
    }.catch(IllegalArgumentException::class) { e ->
        // do stuff
    }
}
