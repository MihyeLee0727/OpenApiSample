package com.example.openapisample.interactor.model

class Tweet(
    val id: Long,
    val imageUrl: String,
    val favoriteCount: Int,
    val retweetCount: Int,
    val contents: String,
    val user: UserInfo
)