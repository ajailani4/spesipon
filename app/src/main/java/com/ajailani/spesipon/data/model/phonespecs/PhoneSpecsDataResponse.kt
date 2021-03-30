package com.ajailani.spesipon.data.model.phonespecs

import com.squareup.moshi.Json

data class PhoneSpecsDataResponse(
    @Json(name = "brand")
    val brandName: String,
    @Json(name = "phone_name")
    val name: String,
    @Json(name = "phone_name_slug")
    val slug: String,
    @Json(name = "phone_img_url")
    val image: String,
    @Json(name = "specifications")
    val specifications: List<PhoneSpecs>
)
