package com.example.movplayv3.data.api

sealed class ApiResponse<out T> {
    class Success<T>(val data: T?) : ApiResponse<T>()
    class Failure<T>(val apiError: ApiError) : ApiResponse<T>()
    class Exception<T>(val exception: Throwable) : ApiResponse<T>()
}

fun <T> ApiResponse<T>.onSuccess(onResult: ApiResponse.Success<T>.() -> Unit): ApiResponse<T> {
    if (this is ApiResponse.Success)
        onResult(this)
    return this
}

fun <T> ApiResponse<T>.onFailure(onResult: ApiResponse.Failure<*>.() -> Unit): ApiResponse<T> {
    if (this is ApiResponse.Failure<*>)
        onResult(this)
    return this
}