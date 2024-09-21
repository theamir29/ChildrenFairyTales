package com.example.erteklerqosqlarhmtaqmaqlar.data.models.notification

import com.google.gson.annotations.SerializedName

data class ResponseData(
    @SerializedName("multicast_id")
    val multicastId: Long,
    val success: Int,
    val failure: Int,
    @SerializedName("canonical_ids")
    val canonicalIds: Int,
    val result: NotificationResult
)
