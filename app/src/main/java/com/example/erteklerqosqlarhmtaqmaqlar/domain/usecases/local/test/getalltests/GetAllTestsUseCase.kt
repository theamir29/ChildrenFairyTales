package com.example.erteklerqosqlarhmtaqmaqlar.domain.usecases.local.test.getalltests

import com.example.erteklerqosqlarhmtaqmaqlar.data.models.test.Test
import kotlinx.coroutines.flow.Flow

interface GetAllTestsUseCase {
    suspend fun execute(): Flow<List<Test>>
}