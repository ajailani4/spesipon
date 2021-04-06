package com.ajailani.spesipon.data.api

import javax.inject.Inject

class ApiHelper @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun getBrands(page: Int, limit: Int) =
        apiService.getBrands(page, limit)

    suspend fun getPhones(brandSlug: String, page: Int, limit: Int) =
        apiService.getPhones(brandSlug, page, limit)

    suspend fun getPhonesHome(brandSlug: String) =
        apiService.getPhonesHome(brandSlug)

    suspend fun getPhoneSpecs(brandSlug: String, phoneSlug: String) =
        apiService.getPhoneSpecs(brandSlug, phoneSlug)

    suspend fun getPhoneSearch(phoneQuery: String, page: Int, limit: Int) =
        apiService.getPhoneSearch(phoneQuery, page, limit)
}