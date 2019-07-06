package com.example.openapisample.presentation.detail.interactor

import com.example.openapisample.data.DataResponse
import com.example.openapisample.data.ITwitterRepository
import com.example.openapisample.data.request.GetDetailRequest
import com.example.openapisample.data.response.Statuse
import com.example.openapisample.presentation.common.interactor.handleFail
import com.example.openapisample.presentation.common.interactor.mapper.TweetMapper
import com.example.openapisample.presentation.common.interactor.model.Tweet

class DetailInteractor(
    private val repo: ITwitterRepository
) {
    suspend fun getDetail(id: Long) : DataResponse<Tweet> {
        val response = repo.getDetail(GetDetailRequest(id))

        return response.getOrNull()?.let {
            DataResponse.Success(TweetMapper.asTweet(it))
        } ?: let {
            response.handleFail<Statuse, Tweet>()
        }
    }
}