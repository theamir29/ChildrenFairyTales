package com.example.erteklerqosqlarhmtaqmaqlar.domain.usecases.local.getall.getallertek

import com.example.erteklerqosqlarhmtaqmaqlar.data.models.main.Ertek
import kotlinx.coroutines.flow.Flow

interface GetAllErtekLikeUseCase {
    suspend fun execute(): Flow<List<Ertek>>
}