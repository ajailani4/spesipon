package com.ajailani.spesipon.data.datasource

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.ajailani.spesipon.data.api.ApiHelper
import com.ajailani.spesipon.data.model.brand.Brand
import java.lang.Exception
import javax.inject.Inject

class BrandDataSource @Inject constructor(
    private val apiHelper: ApiHelper
) : PagingSource<Int, Brand>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Brand> {
        val currentLoadingPageKey = params.key ?: 1

        return try {
            val response = apiHelper.getBrands(currentLoadingPageKey, 5)
            val data = response.body()?.data?.brands ?: emptyList()

            data.forEach { brand ->
                var phonesList = apiHelper.getPhonesHome(brand.slug).body()?.data?.phones ?: emptyList()

                if (phonesList.size > 5) {
                    phonesList = phonesList.subList(0, 5)
                }

                brand.phonesList = phonesList
            }

            val prevKey = if (currentLoadingPageKey == 1) null else currentLoadingPageKey - 1

            LoadResult.Page(
                data = data,
                prevKey = prevKey,
                nextKey = currentLoadingPageKey.plus(1)
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Brand>): Int? {
        return state.anchorPosition
    }
}