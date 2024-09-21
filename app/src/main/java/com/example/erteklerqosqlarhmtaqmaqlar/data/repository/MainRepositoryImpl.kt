package com.example.erteklerqosqlarhmtaqmaqlar.data.repository

import androidx.lifecycle.LiveData
import com.example.erteklerqosqlarhmtaqmaqlar.data.local.MainDao
import com.example.erteklerqosqlarhmtaqmaqlar.data.models.main.Ertek
import com.example.erteklerqosqlarhmtaqmaqlar.data.models.main.Maching
import com.example.erteklerqosqlarhmtaqmaqlar.data.models.main.Qosiq
import com.example.erteklerqosqlarhmtaqmaqlar.data.models.main.Taqmaq
import com.example.erteklerqosqlarhmtaqmaqlar.data.models.test.OnlineTest
import com.example.erteklerqosqlarhmtaqmaqlar.data.models.test.QosiqTest
import com.example.erteklerqosqlarhmtaqmaqlar.data.models.test.Test
import com.example.erteklerqosqlarhmtaqmaqlar.domain.MainRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class MainRepositoryImpl(private val dao: MainDao) : MainRepository {

    override suspend fun getAllErtek(): Flow<List<Ertek>> = flow {
        emit(dao.getAllErtek())
    }

    override suspend fun getAllQosiq(): Flow<List<Qosiq>> = flow {
        emit(dao.getAllQosiq())
    }

    override suspend fun getAllTaqmaq(): Flow<List<Taqmaq>> = flow {
        emit(dao.getAllTaqmaq())
    }

    override suspend fun likeQosiq(id: Int, isSaved: Int) = dao.likeQosiq(id, isSaved)

    override suspend fun likeErtek(id: Int, isSaved: Int) = dao.likeErtek(id, isSaved)

    override suspend fun likeTaqmaq(id: Int, isSaved: Int) = dao.likeTaqmaq(id, isSaved)

    override suspend fun getAllTests(): Flow<List<Test>> = flow {
        emit(dao.getAllTests())
    }

    override suspend fun getTestById(id: Int): Flow<Test> = flow {
        emit(dao.getTestById(id))
    }

    override suspend fun getAllErtekLike(): Flow<List<Ertek>> = flow {
        emit(dao.getAllErtekLike())
    }

    override suspend fun getAllQosiqLike(): Flow<List<Qosiq>> = flow {
        emit(dao.getAllQosiqLike())
    }

    override suspend fun getAllTaqmaqLike(): Flow<List<Taqmaq>> = flow {
        emit(dao.getAllTaqmaqLike())
    }

    override suspend fun getByIdErtek(itemId: Int): Flow<Ertek> = flow {
        emit(dao.getByIdErtek(itemId))
    }

    override suspend fun getByIdQosiq(itemId: Int): Flow<Qosiq> = flow {
        emit(dao.getByIdQosiq(itemId))
    }

    override suspend fun getByIdTaqmaq(itemId: Int): Flow<Taqmaq> = flow {
        emit(dao.getByIdTaqmaq(itemId))
    }

    override suspend fun getRandomMaching(): Maching = dao.getRandomMaching()

    override fun getOnlineTestById(testId: Int): LiveData<OnlineTest> {
        return dao.getOnlineTestById(testId)
    }

    override suspend fun getAllQosiqTests(): Flow<List<QosiqTest>> = flow {
        emit(dao.getAllQosiqTest())
    }

    override suspend fun getQosiqTestById(id: Int): Flow<QosiqTest> = flow {
        emit(dao.getQosiqTestById(id))
    }
}
