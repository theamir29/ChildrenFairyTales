package com.example.erteklerqosqlarhmtaqmaqlar.domain.usecases.local.getall.getallmaching

import com.example.erteklerqosqlarhmtaqmaqlar.data.models.main.Maching
import com.example.erteklerqosqlarhmtaqmaqlar.domain.MainRepository

class GetRandomMachingUseCase(private val mainRepository: MainRepository) {
    suspend operator fun invoke(): Maching {
        return mainRepository.getRandomMaching()
    }
}





