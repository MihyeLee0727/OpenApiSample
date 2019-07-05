package com.example.openapisample.data.request

class SearchRequest(
    val query: String,
    val count: Int = 20,
    val sinceId: Long? = null,
    val maxId: Long? = null,
    val includeEntities: Boolean = true
)