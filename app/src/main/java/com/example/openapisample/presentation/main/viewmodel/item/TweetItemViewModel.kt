package com.example.openapisample.presentation.main.viewmodel.item

import com.example.openapisample.interactor.model.Tweet
import com.example.openapisample.presentation.IClickModel
import com.example.openapisample.presentation.IEventSender

class TweetItemViewModel(
    val data: Tweet,
    val imageUrl: String,
    val contents: String,
    val userProfileImageUrl: String,
    val userName: String,
    val retweetCount: Int,
    val favoriteCount: Int,
    private val eventSender: IEventSender
) {
    val hasImage = imageUrl.isNotBlank()

    fun click() {
        eventSender.click(TweetItemClickModel(id = data.id))
    }
}

class TweetItemClickModel(val id: Long) : IClickModel