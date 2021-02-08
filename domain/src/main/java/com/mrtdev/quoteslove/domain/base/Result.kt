package com.mrtdev.quoteslove.domain.base

import com.mrtdev.quoteslove.base.exceptions.QuotesLoveException
import io.reactivex.Single

sealed class Result<out T> {

    data class Success<out T>(val data: T) : Result<T>()

    data class Error<out T>(val error: QuotesLoveException) : Result<T>()

    inline fun <R> fmap(action: (T) -> R): Result<R> = when (this) {
        is Success -> Success(action(this.data))
        is Error -> Error(this.error)
    }
}

fun <In, Out> Single<Result<In>>.resultMap(transform: (In) -> Out) = map { it.fmap(transform) }

inline fun <In, Out> Single<Result<In>>.resultFlatMap(crossinline transform: (In) -> Single<Result<Out>>) = flatMap {
    when (it) {
        is Result.Success<In> -> transform(it.data)
        is Result.Error -> Single.just(Result.Error(it.error))
    }
}

@Suppress("NOTHING_TO_INLINE")
inline fun <T> T.asSuccess(): Result<T> =
    Result.Success(this)

@Suppress("NOTHING_TO_INLINE")
inline fun <T> QuotesLoveException.asError(): Result<T> =
    Result.Error(this)
