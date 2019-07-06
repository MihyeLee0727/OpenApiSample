package com.example.openapisample.presentation.common.interactor

import com.example.openapisample.data.remote.DataResponse

fun <S : Any, T : Any> DataResponse<S>.handleFail(): DataResponse<T> {
    return when (this) {
        is DataResponse.Fail -> DataResponse.Fail.clone(this)
        else -> DataResponse.Empty()
    }
}