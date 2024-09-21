package com.example.erteklerqosqlarhmtaqmaqlar.domain.usecases.local.getbyid.getbyidqosiq

import com.example.erteklerqosqlarhmtaqmaqlar.domain.MainRepository

class GetByIdQosiqUseCaseImpl(private val mainRepository: MainRepository) : GetByIdQosiqUseCase {
    override suspend fun execute(itemId: Int) = mainRepository.getByIdQosiq(itemId)
}