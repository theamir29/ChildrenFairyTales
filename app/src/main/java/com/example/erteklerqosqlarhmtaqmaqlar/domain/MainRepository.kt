package com.example.erteklerqosqlarhmtaqmaqlar.domain

import androidx.lifecycle.LiveData
import com.example.erteklerqosqlarhmtaqmaqlar.data.local.MainDao
import com.example.erteklerqosqlarhmtaqmaqlar.data.models.main.Ertek
import com.example.erteklerqosqlarhmtaqmaqlar.data.models.main.Maching
import com.example.erteklerqosqlarhmtaqmaqlar.data.models.main.Qosiq
import com.example.erteklerqosqlarhmtaqmaqlar.data.models.main.Taqmaq
import com.example.erteklerqosqlarhmtaqmaqlar.data.models.test.OnlineTest
import com.example.erteklerqosqlarhmtaqmaqlar.data.models.test.QosiqTest
import com.example.erteklerqosqlarhmtaqmaqlar.data.models.test.Test
import kotlinx.coroutines.flow.Flow

interface MainRepository {
    suspend fun getAllErtek(): Flow<List<Ertek>>
    suspend fun getAllQosiq(): Flow<List<Qosiq>>
    suspend fun getAllTaqmaq(): Flow<List<Taqmaq>>
    suspend fun likeQosiq(id: Int, isSaved: Int)
    suspend fun likeErtek(id: Int, isSaved: Int)
    suspend fun likeTaqmaq(id: Int, isSaved: Int)
    suspend fun getAllTests(): Flow<List<Test>>
    suspend fun getTestById(id: Int): Flow<Test>
    suspend fun getAllErtekLike(): Flow<List<Ertek>>
    suspend fun getAllQosiqLike(): Flow<List<Qosiq>>
    suspend fun getAllTaqmaqLike(): Flow<List<Taqmaq>>
    suspend fun getByIdErtek(itemId: Int): Flow<Ertek>
    suspend fun getByIdQosiq(itemId: Int): Flow<Qosiq>
    suspend fun getByIdTaqmaq(itemId: Int): Flow<Taqmaq>
    suspend fun getRandomMaching(): Maching
    fun getOnlineTestById(testId: Int): LiveData<OnlineTest>
    suspend fun getAllQosiqTests(): Flow<List<QosiqTest>>
    suspend fun getQosiqTestById(id: Int): Flow<QosiqTest>
}




