package com.mrtdev.quoteslove.database.usecases

import com.mrtdev.quoteslove.database.base.UseCase
import com.mrtdev.quoteslove.database.models.Quote
import com.mrtdev.quoteslove.database.repositories.QuotesDatabase
import io.reactivex.Observable
import javax.inject.Inject

class GetAllQuotes @Inject constructor(
    private val database: QuotesDatabase
) : UseCase.Continuous<Unit, List<Quote>> {

    override fun execute(param: Unit): Observable<List<Quote>> = database.getAll
}