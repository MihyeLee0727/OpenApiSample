package com.example.openapisample.presentation.intro.interactor

import com.example.openapisample.data.remote.DataResponse
import com.example.openapisample.data.ITwitterRepository
import com.example.openapisample.data.request.GetTokenRequest
import com.example.openapisample.data.response.GetTokenResponse
import com.example.openapisample.presentation.common.interactor.handleFail
import com.example.openapisample.presentation.common.interactor.model.Token

class IntroInteractor(
    private val repo: ITwitterRepository
) {
    suspend fun getToken(key: String): DataResponse<Token> {
        val response = repo.getToken(GetTokenRequest(key))

        return response.getOrNull()?.let {
            DataResponse.Success(data = Token("${it.tokenType} ${it.accessToken}"))
        } ?: let {
            response.handleFail<GetTokenResponse, Token>()
        }
    }
}