package com.example.openapisample.data.response

import com.google.gson.annotations.SerializedName

class SearchResponse(
    @SerializedName("search_metadata")
    val searchMetaData: SearchMetaData?,
    val statuses: List<Statuse>?
)