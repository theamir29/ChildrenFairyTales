package com.example.erteklerqosqlarhmtaqmaqlar.data.models.feedback

data class GetFeedbackItem(
    val id: Int,
    val feedback: String,
    val rating: String,
    val voice: String,
    val test_id: Int,
    val created_at: String
)
