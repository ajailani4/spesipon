package com.ajailani.spesipon.data.model

import com.squareup.moshi.Json

data class PhoneSpecs(
    @field:Json(name = "title")
    val title: String,
    @field:Json(name = "specs")
    val specs: List<PhoneSubSpecs>
)
