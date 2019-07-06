package com.example.openapisample.presentation.main.interactor

import com.example.openapisample.data.DataResponse
import com.example.openapisample.data.ITwitterRepository
import com.example.openapisample.data.request.SearchRequest
import com.example.openapisample.data.response.SearchResponse
import com.example.openapisample.interactor.handleFail
import com.example.openapisample.interactor.mapper.TweetMapper
import com.example.openapisample.interactor.model.Tweet

class MainInteractor(
    private val repo: ITwitterRepository
) {
    suspend fun search(keyword: String): DataResponse<List<Tweet>> {
        val response = repo.search(
            req = SearchRequest(
                query = keyword
            )
        )
        return response.getOrNull()?.let {
            DataResponse.Success(TweetMapper.asTweet(it))
        } ?: let {
            response.handleFail<List<Tweet>, SearchResponse>()
        }
    }
}