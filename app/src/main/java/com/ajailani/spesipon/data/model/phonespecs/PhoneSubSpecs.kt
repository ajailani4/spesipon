package com.ajailani.spesipon.data.model.phonespecs

import com.squareup.moshi.Json

data class PhoneSubSpecs(
    @field:Json(name = "key")
    val subTitle: String,
    @field:Json(name = "val")
    val subSpecs: List<String>
)