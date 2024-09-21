package com.example.erteklerqosqlarhmtaqmaqlar.domain.usecases.local.test.getallQosiqTest

import com.example.erteklerqosqlarhmtaqmaqlar.data.models.test.QosiqTest
import com.example.erteklerqosqlarhmtaqmaqlar.domain.MainRepository
import kotlinx.coroutines.flow.Flow

class GetAllQosiqTestUserCaseImpl (private val mainRepository: MainRepository) :
    GetAllQosiqTestUserCase {
    override suspend fun execute(): Flow<List<QosiqTest>> = mainRepository.getAllQosiqTests()
}