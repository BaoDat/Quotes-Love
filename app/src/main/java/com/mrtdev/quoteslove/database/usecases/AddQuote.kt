package com.mrtdev.quoteslove.database.usecases

import com.mrtdev.quoteslove.database.repositories.QuotesDatabase
import com.mrtdev.quoteslove.database.base.Result
import com.mrtdev.quoteslove.database.base.UseCase
import com.mrtdev.quoteslove.database.base.asSuccess
import io.reactivex.Single
import javax.inject.Inject

class AddQuote @Inject constructor(
    private val database: QuotesDatabase
) : UseCase.Single<AddQuote.QuoteAdd, Unit> {
    data class QuoteAdd(val type: String, val description: String, val author: String)

    override fun execute(param: QuoteAdd): Single<Result<Unit>> =
        database.add(param.type, param.description, param.author)
            .andThen(Single.just(Unit.asSuccess()))
}