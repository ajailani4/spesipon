package com.ajailani.spesipon.data.model.brand

import com.squareup.moshi.Json

data class BrandResponse(
    @Json(name = "status")
    val status: String,
    @Json(name = "data")
    val data: BrandDataResponse
)
