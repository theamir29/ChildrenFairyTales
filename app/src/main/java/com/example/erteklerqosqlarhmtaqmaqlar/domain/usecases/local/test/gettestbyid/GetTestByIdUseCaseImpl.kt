package com.example.erteklerqosqlarhmtaqmaqlar.domain.usecases.local.test.gettestbyid

import com.example.erteklerqosqlarhmtaqmaqlar.data.models.test.Test
import com.example.erteklerqosqlarhmtaqmaqlar.domain.MainRepository
import kotlinx.coroutines.flow.Flow

class GetTestByIdUseCaseImpl(private val mainRepository: MainRepository) : GetTestByIdUseCase {
    override suspend fun execute(id: Int): Flow<Test> = mainRepository.getTestById(id)
}