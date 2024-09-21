package com.example.erteklerqosqlarhmtaqmaqlar.domain.usecases.local.test.getallQosiqTest

import com.example.erteklerqosqlarhmtaqmaqlar.data.models.test.QosiqTest
import kotlinx.coroutines.flow.Flow

interface GetAllQosiqTestUserCase {
    suspend fun execute(): Flow<List<QosiqTest>>
}