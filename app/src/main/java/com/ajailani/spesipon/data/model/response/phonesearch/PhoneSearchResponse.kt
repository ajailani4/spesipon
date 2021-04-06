package com.ajailani.spesipon.data.model.response.phonesearch

import com.squareup.moshi.Json

data class PhoneSearchResponse(
    @Json(name = "status")
    val status: Boolean,
    @Json(name = "data")
    val data: PhoneSearchDataResponse
)
