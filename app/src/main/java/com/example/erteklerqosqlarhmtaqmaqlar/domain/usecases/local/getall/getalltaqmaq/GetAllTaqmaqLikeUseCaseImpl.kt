package com.example.erteklerqosqlarhmtaqmaqlar.domain.usecases.local.getall.getalltaqmaq

import com.example.erteklerqosqlarhmtaqmaqlar.domain.MainRepository

class GetAllTaqmaqLikeUseCaseImpl(private val mainRepository: MainRepository) :
    GetAllTaqmaqLikeUseCase {
    override suspend fun execute() = mainRepository.getAllTaqmaqLike()
}