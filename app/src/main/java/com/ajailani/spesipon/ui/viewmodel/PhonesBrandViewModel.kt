package com.ajailani.spesipon.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.ajailani.spesipon.data.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PhonesBrandViewModel @Inject constructor(
    private val mainRepository: MainRepository
) : ViewModel() {
    fun getPhones(brandSlug: String) = mainRepository.getPhones(brandSlug).cachedIn(viewModelScope)
}