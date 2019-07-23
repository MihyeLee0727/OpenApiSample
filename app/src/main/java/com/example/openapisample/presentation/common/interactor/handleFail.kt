package com.example.openapisample.presentation.common.interactor

import com.example.openapisample.data.remote.DataResponse
import com.example.openapisample.data.response.Error
import com.google.gson.GsonBuilder
import java.lang.Exception

fun <S : Any, T : Any> DataResponse<S>.handleFail(): DataResponse<T> {
    return when (this) {
        is DataResponse.Fail -> DataResponse.Fail.clone(this)
        else -> DataResponse.Empty()
    }
}

fun <T : Any> DataResponse.Fail<T>.getErrorMsg(): String {
    return try {
        if (errorMessage?.isNotEmpty() == true) {
            errorMessage
        } else {
            this.body?.let { source ->
                source.string().takeIf { it.isNotEmpty() }?.let { bodyString ->
                    val error = GsonBuilder().create().fromJson(bodyString, Error::class.java)
                    error.errors.joinToString { it.message }
                } ?: ""
            } ?: ""
        }
    } catch (ex: Exception) {
        ex.message.orEmpty()
    }
}