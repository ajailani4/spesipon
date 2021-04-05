package com.ajailani.spesipon.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.ajailani.spesipon.data.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BrandsViewModel @Inject constructor(
    private val mainRepository: MainRepository
): ViewModel() {
    fun getBrands() = mainRepository.getBrands().cachedIn(viewModelScope)
}