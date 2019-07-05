package com.example.openapisample.data.response

import com.google.gson.annotations.SerializedName

/**
 * https://developer.twitter.com/en/docs/tweets/data-dictionary/overview/tweet-object
 */
class Statuse(
    val id: Long,
    @SerializedName("created_at")
    val createdAt: String,
    val entities: Entities,
    @SerializedName("favorite_count")
    val favoriteCount: Int,
    @SerializedName("retweet_count")
    val retweetCount: Int,
    val source: String?,
    val text: String,
    val truncated: Boolean,
    val user: User
)