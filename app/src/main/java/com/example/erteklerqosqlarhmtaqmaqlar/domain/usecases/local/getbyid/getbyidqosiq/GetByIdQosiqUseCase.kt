package com.example.erteklerqosqlarhmtaqmaqlar.domain.usecases.local.getbyid.getbyidqosiq

import com.example.erteklerqosqlarhmtaqmaqlar.data.models.main.Qosiq
import kotlinx.coroutines.flow.Flow

interface GetByIdQosiqUseCase {
    suspend fun execute(itemId: Int): Flow<Qosiq>
}