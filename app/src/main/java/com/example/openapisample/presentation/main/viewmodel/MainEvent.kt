package com.example.openapisample.presentation.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.openapisample.presentation.IClickModel
import com.example.openapisample.presentation.main.viewmodel.item.TweetItemViewModel

enum class MsgPriority {
    HIGH,
    LOW
}

interface IMainEvent {
    val searchResult: LiveData<List<TweetItemViewModel>>
    val noResult: LiveData<Unit>
    val message: LiveData<Pair<MsgPriority, String>>
    val click: LiveData<IClickModel>
}

class MainEvent : IMainEvent {
    val _searchResult = MutableLiveData<List<TweetItemViewModel>>()
    override val searchResult: LiveData<List<TweetItemViewModel>> = _searchResult

    val _noResult = MutableLiveData<Unit>()
    override val noResult: LiveData<Unit> = _noResult

    val _message = MutableLiveData<Pair<MsgPriority, String>>()
    override val message: LiveData<Pair<MsgPriority, String>> = _message

    val _click = MutableLiveData<IClickModel>()
    override val click: LiveData<IClickModel> = _click
}