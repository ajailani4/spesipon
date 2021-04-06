package com.ajailani.spesipon.data.model.response.phonespecs

import com.squareup.moshi.Json

data class PhoneSpecsResponse(
    @Json(name = "status")
    val status: Boolean,
    @Json(name = "data")
    val data: PhoneSpecsDataResponse
)
