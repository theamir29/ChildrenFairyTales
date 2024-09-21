package com.example.erteklerqosqlarhmtaqmaqlar.presentation.local

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.erteklerqosqlarhmtaqmaqlar.data.models.main.Ertek
import com.example.erteklerqosqlarhmtaqmaqlar.data.models.main.Qosiq
import com.example.erteklerqosqlarhmtaqmaqlar.data.models.main.Taqmaq
import com.example.erteklerqosqlarhmtaqmaqlar.data.models.test.QosiqTest
import com.example.erteklerqosqlarhmtaqmaqlar.data.models.test.Test
import com.example.erteklerqosqlarhmtaqmaqlar.domain.usecases.local.getall.getallertek.GetAllErtekLikeUseCase
import com.example.erteklerqosqlarhmtaqmaqlar.domain.usecases.local.getall.getallertek.GetAllErtekUseCase
import com.example.erteklerqosqlarhmtaqmaqlar.domain.usecases.local.getall.getallqosiq.GetAllQosiqLikeUseCase
import com.example.erteklerqosqlarhmtaqmaqlar.domain.usecases.local.getall.getallqosiq.GetAllQosiqUseCase
import com.example.erteklerqosqlarhmtaqmaqlar.domain.usecases.local.getall.getalltaqmaq.GetAllTaqmaqLikeUseCase
import com.example.erteklerqosqlarhmtaqmaqlar.domain.usecases.local.getall.getalltaqmaq.GetAllTaqmaqUseCase
import com.example.erteklerqosqlarhmtaqmaqlar.domain.usecases.local.getbyid.getbyidertek.GetByIdErtekUseCase
import com.example.erteklerqosqlarhmtaqmaqlar.domain.usecases.local.getbyid.getbyidqosiq.GetByIdQosiqUseCase
import com.example.erteklerqosqlarhmtaqmaqlar.domain.usecases.local.getbyid.getbyidtaqmaq.GetByIdTaqmaqUseCase
import com.example.erteklerqosqlarhmtaqmaqlar.domain.usecases.local.like.likeErtek.LikeErtekUseCase
import com.example.erteklerqosqlarhmtaqmaqlar.domain.usecases.local.like.likeTaqmaq.LikeTaqmaqUseCase
import com.example.erteklerqosqlarhmtaqmaqlar.domain.usecases.local.like.likeqosiq.LikeQosiqUseCase
import com.example.erteklerqosqlarhmtaqmaqlar.domain.usecases.local.test.getQosiqTestById.GetQosiqTestByIdUserCase
import com.example.erteklerqosqlarhmtaqmaqlar.domain.usecases.local.test.getallQosiqTest.GetAllQosiqTestUserCase
import com.example.erteklerqosqlarhmtaqmaqlar.domain.usecases.local.test.getalltests.GetAllTestsUseCase
import com.example.erteklerqosqlarhmtaqmaqlar.domain.usecases.local.test.gettestbyid.GetTestByIdUseCase

