package com.example.movplayv3.data.remotesatasource.api

data class ApiError(
    val errorCode: Int,
    val statusCode: Int?,
    val statusMessage: String?
)