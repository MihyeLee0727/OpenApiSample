package com.example.openapisample.data

import com.example.openapisample.data.remote.AuthDataSource
import com.example.openapisample.data.remote.DataResponse
import com.example.openapisample.data.remote.TweetDataSource
import com.example.openapisample.data.request.GetTokenRequest
import com.example.openapisample.data.request.SearchRequest
import com.example.openapisample.data.request.GetDetailRequest
import com.example.openapisample.data.response.GetTokenResponse
import com.example.openapisample.data.response.SearchResponse
import com.example.openapisample.data.response.Statuse

class TwitterRepository(
    private val authDataSource: AuthDataSource,
    private val tweetDataSource: TweetDataSource
) : ITwitterRepository {
    override suspend fun getToken(req: GetTokenRequest): DataResponse<GetTokenResponse> {
        return authDataSource.getToken(req)
    }

    override suspend fun search(req: SearchRequest): DataResponse<SearchResponse> {
        return tweetDataSource.search(req)
    }

    override suspend fun getDetail(req: GetDetailRequest): DataResponse<Statuse> {
        return tweetDataSource.getDetail(req)
    }
}