package com.example.erteklerqosqlarhmtaqmaqlar.data.retrofit

import com.example.erteklerqosqlarhmtaqmaqlar.data.models.notification.RequestData
import com.example.erteklerqosqlarhmtaqmaqlar.data.models.notification.ResponseData
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

interface FCMApi {
    @POST("/fcm/send")
    @Headers(
        "Content-type: application/json"
    )
    suspend fun sendNotification(
        @Body request: RequestData,
        @Header("Authorization") adminDeviceKey: String,
    ): Response<ResponseData>

}