package com.example.erteklerqosqlarhmtaqmaqlar.data.retrofit

import com.example.erteklerqosqlarhmtaqmaqlar.data.models.audio.SendAudioResponse
import com.example.erteklerqosqlarhmtaqmaqlar.data.models.feedback.GetFeedbackRequestData
import com.example.erteklerqosqlarhmtaqmaqlar.data.models.feedback.GetFeedbackResponseData
import com.example.erteklerqosqlarhmtaqmaqlar.data.models.test.TestResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface NetworkApi {
    @POST("/api/create/voice")
    @Multipart
    suspend fun sendAudio(
        @Part("device_key") deviceKey: RequestBody,
        @Part("test_id") testId: RequestBody,
        @Part file: MultipartBody.Part,
        @Part("name") name: RequestBody
    ): Response<SendAudioResponse>

    @Headers(
        "Content-Type: application/json",
        "Accept: application/json"
    )
    @POST("/api/user/feedbacks")
    suspend fun getFeedbacks(
        @Body body: GetFeedbackRequestData
    ): Response<GetFeedbackResponseData>

    @GET("/api/tests/1")
    suspend fun allTests(): Response<TestResponse>
}