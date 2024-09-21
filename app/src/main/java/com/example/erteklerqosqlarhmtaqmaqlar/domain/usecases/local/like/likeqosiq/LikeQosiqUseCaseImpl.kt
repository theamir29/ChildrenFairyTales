package com.example.erteklerqosqlarhmtaqmaqlar.domain.usecases.local.like.likeqosiq

import com.example.erteklerqosqlarhmtaqmaqlar.domain.MainRepository

class LikeQosiqUseCaseImpl(private val mainRepository: MainRepository) : LikeQosiqUseCase {

    override suspend fun execute(id: Int, isSaved: Int) = mainRepository.likeQosiq(id, isSaved)
}