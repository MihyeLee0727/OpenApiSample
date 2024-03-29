package com.example.openapisample.data

import com.example.openapisample.data.remote.DataResponse
import com.example.openapisample.data.request.GetTokenRequest
import com.example.openapisample.data.request.SearchRequest
import com.example.openapisample.data.request.GetDetailRequest
import com.example.openapisample.data.response.GetTokenResponse
import com.example.openapisample.data.response.SearchResponse
import com.example.openapisample.data.response.Statuse

interface ITwitterRepository {
    suspend fun getToken(req: GetTokenRequest): DataResponse<GetTokenResponse>

    suspend fun search(req: SearchRequest): DataResponse<SearchResponse>

    suspend fun getDetail(req: GetDetailRequest): DataResponse<Statuse>
}