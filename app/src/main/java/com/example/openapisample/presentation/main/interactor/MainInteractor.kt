package com.example.openapisample.presentation.main.interactor

import com.example.openapisample.data.remote.DataResponse
import com.example.openapisample.data.ITwitterRepository
import com.example.openapisample.data.request.SearchRequest
import com.example.openapisample.data.response.SearchResponse
import com.example.openapisample.presentation.common.interactor.handleFail
import com.example.openapisample.presentation.common.interactor.mapper.TweetMapper
import com.example.openapisample.presentation.common.interactor.model.Tweet
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MainInteractor(
    private val repo: ITwitterRepository
) {
    suspend fun search(keyword: String, maxId: Long? = null): DataResponse<List<Tweet>> {
        return withContext(Dispatchers.IO) {
            val response = repo.search(
                req = SearchRequest(
                    query = keyword,
                    maxId = maxId
                )
            )

            response.getOrNull()?.let {
                DataResponse.Success(TweetMapper.asTweetList(it))
            } ?: let {
                response.handleFail<SearchResponse, List<Tweet>>()
            }
        }
    }
}