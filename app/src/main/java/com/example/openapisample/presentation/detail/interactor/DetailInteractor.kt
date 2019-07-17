package com.example.openapisample.presentation.detail.interactor

import com.example.openapisample.data.remote.DataResponse
import com.example.openapisample.data.ITwitterRepository
import com.example.openapisample.data.request.GetDetailRequest
import com.example.openapisample.data.response.Statuse
import com.example.openapisample.presentation.common.interactor.handleFail
import com.example.openapisample.presentation.common.interactor.mapper.TweetMapper
import com.example.openapisample.presentation.common.interactor.model.Tweet
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DetailInteractor(
    private val repo: ITwitterRepository
) {
    suspend fun getDetail(id: Long): DataResponse<Tweet> {
        return withContext(Dispatchers.IO) {
            val response = repo.getDetail(GetDetailRequest(id))

            response.getOrNull()?.let {
                DataResponse.Success(TweetMapper.asTweet(it))
            } ?: let {
                response.handleFail<Statuse, Tweet>()
            }
        }
    }
}