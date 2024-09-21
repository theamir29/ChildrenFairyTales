package com.example.erteklerqosqlarhmtaqmaqlar.domain.usecases.local.test.gettestbyid

import com.example.erteklerqosqlarhmtaqmaqlar.data.models.test.Test
import kotlinx.coroutines.flow.Flow

interface GetTestByIdUseCase {
    suspend fun execute(id: Int): Flow<Test>
}