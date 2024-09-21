package com.example.erteklerqosqlarhmtaqmaqlar.data.models.notification

data class RequestData(
    val to: String,
    val notification: Notification,
    val data: Data
)
