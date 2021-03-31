package com.ajailani.spesipon.data.api

import javax.inject.Inject

class ApiHelper @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun getBrands(page: Int) =
        apiService.getBrands(page)

    suspend fun getPhones(brandSlug: String, page: Int) =
        apiService.getPhones(brandSlug, page)

    suspend fun getPhonesHome(brandSlug: String) =
        apiService.getPhonesHome(brandSlug)

    suspend fun getPhoneSpecs(brandSlug: String, phoneSlug: String) =
        apiService.getPhoneSpecs(brandSlug, phoneSlug)
}