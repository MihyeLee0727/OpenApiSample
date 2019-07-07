package com.example.openapisample.data.remote

import com.example.openapisample.data.request.GrantTypeRequest
import com.example.openapisample.data.response.SearchResponse
import com.example.openapisample.data.response.Statuse
import com.example.openapisample.data.response.GetTokenResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.*

private const val VERSION = "1.1"

interface TwitterRemoteService {


    /**
     * https://developer.twitter.com/en/docs/basics/authentication/overview/application-only
     *
     * https://developer.twitter.com/en/docs/basics/authentication/api-reference/token
     */
    @POST("oauth2/token")
    fun getTokenAsync(
        @Header("Authorization") key: String,
        @Header("Content-Type") contentType: String = "application/x-www-form-urlencoded;charset=UTF-8",
        @Body body: GrantTypeRequest = GrantTypeRequest("client_credentials")
    ): Deferred<Response<GetTokenResponse>>

    /**
     * https://developer.twitter.com/en/docs/tweets/search/api-reference/get-search-tweets.html
     */
    @GET("$VERSION/search/tweets.json")
    fun searchAsync(
        @Query("q") query: String,
        @Query("count") count: Int?,
        @Query("since_id") sinceId: Long?,
        @Query("max_id") maxId: Long?,
        @Query("include_entities") includeEntities: Boolean?
    ): Deferred<Response<SearchResponse>>

    /**
     * https://developer.twitter.com/en/docs/tweets/post-and-engage/api-reference/get-statuses-show-id
     */
    @GET("$VERSION/statuses/show.json")
    fun getDetailAsync(
        @Query("id") id: Long,
        @Query("include_entities") includeEntities: Boolean?
    ): Deferred<Response<Statuse>>
}