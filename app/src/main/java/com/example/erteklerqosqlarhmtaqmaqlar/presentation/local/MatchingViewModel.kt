package com.example.erteklerqosqlarhmtaqmaqlar.presentation.local

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.erteklerqosqlarhmtaqmaqlar.data.models.main.Maching
import com.example.erteklerqosqlarhmtaqmaqlar.domain.usecases.local.getall.getallmaching.GetRandomMachingUseCase
import kotlinx.coroutines.launch

class MatchingViewModel(
    private val getRandomMachingUseCase: GetRandomMachingUseCase
) : ViewModel() {

    private val _machingData = MutableLiveData<Maching>()
    val machingData: LiveData<Maching> get() = _machingData

    fun fetchMachingData() {
        viewModelScope.launch {
            _machingData.value = getRandomMachingUseCase.invoke()
        }
    }

}
