package com.ajailani.spesipon.data.model.brand

import android.os.Parcelable
import com.ajailani.spesipon.data.model.phone.Phone
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
data class Brand(
    @field:Json(name = "brand")
    val name: String,
    @field:Json(name = "brand_slug")
    val slug: String,
    @field:Json(name = "count_devices")
    val countDevices: Int,
    var phonesList: List<Phone>?
) : Parcelable