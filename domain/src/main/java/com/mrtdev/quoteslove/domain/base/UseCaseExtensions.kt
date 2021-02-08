package com.mrtdev.quoteslove.domain.base

fun Usecase.Action<Unit>.execute() = execute(Unit)

fun Usecase.ActionSuccessful<Unit>.execute() = execute(Unit)

fun <T> Usecase.Single<Unit, T>.execute() = execute(Unit)

fun <T> Usecase.SingleSuccessful<Unit, T>.execute() = execute(Unit)

fun <T> Usecase.Continuous<Unit, T>.execute() = execute(Unit)
