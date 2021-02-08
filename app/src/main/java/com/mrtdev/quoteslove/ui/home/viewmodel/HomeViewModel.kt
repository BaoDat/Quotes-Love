package com.mrtdev.quoteslove.ui.home.viewmodel

import androidx.lifecycle.MutableLiveData
import com.mrtdev.quoteslove.base.BaseViewModel
import com.mrtdev.quoteslove.database.usecases.AddQuote
import com.mrtdev.quoteslove.database.base.Result
import com.mrtdev.quoteslove.utils.plusAssign
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private var add: AddQuote
) : BaseViewModel() {

    private val saveScoreSuccessSubject = PublishSubject.create<Unit>()
    val saveScoreSuccess = saveScoreSuccessSubject.hide()
    
    val isExecuting = MutableLiveData<Boolean>()
//    fun saveScore() {
//        compositeDisposable +=
//            add.execute(AddQuote.QuoteAdd(type = "Thơ", description = "Xin chào", author = "MrT"))
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .doOnSubscribe { isExecuting.value = true }
//                .doFinally { isExecuting.value = false }
//                .subscribe(Consumer {
//                    when (it) {
//                        is Result.Success -> saveScoreSuccessSubject.onNext(Unit)
//                        is Result.Error -> {
//                        }
//                    }
//                })
//
//    }
}