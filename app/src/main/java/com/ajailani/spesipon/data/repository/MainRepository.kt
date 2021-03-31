package com.ajailani.spesipon.data.repository

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.ajailani.spesipon.data.api.ApiHelper
import com.ajailani.spesipon.data.datasource.BrandDataSource
import com.ajailani.spesipon.data.model.brand.Brand
import com.ajailani.spesipon.data.model.phone.Phone
import com.ajailani.spesipon.utils.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val apiHelper: ApiHelper
) {
    fun getBrands(): Flow<PagingData<Brand>> {
        return Pager(
            config = PagingConfig(enablePlaceholders = false, pageSize = 20),
            pagingSourceFactory = {
                BrandDataSource(apiHelper)
            }
        ).flow
    }

    @ExperimentalCoroutinesApi
    fun getPhonesHome(brandSlug: String): Flow<List<Phone>> = flow {
        val phonesHomeList = mutableListOf<Phone>()
        val response = apiHelper.getPhonesHome(brandSlug).body()?.data?.phones ?: emptyList()

        // Put 5 phones only to phoneHomeList
        for (i in 0..4) {
            phonesHomeList.add(response[i])
        }

        emit(phonesHomeList)
    }
}