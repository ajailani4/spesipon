package com.ajailani.spesipon.data.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.ajailani.spesipon.data.api.ApiHelper
import com.ajailani.spesipon.data.model.phone.Phone
import java.lang.Exception
import javax.inject.Inject

class PhoneDataSource @Inject constructor(
    private val apiHelper: ApiHelper
) : PagingSource<Int, Phone>() {
    private var brandSlug = ""

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Phone> {
        return try {
            val currentLoadingPageKey = params.key ?: 1
            val response = apiHelper.getPhones(brandSlug, currentLoadingPageKey, 18)
            val data = response.body()?.data?.phones ?: emptyList()

            val prevKey = if (currentLoadingPageKey == 1) null else currentLoadingPageKey - 1

            LoadResult.Page(
                data = data,
                prevKey = prevKey,
                nextKey = currentLoadingPageKey.plus(1)
            )
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Phone>): Int? {
        TODO("Not yet implemented")
    }

    fun setBrandSlug(slug: String) {
        brandSlug = slug
    }
}