package com.example.erteklerqosqlarhmtaqmaqlar.domain.usecases.network.sendnotification

import com.example.erteklerqosqlarhmtaqmaqlar.data.models.ResultData
import com.example.erteklerqosqlarhmtaqmaqlar.data.models.notification.RequestData
import com.example.erteklerqosqlarhmtaqmaqlar.data.models.notification.ResponseData
import kotlinx.coroutines.flow.Flow

interface SendNotificationUseCase {
    suspend fun execute(requestData: RequestData, adminDeviceKey: String): Flow<ResultData<ResponseData>>
}