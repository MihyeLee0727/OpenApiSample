package com.example.openapisample.data.response

import com.google.gson.annotations.SerializedName

class SearchMetaData(
    @SerializedName("completed_in")
    val completedIn: Double?,
    val count: Int?,
    @SerializedName("max_id")
    val maxId: Long?,
    val query: String?,
    @SerializedName("since_id")
    val sinceId: Int?
)