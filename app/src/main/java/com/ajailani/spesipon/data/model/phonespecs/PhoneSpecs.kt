package com.ajailani.spesipon.data.model.phonespecs

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class PhoneSpecs(
    @field:Json(name = "title")
    val title: String,
    @field:Json(name = "specs")
    val specs: HashMap<String, List<String>>
) : Parcelable
