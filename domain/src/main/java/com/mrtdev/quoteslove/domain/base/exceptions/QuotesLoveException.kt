package com.mrtdev.quoteslove.base.exceptions



sealed class QuotesLoveException : Throwable() {

    data class NoConnectivityError(val source: ConnectivityErrorSource) : QuotesLoveException()

}

enum class ConnectivityErrorSource {
    MOBILE_DEVICE,
    SERVER
}