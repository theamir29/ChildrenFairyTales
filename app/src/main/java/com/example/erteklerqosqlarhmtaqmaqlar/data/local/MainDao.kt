package com.example.erteklerqosqlarhmtaqmaqlar.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.example.erteklerqosqlarhmtaqmaqlar.data.models.main.Maching
import com.example.erteklerqosqlarhmtaqmaqlar.data.models.main.Ertek
import com.example.erteklerqosqlarhmtaqmaqlar.data.models.main.Qosiq
import com.example.erteklerqosqlarhmtaqmaqlar.data.models.main.Taqmaq
import com.example.erteklerqosqlarhmtaqmaqlar.data.models.test.OnlineTest
import com.example.erteklerqosqlarhmtaqmaqlar.data.models.test.QosiqTest
import com.example.erteklerqosqlarhmtaqmaqlar.data.models.test.Test

@Dao
interface MainDao {

    @Query("SELECT * FROM ertekler")
    suspend fun getAllErtek(): List<Ertek>

    @Query("SELECT * FROM qosiqlar")
    suspend fun getAllQosiq(): List<Qosiq>

    @Query("SELECT * FROM taqmaqlar")
    suspend fun getAllTaqmaq(): List<Taqmaq>

    @Query("UPDATE ertekler SET is_saved = :isSaved WHERE id = :id")
    suspend fun likeErtek(id: Int, isSaved: Int)

    @Query("UPDATE qosiqlar SET is_saved = :isSaved WHERE id = :id")
    suspend fun likeQosiq(id: Int, isSaved: Int)

    @Query("UPDATE taqmaqlar SET is_saved = :isSaved WHERE id = :id")
    suspend fun likeTaqmaq(id: Int, isSaved: Int)

       //Maching
    @Query("SELECT * FROM maching")
   suspend fun getAllMaching(): List<Maching>

    @Query("SELECT * FROM maching ORDER BY RANDOM() LIMIT 1")
    suspend fun getRandomMaching(): Maching

    //OnlineTest
    @Query("SELECT * FROM onlineTest")
    suspend fun getAllOnlineTest(): List<OnlineTest>

    @Query("SELECT * FROM onlineTest WHERE id = :testId")
    fun getOnlineTestById(testId: Int): LiveData<OnlineTest>


    //QosiqTest
    @Query("SELECT * FROM qosiq_test")
    suspend fun getAllQosiqTest(): List<QosiqTest>

    @Query("SELECT * FROM qosiq_test WHERE id = :testId")
    suspend fun getQosiqTestById(testId: Int): QosiqTest



    //Test
    @Query("SELECT * FROM testler")
    suspend fun getAllTests(): List<Test>

    @Query("SELECT * FROM testler WHERE id = :testId")
    suspend fun getTestById(testId: Int): Test

    @Query("SELECT * FROM ertekler WHERE is_saved = 1")
    suspend fun getAllErtekLike(): List<Ertek>

    @Query("SELECT * FROM qosiqlar WHERE is_saved = 1")
    suspend fun getAllQosiqLike(): List<Qosiq>

    @Query("SELECT * FROM taqmaqlar WHERE is_saved = 1")
    suspend fun getAllTaqmaqLike(): List<Taqmaq>

    @Query("SELECT * from ertekler WHERE id = :itemId")
    suspend fun getByIdErtek(itemId: Int): Ertek

    @Query("SELECT * from qosiqlar WHERE id = :itemId")
    suspend fun getByIdQosiq(itemId: Int): Qosiq

    @Query("SELECT * from taqmaqlar WHERE id = :itemId")
    suspend fun getByIdTaqmaq(itemId: Int): Taqmaq
}