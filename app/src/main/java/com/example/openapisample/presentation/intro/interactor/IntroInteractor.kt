package com.example.openapisample.presentation.intro.interactor

import com.example.openapisample.data.remote.DataResponse
import com.example.openapisample.data.ITwitterRepository
import com.example.openapisample.data.request.GetTokenRequest
import com.example.openapisample.data.response.GetTokenResponse
import com.example.openapisample.presentation.common.interactor.handleFail
import com.example.openapisample.presentation.common.interactor.model.Token
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class IntroInteractor(
    private val repo: ITwitterRepository
) {
    suspend fun getToken(key: String): DataResponse<Token> {
        return withContext(Dispatchers.IO) {
            val response = repo.getToken(GetTokenRequest(key))

            response.getOrNull()?.let {
                DataResponse.Success(data = Token("${it.tokenType} ${it.accessToken}"))
            } ?: let {
                response.handleFail<GetTokenResponse, Token>()
            }
        }
    }
}