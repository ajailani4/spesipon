package com.ajailani.spesipon.data.api

import com.ajailani.spesipon.data.model.response.brand.BrandResponse
import com.ajailani.spesipon.data.model.response.phone.PhoneResponse
import com.ajailani.spesipon.data.model.response.phonesearch.PhoneSearchResponse
import com.ajailani.spesipon.data.model.response.phonespecs.PhoneSpecsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("brands")
    suspend fun getBrands(
        @Query("page") page: Int,
        @Query("limit") limit: Int
    ): Response<BrandResponse>

    @GET("brands/{brandSlug}")
    suspend fun getPhones(
        @Path("brandSlug") brandSlug: String,
        @Query("page") page: Int,
        @Query("limit") limit: Int
    ): Response<PhoneResponse>

    @GET("brands/{brandSlug}?limit=5")
    suspend fun getPhonesHome(
        @Path("brandSlug") brandSlug: String
    ): Response<PhoneResponse>

    @GET("brands/{brandSlug}/{phoneSlug}")
    suspend fun getPhoneSpecs(
        @Path("brandSlug") brandSlug: String,
        @Path("phoneSlug") phoneSlug: String
    ): Response<PhoneSpecsResponse>

    @GET("search")
    suspend fun getPhoneSearch(
        @Query("query") query: String,
        @Query("page") page: Int,
        @Query("limit") limit: Int
    ): Response<PhoneSearchResponse>
}