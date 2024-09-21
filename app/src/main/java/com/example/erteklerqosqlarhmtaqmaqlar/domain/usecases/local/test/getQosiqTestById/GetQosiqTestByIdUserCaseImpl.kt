package com.example.erteklerqosqlarhmtaqmaqlar.domain.usecases.local.test.getQosiqTestById

import com.example.erteklerqosqlarhmtaqmaqlar.data.models.test.QosiqTest
import com.example.erteklerqosqlarhmtaqmaqlar.domain.MainRepository
import kotlinx.coroutines.flow.Flow

class GetQosiqTestByIdUserCaseImpl (private val mainRepository: MainRepository) :
    GetQosiqTestByIdUserCase {
    override suspend fun execute(id: Int): Flow<QosiqTest> = mainRepository.getQosiqTestById(id)
}