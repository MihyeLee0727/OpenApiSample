package com.example.openapisample.data.request

class GetTokenRequest(
    val key: String
)

class GrantTypeRequest(
    val grantType: String = "client_credentials"
)