package com.example.erteklerqosqlarhmtaqmaqlar.domain.usecases.local.getall.getalltaqmaq

import com.example.erteklerqosqlarhmtaqmaqlar.domain.MainRepository

class GetAllTaqmaqUseCaseImpl(private val mainRepository: MainRepository) : GetAllTaqmaqUseCase {
    override suspend fun execute() = mainRepository.getAllTaqmaq()
}