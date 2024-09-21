package com.example.erteklerqosqlarhmtaqmaqlar.domain.usecases.local.like.likeErtek

interface LikeErtekUseCase {
    suspend fun execute(id: Int, isSaved: Int)
}