package com.example.openapisample.data

import com.example.openapisample.data.request.SearchRequest
import com.example.openapisample.data.request.GetDetailRequest
import com.example.openapisample.data.response.SearchResponse
import com.example.openapisample.data.response.Statuse
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class TweetDataSource {

    private val service by lazy {
        val authInterceptor = Interceptor {
            val newRequest = it.request()
                .newBuilder()
                .addHeader("Authorization", "") // TODO apply token
                .build()
            it.proceed(newRequest)
        }
        val client = OkHttpClient().newBuilder()
            .addInterceptor(authInterceptor)
            .build()

        Retrofit.Builder()
            .baseUrl("https://api.twitter.com/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
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