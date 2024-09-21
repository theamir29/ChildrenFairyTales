package com.example.erteklerqosqlarhmtaqmaqlar.domain.usecases.local.getall.getallqosiq

import com.example.erteklerqosqlarhmtaqmaqlar.domain.MainRepository

class GetAllQosiqUseCaseImpl(private val mainRepository: MainRepository) : GetAllQosiqUseCase {
    override suspend fun execute() = mainRepository.getAllQosiq()
}