package com.example.openapisample.presentation.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.openapisample.data.DataResponse
import com.example.openapisample.presentation.main.interactor.MainInteractor
import com.example.openapisample.presentation.IClickModel
import com.example.openapisample.presentation.IEventSender
import com.example.openapisample.presentation.common.viewmodel.MsgPriority
import com.example.openapisample.presentation.main.mapper.TweetItemViewModelMapper
import kotlinx.coroutines.*

class MainViewModel(
    private val interactor: MainInteractor
) : ViewModel(), IEventSender {

    val viewState = MainViewState()
    private val _event = MainEvent()
    val event: IMainEvent = _event

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        _event._message.postValue(Pair(MsgPriority.HIGH, throwable.message.orEmpty()))
    }

    fun search() {
        viewModelScope.launch(Dispatchers.Default + coroutineExceptionHandler) {
            when (val result = interactor.search(viewState.searchKeyword)) {
                is DataResponse.Success -> {
                    _event._searchResult.postValue(
                        TweetItemViewModelMapper.asItemViewModel(
                            source = result.data,
                            eventSender = this@MainViewModel
                        )
                    )
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

    override fun click(clickModel: IClickModel) {
        _event._click.value = clickModel
    }
}