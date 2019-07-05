package com.example.openapisample.presentation.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.openapisample.data.DataResponse
import com.example.openapisample.interactor.MainInteractor
import com.example.openapisample.presentation.IClickModel
import com.example.openapisample.presentation.IEventSender
import com.example.openapisample.presentation.main.mapper.TweetItemViewModelMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

class MainViewModel(
    private val interactor: MainInteractor
) : ViewModel(), IEventSender {

    val viewState = MainViewState()
    private val _event = MainEvent()
    val event: IMainEvent = _event

    fun search() {
        viewModelScope.launch(Dispatchers.Default) {
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