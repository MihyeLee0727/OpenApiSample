package com.example.openapisample.presentation.intro.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.openapisample.presentation.common.viewmodel.ICommonEvent
import com.example.openapisample.presentation.common.viewmodel.MsgPriority

interface IIntroEvent : ICommonEvent {
    val processDone: LiveData<Unit>
}

class IntroEvent : IIntroEvent {
    val _message = MutableLiveData<Pair<MsgPriority, String>>()
    override val message: LiveData<Pair<MsgPriority, String>> = _message

    val _processDone = MutableLiveData<Unit>()
    override val processDone: LiveData<Unit> = _processDone
}