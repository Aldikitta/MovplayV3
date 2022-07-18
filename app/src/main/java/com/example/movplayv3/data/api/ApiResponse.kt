package com.example.movplayv3.data.api

sealed class ApiResponse<out T> {
    class Success<T>(val data: T?) : ApiResponse<T>()
    class Failure<T>(val apiError: ApiError) : ApiResponse<T>()
    class Exception<T>(val exception: Throwable) : ApiResponse<T>()
}