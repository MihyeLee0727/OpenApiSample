package com.example.openapisample.data.remote

import com.example.openapisample.data.request.SearchRequest
import com.example.openapisample.data.request.GetDetailRequest
import com.example.openapisample.data.response.SearchResponse
import com.example.openapisample.data.response.Statuse
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

class TweetDataSource(
    private val tokenManager: IRemoteTokenManager
) {

    private val service by lazy {
        val authInterceptor = Interceptor {
            val newRequest = it.request()
                .newBuilder()
                .addHeader("Authorization", tokenManager.getToken())
                .build()
            it.proceed(newRequest)
        }
        val client = OkHttpClient().newBuilder()
            .addInterceptor(authInterceptor)
            .build()

        val gsonBuilder =
            GsonBuilder().registerTypeAdapter(Date::class.java, GsonUtcDateFormatAdapter())

        Retrofit.Builder()
            .baseUrl("https://api.twitter.com/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gsonBuilder.create()))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
            .create(TwitterRemoteService::class.java)
    }

    suspend fun search(req: SearchRequest): DataResponse<SearchResponse> {
        return service.searchAsync(
            query = req.query,
            sinceId = req.sinceId,
            maxId = req.maxId,
            count = req.count,
            includeEntities = req.includeEntities
        ).awaitData()
    }

    suspend fun getDetail(req: GetDetailRequest): DataResponse<Statuse> {
        return service.getDetailAsync(
            id = req.id,
            includeEntities = req.includeEntities
        ).awaitData()
    }
}