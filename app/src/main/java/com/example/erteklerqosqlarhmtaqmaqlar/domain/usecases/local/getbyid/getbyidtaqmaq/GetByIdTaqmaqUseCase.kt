package com.example.erteklerqosqlarhmtaqmaqlar.domain.usecases.local.getbyid.getbyidtaqmaq

import com.example.erteklerqosqlarhmtaqmaqlar.data.models.main.Taqmaq
import kotlinx.coroutines.flow.Flow

interface GetByIdTaqmaqUseCase {
    suspend fun execute(itemId: Int): Flow<Taqmaq>
}