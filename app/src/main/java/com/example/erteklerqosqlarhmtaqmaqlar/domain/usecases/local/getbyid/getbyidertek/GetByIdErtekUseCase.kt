package com.example.erteklerqosqlarhmtaqmaqlar.domain.usecases.local.getbyid.getbyidertek

import com.example.erteklerqosqlarhmtaqmaqlar.data.models.main.Ertek
import kotlinx.coroutines.flow.Flow

interface GetByIdErtekUseCase {
    suspend fun execute(itemId: Int): Flow<Ertek>
}