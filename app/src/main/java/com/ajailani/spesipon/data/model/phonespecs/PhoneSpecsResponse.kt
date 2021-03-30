package com.ajailani.spesipon.data.model.phonespecs

import com.squareup.moshi.Json

data class PhoneSpecsResponse(
    @Json(name = "status")
    val status: String,
    @Json(name = "data")
    val data: PhoneSpecsDataResponse
)
