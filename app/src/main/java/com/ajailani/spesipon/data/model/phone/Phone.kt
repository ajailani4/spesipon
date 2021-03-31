package com.ajailani.spesipon.data.model.phone

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class Phone(
    @field:Json(name = "phone_name")
    val name: String,
    @field:Json(name = "phone_name_slug")
    val slug: String,
    @field:Json(name = "brand")
    val brandName: String,
    @field:Json(name = "phone_img_url")
    val image: String
) : Parcelable
