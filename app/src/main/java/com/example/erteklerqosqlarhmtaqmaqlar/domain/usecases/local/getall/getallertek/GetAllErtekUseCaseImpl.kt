package com.example.erteklerqosqlarhmtaqmaqlar.domain.usecases.local.getall.getallertek

import com.example.erteklerqosqlarhmtaqmaqlar.domain.MainRepository

class GetAllErtekUseCaseImpl(private val mainRepository: MainRepository) : GetAllErtekUseCase {
    override suspend fun execute() = mainRepository.getAllErtek()
}