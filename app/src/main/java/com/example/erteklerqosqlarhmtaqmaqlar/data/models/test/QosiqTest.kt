package com.example.erteklerqosqlarhmtaqmaqlar.data.models.test

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "qosiq_test")
data class QosiqTest(
    @PrimaryKey
    var id: Int,
    var image: String?,
    var audio: String,
    var answers: String
)