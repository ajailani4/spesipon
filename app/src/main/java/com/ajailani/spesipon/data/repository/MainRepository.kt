package com.ajailani.spesipon.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.ajailani.spesipon.data.api.ApiHelper
import com.ajailani.spesipon.data.datasource.BrandsDataSource
import com.ajailani.spesipon.data.datasource.BrandsHomeDataSource
import com.ajailani.spesipon.data.datasource.PhonesDataSource
import com.ajailani.spesipon.data.model.phone.Phone
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val apiHelper: ApiHelper
) {
    fun getBrandsHome() =
        Pager(
            config = PagingConfig(enablePlaceholders = false, pageSize = 5),
            pagingSourceFactory = {
                BrandsHomeDataSource(apiHelper)
            }
        ).flow

    fun getPhones(brandSlug: String): Flow<PagingData<Phone>> {
        val phoneDataSource = PhonesDataSource(apiHelper)
        phoneDataSource.setBrandSlug(brandSlug)

        return Pager(
            config = PagingConfig(enablePlaceholders = false, pageSize = 20),
            pagingSourceFactory = {
                phoneDataSource
            }
        ).flow
    }

    fun getPhoneSpecs(brandSlug: String, phoneSlug: String) =
        flow {
            emit(apiHelper.getPhoneSpecs(brandSlug, phoneSlug))
        }

    fun getBrands() =
        Pager(
            config = PagingConfig(enablePlaceholders = false, pageSize = 10),
            pagingSourceFactory = {
                BrandsDataSource(apiHelper)
            }
        ).flow
}