package com.example.openapisample.presentation.intro.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.openapisample.data.remote.DataResponse
import com.example.openapisample.presentation.common.interactor.RemoteTokenManager
import com.example.openapisample.presentation.common.interactor.model.Token
import com.example.openapisample.presentation.common.viewmodel.MsgPriority
import com.example.openapisample.presentation.intro.interactor.IntroInteractor
import kotlinx.coroutines.*

class IntroViewModel(
    private val interactor: IntroInteractor,
    private val tokenManager: RemoteTokenManager
) : ViewModel() {

    private val _event = IntroEvent()
    val event: IIntroEvent = _event

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        _event._message.value = Pair(MsgPriority.HIGH, throwable.message.orEmpty())
    }

    fun requestToken() {
        viewModelScope.launch(coroutineExceptionHandler) {
            var result: DataResponse<Token>? = null
            val tokenJob = launch {
                result = interactor.getToken(tokenManager.getBaseKey())
            }
            val delayJob = launch {
                delay(1000)
            }
            joinAll(tokenJob, delayJob)
            handleTokenResult(result!!)
        }
    }

    private fun handleTokenResult(result: DataResponse<Token>) {
        when (result) {
            is DataResponse.Success -> {
                saveToken(result.data.token)
                _event._processDone.value = Unit
            }
            else -> {
                _event._message.value = Pair(MsgPriority.HIGH, "인증에 실패했습니다")
            }
        }
    }

    private fun saveToken(token: String) {
        tokenManager.setToken(token)
    }
}