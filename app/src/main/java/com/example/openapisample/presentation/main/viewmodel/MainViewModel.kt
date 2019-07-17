package com.example.openapisample.presentation.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.openapisample.data.remote.DataResponse
import com.example.openapisample.presentation.main.interactor.MainInteractor
import com.example.openapisample.presentation.IClickModel
import com.example.openapisample.presentation.IEventSender
import com.example.openapisample.presentation.common.interactor.getErrorMsg
import com.example.openapisample.presentation.common.viewmodel.MsgPriority
import com.example.openapisample.presentation.main.mapper.TweetItemViewModelMapper
import kotlinx.coroutines.*

class MainViewModel(
    private val interactor: MainInteractor
) : ViewModel(), IEventSender {

    val viewState = MainViewState()
    private val _event = MainEvent()
    val event: IMainEvent = _event

    private var readMoreJob: Job? = null
    private var maxId: Long? = null

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        _event._message.value = Pair(MsgPriority.HIGH, throwable.message.orEmpty())
    }

    fun search() {
        viewModelScope.launch(coroutineExceptionHandler) {
            when (val result = interactor.search(viewState.searchKeyword)) {
                is DataResponse.Success -> {
                    maxId = result.data.last().id
                    _event._searchResult.value = TweetItemViewModelMapper.asItemViewModel(
                        source = result.data,
                        eventSender = this@MainViewModel
                    )
                }
                is DataResponse.Empty -> {
                    _event._message.value = Pair(MsgPriority.LOW, "검색 결과가 없습니다.")
                }
                is DataResponse.Fail -> {
                    _event._message.value = Pair(MsgPriority.HIGH, result.getErrorMsg())
                }
            }
        }
    }

    // https://developer.twitter.com/en/docs/tweets/timelines/guides/working-with-timelines.html
    fun readMore() {
        if (readMoreJob != null) return

        readMoreJob = viewModelScope.launch(coroutineExceptionHandler) {
            when (val result =
                interactor.search(keyword = viewState.searchKeyword, maxId = maxId?.minus(1))) {
                is DataResponse.Success -> {
                    maxId = result.data.last().id
                    _event._readMoreResult.value = TweetItemViewModelMapper.asItemViewModel(
                        source = result.data,
                        eventSender = this@MainViewModel
                    )
                }
                is DataResponse.Fail -> {
                    _event._message.value = Pair(MsgPriority.HIGH, result.getErrorMsg())
                }
            }
            readMoreJob = null
        }
    }

    override fun click(clickModel: IClickModel) {
        _event._click.value = clickModel
    }
}