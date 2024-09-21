package com.example.erteklerqosqlarhmtaqmaqlar.presentation.network

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.erteklerqosqlarhmtaqmaqlar.data.models.ResultData
import com.example.erteklerqosqlarhmtaqmaqlar.data.models.audio.SendAudioResponse
import com.example.erteklerqosqlarhmtaqmaqlar.data.models.notification.RequestData
import com.example.erteklerqosqlarhmtaqmaqlar.data.models.notification.ResponseData
import com.example.erteklerqosqlarhmtaqmaqlar.data.models.test.TestResponse
import com.example.erteklerqosqlarhmtaqmaqlar.domain.usecases.network.alltests.AllTestsUseCase
import com.example.erteklerqosqlarhmtaqmaqlar.domain.usecases.network.sendaudio.SendAudioUseCase
import com.example.erteklerqosqlarhmtaqmaqlar.domain.usecases.network.sendnotification.SendNotificationUseCase
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File

class NetworkViewModelImpl(
    private val sendNotificationUseCase: SendNotificationUseCase,
    private val sendAudioUseCase: SendAudioUseCase,
    private val allTestsUseCase: AllTestsUseCase,
) : NetworkViewModel() {
    override val messageLiveData: LiveData<String>
        get() = _messageLiveData
    private val _messageLiveData = MutableLiveData<String>()
    override val errorLiveData: LiveData<Throwable>
        get() = _errorLiveData
    private val _errorLiveData = MutableLiveData<Throwable>()
    override val notificationResponseLiveData: LiveData<ResponseData>
        get() = _notificationResponseLiveData
    private val _notificationResponseLiveData = MutableLiveData<ResponseData>()

    override suspend fun sendNotification(requestData: RequestData, adminDeviceKey: String) {
        sendNotificationUseCase.execute(requestData, adminDeviceKey).collect {
            when (it) {
                is ResultData.Success -> {
                    _notificationResponseLiveData.value = it.success
                }

                is ResultData.Message -> {
                    _messageLiveData.value = it.message
                }

                is ResultData.Error -> {
                    _errorLiveData.value = it.error
                }
            }
        }
    }

    override val sendAudioLiveData: LiveData<SendAudioResponse>
        get() = _sendAudioLiveData
    private val _sendAudioLiveData = MutableLiveData<SendAudioResponse>()
    override suspend fun sendAudio(deviceKey: String, testId: String, voice: File, name: String) {
        sendAudioUseCase.execute(
            deviceKey.toRequestBody("text/plain".toMediaTypeOrNull()),
            testId.toRequestBody("text/plain".toMediaTypeOrNull()),
            MultipartBody.Part.createFormData(
                "voice",
                "Voice",
                voice.asRequestBody("audio/mp3".toMediaTypeOrNull()),
            ),
            name.toRequestBody("text/plain".toMediaTypeOrNull())
        ).collect {
            when (it) {
                is ResultData.Success -> {
                    _sendAudioLiveData.value = it.success
                }

                is ResultData.Message -> {
                    _messageLiveData.value = it.message
                }

                is ResultData.Error -> {
                    _errorLiveData.value = it.error
                }
            }
        }
    }

    override val allTestLiveData: LiveData<TestResponse>
        get() = _allTestLiveData
    private val _allTestLiveData = MutableLiveData<TestResponse>()
    override suspend fun allTests() {
        allTestsUseCase.execute().collect {
            when (it) {
                is ResultData.Success -> {
                    _allTestLiveData.value = it.success
                }

                is ResultData.Message -> {
                    _messageLiveData.value = it.message
                }

                is ResultData.Error -> {
                    _errorLiveData.value = it.error
                }
            }
        }
    }

}