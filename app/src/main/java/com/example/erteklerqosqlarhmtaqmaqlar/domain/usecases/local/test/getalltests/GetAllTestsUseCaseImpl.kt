package com.example.erteklerqosqlarhmtaqmaqlar.domain.usecases.local.test.getalltests

import com.example.erteklerqosqlarhmtaqmaqlar.data.models.test.Test
import com.example.erteklerqosqlarhmtaqmaqlar.domain.MainRepository
import kotlinx.coroutines.flow.Flow

class GetAllTestsUseCaseImpl(private val mainRepository: MainRepository) : GetAllTestsUseCase {
    override suspend fun execute(): Flow<List<Test>> = mainRepository.getAllTests()
}