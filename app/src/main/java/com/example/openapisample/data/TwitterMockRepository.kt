package com.example.openapisample.data

import com.example.openapisample.data.mock.mockGetToken
import com.example.openapisample.data.mock.mockSearch
import com.example.openapisample.data.mock.mockDetail
import com.example.openapisample.data.remote.DataResponse
import com.example.openapisample.data.request.GetTokenRequest
import com.example.openapisample.data.request.SearchRequest
import com.example.openapisample.data.request.GetDetailRequest
import com.example.openapisample.data.response.GetTokenResponse
import com.example.openapisample.data.response.SearchResponse
import com.example.openapisample.data.response.Statuse
import com.google.gson.GsonBuilder

class TwitterMockRepository : ITwitterRepository {
    private val gson = GsonBuilder().create()

    override suspend fun getToken(req: GetTokenRequest): DataResponse<GetTokenResponse> {
        return DataResponse.Success(
            data = gson.fromJson(
                mockGetToken,
                GetTokenResponse::class.java
            )
        )
    }

    override suspend fun search(req: SearchRequest): DataResponse<SearchResponse> {
        return DataResponse.Success(
            data = gson.fromJson(
                mockSearch,
                SearchResponse::class.java
            )
        )
    }

    override suspend fun getDetail(req: GetDetailRequest): DataResponse<Statuse> {
        return DataResponse.Success(
            data = gson.fromJson(
                mockDetail,
                Statuse::class.java
            )
        )
    }
}