package com.example.openapisample.interactor.mapper

import com.example.openapisample.data.response.SearchResponse
import com.example.openapisample.data.response.Statuse
import com.example.openapisample.interactor.model.Tweet
import com.example.openapisample.interactor.model.UserInfo

object TweetMapper {
    fun asTweetList(source: SearchResponse) = source.statuses?.map {
        asTweet(it)
    }.orEmpty()

    fun asTweet(source: Statuse) = Tweet(
        id = source.id,
        favoriteCount = source.favoriteCount,
        retweetCount = source.retweetCount,
        imageUrl = source.entities.media?.firstOrNull()?.mediaUrlHttps.orEmpty(),
        contents = source.text,
        user = UserInfo(
            name = source.user.name,
            id = source.user.id,
            profileImageUrl = source.user.profileImageUrlHttps,
            profileBannerUrl = source.user.profileBannerUrl
        )
    )
}