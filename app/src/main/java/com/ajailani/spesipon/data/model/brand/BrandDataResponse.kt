package com.ajailani.spesipon.data.model.brand

import com.squareup.moshi.Json

data class BrandDataResponse(
    @Json(name = "page")
    val page: Int,
    @Json(name = "limit")
    val limit: Int,
    @Json(name = "last_page")
    val lastPage: Int,
    @Json(name = "brands")
    val brands: List<Brand>
)
