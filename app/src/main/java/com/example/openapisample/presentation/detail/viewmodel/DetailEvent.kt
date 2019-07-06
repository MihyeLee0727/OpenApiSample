package com.example.openapisample.presentation.detail.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.openapisample.presentation.common.viewmodel.ICommonEvent
import com.example.openapisample.presentation.common.viewmodel.MsgPriority

interface IDetailEvent : ICommonEvent

class DetailEvent : IDetailEvent {
    val _message = MutableLiveData<Pair<MsgPriority, String>>()
    override val message: LiveData<Pair<MsgPriority, String>> = _message
}