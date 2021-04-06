package com.ajailani.spesipon.data.model.response.phonespecs

import com.ajailani.spesipon.data.model.PhoneSpecs
import com.squareup.moshi.Json

data class PhoneSpecsDataResponse(
    @field:Json(name = "brand")
    val brandName: String,
    @field:Json(name = "phone_name")
    val name: String,
    @field:Json(name = "phone_name_slug")
    val slug: String,
    @field:Json(name = "phone_img_url")
    val image: String,
    @field:Json(name = "specifications")
    val specifications: List<PhoneSpecs>
)