class MainViewModelImpl(
    private val getAllErtekUseCase: GetAllErtekUseCase,
    private val getAllQosiqUseCase: GetAllQosiqUseCase,
    private val getAllTaqmaqUseCase: GetAllTaqmaqUseCase,
    private val likeQosiqUseCase: LikeQosiqUseCase,
    private val getAllTestsUseCase: GetAllTestsUseCase,
    private val likeTaqmaqUseCase: LikeTaqmaqUseCase,
    private val likeErtekUseCase: LikeErtekUseCase,
    private val getAllQosiqLikeUseCase: GetAllQosiqLikeUseCase,
    private val getAllTaqmaqLikeUseCase: GetAllTaqmaqLikeUseCase,
    private val getAllErtekLikeUseCase: GetAllErtekLikeUseCase,
    private val getTestByIdUseCase: GetTestByIdUseCase,
    private val getByIdErtekUseCase: GetByIdErtekUseCase,
    private val getByIdQosiqUseCase: GetByIdQosiqUseCase,
    private val getByIdTaqmaqUseCase: GetByIdTaqmaqUseCase,
    private val getAllQosiqTestsUseCase: GetAllQosiqTestUserCase,
    private val getQosiqTestByIdUserCase: GetQosiqTestByIdUserCase,

    ) : MainViewModel() {

    override val erteklerLiveData: LiveData<List<Ertek>>
        get() = _erteklerLiveData
    private val _erteklerLiveData = MutableLiveData<List<Ertek>>()

    override val qosiqlarLiveData: LiveData<List<Qosiq>>
        get() = _qosiqlarLiveData
    private val _qosiqlarLiveData = MutableLiveData<List<Qosiq>>()

    override val taqmaqlarLiveData: LiveData<List<Taqmaq>>
        get() = _taqmaqlarLiveData
    private val _taqmaqlarLiveData = MutableLiveData<List<Taqmaq>>()

    override suspend fun getAllErtek() {
        getAllErtekUseCase.execute().collect {
            _erteklerLiveData.value = it
        }
    }

    override suspend fun getAllQosiq() {
        getAllQosiqUseCase.execute().collect {
            _qosiqlarLiveData.value = it
        }
    }

    override suspend fun getAllTaqmaq() {
        getAllTaqmaqUseCase.execute().collect {
            _taqmaqlarLiveData.value = it
        }
    }

    override suspend fun likeQosiq(id: Int, isSaved: Int) = likeQosiqUseCase.execute(id, isSaved)

    override suspend fun likeErtek(id: Int, isSaved: Int) = likeErtekUseCase.execute(id, isSaved)

    override suspend fun likeTaqmaq(id: Int, isSaved: Int) = likeTaqmaqUseCase.execute(id, isSaved)

    override val testsLiveData: LiveData<List<Test>>
        get() = _testsLiveData
    private val _testsLiveData = MutableLiveData<List<Test>>()

    override val qosiqTestsLiveData: LiveData<List<QosiqTest>>
        get() = _qosiqTestsLiveData
    private val _qosiqTestsLiveData = MutableLiveData<List<QosiqTest>>()

    override suspend fun getAllErtekLike() {
        getAllErtekLikeUseCase.execute().collect {
            _erteklerLikeLiveData.value = it
        }
    }

    override val erteklerLikeLiveData: LiveData<List<Ertek>>
        get() = _erteklerLikeLiveData
    private val _erteklerLikeLiveData = MutableLiveData<List<Ertek>>()

    override suspend fun getAllQosiqLike() {
        getAllQosiqLikeUseCase.execute().collect {
            _qosiqlarLikeLiveData.value = it
        }
    }

    override val qosiqlarLikeLiveData: LiveData<List<Qosiq>>
        get() = _qosiqlarLikeLiveData
    private val _qosiqlarLikeLiveData = MutableLiveData<List<Qosiq>>()

    override suspend fun getAllTaqmaqLike() {
        getAllTaqmaqLikeUseCase.execute().collect {
            _taqmaqlarLikeLiveData.value = it
        }
    }

    override val taqmaqlarLikeLiveData: LiveData<List<Taqmaq>>
        get() = _taqmaqlarLikeLiveData
    private val _taqmaqlarLikeLiveData = MutableLiveData<List<Taqmaq>>()

    override suspend fun getAllTests() {
        getAllTestsUseCase.execute().collect {
            _testsLiveData.value = it
        }
    }

    override suspend fun getAllQosiqTests() {
        getAllQosiqTestsUseCase.execute().collect {
            _qosiqTestsLiveData.value = it
        }
    }



    override val testByIdLiveData: LiveData<Test>
        get() = _testByIdLiveData
    private val _testByIdLiveData = MutableLiveData<Test>()

    override suspend fun getTestById(id: Int) {
        getTestByIdUseCase.execute(id).collect {
            _testByIdLiveData.value = it
        }
    }

    override val qosiqTestByIdLiveData: LiveData<QosiqTest>
        get() = _qosiqTestByIdLiveData
    private val _qosiqTestByIdLiveData = MutableLiveData<QosiqTest>()


    override suspend fun getQosiqTestById(id: Int) {
        getQosiqTestByIdUserCase.execute(id).collect {
            _qosiqTestByIdLiveData.value = it
        }
    }




    override val byIdErtekLiveData: LiveData<Ertek>
        get() = _byIdErtekLiveData
    private val _byIdErtekLiveData = MutableLiveData<Ertek>()
    override suspend fun getByIdErtek(itemId: Int) {
        getByIdErtekUseCase.execute(itemId).collect {
            _byIdErtekLiveData.value = it
        }
    }

    override val byIdQosiqLiveData: LiveData<Qosiq>
        get() = _byIdQosiqLiveData
    private val _byIdQosiqLiveData = MutableLiveData<Qosiq>()
    override suspend fun getByIdQosiq(itemId: Int) {
        getByIdQosiqUseCase.execute(itemId).collect {
            _byIdQosiqLiveData.value = it
        }
    }

    override val byIdTaqmaqLiveData: LiveData<Taqmaq>
        get() = _byIdTaqmaqLiveData
    private val _byIdTaqmaqLiveData = MutableLiveData<Taqmaq>()
    override suspend fun getByIdTaqmaq(itemId: Int) {
        getByIdTaqmaqUseCase.execute(itemId).collect {
            _byIdTaqmaqLiveData.value = it
        }
    }



}