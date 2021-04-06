package com.ajailani.spesipon.data.model.response.phone

import com.ajailani.spesipon.data.model.Brand
import com.ajailani.spesipon.data.model.Phone
import com.squareup.moshi.Json

data class PhoneSearchDataResponse(
    @field:Json(name = "page")
    val page: Int,
    @field:Json(name = "limit")
    val limit: Int,
    @field:Json(name = "last_page")
    val lastPage: Int,
    @field:Json(name = "phones")
    val phones: List<Phone>
)
