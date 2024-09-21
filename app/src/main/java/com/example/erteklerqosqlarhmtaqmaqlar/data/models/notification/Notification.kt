package com.example.erteklerqosqlarhmtaqmaqlar.data.models.notification

import com.google.gson.annotations.SerializedName

data class Notification(
    val title: String,
    val body: String,
    @SerializedName("mutable_content")
    val mutableContent: Boolean,
    val sound: String
)