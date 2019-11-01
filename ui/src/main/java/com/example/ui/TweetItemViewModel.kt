package com.example.ui

data class TweetItemViewModel(
    val id: Long,
    val imageUrl: String,
    val contents: String,
    val userProfileImageUrl: String,
    val userName: String,
    val retweetCount: Int,
    val favoriteCount: Int,
    val createdAt: String,
    private val eventSender: IEventSender
) {
    val hasImage = imageUrl.isNotBlank()

    fun click() {
        eventSender.click(TweetItemClickModel(id = id))
    }
}

class TweetItemClickModel(val id: Long) : IClickModel

interface IEventSender {
    fun click(clickModel: IClickModel)
}

interface IClickModel