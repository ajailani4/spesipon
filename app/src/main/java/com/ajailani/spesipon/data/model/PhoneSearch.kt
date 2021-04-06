package com.ajailani.spesipon.data.model

import com.squareup.moshi.Json

data class PhoneSearch(
    @field:Json(name = "phone_name")
    val name: String,
    @field:Json(name = "phone_name_slug")
    val slug: String,
    @field:Json(name = "brand")
    val brandName: String,
    @field:Json(name = "brand_slug")
    val brandSlug: String,
    @field:Json(name = "phone_img_url")
    val image: String
)
