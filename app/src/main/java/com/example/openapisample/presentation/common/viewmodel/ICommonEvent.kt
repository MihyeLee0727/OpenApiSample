package com.example.openapisample.presentation.common.viewmodel

import androidx.lifecycle.LiveData

enum class MsgPriority {
    HIGH,
    LOW
}

interface ICommonEvent {
    val message: LiveData<Pair<MsgPriority, String>>
}