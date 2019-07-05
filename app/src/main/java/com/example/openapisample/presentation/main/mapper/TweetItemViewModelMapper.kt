package com.example.openapisample.presentation.main.mapper

import com.example.openapisample.interactor.model.Tweet
import com.example.openapisample.presentation.IEventSender
import com.example.openapisample.presentation.main.viewmodel.item.TweetItemViewModel

object TweetItemViewModelMapper {
    fun asItemViewModel(source: List<Tweet>, eventSender: IEventSender) = source.map {
        TweetItemViewModel(
            data = it,
            imageUrl = it.imageUrl,
            contents = it.contents,
            userName = it.user.name,
            userProfileImageUrl = it.user.profileImageUrl,
            retweetCount = it.retweetCount,
            favoriteCount = it.favoriteCount,
            eventSender = eventSender
        )
    }
}