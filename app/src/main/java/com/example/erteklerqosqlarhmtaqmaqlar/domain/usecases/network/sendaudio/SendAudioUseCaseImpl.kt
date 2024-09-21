package com.example.erteklerqosqlarhmtaqmaqlar.domain.usecases.network.sendaudio

import com.example.erteklerqosqlarhmtaqmaqlar.data.models.ResultData
import com.example.erteklerqosqlarhmtaqmaqlar.data.models.audio.SendAudioResponse
import com.example.erteklerqosqlarhmtaqmaqlar.domain.NetworkRepository
import kotlinx.coroutines.flow.Flow
import okhttp3.MultipartBody
import okhttp3.RequestBody

class SendAudioUseCaseImpl(private val networkRepository: NetworkRepository) : SendAudioUseCase {
    override suspend fun execute(
        deviceKey: RequestBody,
        testId: RequestBody,
        voice: MultipartBody.Part,
        name: RequestBody
    ): Flow<ResultData<SendAudioResponse>> =
        networkRepository.sendAudio(deviceKey, testId, voice, name)
}