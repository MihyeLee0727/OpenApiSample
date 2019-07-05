package com.example.openapisample.data.request

class GetTokenRequest(
    val token: String,
    val contentType: String = "application/x-www-form-urlencoded;charset=UTF-8",
    val grantType: GrantTypeRequest = GrantTypeRequest()
)

class GrantTypeRequest(
    val grantType: String = "client_credentials"
)