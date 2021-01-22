package com.mrtdev.quoteslove.base.ui.callbacks

interface ProgressCallback {
    fun showProgress()
    fun showProgressError()
    fun showProgressSuccess()
    fun hideProgress()
}