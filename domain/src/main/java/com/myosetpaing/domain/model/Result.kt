package com.myosetpaing.domain.model


sealed class Result<out T> {
    data class Success<T>(val successData: T) : Result<T>()
    data class Error(val code: Int? = null, val message: String? = null) : Result<Nothing>()

}