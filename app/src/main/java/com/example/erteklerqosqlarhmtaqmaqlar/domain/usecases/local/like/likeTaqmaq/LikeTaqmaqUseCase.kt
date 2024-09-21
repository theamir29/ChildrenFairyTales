package com.example.erteklerqosqlarhmtaqmaqlar.domain.usecases.local.like.likeTaqmaq

interface LikeTaqmaqUseCase {
    suspend fun execute(id: Int, isSaved: Int)
}