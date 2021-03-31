package com.ajailani.spesipon.data.model.phone

import com.squareup.moshi.Json

data class PhoneDataResponse(
    @field:Json(name = "page")
    val page: Int,
    @field:Json(name = "limit")
    val limit: Int,
    @field:Json(name = "last_page")
    val lastPage: Int,
    @field:Json(name = "phones")
    val phones: List<Phone>
)
