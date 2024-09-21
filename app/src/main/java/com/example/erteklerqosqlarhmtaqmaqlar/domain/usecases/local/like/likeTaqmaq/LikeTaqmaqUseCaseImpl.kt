package com.example.erteklerqosqlarhmtaqmaqlar.domain.usecases.local.like.likeTaqmaq

import com.example.erteklerqosqlarhmtaqmaqlar.domain.MainRepository

class LikeTaqmaqUseCaseImpl(private val mainRepository: MainRepository) : LikeTaqmaqUseCase {

    override suspend fun execute(id: Int, isSaved: Int) = mainRepository.likeTaqmaq(id, isSaved)
}