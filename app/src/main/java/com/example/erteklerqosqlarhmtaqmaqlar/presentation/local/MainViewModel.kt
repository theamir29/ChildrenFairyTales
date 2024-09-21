package com.example.erteklerqosqlarhmtaqmaqlar.presentation.local

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.erteklerqosqlarhmtaqmaqlar.data.models.main.Ertek
import com.example.erteklerqosqlarhmtaqmaqlar.data.models.main.Qosiq
import com.example.erteklerqosqlarhmtaqmaqlar.data.models.main.Taqmaq
import com.example.erteklerqosqlarhmtaqmaqlar.data.models.test.QosiqTest
import com.example.erteklerqosqlarhmtaqmaqlar.data.models.test.Test

abstract class MainViewModel : ViewModel() {
    abstract suspend fun getAllErtek()
    abstract val erteklerLiveData: LiveData<List<Ertek>>

    abstract suspend fun getAllQosiq()
    abstract val qosiqlarLiveData: LiveData<List<Qosiq>>

    abstract suspend fun getAllTaqmaq()
    abstract val taqmaqlarLiveData: LiveData<List<Taqmaq>>

    abstract suspend fun likeQosiq(id: Int, isSaved: Int)

    abstract suspend fun likeErtek(id: Int, isSaved: Int)

    abstract suspend fun likeTaqmaq(id: Int, isSaved: Int)




    abstract suspend fun getAllTests()
    abstract val testsLiveData: LiveData<List<Test>>

    abstract suspend fun getAllQosiqTests()
    abstract val qosiqTestsLiveData: LiveData<List<QosiqTest>>





    abstract suspend fun getTestById(id: Int)
    abstract val testByIdLiveData: LiveData<Test>


    abstract suspend fun getQosiqTestById(id: Int)
    abstract val qosiqTestByIdLiveData: LiveData<QosiqTest>


    abstract suspend fun getAllErtekLike()
    abstract val erteklerLikeLiveData: LiveData<List<Ertek>>

    abstract suspend fun getAllQosiqLike()
    abstract val qosiqlarLikeLiveData: LiveData<List<Qosiq>>

    abstract suspend fun getAllTaqmaqLike()
    abstract val taqmaqlarLikeLiveData: LiveData<List<Taqmaq>>


    abstract suspend fun getByIdErtek(itemId: Int)
    abstract val byIdErtekLiveData: LiveData<Ertek>

    abstract suspend fun getByIdQosiq(itemId: Int)
    abstract val byIdQosiqLiveData: LiveData<Qosiq>

    abstract suspend fun getByIdTaqmaq(itemId: Int)
    abstract val byIdTaqmaqLiveData: LiveData<Taqmaq>


//    abstract suspend fun getAllMaching(itemId: Int)
//    abstract val machingLiveData: LiveData<Maching>

}
