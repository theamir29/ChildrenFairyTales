package com.example.erteklerqosqlarhmtaqmaqlar.domain.usecases.local.test.getQosiqTestById

import com.example.erteklerqosqlarhmtaqmaqlar.data.models.test.QosiqTest
import kotlinx.coroutines.flow.Flow

interface GetQosiqTestByIdUserCase {
    suspend fun execute(id: Int): Flow<QosiqTest>
}