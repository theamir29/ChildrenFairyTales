package com.example.erteklerqosqlarhmtaqmaqlar.domain.usecases.local.getbyid.getbyidtaqmaq

import com.example.erteklerqosqlarhmtaqmaqlar.domain.MainRepository

class GetByIdTaqmaqUseCaseImpl(private val mainRepository: MainRepository) : GetByIdTaqmaqUseCase {
    override suspend fun execute(itemId: Int) = mainRepository.getByIdTaqmaq(itemId)
}