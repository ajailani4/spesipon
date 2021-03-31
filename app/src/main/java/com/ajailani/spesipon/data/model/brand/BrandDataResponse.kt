package com.ajailani.spesipon.data.model.brand

import com.squareup.moshi.Json

data class BrandDataResponse(
    @field:Json(name = "page")
    val page: Int,
    @field:Json(name = "limit")
    val limit: Int,
    @field:Json(name = "last_page")
    val lastPage: Int,
    @field:Json(name = "brands")
    val brands: List<Brand>
)
