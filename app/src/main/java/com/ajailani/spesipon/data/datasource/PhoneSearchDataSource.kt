package com.ajailani.spesipon.data.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.ajailani.spesipon.data.api.ApiHelper
import com.ajailani.spesipon.data.model.PhoneSearch
import java.lang.Exception
import javax.inject.Inject

class PhoneSearchDataSource @Inject constructor(
    private val apiHelper: ApiHelper
) : PagingSource<Int, PhoneSearch>() {
    private var phoneQuery = ""

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PhoneSearch> {
        return try {
            val currentLoadingPageKey = params.key ?: 1
            val response = apiHelper.getPhoneSearch(phoneQuery, currentLoadingPageKey, 14)
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

    override fun getRefreshKey(state: PagingState<Int, PhoneSearch>): Int? {
        TODO("Not yet implemented")
    }

    fun setPhoneQuery(query: String) {
        phoneQuery = query
    }
}