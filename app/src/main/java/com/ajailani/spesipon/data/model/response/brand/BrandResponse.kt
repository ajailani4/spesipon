package com.ajailani.spesipon.data.model.response.brand

import com.squareup.moshi.Json

data class BrandResponse(
    @Json(name = "status")
    val status: Boolean,
    @Json(name = "data")
    val data: BrandDataResponse
)
