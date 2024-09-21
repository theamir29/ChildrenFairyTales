package com.example.erteklerqosqlarhmtaqmaqlar.presentation.network

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.erteklerqosqlarhmtaqmaqlar.data.models.feedback.GetFeedbackResponseData
import com.example.erteklerqosqlarhmtaqmaqlar.domain.usecases.network.getFeedbacks.GetFeedbacksUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import okhttp3.RequestBody

class GetFeedbacksViewModel(private val useCase: GetFeedbacksUseCase) : ViewModel() {

    private val _getFeedbacksResult = MutableSharedFlow<Result<GetFeedbackResponseData>>()

    val getFeedbackResult: Flow<Result<GetFeedbackResponseData>> get() = _getFeedbacksResult

    fun getFeedbacks(device_key: String) {
        viewModelScope.launch {
            _getFeedbacksResult.emit(useCase.getFeedbacks(device_key))
        }
    }
}

