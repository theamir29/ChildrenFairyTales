package com.example.erteklerqosqlarhmtaqmaqlar.domain.usecases.network.getFeedbacks

import com.example.erteklerqosqlarhmtaqmaqlar.data.models.feedback.GetFeedbackResponseData
import okhttp3.RequestBody

interface GetFeedbacksUseCase {

    suspend fun getFeedbacks(device_key: String): Result<GetFeedbackResponseData>
}