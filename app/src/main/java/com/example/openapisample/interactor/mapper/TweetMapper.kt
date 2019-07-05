package com.example.openapisample.interactor.mapper

import com.example.openapisample.data.response.SearchResponse
import com.example.openapisample.interactor.model.Tweet
import com.example.openapisample.interactor.model.UserInfo

object TweetMapper {
    fun asTweet(source: SearchResponse) = source.statuses?.map {
        Tweet(
            id = it.id,
            favoriteCount = it.favoriteCount,
            retweetCount = it.retweetCount,
            imageUrl = it.entities.media?.firstOrNull()?.mediaUrlHttps.orEmpty(),
            contents = it.text,
            user = UserInfo(
                name = it.user.name,
                id = it.user.id,
                profileImageUrl = it.user.profileImageUrlHttps
            )
        )
    }.orEmpty()
}