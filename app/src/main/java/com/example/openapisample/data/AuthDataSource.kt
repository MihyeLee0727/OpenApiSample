package com.example.openapisample.data

import com.example.openapisample.data.request.GetTokenRequest
import com.example.openapisample.data.response.GetTokenResponse
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AuthDataSource {

    private val service by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.twitter.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
            .create(TwitterRemoteService::class.java)
    }

    suspend fun getToken(req: GetTokenRequest): DataResponse<GetTokenResponse> {
        return service.getTokenAsync(
            key = req.key,
            contentType = req.contentType,
            body = req.grantType
        ).awaitData()
    }
}