package com.ajailani.spesipon.data.model.brand

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class Brand(
    @Json(name = "brand")
    val name: String,
    @Json(name = "brand_slug")
    val slug: String,
    @Json(name = "count_devices")
    val countDevices: Int
) : Parcelable