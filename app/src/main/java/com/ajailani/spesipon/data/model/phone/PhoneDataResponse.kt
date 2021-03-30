package com.ajailani.spesipon.data.model.phone

import com.squareup.moshi.Json

data class PhoneDataResponse(
    @Json(name = "page")
    val page: Int,
    @Json(name = "limit")
    val limit: Int,
    @Json(name = "last_page")
    val lastPage: Int,
    @Json(name = "phones")
    val phones: List<Phone>
)
