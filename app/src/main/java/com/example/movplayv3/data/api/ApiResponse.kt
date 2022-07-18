package com.example.movplayv3.data.api

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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

fun <T> ApiResponse<T>.onException(onResult: ApiResponse.Exception<*>.() -> Unit): ApiResponse<T> {
    if (this is ApiResponse.Exception<*>)
        onResult(this)
    return this
}

inline fun <T> Call<T>.request(crossinline onResult: (response: ApiResponse<T>) -> Unit) {
    enqueue(object : Callback<T> {
        override fun onResponse(call: Call<T>, response: Response<T>) {
            if (response.isSuccessful) {
                onResult(ApiResponse.Success(response.body()))
                return
            }
        }

        override fun onFailure(call: Call<T>, t: Throwable) {
            TODO("Not yet implemented")
        }

    })
}