package com.example.erteklerqosqlarhmtaqmaqlar.presentation.network

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.erteklerqosqlarhmtaqmaqlar.data.models.audio.SendAudioResponse
import com.example.erteklerqosqlarhmtaqmaqlar.data.models.notification.RequestData
import com.example.erteklerqosqlarhmtaqmaqlar.data.models.notification.ResponseData
import com.example.erteklerqosqlarhmtaqmaqlar.data.models.test.TestResponse
import java.io.File

abstract class NetworkViewModel : ViewModel() {
    abstract val messageLiveData: LiveData<String>
    abstract val errorLiveData: LiveData<Throwable>
    abstract suspend fun sendNotification(requestData: RequestData, adminDeviceKey: String)
    abstract val notificationResponseLiveData: LiveData<ResponseData>

    abstract suspend fun sendAudio(deviceKey: String, testId: String, voice: File, name: String)
    abstract val sendAudioLiveData: LiveData<SendAudioResponse>

    abstract suspend fun allTests()
    abstract val allTestLiveData: LiveData<TestResponse>
}