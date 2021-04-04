package com.ajailani.spesipon.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ajailani.spesipon.data.model.phonespecs.PhoneSpecsDataResponse
import com.ajailani.spesipon.data.repository.MainRepository
import com.ajailani.spesipon.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PhoneSpecsViewModel @Inject constructor(
    private val mainRepository: MainRepository
): ViewModel() {
    @ExperimentalCoroutinesApi
    private val phoneSpecsData =
        MutableStateFlow<Resource<PhoneSpecsDataResponse>>(Resource.loading(null))

    @ExperimentalCoroutinesApi
    fun fetchPhoneSpecsData(brandSlug: String, phoneSlug: String) {
        viewModelScope.launch {
            mainRepository.getPhoneSpecs(brandSlug, phoneSlug)
                .catch {
                    phoneSpecsData.value = Resource.error(it.toString(), null)
                }
                .collect {
                    phoneSpecsData.value = Resource.success(it.body()?.data)
                }
        }
    }

    @ExperimentalCoroutinesApi
    fun getPhoneSpecsData(): StateFlow<Resource<PhoneSpecsDataResponse>> =
        phoneSpecsData
}