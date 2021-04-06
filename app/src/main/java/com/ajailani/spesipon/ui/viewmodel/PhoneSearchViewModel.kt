package com.ajailani.spesipon.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.ajailani.spesipon.data.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PhoneSearchViewModel @Inject constructor(
    private val mainRepository: MainRepository
) : ViewModel() {
    fun getPhoneSearch(phoneQuery: String) =
        mainRepository.getPhoneSearch(phoneQuery).cachedIn(viewModelScope)
}