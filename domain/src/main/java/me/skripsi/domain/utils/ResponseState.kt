package me.skripsi.domain.utils

sealed class ResponseState<T> {
    class Loading<T> : ResponseState<T>()
    data class Success<T>(val data: T?) : ResponseState<T>()
    data class Error<T>(val message: String?): ResponseState<T>()
}
