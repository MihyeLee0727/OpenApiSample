package com.example.openapisample.data

import kotlinx.coroutines.Deferred
import okhttp3.ResponseBody
import retrofit2.Response
import java.net.HttpURLConnection

sealed class DataResponse<T : Any> {
    fun hasData() = this is Success
    fun isSuccess() = this is Success || this is Empty
    fun isFail() = this is Fail

    fun getOrNull(): T? {
        return if (this is Success) {
            data
        } else null
    }

    class Success<T : Any>(val data: T) : DataResponse<T>()

    class Empty<T : Any> : DataResponse<T>()

    class Fail<T : Any>(
        val errorCode: Int? = null,
        val errorMessage: String? = null,
        val ex: Throwable? = null,
        val body: ResponseBody? = null
    ) : DataResponse<T>() {
        companion object {
            fun <T : Any, S : Any> clone(source: Fail<S>): Fail<T> {
                return Fail(
                    errorCode = source.errorCode,
                    errorMessage = source.errorMessage,
                    ex = source.ex,
                    body = source.body
                )
            }
        }
    }
}

suspend fun <T : Any> Deferred<Response<T>>.awaitData(): DataResponse<T> {
    val response = await()
    return if (response.isSuccessful) {
        when (response.code()) {
            HttpURLConnection.HTTP_NO_CONTENT -> DataResponse.Empty()
            else -> response.body()?.let { DataResponse.Success<T>(it) }
                ?: let { DataResponse.Empty<T>() }
        }
    } else {
        response.errorBody()?.let {
            DataResponse.Fail<T>(
                errorCode = response.code(),
                errorMessage = response.message(),
                body = it
            )
        } ?: let {
            DataResponse.Fail<T>()
        }
    }
}