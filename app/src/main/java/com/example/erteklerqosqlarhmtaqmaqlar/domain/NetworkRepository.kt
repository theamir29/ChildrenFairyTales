package com.example.erteklerqosqlarhmtaqmaqlar.domain

import com.example.erteklerqosqlarhmtaqmaqlar.data.models.ResultData
import com.example.erteklerqosqlarhmtaqmaqlar.data.models.audio.SendAudioResponse
import com.example.erteklerqosqlarhmtaqmaqlar.data.models.feedback.GetFeedbackResponseData
import com.example.erteklerqosqlarhmtaqmaqlar.data.models.notification.RequestData
import com.example.erteklerqosqlarhmtaqmaqlar.data.models.notification.ResponseData
import com.example.erteklerqosqlarhmtaqmaqlar.data.models.test.TestResponse
import kotlinx.coroutines.flow.Flow
import okhttp3.MultipartBody
import okhttp3.RequestBody

interface NetworkRepository {
    suspend fun sendNotification(
        requestData: RequestData,
        adminDeviceKey: String
    ): Flow<ResultData<ResponseData>>

    suspend fun sendAudio(
        deviceKey: RequestBody,
        testId: RequestBody,
        voice: MultipartBody.Part,
        name: RequestBody,
    ): Flow<ResultData<SendAudioResponse>>

    suspend fun allTests(): Flow<ResultData<TestResponse>>

    suspend fun getFeedbacks(
        deviceKey: String
    ) : Result<GetFeedbackResponseData>
}