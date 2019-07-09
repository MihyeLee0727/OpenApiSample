package com.example.openapisample.data.response

import com.google.gson.annotations.SerializedName

/**
 * https://developer.twitter.com/en/docs/tweets/data-dictionary/overview/user-object
 */
class User(
    val id: Long,
    @SerializedName("screen_name")
    val screenName: String,
    val name: String,
    val description: String?,
    val verified: Boolean,
    @SerializedName("followers_count")
    val followersCount: Int,
    @SerializedName("favourites_count")
    val favouritesCount: Int,
    @SerializedName("statuses_count")
    val statusesCount: Int,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("profile_banner_url")
    val profileBannerUrl: String?,
    @SerializedName("profile_image_url_https")
    val profileImageUrlHttps: String?,
    @SerializedName("default_profile")
    val defaultprofile: Boolean,
    @SerializedName("default_profile_image")
    val defaultProfileImage: Boolean
)