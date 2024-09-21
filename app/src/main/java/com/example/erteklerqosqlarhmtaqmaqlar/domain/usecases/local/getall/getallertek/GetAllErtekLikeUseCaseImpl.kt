package com.example.erteklerqosqlarhmtaqmaqlar.domain.usecases.local.getall.getallertek

import com.example.erteklerqosqlarhmtaqmaqlar.domain.MainRepository

class GetAllErtekLikeUseCaseImpl(private val mainRepository: MainRepository) :
    GetAllErtekLikeUseCase {
    override suspend fun execute() = mainRepository.getAllErtekLike()
}