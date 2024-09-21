package com.example.erteklerqosqlarhmtaqmaqlar.data.repository

import com.example.erteklerqosqlarhmtaqmaqlar.data.models.ResultData
import com.example.erteklerqosqlarhmtaqmaqlar.data.models.audio.SendAudioResponse
import com.example.erteklerqosqlarhmtaqmaqlar.data.models.feedback.GetFeedbackRequestData
import com.example.erteklerqosqlarhmtaqmaqlar.data.models.feedback.GetFeedbackResponseData
import com.example.erteklerqosqlarhmtaqmaqlar.data.models.notification.RequestData
import com.example.erteklerqosqlarhmtaqmaqlar.data.models.notification.ResponseData
import com.example.erteklerqosqlarhmtaqmaqlar.data.models.test.TestResponse
import com.example.erteklerqosqlarhmtaqmaqlar.data.retrofit.FCMApi
import com.example.erteklerqosqlarhmtaqmaqlar.data.retrofit.NetworkApi
import com.example.erteklerqosqlarhmtaqmaqlar.domain.NetworkRepository
import com.example.erteklerqosqlarhmtaqmaqlar.utils.ErrorObjectMessage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import okhttp3.MultipartBody
import okhttp3.RequestBody

class NetworkRepositoryImpl(private val fcmApi: FCMApi, private val networkApi: NetworkApi) :
    NetworkRepository {

    override suspend fun sendNotification(
        requestData: RequestData, adminDeviceKey: String
    ): Flow<ResultData<ResponseData>> = flow {
        val response = fcmApi.sendNotification(requestData, adminDeviceKey)
        if (response.isSuccessful) {
            emit(ResultData.Success(response.body()!!))
        } else {
            emit(ResultData.Message(response.message()))
        }
    }.catch {
        emit(ResultData.Error(it))
    }

    override suspend fun sendAudio(
        deviceKey: RequestBody, testId: RequestBody, voice: MultipartBody.Part, name: RequestBody
    ): Flow<ResultData<SendAudioResponse>> = flow {
        val response = networkApi.sendAudio(deviceKey, testId, voice, name)
        if (response.isSuccessful) {
            emit(ResultData.Success(response.body()!!))
        } else {
            emit(ResultData.Message(response.message()))
        }
    }.catch {
        emit(ResultData.Error(it))
        it.printStackTrace()
    }

    override suspend fun getFeedbacks(deviceKey: String): Result<GetFeedbackResponseData> {
        return try {
            val response = networkApi.getFeedbacks(GetFeedbackRequestData(deviceKey))
            val responseBody = response.body()
            if (response.isSuccessful && responseBody != null) {
                Result.success(responseBody)
            } else {
                val message = ErrorObjectMessage.getErrorObjectMessage(response.errorBody())
                Result.failure(Throwable(message))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun allTests(): Flow<ResultData<TestResponse>> = flow {
        val response = networkApi.allTests()
        if (response.isSuccessful) {
            emit(ResultData.Success(response.body()!!))
        } else emit(ResultData.Message(response.message()))
    }.catch {
        emit(ResultData.Error(it))
        it.printStackTrace()
    }
}