package com.example.openapisample.presentation.detail.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.openapisample.data.DataResponse
import com.example.openapisample.presentation.common.interactor.model.Tweet
import com.example.openapisample.presentation.common.viewmodel.MsgPriority
import com.example.openapisample.presentation.detail.interactor.DetailInteractor
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailViewModel(
    private val id: Long,
    private val interactor: DetailInteractor
) : ViewModel() {

    val viewState = DetailViewState()
    private val _event = DetailEvent()
    val event: IDetailEvent = _event

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        _event._message.postValue(Pair(MsgPriority.HIGH, throwable.message.orEmpty()))
    }

    fun requestDetail() {
        viewModelScope.launch(Dispatchers.Default + coroutineExceptionHandler) {
            when (val result = interactor.getDetail(id)) {
                is DataResponse.Success -> {
                    mapData(result.data)
                }
                is DataResponse.Empty -> {
                    _event._message.postValue(Pair(MsgPriority.LOW, "검색 결과가 없습니다."))
                }
                is DataResponse.Fail -> {
                    _event._message.postValue(Pair(MsgPriority.HIGH, result.errorMessage.orEmpty()))
                }
            }
        }
    }

    private fun mapData(source: Tweet) {
        with (viewState) {
            contents = source.contents
            imageUrl = source.imageUrl
            userProfileBgImageUrl = source.user.profileBannerUrl
            userProfileImageUrl = source.user.profileImageUrl
            userName = source.user.name
            retweetCount = source.retweetCount
            favoriteCount = source.favoriteCount
        }
    }
}