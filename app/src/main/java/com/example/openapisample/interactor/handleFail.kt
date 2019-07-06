package com.example.openapisample.interactor

import com.example.openapisample.data.DataResponse

fun <T : Any, S : Any> DataResponse<S>.handleFail(): DataResponse<T> {
    return when (this) {
        is DataResponse.Fail -> DataResponse.Fail.clone(this)
        else -> DataResponse.Empty()
    }
}