package com.example.erteklerqosqlarhmtaqmaqlar.domain.usecases.network.alltests

import com.example.erteklerqosqlarhmtaqmaqlar.data.models.ResultData
import com.example.erteklerqosqlarhmtaqmaqlar.data.models.test.TestResponse
import com.example.erteklerqosqlarhmtaqmaqlar.domain.NetworkRepository
import kotlinx.coroutines.flow.Flow

class AllTestsUseCaseImpl(private val networkRepository: NetworkRepository) : AllTestsUseCase {
    override suspend fun execute(): Flow<ResultData<TestResponse>> = networkRepository.allTests()
}