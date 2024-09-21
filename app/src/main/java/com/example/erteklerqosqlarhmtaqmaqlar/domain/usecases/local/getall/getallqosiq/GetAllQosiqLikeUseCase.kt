package com.example.erteklerqosqlarhmtaqmaqlar.domain.usecases.local.getall.getallqosiq

import com.example.erteklerqosqlarhmtaqmaqlar.data.models.main.Qosiq
import kotlinx.coroutines.flow.Flow

interface GetAllQosiqLikeUseCase {
    suspend fun execute(): Flow<List<Qosiq>>
}