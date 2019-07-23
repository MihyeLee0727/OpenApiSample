package com.example.openapisample.data.response

class Error(
    val errors: List<ErrorBody>
)

class ErrorBody(
    val code: Int,
    val message: String
)