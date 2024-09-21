package com.example.erteklerqosqlarhmtaqmaqlar.domain.usecases.local.like.likeqosiq

interface LikeQosiqUseCase {
    suspend fun execute(id: Int, isSaved: Int)
}