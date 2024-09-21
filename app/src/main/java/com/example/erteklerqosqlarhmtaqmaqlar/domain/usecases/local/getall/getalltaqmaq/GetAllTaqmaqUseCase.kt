package com.example.erteklerqosqlarhmtaqmaqlar.domain.usecases.local.getall.getalltaqmaq

import com.example.erteklerqosqlarhmtaqmaqlar.data.models.main.Taqmaq
import kotlinx.coroutines.flow.Flow

interface GetAllTaqmaqUseCase {
    suspend fun execute(): Flow<List<Taqmaq>>
}