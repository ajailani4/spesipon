package com.ajailani.spesipon.data.api

import com.ajailani.spesipon.data.model.brand.BrandResponse
import com.ajailani.spesipon.data.model.phone.PhoneResponse
import com.ajailani.spesipon.data.model.phonespecs.PhoneSpecsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("brands")
    suspend fun getBrands(
        @Query("page") page: Int,
    ): Response<BrandResponse>

    @GET("brands/{brand}")
    suspend fun getPhones(
        @Path("brand") brand: String,
        @Query("page") page: Int
    ): Response<PhoneResponse>

    @GET("brands/{brand}/{phone}")
    suspend fun getPhoneSpecs(
        @Path("brand") brand: String,
        @Path("phone") phone: String
    ): Response<PhoneSpecsResponse>
}