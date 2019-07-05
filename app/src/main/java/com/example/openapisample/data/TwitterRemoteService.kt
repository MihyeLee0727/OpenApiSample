package com.example.openapisample.data

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
        @Header("Authorization") token: String,
        @Header("Content-Type") contentType: String,
        @Body body: GrantTypeRequest
    ) : Deferred<Response<GetTokenResponse>>

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
    ) : Deferred<Response<SearchResponse>>

    /**
     * https://developer.twitter.com/en/docs/tweets/post-and-engage/api-reference/get-statuses-show-id
     */
    @GET("$VERSION/statuses/show.json")
    fun showDetailAsync(
        @Query("id") id: Long,
        @Query("include_entities") includeEntities: Boolean?
    ) : Deferred<Response<Statuse>>
}