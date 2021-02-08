package com.mrtdev.quoteslove.domain.splash.usecases

import com.mrtdev.quoteslove.app.SessionStorage
import com.mrtdev.quoteslove.domain.base.Usecase
import com.mrtdev.quoteslove.domain.base.Result
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class CountAppLaunches @Inject constructor(
    private val sessionStorage: SessionStorage
) : Usecase.Single<Unit, Boolean> {

    override fun execute(param: Unit): Single<Result<Boolean>> = Completable.fromCallable {
        if (sessionStorage.isAppRunFirst){
            sessionStorage.isAppRunFirst = false
        }
    }.toSingle {
        Result.Success(sessionStorage.isAppRunFirst)
    }
}
