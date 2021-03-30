package com.ajailani.spesipon.data.model.phone

import com.squareup.moshi.Json

data class PhoneResponse(
    @Json(name = "status")
    val status: String,
    @Json(name = "data")
    val data: PhoneDataResponse
)
