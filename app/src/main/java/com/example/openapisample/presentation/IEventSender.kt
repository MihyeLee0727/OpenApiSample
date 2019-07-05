package com.example.openapisample.presentation

interface IEventSender {
    fun click(clickModel: IClickModel)
}

interface IClickModel