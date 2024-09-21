package com.example.erteklerqosqlarhmtaqmaqlar.domain.usecases.local.getall.getallqosiq

import com.example.erteklerqosqlarhmtaqmaqlar.domain.MainRepository

class GetAllQosiqLikeUseCaseImpl(private val mainRepository: MainRepository) :
    GetAllQosiqLikeUseCase {
    override suspend fun execute() = mainRepository.getAllQosiqLike()
}