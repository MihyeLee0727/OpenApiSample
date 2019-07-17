package com.example.openapisample.presentation.common.interactor.mapper

import com.example.openapisample.data.response.SearchResponse
import com.example.openapisample.data.response.Statuse
import com.example.openapisample.presentation.common.interactor.model.Tweet
import com.example.openapisample.presentation.common.interactor.model.UserInfo

object TweetMapper {
    fun asTweetList(source: SearchResponse) = source.statuses?.map {
        asTweet(it)
    }.orEmpty()

    fun asTweet(source: Statuse) = Tweet(
        id = source.id,
        createdAt = source.createdAt,
        favoriteCount = source.favoriteCount,
        retweetCount = source.retweetCount,
        imageUrl = source.entities.media?.firstOrNull()?.mediaUrlHttps.orEmpty(),
        contents = source.text,
        user = UserInfo(
            name = source.user.name,
            id = source.user.id,
            profileImageUrl = source.user.profileImageUrlHttps.orEmpty(),
            profileBannerUrl = source.user.profileBannerUrl.orEmpty()
        )
    )
}