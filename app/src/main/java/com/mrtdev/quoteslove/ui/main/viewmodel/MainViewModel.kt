package com.mrtdev.quoteslove.ui.main.viewmodel

import android.content.Context
import com.mrtdev.quoteslove.base.BaseViewModel
import com.mrtdev.quoteslove.ui.main.navigator.MainNavigator
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

class MainViewModel @Inject constructor(
    context: Context
) : BaseViewModel() {

    private val navigationSubject = PublishSubject.create<MainNavigator.Event>()
    val navigation: Observable<MainNavigator.Event> = navigationSubject.hide()

}