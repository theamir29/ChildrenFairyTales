package com.example.erteklerqosqlarhmtaqmaqlar.domain.usecases.network.getFeedbacks

import com.example.erteklerqosqlarhmtaqmaqlar.data.models.feedback.GetFeedbackResponseData
import com.example.erteklerqosqlarhmtaqmaqlar.domain.NetworkRepository
import okhttp3.RequestBody

class GetFeedbacksUseCaseImpl(private val repository: NetworkRepository) :
    GetFeedbacksUseCase {
    override suspend fun getFeedbacks(device_key: String): Result<GetFeedbackResponseData> {
        return repository.getFeedbacks(device_key)
    }
}