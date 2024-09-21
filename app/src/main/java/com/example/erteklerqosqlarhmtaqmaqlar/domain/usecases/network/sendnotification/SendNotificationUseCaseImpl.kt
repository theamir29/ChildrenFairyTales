package com.example.erteklerqosqlarhmtaqmaqlar.domain.usecases.network.sendnotification

import com.example.erteklerqosqlarhmtaqmaqlar.data.models.notification.RequestData
import com.example.erteklerqosqlarhmtaqmaqlar.domain.NetworkRepository

class SendNotificationUseCaseImpl(private val networkRepository: NetworkRepository) :
    SendNotificationUseCase {
    override suspend fun execute(requestData: RequestData, adminDeviceKey: String) =
        networkRepository.sendNotification(requestData, adminDeviceKey)
}