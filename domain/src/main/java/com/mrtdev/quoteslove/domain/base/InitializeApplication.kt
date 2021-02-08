package com.mrtdev.quoteslove.domain.base

import com.mrtdev.quoteslove.domain.splash.usecases.CountAppLaunches
import io.reactivex.Single
import javax.inject.Inject

class InitializeApplication @Inject constructor(
    private val countAppLaunches: CountAppLaunches
) : Usecase.Single<Unit, Boolean> {

    override fun execute(param: Unit): Single<Result<Boolean>> =
        countAppLaunches.execute()
            .doOnSuccess {
                when (it) {
                    is Result.Success -> {
                        if (it.data) {
                            updateData()
                        }
                    }
                    is Result.Error -> it
                }
            }

    private fun updateData() {

    }
}
