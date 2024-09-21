package com.example.erteklerqosqlarhmtaqmaqlar.domain.usecases.local.like.likeErtek

import com.example.erteklerqosqlarhmtaqmaqlar.domain.MainRepository

class LikeErtekUseCaseImpl(private val mainRepository: MainRepository) : LikeErtekUseCase {

    override suspend fun execute(id: Int, isSaved: Int) = mainRepository.likeErtek(id, isSaved)
}