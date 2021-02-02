package com.mrtdev.quoteslove.ui.allquotes.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.mrtdev.quoteslove.base.BaseViewModel
import com.mrtdev.quoteslove.data.DataAsyncTask
import com.mrtdev.quoteslove.database.models.Quote
import com.mrtdev.quoteslove.database.usecases.GetAllQuotes
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import com.mrtdev.quoteslove.utils.plusAssign
import javax.inject.Inject

class AllQuotesViewModel @Inject constructor(
    private val context: Context,
    getAllQuotes: GetAllQuotes,
    jSoup: DataAsyncTask
) : BaseViewModel() {

    val quotes = MutableLiveData<List<Quote>>()
    val values = MutableLiveData<String>()

    init {
        compositeDisposable += getAllQuotes.execute(Unit)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                quotes.value = it
            }

        compositeDisposable += jSoup.getJsonData<Quote>(context, values.value!!).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                },
                {

                }
            )
    }
}