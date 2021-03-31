package com.ajailani.spesipon.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.ajailani.spesipon.data.model.phone.Phone
import com.ajailani.spesipon.data.repository.MainRepository
import com.ajailani.spesipon.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val mainRepository: MainRepository
): ViewModel() {
    @ExperimentalCoroutinesApi
    private val phonesHomeList =
        MutableStateFlow<Resource<List<Phone>>>(Resource.loading(null))

    fun getBrands() = mainRepository.getBrands().cachedIn(viewModelScope)

    @ExperimentalCoroutinesApi
    fun getPhonesHome(brandSlug: String) {
        viewModelScope.launch {
            mainRepository.getPhonesHome(brandSlug)
                .catch { e ->
                    phonesHomeList.value = Resource.error(e.toString(), null)
                }
                .collect { data ->
                    phonesHomeList.value = Resource.success(data)
                }
        }
    }
}