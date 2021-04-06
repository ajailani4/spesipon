package com.ajailani.spesipon.data.model.response.phone

import com.squareup.moshi.Json

data class PhoneResponse(
    @Json(name = "status")
    val status: Boolean,
    @Json(name = "data")
    val data: PhoneDataResponse
)
