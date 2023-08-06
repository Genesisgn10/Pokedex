package com.example.network

import retrofit2.Response
import java.lang.Exception
import java.net.HttpURLConnection

sealed class Result<out Response> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val exception: Int) : Result<Nothing>()
}

fun <R : Any > Response<R>.parseResponse(): Result<R> {
    if (isSuccessful){
        val body = body()

        if (body != null){
            return Result.Success(body)
        }
    } else {
        return Result.Error(exception = code())
    }

    return Result.Error(HttpURLConnection.HTTP_INTERNAL_ERROR)
}