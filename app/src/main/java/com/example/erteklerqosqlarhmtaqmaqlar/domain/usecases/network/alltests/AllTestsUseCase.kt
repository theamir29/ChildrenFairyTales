package com.example.erteklerqosqlarhmtaqmaqlar.domain.usecases.network.alltests

import com.example.erteklerqosqlarhmtaqmaqlar.data.models.ResultData
import com.example.erteklerqosqlarhmtaqmaqlar.data.models.test.TestResponse
import kotlinx.coroutines.flow.Flow

interface AllTestsUseCase {
    suspend fun execute(): Flow<ResultData<TestResponse>>
}