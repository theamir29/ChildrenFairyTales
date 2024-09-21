package com.example.erteklerqosqlarhmtaqmaqlar.domain.usecases.local.getbyid.getbyidertek

import com.example.erteklerqosqlarhmtaqmaqlar.domain.MainRepository

class GetByIdErtekUseCaseImpl(private val mainRepository: MainRepository) : GetByIdErtekUseCase {
    override suspend fun execute(itemId: Int) = mainRepository.getByIdErtek(itemId)
}