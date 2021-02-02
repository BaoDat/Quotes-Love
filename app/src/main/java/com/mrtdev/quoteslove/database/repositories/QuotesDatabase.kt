package com.mrtdev.quoteslove.database.repositories

import com.mrtdev.quoteslove.database.RomQuotesLoveDatabase
import com.mrtdev.quoteslove.database.entity.QuotesDto
import com.mrtdev.quoteslove.database.models.Quote
import io.reactivex.Completable
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Singleton

interface QuotesDatabase {
    val getAll: Observable<List<Quote>>
    fun add(type: String, description: String, author: String): Completable
}

@Singleton
class DatabaseQuotesDatabase @Inject constructor(
    private val database: RomQuotesLoveDatabase
) : QuotesDatabase {

    override val getAll: Observable<List<Quote>> = Observable.fromCallable {
        database.quotesDao().getQuotes().map { quotesDto ->
            Quote(
                type = quotesDto.type,
                description = quotesDto.description,
                author = quotesDto.author
            )
        }
    }

    override fun add(type: String, description: String, author: String): Completable =
        Completable.fromCallable {
            database.quotesDao().insertQuotes(
                QuotesDto(
                    type = type,
                    description = description,
                    author = author
                )
            )
        }
}