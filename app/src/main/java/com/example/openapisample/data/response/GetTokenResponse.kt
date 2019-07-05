package com.example.openapisample.data.response

import com.google.gson.annotations.SerializedName

class GetTokenResponse(
    @SerializedName("token_type")
    val tokenType: String,
    @SerializedName("access_token")
    val accessToken: String
)