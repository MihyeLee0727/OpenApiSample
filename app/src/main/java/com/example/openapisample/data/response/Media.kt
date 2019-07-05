package com.example.openapisample.data.response

import com.google.gson.annotations.SerializedName

class Media(
    val id: Long,
    @SerializedName("media_url_https")
    val mediaUrlHttps: String
)