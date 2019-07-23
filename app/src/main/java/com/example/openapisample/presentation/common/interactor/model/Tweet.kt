package com.example.openapisample.presentation.common.interactor.model

import java.util.*

class Tweet(
    val id: Long,
    val imageUrl: String,
    val favoriteCount: Int,
    val retweetCount: Int,
    val contents: String,
    val user: UserInfo,
    val createdAt: Date
)